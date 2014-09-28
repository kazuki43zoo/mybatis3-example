package com.example.infra.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.inject.Inject;
import java.io.Reader;
import java.sql.*;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public class ReaderTypeHandler extends BaseTypeHandler<Reader> {

    @Inject
    LobHandler lobHandler;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Reader parameter, JdbcType jdbcType) throws SQLException {
        if (ps.getConnection().getMetaData().getDatabaseProductName().toLowerCase().contains("oracle")) {
            ps.setClob(i, parameter);
        } else {
            lobHandler.getLobCreator().setClobAsCharacterStream(ps, i, parameter, Integer.MAX_VALUE);
        }
    }

    @Override
    public Reader getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return lobHandler.getClobAsCharacterStream(rs, columnName);
    }

    @Override
    public Reader getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return lobHandler.getClobAsCharacterStream(rs, columnIndex);
    }

    @Override
    public Reader getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toReader(cs.getClob(columnIndex));
    }

    private Reader toReader(Clob blog) throws SQLException {
        return blog == null ? null : blog.getCharacterStream();
    }

}
