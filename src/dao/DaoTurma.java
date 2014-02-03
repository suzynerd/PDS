package dao;

import java.sql.*;
import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

import dominio.Amigo;
import dominio.Perfil;
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
	
	public static Turma findTurma(Integer id) throws SQLException{
		Turma t = new Turma();
		String sql = "select * from turma where idTurma = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			t.setId(id);
			t.setNome(rs.getString("nome"));
			t.setDescricao(rs.getString("bio"));
			t.setDono(DaoPerfil.findNomePerfil(rs.getInt("idPerfil")));
		}
		return t;
	}
	
	public static List<Perfil> listarAlunos(Integer idTurma) throws SQLException{
		List<Perfil> perfis = new ArrayList<>(); Perfil p;
		String sql = "select idAluno from membros where idTurma = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			p = new Perfil();
			p.setIdPerfil(rs.getInt("idAluno"));
			perfis.add(p);
		}
		
		sql = "select * from perfil where idPerfil = ?";
		for (int i = 0; i < perfis.size(); i++) {
			stm = conexao.prepareStatement(sql);
			stm.setInt(1, perfis.get(i).getIdPerfil());
			rs = stm.executeQuery();
			while(rs.next()){
				perfis.get(i).setNome(rs.getString("nome"));
				perfis.get(i).setEmail(rs.getString("email"));
				perfis.get(i).setIdTipoPerfil(rs.getInt("idTipo"));
			}
				
		}
		return perfis;
	}
	public static void addAluno(Integer idTurma, Integer idAluno) throws SQLException{
		String sql = "insert into menbros (idTurma, idAluno) values (?,?)";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idTurma);
		stm.setInt(2, idAluno);
		stm.executeUpdate();
		stm.close();
	}
	public static void removeAluno(Integer idTurma, Integer idAluno) throws SQLException{
		String sql = "delete menbros where idTurma = ? AND idAluno = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idTurma);
		stm.setInt(2, idAluno);
		stm.executeUpdate();
		stm.close();
	}
	public static List<Amigo> novosAlunos(Integer idTurma, Integer idProfessor) throws SQLException{
		List<Amigo> perfis =  DaoAmigo.listarAmigo(idProfessor);
		for (int i = 0; i < perfis.size(); i++) {
			if (!DaoPerfil.isAluno(perfis.get(i).getIdAmigo())) {
				perfis.remove(i);
			}
		}
		return perfis;
	}
}
