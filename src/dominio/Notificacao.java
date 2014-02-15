package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DaoPerfil;
import dao.DaoTurma;

public class Notificacao {
	private Integer id, idPerfil, idTurma;
	private String mensagem;
	public Notificacao() {
	}
	public Notificacao(Integer idPerfil, Integer idTurma) {
		this.idPerfil = idPerfil;
		this.idTurma = idTurma;
		this.mensagem = 
				"O Aluno " + DaoPerfil.findPerfil(idPerfil).getNome() +
				" esta solicitando entrada na turma " + DaoTurma.findTurma(idTurma).getNome();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	public Integer getIdTurma() {
		return idTurma;
	}
	public void setIdTurma(Integer idTurma) {
		this.idTurma = idTurma;
	}
	public String getMensagem() {
		return mensagem;
	}
	
	public Notificacao(ResultSet rs){
		try {
			this.id = rs.getInt("idNotificacao");
			this.idTurma = rs.getInt("idTurma");
			this.idPerfil = rs.getInt("idPerfil");
			this.mensagem = rs.getString("mensagem");
		} catch (SQLException e) {e.printStackTrace();}
	}
	
}