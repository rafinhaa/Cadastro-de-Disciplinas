package com.rafinhaa.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BD {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
			String connectionURL = "jdbc:mysql://localhost:3306/cursos";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(connectionURL, "root", "root");
			return con;
			//JOptionPane.showMessageDialog(null, "Sem conexão com o banco de dados!\n Erro: \n" + e.toString(), "Mensagem" , JOptionPane.ERROR_MESSAGE);
	}
}
