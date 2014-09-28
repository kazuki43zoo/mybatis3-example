package com.example.infra.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public class DateTimeTypeHandler extends BaseTypeHandler<DateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter, JdbcType jdbcType) throws SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toDateTime(rs.getTimestamp(columnName));
    }

    @Override
    public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toDateTime(rs.getTimestamp(columnIndex));
    }

    @Override
    public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toDateTime(cs.getTimestamp(columnIndex));
    }

    private DateTime toDateTime(Timestamp timestamp) {
        return timestamp == null ? null : new DateTime(timestamp.getTime());
    }

}
