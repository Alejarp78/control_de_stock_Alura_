package com.alura.jdbc.controller;

import com.alura.jdbc.dao.CategoriaDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;


public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController()
    {
        var factory = new ConnectionFactory();
        this.categoriaDAO = new CategoriaDAO(factory.recuperaConexion());
    }
	public List<Categoria> listar() {
//		return new ArrayList<>();
        return categoriaDAO.listar();
	}

    public List<Categoria> cargaReporte() {
        // TODO
//        return new ArrayList<>();
//        return this.listar();
        return categoriaDAO.listarConProductos();
    }

}
