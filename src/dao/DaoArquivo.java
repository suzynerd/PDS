package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Arquivo;

public class DaoArquivo {
	private static DaoArquivo daoArquivo;
	private static Connection conexao = Conexao.getConnection();

	public static synchronized DaoArquivo getInstance(){
		if (daoArquivo == null)
			daoArquivo = new DaoArquivo();
		return daoArquivo;
	}
	
	public static List<Arquivo> getList(Integer idPerfil) throws SQLException{
		List<Arquivo> arquivos = new ArrayList<>();
		String sql = "select * from arquivo where idPerfil = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idPerfil);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()){
			Arquivo a = new Arquivo();
			a.setId(rs.getInt("idArquivo"));
			a.setNome(rs.getNString("nome"));
			a.setTipo(rs.getString("tipo"));
			arquivos.add(a);
		}
		stm.close(); rs.close();
		return arquivos;
	}
	
	public static void insert(Arquivo arquivo, Integer idPerfil) throws SQLException{
		String sql = "insert into arquivo (nome, arquivo, tipo, idPerfil) values (?,?,?,?)";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setString(1, arquivo.getNome());
		stm.setBytes(2, arquivo.getArquivo());
		stm.setString(3, arquivo.getTipo());
		stm.setInt(4, idPerfil);
		stm.executeUpdate();
		stm.close();
	}
	
	public static void remove(Integer idArquivo) throws SQLException{
		String sql = "delete from arquivo where idArquivo = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idArquivo);
		stm.executeUpdate();
		stm.close();
	}
	
	public static Arquivo find(Integer id) throws SQLException{
		Arquivo a = new Arquivo();
		String sql = "select * from arquivo where idArquivo = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			a.setId(rs.getInt("idArquivo"));
			a.setNome(rs.getNString("nome"));
			a.setArquivo(rs.getBytes("arquivo"));
			a.setTipo(rs.getString("tipo"));
		}
		stm.close(); rs.close();
		return a;
	}
}
