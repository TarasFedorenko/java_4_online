package ua.com.alevel.persistence.config.impl;

import ua.com.alevel.Main;
import ua.com.alevel.persistence.config.JDBCService;
import ua.com.alevel.util.ResourcesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class JDBCServiceImpl implements JDBCService {

    private Connection connection;
    private Statement statement;

    @Override
    public Connection getConnection() {
        if (connection == null) {
            init();
        }
        return this.connection;
    }

    @Override
    public Statement getStatement() {
        if (connection == null) {
            init();
        }
        return this.statement;
    }

    private void init() {
        Map<String, String> propertiesMap = ResourcesUtil.getResources(Main.class.getClassLoader());
        try {
            Class.forName(propertiesMap.get("jdbc.driver"));
            connection = DriverManager.getConnection((propertiesMap.get("jdbc.url")), (propertiesMap.get("jdbc.username")), (propertiesMap.get("jdbc.password")));
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}