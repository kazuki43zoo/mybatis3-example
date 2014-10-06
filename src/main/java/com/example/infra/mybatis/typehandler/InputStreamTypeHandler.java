package com.example.infra.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.InputStream;
import java.sql.*;

/**
 * Created by shimizukazuki on 2014/09/26.
 */
public class InputStreamTypeHandler extends BaseTypeHandler<InputStream> {

    public InputStreamTypeHandler(){
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, final InputStream parameter, JdbcType jdbcType) throws SQLException {
        ps.setBlob(i,parameter);
    }

    @Override
    public InputStream getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toInputStream(rs.getBlob(columnName));
    }

    @Override
    public InputStream getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toInputStream(rs.getBlob(columnIndex));
    }

    @Override
    public InputStream getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toInputStream(cs.getBlob(columnIndex));
    }

    private InputStream toInputStream(Blob blog) throws SQLException {
        return blog == null ? null : blog.getBinaryStream();
    }

}
