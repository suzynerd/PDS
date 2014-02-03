package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Perfil;
import dominio.Usuario;

public class DaoPerfil{
	private static DaoPerfil daoPerfil;
	private static Connection conexao = Conexao.getConnection();
	
	
	public static synchronized DaoPerfil getInstance(){
		if (daoPerfil == null)
			daoPerfil = new DaoPerfil();
		return daoPerfil;
	}
	public static void inserirPerfil(Perfil perfil) throws SQLException {
		try {
			
			String sql = "insert into perfil (nome, email, senha, idTipo) values (?, ?, ?, ?)";
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, perfil.getNome());
			stm.setString(2, perfil.getEmail());
			stm.setString(3, perfil.getSenha());
			stm.setInt(4, perfil.getIdTipoPerfil());
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
		
		stm.close(); rs.close();
		
		return perfis;
	}
	
	public static String findNomePerfil(Integer id) throws SQLException{
		String sql = "select nome from perfil where idPerfil = ?";
		String nome = null;
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		while(rs.next())
			nome = rs.getString("nome");
		stm.close(); rs.close();
		return nome;
		
	}
	
	public static boolean isAluno(Integer id) throws SQLException{
		String sql = "select idTipo from perfil where idPerfil = ?";
		PreparedStatement stm  = conexao.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();

		return rs.getInt("idTipo") == 1;
	}
	
	

}
