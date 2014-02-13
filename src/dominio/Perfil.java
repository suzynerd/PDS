package dominio;

public class Perfil {
	private Integer idPerfil, idTipoPerfil, idInstituicao;
	private String nome;
	private String email;
	private String senha;
	
	public Perfil(){}
	
	public Perfil(Integer idPerfil, Integer idTipoPerfil,
			Integer idInstituicao, String nome, String email, String senha) {
		super();
		this.idPerfil = idPerfil;
		this.idTipoPerfil = idTipoPerfil;
		this.idInstituicao = idInstituicao;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getIdTipoPerfil() {
		return idTipoPerfil;
	}
	public void setIdTipoPerfil(Integer idTipoPerfil) {
		this.idTipoPerfil = idTipoPerfil;
	}
	public Integer getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
