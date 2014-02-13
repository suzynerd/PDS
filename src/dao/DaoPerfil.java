package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Amigo;
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
	public static void insertPerfil(Perfil perfil){
			String sql = "insert into perfil (nome, email, senha, idTipo, idInstituicao) values (?, ?, ?, ?, ?)";
			PreparedStatement stm;
			try {
				stm = conexao.prepareStatement(sql);
				stm.setString(1, perfil.getNome());
				stm.setString(2, perfil.getEmail());
				stm.setString(3, perfil.getSenha());
				stm.setInt(4, perfil.getIdTipoPerfil());
				stm.executeUpdate();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			

	}
	
	public static List<Perfil> getListPerfil(Integer idPerfil){
		List<Perfil> perfis = new ArrayList<>();
		List<Amigo> amigos = DaoAmigo.listarAmigo(idPerfil);
		
		String sql = "select * from perfil";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				Perfil p = new Perfil();
				p.setIdPerfil(rs.getInt("idPerfil"));
				p.setNome(rs.getString("nome"));
				perfis.add(p);
			}
			
			stm.close(); rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < perfis.size(); i++) {
			for (int j = 0; j < amigos.size(); j++) {
				if (perfis.get(i).getIdPerfil() == amigos.get(j).getIdAmigo()) {
					perfis.remove(i);
				}
			}
		}
		
		
		return perfis;
	}
	
	public static void deletePerfil(Integer idPerfil){
		String sql = "delete from perfil where idPerfil = ?";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
				p.setIdTipoPerfil(rs.getInt("idTipo"));
			}
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static Perfil findPerfil(Integer id){
		String sql = "select * from perfil where idPerfil = ?";
		Perfil p = new Perfil();
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setIdPerfil(rs.getInt("idPerfil"));
				p.setIdTipoPerfil(rs.getInt("idTipo"));
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
		
	}
	
	public static boolean isProfessor(Integer id){
		String sql = "select idTipo from perfil where idPerfil = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			while(rs.next())
				return rs.getInt("idTipo") == 2;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static boolean isAluno(Integer id){
		return !isProfessor(id);
	}
	
	

}
