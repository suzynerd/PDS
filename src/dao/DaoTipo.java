package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Lista;

public class DaoTipo {
	private static DaoTipo daoTipo;
	private static Connection conexao = Conexao.getConnection();
	
	public static synchronized DaoTipo getInstance(){
		if (daoTipo == null)
			daoTipo = new DaoTipo();
		return daoTipo;
	}
	
	public static List<Lista> listarTipos() throws SQLException{
		List<Lista> tipos = new ArrayList<>();
		String sql = "select * from tipoperfil";
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Lista l = new Lista();
			l.setId(rs.getInt("idTipo"));
			l.setNome(rs.getString("nomeTipo"));
			tipos.add(l);
		}
		stm.close();
		rs.close();
		return tipos;
	}
}
