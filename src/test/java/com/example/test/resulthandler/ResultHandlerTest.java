package com.example.test.resulthandler;

import com.example.domain.model.Account;
import com.example.domain.repository.account.AccountRepository;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.SystemException;

import javax.inject.Inject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/example/test/resulthandler/ResultHandlerTest.xml"})
public class ResultHandlerTest {

    @Inject
    AccountRepository accountRepository;

    @Transactional
    @Test
    public void rowhandler() throws IOException {

        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("./log", "loadedAccount" + System.currentTimeMillis() + ".csv")))) {
            DateTime targetMonth = DateTime.parse("2014-01-01", DateTimeFormat.forPattern("yyyy-MM-dd"));
            accountRepository.collectAllByBirthMonth(targetMonth, new ResultHandler() {
                @Override
                public void handleResult(ResultContext context) {
                    Account account = (Account) context.getResultObject();
                    try {
                        out.append(String.valueOf(context.getResultCount()));
                        out.append(',');
                        out.append(account.getAccountUuid());
                        out.append(',');
                        out.append(account.getAccountName());
                        out.append(',');
                        out.append(account.getBirthDate().toString("yyyy-MM-dd"));
                        out.newLine();
                    } catch (IOException e) {
                        throw new SystemException("e.xx.fw.9001", e);
                    }
                }
            });
        }


    }

    @Transactional
    @Test
    public void rowhandlerWithRowBounds() throws IOException {

        try (BufferedWriter out = new BufferedWriter(new FileWriter(new File("./log", "loadedAccount" + System.currentTimeMillis() + ".csv")))) {
            DateTime targetMonth = DateTime.parse("2014-01-01", DateTimeFormat.forPattern("yyyy-MM-dd"));
            accountRepository.collectPageByBirthMonth(targetMonth, new RowBounds(1, 2), new ResultHandler() {
                @Override
                public void handleResult(ResultContext context) {
                    Account account = (Account) context.getResultObject();
                    try {
                        out.append(String.valueOf(context.getResultCount()));
                        out.append(',');
                        out.append(account.getAccountUuid());
                        out.append(',');
                        out.append(account.getAccountName());
                        out.append(',');
                        out.append(account.getBirthDate().toString("yyyy-MM-dd"));
                        out.append(',');
                        System.out.println(account.getAddress());
                        if (account.getAddress() != null) {
                            out.append(account.getAddress().getAccountUuid());
                            out.append(',');
                            out.append(account.getAddress().getZipCode());
                            out.append(',');
                            out.append(account.getAddress().getAddress());
                            out.newLine();
                        }
                    } catch (IOException e) {
                        throw new SystemException("e.xx.fw.9001", e);
                    }
                }
            });
        }


    }


}
