package com.alura.jdbc.tests;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaPoolDeConexiones {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 0; i < 20; i++) {
            Connection conexion = connectionFactory.recuperaConexion();

            System.out.println("Abriendo la conexion de numero " + (i + 1));
        }
    }
}
