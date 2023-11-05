package com.queuemanagementsystem.Repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class JDBCWrapper {

    private Connection connection;

    @Value("${spring.datasource.url}")
    private static String URL;

    @Value("${spring.datasource.username}")
    private static String userName;

    @Value("spring.datasource.password")
    private static String password;

    public void openConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(URL, userName, password);
        }
        catch(Exception connectionException){
            log.error(connectionException.getLocalizedMessage(),connectionException);
            throw new SQLException(connectionException);
        }
    }
    public void closeConnection() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        catch(Exception closeConnectionException){
            log.error(closeConnectionException.getLocalizedMessage(),closeConnectionException);
            throw closeConnectionException;
        }
    }

    // Method to execute a SELECT query using a prepared statement
    public ResultSet executeQuery(String sql, Object... parameters) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        setParameters(preparedStatement, parameters);
        return preparedStatement.executeQuery();
    }

    // Method to execute an UPDATE, INSERT, or DELETE query using a prepared statement
    public int executeUpdate(String sql, Object... parameters) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        setParameters(preparedStatement, parameters);
        return preparedStatement.executeUpdate();
    }

    // Method to set parameters for a prepared statement
    private void setParameters(PreparedStatement preparedStatement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i + 1, parameters[i]);
        }
    }
    public interface RowMapper<T> {
        T mapRow(ResultSet resultSet) throws SQLException;
    }

    // Method to execute a SELECT query and map the result to a list of objects
    public <T> List<T> executeQuery(String sql, RowMapper<T> rowMapper, Object... parameters) throws SQLException {
        List<T> results = new ArrayList<>();
        ResultSet resultSet = executeQuery(sql, parameters);
        while (resultSet.next()) {
            T result = rowMapper.mapRow(resultSet);
            results.add(result);
        }
        return results;
    }

}
