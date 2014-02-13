package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Item;

public class DaoTipo {
	private static DaoTipo daoTipo;
	private static Connection conexao = Conexao.getConnection();
	
	public static synchronized DaoTipo getInstance(){
		if (daoTipo == null)
			daoTipo = new DaoTipo();
		return daoTipo;
	}
	
	public static List<Item> listarTipos() throws SQLException{
		List<Item> tipos = new ArrayList<>();
		String sql = "select * from tipoperfil";
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Item l = new Item();
			l.setId(rs.getInt("idTipo"));
			l.setNome(rs.getString("nomeTipo"));
			tipos.add(l);
		}
		stm.close();
		rs.close();
		return tipos;
	}
	
	public static Integer countAlunos(){
		String sql = "select count(*) as nAlunos from perfil where idTipo = 1";
		PreparedStatement stm;
		Integer nAlunos = null;
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			rs.next();
			nAlunos = rs.getInt("nAlunos");
		} catch (SQLException e) {e.printStackTrace();}
		return nAlunos;
	}
	
	public static Integer countProfessores(){
		String sql = "select count(*) as nProfessores from perfil where idTipo = 2";
		PreparedStatement stm;
		Integer nProfessores = null;
		try {
			stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			rs.next();
			nProfessores = rs.getInt("nProfessores");
		} catch (SQLException e) {e.printStackTrace();}
		return nProfessores;
	}
}
