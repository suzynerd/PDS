package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Instituicao;
import dominio.Item;

public class DaoInstituicao {
	private static DaoInstituicao daoInst;
	private static Connection conexao = Conexao.getConnection();
	
	public static synchronized DaoInstituicao getInstance(){
		if(daoInst == null)
			daoInst = new DaoInstituicao();
		return daoInst;
	}
	
	public static void insert(Instituicao inst){
		String sql = "insert into instituicao (nome, sigla, idEstado) values (?, ?, ?)";
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setString(1, inst.getNome());
			stm.setString(2, inst.getSigla());
			stm.setInt(3, inst.getIdEstado());
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	public static List<Item> getList(){
		String sql = "select * from instituicao";
		List<Item> itens = new ArrayList<>();
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setId(rs.getInt("idInstituicao"));
				item.setNome(rs.getString("sigla") + " - " +rs.getString("nome"));
				itens.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itens;
	}
	
	public static Instituicao find(Integer idInstituicao){
		String sql = "select * from instituicao where idInstituicao = ?";
		Instituicao instituicao = new Instituicao();
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, idInstituicao);
			ResultSet rs = stm.executeQuery();
			rs.next();
			instituicao.setId(rs.getInt("idInstituicao"));
			instituicao.setNome(rs.getString("nome"));
			instituicao.setSigla(rs.getString("sigla"));
			instituicao.setIdEstado(rs.getInt("idEstado"));
		} catch (SQLException e) {e.printStackTrace();}
		return instituicao;
	}
	
	public static void remove(Integer idInstituicao){
		String slq = "delete from instituicao where idInstituicao = ?";
		try {
			PreparedStatement stm = conexao.prepareStatement(slq);
			stm.setInt(1, idInstituicao);
			stm.executeUpdate();
			stm.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
}
