package com.alura.jdbc;

import com.alura.jdbc.view.ControlDeStockFrame;

import javax.swing.*;
import java.sql.SQLException;

public class ControlDeStockMain {

	public static void main(String[] args) throws SQLException {
		ControlDeStockFrame produtoCategoriaFrame = new ControlDeStockFrame();
		produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
