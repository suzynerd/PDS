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
				Item i = new Item();
				i.setId(rs.getInt("idInstituicao"));
				i.setNome(rs.getString("sigla") + " - " +rs.getString("nome"));
				itens.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return itens;
	}
	
	public static Instituicao getInstituicao(Integer id){
		String sql = "select * from instituicao where idInstituicao = ?";
		Instituicao i = new Instituicao();
		try {
			PreparedStatement stm = conexao.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			rs.next();
			i.setId(rs.getInt("idInstituicao"));
			i.setNome(rs.getString("nome"));
			i.setSigla(rs.getString("sigla"));
			i.setIdEstado(rs.getInt("idEstado"));
		} catch (SQLException e) {e.printStackTrace();}
		return i;
	}
}
