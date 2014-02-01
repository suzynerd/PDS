package dominio;

public class Tipo {
	private Integer idTipo;
	private String nome;
	public Tipo() {
	}
	public Tipo(Integer idTipo, String nome) {
		super();
		this.idTipo = idTipo;
		this.nome = nome;
	}
	public Integer getIdTipo() {
		return idTipo;
	}
	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
