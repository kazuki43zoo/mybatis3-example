package com.example.domain.repository.account;

import com.example.domain.model.Account;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by shimizukazuki on 2014/09/28.
 */
public interface AccountRepository {

    void collectAllByBirthMonth(DateTime birthMonth, ResultHandler handler);

    void collectPageByBirthMonth(DateTime birthMonth, RowBounds rowBounds, ResultHandler handler);

}
