package dominio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Perfil {
	private Integer id, idTipo, idInstituicao;
	private String nome, senha, email, instituicao, tipo;
	
	public Perfil(ResultSet rs){
		
		try {
			this.tipo = rs.getString("nomeTipo");
			this.id = rs.getInt("idPerfil");
			this.nome = rs.getString("nome");
			this.email = rs.getString("email");
			this.instituicao = rs.getString("nomeInstituicao");
			this.idInstituicao = rs.getInt("idInstituicao");
		} catch (SQLException e) {
			System.out.println("Erro ao sear valores na lista de perfis");
		}
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Perfil() {}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
