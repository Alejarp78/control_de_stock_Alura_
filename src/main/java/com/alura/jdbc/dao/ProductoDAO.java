package com.alura.jdbc.dao;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//La clase persistenciaProducto se cambia por DAO
//DAO significa Data Acces Object
public class ProductoDAO {
    final private Connection con;

    public ProductoDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Producto producto) {

//        final Connection con = new ConnectionFactory().recuperaConexion();

        final PreparedStatement statement;
        try (con) {
            //con.setAutoCommit(false);

            statement = con.prepareStatement(
                    "INSERT INTO PRODUCTO "
                            + "(nombre, descripcion, cantidad, categoria_Id) "
                            + "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            //Ahora se va a  convertir en un metodo desde el statement hasta el while:
            try (statement) {
//                producto = null;
                ejecutaRegistro(statement, producto);

                //con.commit(); // Se escribe para garantizar que todos los comandos del loop hayan sido ejecutados correctamente.
//                System.out.println("COMMIT");
            }
        } catch (SQLException e) {
//            con.rollback();
//            System.out.println("ROLLBACK");
            throw new RuntimeException(e);

        }

    }

    private void ejecutaRegistro(PreparedStatement statement, Producto producto) throws SQLException {
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setInt(3, producto.getCantidad());
        statement.setInt(4, producto.getCategoriaId());

        statement.execute();

        final ResultSet resulSet = statement.getGeneratedKeys();

        try (resulSet) {

            while (resulSet.next()) {
                producto.setId(resulSet.getInt(1));
//                System.out.println(String.format("Fue insertado el producto de ID %d", ));
                System.out.println(String.format("Fue insertado el producto %s", producto));
            }
        }
    }

    //public List<Map<String, String>> listar() {
    public List<Producto> listar() {
        final Connection con = new ConnectionFactory().recuperaConexion();

        //Para realizar la operacion de select, son considerados como statement en java.

        // Para evitar el SQL Injection se utiliza el PreparedStatement
        //Se debe preprar el enunciado, lo vuelvo a copiar y ahora el statement lo pongo en un statement:
        //por lo tanto el string se extrae a una variable.
        // Se pasa la responsabilidad al jdbc.

        //Statement statement = con.createStatement();

//		boolean result = statement.execute("SELECT ID, " +
        /*statement.execute("SELECT ID, " +
                "NOMBRE, DESCRIPCION, " +
                "CANTIDAD FROM PRODUCTO");*/

        //Para poder obtener el resultado se debeusar el resulset

        try (con) {

            final PreparedStatement statement = con.prepareStatement("SELECT ID, " +
                    "NOMBRE, DESCRIPCION, " +
                    "CANTIDAD FROM PRODUCTO");

            try (statement) {

                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

//		ArrayList<Object> resultado = new ArrayList<>();
                //List<Map<String, String>> resultado = new ArrayList<>();
                List<Producto> resultado = new ArrayList<>();
/*                while (resultSet.next()) {
                    //Map<String, String> fila = new HashMap<>();
                    Producto fila = new Producto();
                    fila.put("ID", String.valueOf(resultSet.getInt("ID")));
                    fila.put("NOMBRE", resultSet.getString("NOMBRE"));
                    fila.put("DESCRIPCION", resultSet.getString("DESCRIPCION"));
                    fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));
                    resultado.add(fila);
                }*/

                while (resultSet.next()) {
                    //Map<String, String> fila = new HashMap<>();
                    Producto fila = new Producto(resultSet.getInt("ID"),
                            resultSet.getString("NOMBRE"),
                            resultSet.getString("DESCRIPCION"),
                            resultSet.getInt("CANTIDAD")
                    );

                    resultado.add(fila);
                }
                //con.close();
                return resultado;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Producto> listar(Integer categoriaId) {

        List<Producto> resultado = new ArrayList<>();

        try {
            String sql = "SELECT ID, " +
                    "NOMBRE, DESCRIPCION, "
                    + "CANTIDAD FROM PRODUCTO "
                    + "WHERE CATEGORIA_ID = ?";

            System.out.println(sql);

            final PreparedStatement statement = con.prepareStatement(sql);

            try (statement) {
                statement.setInt(1, categoriaId);
                statement.execute();

                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}

