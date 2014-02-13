package dominio;

public class Instituicao {
	private Integer id, idEstado;
	private String nome, sigla;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Instituicao(Integer id, Integer idEstado, String nome, String sigla) {
		super();
		this.id = id;
		this.idEstado = idEstado;
		this.nome = nome;
		this.sigla = sigla;
	}
	public Instituicao() {
	}
}
