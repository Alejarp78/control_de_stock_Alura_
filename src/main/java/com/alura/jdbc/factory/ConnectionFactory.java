package com.alura.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private DataSource datasource;
    public ConnectionFactory() {
        var poolDataSource = new ComboPooledDataSource();

        poolDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");

        poolDataSource.setUser("root");

        poolDataSource.setPassword("Ironbird231*");

        //Maximo numero de conexiones que se pueden mantener abiertas
        poolDataSource.setMaxPoolSize(10);

        this.datasource = poolDataSource;
    }

    public Connection recuperaConexion() {
/*        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
                "root",
                "Ironbird231*");

        return con;*/

        try {
            return this.datasource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
