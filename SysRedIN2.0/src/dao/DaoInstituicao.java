package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.Item;

public class DaoInstituicao {
	private static Connection conexao = Conexao.getConnection();
	
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
			System.out.println("Erro ao listar Instituições");
		}
		return itens;
	}
}
