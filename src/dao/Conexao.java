package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Conexao conect;
	private static Connection conexao;
	
	public static synchronized Conexao getInstance(){
		if (conect == null)
			conect = new Conexao();
		return conect;
	}
	
	public static Connection getConnection(){
		if (Conexao.conexao == null) {
			try {
				Conexao.conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "alejandro");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Conexao.conexao;
	}
}
