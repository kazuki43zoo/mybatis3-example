package com.example.domain.repository.order;

import com.example.domain.model.Stock;
import org.apache.ibatis.annotations.Param;

/**
 * Created by shimizukazuki on 2014/10/14.
 */
public interface StockRepository {

    boolean decrementQuantity(@Param("itemCode") String itemCode,
                              @Param("quantity") int quantity);

    Stock findOne(String itemCode);

    boolean update(Stock stock);

    Stock findOneForUpdate(String itemCode);
}
