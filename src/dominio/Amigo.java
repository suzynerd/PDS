package dominio;

public class Amigo {
	private Integer idRelacao, idAmigo;
	private String nome;
	public Amigo() {
	}
	public Amigo(Integer idRelacao, Integer idAmigo, String nome) {
		super();
		this.idRelacao = idRelacao;
		this.idAmigo = idAmigo;
		this.nome = nome;
	}
	public Integer getIdRelacao() {
		return idRelacao;
	}
	public void setIdRelacao(Integer idRelacao) {
		this.idRelacao = idRelacao;
	}
	public Integer getIdAmigo() {
		return idAmigo;
	}
	public void setIdAmigo(Integer idAmigo) {
		this.idAmigo = idAmigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
