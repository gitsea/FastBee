package com.fastbee.system.handle;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.BaseTypeHandler;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class PostgreSqlByteaToStringHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (parameter != null) {
            byte[] bytes = parameter.getBytes(StandardCharsets.UTF_8);
            ps.setBytes(i, bytes);  // 直接设置字节数组，而不是流
        } else {
            ps.setNull(i, Types.BINARY);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        byte[] bytes = rs.getBytes(columnName);  // 直接获取字节数组
        return bytes != null ? new String(bytes, StandardCharsets.UTF_8) : null;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        byte[] bytes = rs.getBytes(columnIndex);  // 直接获取字节数组
        return bytes != null ? new String(bytes, StandardCharsets.UTF_8) : null;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        byte[] bytes = cs.getBytes(columnIndex);  // 直接获取字节数组
        return bytes != null ? new String(bytes, StandardCharsets.UTF_8) : null;
    }
}

