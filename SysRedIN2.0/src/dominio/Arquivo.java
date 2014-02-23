package dominio;

public class Arquivo {
	private int id;
	private String tipo = null, formato, nome;
	private byte[] bites;
	public Arquivo() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormato() {
		return formato;
	}
	public void setFormato(String formato) {
		this.formato = formato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public byte[] getBites() {
		return bites;
	}
	public void setBites(byte[] bites) {
		this.bites = bites;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
