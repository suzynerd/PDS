package dao;

import java.sql.*;
import java.util.*;

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
			t.setDono((DaoPerfil.findNamePerfil(rs.getInt("idPerfil"))).getNome());
		}
		return t;
	}
	
	public static List<Perfil> listarAlunos(Integer idTurma) throws SQLException{
		List<Perfil> perfis = new ArrayList<>(); Perfil p;
		String sql = "select idPerfil from membros where idTurma = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idTurma);
		ResultSet rs = stm.executeQuery();
		while(rs.next()){
			p = new Perfil();
			p.setIdPerfil(rs.getInt("idPerfil"));
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
		String sql = "insert into membros (idTurma, idPerfil) values (?,?)";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idTurma);
		stm.setInt(2, idAluno);
		stm.executeUpdate();
		stm.close();
	}
	public static void removeAluno(Integer idTurma, Integer idAluno) throws SQLException{
		String sql = "delete from membros where idTurma = ? AND idPerfil = ?";
		PreparedStatement stm = conexao.prepareStatement(sql);
		stm.setInt(1, idTurma);
		stm.setInt(2, idAluno);
		stm.executeUpdate();
		stm.close();
	}
	
	public static List<Amigo> novosAlunos(Integer idTurma, Integer idProfessor) throws SQLException{
		List<Amigo> alunos =  DaoAmigo.listarAmigo(idProfessor);
		List<Perfil> p = listarAlunos(idTurma);
		for (int i = 0; i < alunos.size(); i++) {
			if (DaoPerfil.isProfessor(alunos.get(i).getIdAmigo())) {
				alunos.remove(i);
			}
			for (int j = 0; j < p.size(); j++) {
				if (alunos.get(i).getIdAmigo() == p.get(j).getIdPerfil()) {
					alunos.remove(i);
				}
			}
		}
		return alunos;
	}
}
