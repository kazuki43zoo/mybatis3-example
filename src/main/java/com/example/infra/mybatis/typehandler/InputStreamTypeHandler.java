package com.example.infra.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.inject.Inject;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public class InputStreamTypeHandler extends BaseTypeHandler<InputStream> {

    @Inject
    LobHandler lobHandler;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, InputStream parameter, JdbcType jdbcType) throws SQLException {
        try {
            lobHandler.getLobCreator().setBlobAsBinaryStream(ps,i,parameter,parameter.available());
        } catch (IOException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public InputStream getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return lobHandler.getBlobAsBinaryStream(rs, columnName);
    }

    @Override
    public InputStream getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return lobHandler.getBlobAsBinaryStream(rs, columnIndex);
    }

    @Override
    public InputStream getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toInputStream(cs.getBlob(columnIndex));
    }

    private InputStream toInputStream(Blob blog) throws SQLException {
        return blog == null ? null : blog.getBinaryStream();
    }

}
