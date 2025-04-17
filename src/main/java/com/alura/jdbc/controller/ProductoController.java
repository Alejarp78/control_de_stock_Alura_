package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;
import com.alura.jdbc.dao.ProductoDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoController {

    private final ProductoDAO productoDAO;

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
        // TODO

        //Aplicando try-with-resources version java 9
        //Para garantizar que todos los bloques de codigos sean cerrados correctamente

        final Connection con = new ConnectionFactory().recuperaConexion();

        // Para evitar el SQL Injection se utiliza el PreparedStatement
        //Se debe preprar el enunciado, lo vuelvo a copiar y ahora el statement lo pongo en un statement:
        //por lo tanto el string se extrae a una variable.
        // Se pasa la responsabilidad al jdbc.

        //Statement statement = con.createStatement();

        try(con) {

            final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET "
                    + "NOMBRE = ? "
                    + ", DESCRIPCION = ? "
                    + ", CANTIDAD = ? "
                    + "WHERE ID = ? ");

            try(statement) {

                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);

/*        statement.execute("UPDATE PRODUCTO SET "
                + "NOMBRE = '" + nombre + "'"
                + ", DESCRIPCION = '" + descripcion + "'"
                + ", CANTIDAD = " + cantidad
                + "WHERE ID = " + id);*/

                statement.execute();

                int updateCount = statement.getUpdateCount();

                //con.close();

                return updateCount;

            }

        }

    }

    public int eliminar(Integer id) throws SQLException {
        // TODO
        final Connection con = new ConnectionFactory().recuperaConexion();

        // Para evitar el SQL Injection se utiliza el PreparedStatement
        //Se debe preprar el enunciado, lo vuelvo a copiar y ahora el statement lo pongo en un statement:
        //por lo tanto el string se extrae a una variable.
        // Se pasa la responsabilidad al jdbc.

        //Statement statement = con.createStatement();

        try(con) {

            final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

            try(statement) {

                statement.setInt(1, id);

                statement.execute();

                return statement.getUpdateCount();

            }

        }

    }

    public ProductoController() {
        this.productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
    }

    //public List<Map<String, String>> listar() {
    public List<Producto> listar() {
        // TODO

        return productoDAO.listar();


//        ProductoDAO productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());

        //final Connection con = new ConnectionFactory().recuperaConexion();

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

/*        try(con) {

            final PreparedStatement statement = con.prepareStatement("SELECT ID, " +
                    "NOMBRE, DESCRIPCION, " +
                    "CANTIDAD FROM PRODUCTO");

            try(statement) {

                statement.execute();

                ResultSet resultSet = statement.getResultSet();

//		ArrayList<Object> resultado = new ArrayList<>();
                //List<Map<String, String>> resultado = new ArrayList<>();
                List<Producto> resultado = new ArrayList<>();


                try(resultSet) {
                while (resultSet.next()) {
                    Map<String, String> fila = new HashMap<>();
                    fila.put("ID", String.valueOf(resultSet.getInt("ID")));
                    fila.put("NOMBRE", resultSet.getString("NOMBRE"));
                    fila.put("DESCRIPCION", resultSet.getString("DESCRIPCION"));
                    fila.put("CANTIDAD", String.valueOf(resultSet.getInt("CANTIDAD")));
                    resultado.add(fila);
                }
                //con.close();
                return resultado;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
    }



//    public void guardar(Map<String, String> producto) throws SQLException {
//    public void guardar(Producto producto, Integer id) {
        // TODO
//        ProductoDAO productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());

//        productoDAO.guardar(producto);

//        String nombre = producto.get("NOMBRE");
//        String descripcion = producto.get("DESCRIPCION");
//        Integer cantidad = Integer.valueOf(producto.get("CANTIDAD"));
//        String nombre = producto.getNombre();
//        String descripcion = producto.getDescripcion();
//        Integer cantidad = producto.getCantidad();
//        Integer maximoCantidad = 50;

 /*       final Connection con = new ConnectionFactory().recuperaConexion();

        try(con) {
            con.setAutoCommit(false);
        
*//*             Statement statement = con.createStatement();

                boolean execute = statement.execute("INSERT INTO PRODUCTO(nombre, descripcion, cantidad) "
                + "VALUES('" + producto.get("NOMBRE") + "'," +
                " '" + producto.get("DESCRIPCION") + "'," +
                " " + producto.get("CANTIDAD") + ")", Statement.RETURN_GENERATED_KEYS);*//*

            // Para evitar el SQL Injection se utiliza el PreparedStatement
            //Se debe preprar el enunciado, lo vuelvo a copiar y ahora el statement lo pongo en un statement:
            //por lo tanto el string se extrae a una variable.
            // Se pasa la responsabilidad al jdbc.

            final PreparedStatement statement = con.prepareStatement("INSERT INTO PRODUCTO " +
                    "(nombre, descripcion, cantidad) "
                    + "VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            //Ahora se va a  convertir en un metodo desde el statement hasta el while:
        
*//*        statement.setString(1, nombre);
        statement.setString(2, descripcion);
        statement.setInt(3, cantidad);

        statement.execute();

        ResultSet resulSet = statement.getGeneratedKeys();

        while (resulSet.next()) {
            System.out.println(String.format("Fue insertado el producto de ID %d", resulSet.getInt(1)));
        }*//*

                try(statement) {
                //try {
                    //Codigo para garantizar que el valor maximo de productos guardados sea de 50.
//                    do {
//                        int cantidadParaGuardar = Math.min(cantidad, maximoCantidad);
//                        ejecutaRegistro(statement, nombre, descripcion, cantidadParaGuardar);
                    ejecutaRegistro(statement, producto);

//                        cantidad -= maximoCantidad;
//                    } while (cantidad > 0);

                    con.commit(); // Se escribe para garantizar que todos los comandos del loop hayan sido ejecutados correctamente.
                    System.out.println("COMMIT");
                } catch (Exception e) {
                    con.rollback();
                    System.out.println("ROLLBACK");
                    //throw new RuntimeException(e);
                }
               //statement.close();
                //con.close();

            //}

        }*/
//    }

    public List<Producto> listar(Categoria categoria){
        return productoDAO.listar(categoria.getId());
    }

    public void guardar(Producto producto, Integer categoriaId) {
        producto.setCategoriaId(categoriaId);
        productoDAO.guardar(producto);
    }

//    private static void ejecutaRegistro(PreparedStatement statement, String nombre, String descripcion, Integer cantidad) throws SQLException {
/*    private static void ejecutaRegistro(PreparedStatement statement, Producto producto) throws SQLException {


    //Para evitar este error, se realiza un setAutocommit...
*//*        if (cantidad < 50) {
            throw new RuntimeException("Ocurrió un error");
        }*//*

//        statement.setString(1, nombre);
//        statement.setString(2, descripcion);
//        statement.setInt(3, cantidad);

        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setInt(3, producto.getCantidad());

        statement.execute();

        final ResultSet resulSet = statement.getGeneratedKeys();

        try(resulSet) {

            while (resulSet.next()) {
                producto.setId(resulSet.getInt(1));
//                System.out.println(String.format("Fue insertado el producto de ID %d", ));
                System.out.println(String.format("Fue insertado el producto %s", producto));
            }

            //resulSet.close();

        }
    }*/

}
