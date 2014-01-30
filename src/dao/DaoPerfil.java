package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Perfil;
import dominio.Usuario;

public class DaoPerfil{
	private static DaoPerfil daoPerfil;
	private static Connection conexao;
	
	public DaoPerfil(){
		Connection conexao = null;
		try {conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "alejandro");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DaoPerfil.conexao = conexao;
	}
	
	public static synchronized DaoPerfil getInstance(){
		if (daoPerfil == null)
			daoPerfil = new DaoPerfil();
		return daoPerfil;
	}
	
	public static Connection getConnection(){
		return conexao;
	}
	
	public static void inserir(Perfil perfil) throws SQLException {
		try {
			
			String sql = "insert into perfil (nome, email, senha) values (?, ?, ?)";
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, perfil.getNome());
			stm.setString(2, perfil.getEmail());
			stm.setString(3, perfil.getSenha());
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			throw new SQLException();
		}

	}

	public static Perfil logar(Usuario usuario){
		Perfil p = null;
		try {
			String sql = "select * from perfil where email = ? AND senha = ?";
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, usuario.getEmail());
			stm.setString(2, usuario.getSenha());
			ResultSet rs = stm.executeQuery();
			p = new Perfil();
			while(rs.next()){
				p.setIdPerfil(rs.getInt("idPerfil"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
			}
			stm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public static List<Perfil> listarPerfis() throws SQLException{
		
		ArrayList<Perfil> perfis = new ArrayList<Perfil>();
		String sql = "select * from perfil";
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
			
		while(rs.next()){
			Perfil p = new Perfil();
			p.setIdPerfil(rs.getInt("idPerfil"));
			p.setNome(rs.getString("nome"));
			perfis.add(p);
		}
		
		return perfis;
	}
	
	

}
