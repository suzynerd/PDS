package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Amigo;

public class DaoAmigo {
	private static DaoAmigo daoAmigo;
	private static Connection conexao = Conexao.getConnection();

	public static synchronized DaoAmigo getInstance(){
		if (daoAmigo == null)
			daoAmigo = new DaoAmigo();
		return daoAmigo;
	}
	
	public static void adicionarAmigo(Integer idPerfil, Integer idAmigo) throws SQLException{
		String sql = "insert into amigo (idPerfil, idPerfil1) values (?,?)";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idPerfil); stm.setInt(2, idAmigo);
		stm.executeUpdate();
		stm.close();
	}
	
	public static List<Amigo> listarAmigo(Integer idPerfil) throws SQLException{
		List<Amigo> p = new ArrayList<>();
		
		String sql = "select * from amigo where idPerfil = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idPerfil);
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			Amigo a = new Amigo();
			a.setIdAmigo(rs.getInt("idPerfil1"));
			a.setIdRelacao(rs.getInt("idRelacao"));
			p.add(a);
		}
		sql = "select * from amigo where idPerfil1 = ?";
		stm = conexao.prepareStatement(sql);
		stm.setInt(1, idPerfil);
		rs = stm.executeQuery();
		while(rs.next()){
			Amigo a = new Amigo();
			a.setIdAmigo(rs.getInt("idPerfil"));
			a.setIdRelacao(rs.getInt("idRelacao"));
			p.add(a);
		}
		
		sql = "select * from perfil where idPerfil = ?";
		for (int i = 0; i < p.size(); i++) {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, p.get(i).getIdAmigo());
			rs = stm.executeQuery();
			while(rs.next()){
				p.get(i).setNome(rs.getString("nome"));
			}
		}
		stm.close();
		rs.close();
		return p;
	}

	public static void removeAmigo(Integer idRelacao) throws SQLException {
		String sql = "delete from amigo where idRelacao = ?";
		PreparedStatement stm;
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, idRelacao);
			stm.executeUpdate();
			stm.close();
	}
}
