package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Perfil;

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
	
	public static List<Perfil> listarAmigo(Integer idPerfil) throws SQLException{
		List<Integer> ids = new ArrayList<>();
		List<Perfil> p = new ArrayList<>();
		
		String sql = "select idPerfil1 from amigo where idPerfil = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idPerfil);
		ResultSet rs = stm.executeQuery();
		while(rs.next())
			ids.add(rs.getInt("idPerfil1"));
		
		sql = "select idPerfil from amigo where idPerfil1 = ?";
		stm = conexao.prepareStatement(sql);
		stm.setInt(1, idPerfil);
		rs = stm.executeQuery();
		while(rs.next())
			ids.add(rs.getInt("idPerfil"));
		
		sql = "select * from perfil where idPerfil = ?";
		for (int i = 0; i < ids.size(); i++) {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, ids.get(i));
			rs = stm.executeQuery();
			while(rs.next()){
				Perfil pe = new Perfil();
				pe.setIdPerfil(rs.getInt("idPerfil"));
				pe.setNome(rs.getString("nome"));
				pe.setEmail(rs.getString("email"));
				p.add(pe);
			}
		}
		stm.close();
		rs.close();
		return p;
	}
}
