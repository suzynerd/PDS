package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Amigo;

public class DaoAmigo {
	public static Connection conexao = Conexao.getConnection();
	
	public static void insert(Integer idPerfil, Integer idAmigo){
		String sql = "insert into amigo (idPerfil, idPerfil1) values (?,?)";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil); stm.setInt(2, idAmigo);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao Adicionar amigo: DaoAmigo.insert()");
		}
	}
	
	public static List<Amigo> getList(Integer idPerfil){
		List<Amigo> amigos = new ArrayList<>();
		
		String sql = "select * from amigo where idPerfil = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				Amigo a = new Amigo();
				a.setId(rs.getInt("idPerfil1"));
				a.setIdRelacao(rs.getInt("idRelacao"));
				amigos.add(a);
			}
			sql = "select * from amigo where idPerfil1 = ?";
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idPerfil);
			rs = stm.executeQuery();
			while(rs.next()){
				Amigo a = new Amigo();
				a.setId(rs.getInt("idPerfil"));
				a.setIdRelacao(rs.getInt("idRelacao"));
				amigos.add(a);
			}
			
			sql = "select * from perfil where idPerfil = ?";
			for (int i = 0; i < amigos.size(); i++) {
				stm = conexao.prepareStatement(sql);
				stm.setInt(1, amigos.get(i).getId());
				rs = stm.executeQuery();
				while(rs.next()){
					amigos.get(i).setNome(rs.getString("nome"));
				}
			}
			stm.close();
			rs.close();
		} catch (SQLException e) {System.out.println("Erro ao Procurar amigo: DaoAmigo.getList()");}
		return amigos;
	}
	
	public static void delete(Integer idRelacao){
		String sql = "delete from amigo where idRelacao = ?";
		PreparedStatement stm;
		try {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idRelacao);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {
			System.out.println("Erro ao deletar amigo: DaoAmigo.delete()");
		}
	}
	
	public static boolean isAmigo(Integer idPerfil, Integer idAmigo){
		List<Amigo> amigos = getList(idPerfil);
		for (Amigo amigo : amigos) {
			if(amigo.getId() == idAmigo)
				return true;
		}
		
		return false;
	}
	
	public static Integer count(Integer idPerfil){
		List<Amigo> amigos = getList(idPerfil);
		return amigos.size();
	}
}
