package dao;

import java.sql.*;
import java.util.*;

import dominio.Turma;

public class DaoTurma {
	private static DaoTurma daoTurma;
	private static Connection conexao = Conexao.getConnection();
	
	public static synchronized DaoTurma getInstance(){
		if (daoTurma == null)
			daoTurma = new DaoTurma();
		return daoTurma;
	}
	
	public static void criarTurma(Turma turma, Integer idProfessor) throws SQLException{
		String sql = "insert into turma (nome, bio, idPerfil) values (?,?,?)";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setString(1, turma.getNome());
		stm.setString(2, turma.getDescricao());
		stm.setInt(3, idProfessor);
		stm.executeUpdate();
		stm.close();
	}
	
	public static List<Turma> listarTurmas() throws SQLException{
		List<Turma> turmas = new ArrayList<>();
		String sql = "select * from turma";
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Turma t = new Turma();
			t.setId(rs.getInt("idTurma"));
			t.setNome(rs.getString("nome"));
			t.setDescricao(rs.getString("bio"));
			turmas.add(t);
		}
		return turmas;
	}
	
	public static List<Turma> listarTurmas(Integer idProfessor) throws SQLException{
		List<Turma> turmas = new ArrayList<>();
		String sql = "select * from turma where idPerfil = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idProfessor);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Turma t = new Turma();
			t.setId(rs.getInt("idTurma"));
			t.setNome(rs.getString("nome"));
			t.setDescricao(rs.getString("bio"));
			turmas.add(t);
		}
		return turmas;
	}
}
