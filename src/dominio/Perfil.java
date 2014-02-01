package dominio;

public class Perfil {
	public Integer idPerfil, idTipoPerfil;
	public String nome;
	public String email;
	public String senha;
	
	public Perfil(){}
	public Perfil(String nome,
			String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
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
