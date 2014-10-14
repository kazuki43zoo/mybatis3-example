package com.example.test.exclusioncontrol;

import com.example.domain.model.Stock;
import com.example.domain.repository.order.StockRepository;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/exclusioncontrol/ExclusionTest.xml"})
public class ExclusionTest {

    @Inject
    StockRepository stockRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void decrementQuantitySuccess() {
        String itemCode = "ITM0000001";
        int quantityOfOrder = 101;
        boolean updated = stockRepository.decrementQuantity(itemCode, quantityOfOrder);
        Assert.assertThat(updated, Is.is(true));
        Stock stock = stockRepository.findOne(itemCode);
        Assert.assertThat(stock.getQuantity(), Is.is(0));
        Assert.assertThat(stock.getVersion(), Is.is(2L));

    }

    @Test(expected = BusinessException.class)
    @Transactional
    @Rollback(false)
    public void decrementQuantityFail() {
        String itemCode = "ITM0000002";
        int quantityOfOrder = 201;

        // (5)
        boolean updated = stockRepository.decrementQuantity(itemCode, quantityOfOrder);
        // (6)
        if (!updated) {
            // (7)
            ResultMessages messages = ResultMessages.error().add(ResultMessage
                    .fromText("Not enough stock. Please, change quantity."));
            throw new BusinessException(messages);
        }

    }

    @Test
    @Transactional
    @Rollback(false)
    public void updateSuccess() {
        String itemCode = "ITM0000003";
        int addedQuantity = 10;

        Stock stock = stockRepository.findOne(itemCode);

        stock.setQuantity(stock.getQuantity() + addedQuantity);

        boolean updated = stockRepository.update(stock);
        Assert.assertThat(updated, Is.is(true));

        Stock updatedStock = stockRepository.findOne(itemCode);
        Assert.assertThat(updatedStock.getQuantity(), Is.is(310));
        Assert.assertThat(updatedStock.getVersion(), Is.is(2L));

    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    @Transactional
    @Rollback(false)
    public void updateFail() {
        String itemCode = "ITM0000004";
        int addedQuantity = 10;

        Stock stock = stockRepository.findOne(itemCode);

        stock.setQuantity(stock.getQuantity() + addedQuantity);
        stock.setVersion(stock.getVersion() - 1);

        // (7)
        boolean updated = stockRepository.update(stock);
        if (!updated) {
            // (8)
            throw new ObjectOptimisticLockingFailureException(Stock.class, itemCode);
        }
    }

    @Test(expected = ResourceNotFoundException.class)
    @Transactional
    @Rollback(false)
    public void updateFailNotFound() {
        String itemCode = "ITM9999999";

        Stock stock = stockRepository.findOne(itemCode);
        if (stock == null) {
            ResultMessages messages = ResultMessages.error().add(ResultMessage
                    .fromText("Stock not found. itemCode : " + itemCode));
            throw new ResourceNotFoundException(messages);
        }

    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    @Transactional
    @Rollback(false)
    public void longTranNotFound() {
        String itemCode = "ITM9999999";
        int addedQuantity = 10;
        long version = 1;

        Stock stock = stockRepository.findOne(itemCode);
        if (stock == null || stock.getVersion() != version) {
            // (9)
            throw new ObjectOptimisticLockingFailureException(Stock.class, itemCode);
        }

        stock.setQuantity(stock.getQuantity() + addedQuantity);
        boolean updated = stockRepository.update(stock);
    }

    @Test(expected = ObjectOptimisticLockingFailureException.class)
    @Transactional
    @Rollback(false)
    public void longTranVersionDifference() {
        String itemCode = "ITM0000004";
        int addedQuantity = 10;
        long version = 1;

        Stock stock = stockRepository.findOne(itemCode);
        if (stock != null && stock.getVersion() != version) {
            // (9)
            throw new ObjectOptimisticLockingFailureException(Stock.class, itemCode);
        }

        stock.setQuantity(stock.getQuantity() + addedQuantity);
        boolean updated = stockRepository.update(stock);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findOneForUpdate() {
        String itemCode = "ITM0000001";
        Stock stock = stockRepository.findOneForUpdate(itemCode);
    }
}