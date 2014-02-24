package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Amigo;
import dominio.Perfil;

public class DaoPerfil{
	private static Connection conexao = Conexao.getConnection();
	
	public static void insert(Perfil perfil) {
		String sql = "insert into perfil (nome, email, senha, idTipo, idInstituicao) values (?, ?, ?, ?, ?)";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, perfil.getNome());
			if(!existsEmail(perfil))
				stm.setString(2, perfil.getEmail());
			stm.setString(3, perfil.getSenha());
			stm.setInt(4, perfil.getIdTipo());
			stm.setInt(5, perfil.getIdInstituicao());
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir um novo Perfil no Banco de Dados");
		}
	}

	public static List<Perfil> getList() {
		List<Perfil> perfis = new ArrayList<>();
		
		String sql = "select * from perfil";
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			
			while(rs.next()){
				Perfil p = new Perfil();
				p.setId(rs.getInt("idPerfil"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setIdInstituicao(rs.getInt("idInstituicao"));
				p.setInstituicao(DaoInstituicao.get(p.getIdInstituicao()).getNome());
				p.setIdTipo(rs.getInt("idTipo"));
				perfis.add(p);
			}
			stm.close(); rs.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar Perfis");
		}
		return perfis;
	}
	
	public static List<Perfil> getList(Integer id){
		List<Perfil> perfis = getList();
		List<Amigo> amigos = DaoAmigo.getList(id);
		
		for (int i = 0; i < perfis.size(); i++) {
			if(perfis.get(i).getId() == id)
				perfis.remove(i);
		}
		
		for (int i = 0; i < perfis.size(); i++) {
			for (Amigo amigo : amigos) {
				if(amigo.getId() == perfis.get(i).getId())
					perfis.remove(i);
			}
		}
		
		return perfis;
	}

	public static void delete(Integer id) {
		String sql = "delete from perfil where idPerfil = ?";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Perfil");
		}
		
	}

	//
	public static void update(Object obj) {
		
	}

	public static Object get(Integer id) {
		Perfil perfil = new Perfil();
		String sql = "select * from perfil where idperfil = " + id;
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			rs.next();
			perfil.setId(rs.getInt("idPerfil"));
			perfil.setNome(rs.getString("nome"));
			perfil.setEmail(rs.getString("email"));
			perfil.setIdInstituicao(rs.getInt("idInstituicao"));
			perfil.setIdTipo(rs.getInt("idTipo"));
		}catch(SQLException e){
			System.out.println("Erro ao procurar Perfil");
		}
		
		return perfil;
	}
	
	public static boolean exists(String login, String senha){
		String sql = "select * from perfil where email = ? AND senha = ?";
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, login);
			stm.setString(2, senha);
			ResultSet rs = stm.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println("Erro ao verificar Perfil boolean");
		}
		return false;
	}
	
	public static Perfil logar(String login, String senha){
		
		Perfil p = null;
		String sql = "select * from perfil where email = ? AND senha = ?";
		PreparedStatement stm;
		
		try {
			stm = conexao.prepareStatement(sql);
			stm.setString(1, login);
			stm.setString(2, senha);
			ResultSet rs = stm.executeQuery();
			rs.next();
			
			p = new Perfil();
			p.setId(rs.getInt("idPerfil"));
			p.setNome(rs.getString("nome"));
			p.setEmail(rs.getString("email"));
			p.setIdInstituicao(rs.getInt("idInstituicao"));
			p.setIdTipo(rs.getInt("idTipo"));
			
		} catch (SQLException e) {
			System.out.println("Erro ao verificar Perfil para login");
		}
		
		return p;
	}
	
	public static boolean existsEmail(Perfil perfil){
		String sql = "select * from perfil where email = '" + perfil.getEmail() + "'";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			System.out.println("Erro ao Verificar E-mail");
		}
		return false;
	}

}
