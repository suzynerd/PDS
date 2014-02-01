package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import dominio.Tipo;

public class DaoTipo {
	private static DaoTipo daoTipo;
	private static Connection conexao = Conexao.getConnection();
	
	public static synchronized DaoTipo getInstance(){
		if (daoTipo == null)
			daoTipo = new DaoTipo();
		return daoTipo;
	}
	
	public static List<Tipo> listarTipos() throws SQLException{
		List<Tipo> tipos = new ArrayList<>();
		String sql = "select * from tipoperfil";
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Tipo t = new Tipo();
			t.setIdTipo(rs.getInt("idTipo"));
			t.setNome(rs.getString("nomeTipo"));
			tipos.add(t);
		}
		stm.close();
		rs.close();
		return tipos;
	}
}
