package dominio;

public class Arquivo {
	private Integer id;
	private String nome;
	private String tipo;
	private byte[] arquivo;
	
	public Arquivo(Integer idArquivo, String nome, String tipo, byte[] arquivo) {
		this.id = idArquivo;
		this.nome = nome;
		this.tipo = tipo;
		this.arquivo = arquivo;
	}
	public Arquivo() {
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer idArquivo) {
		this.id = idArquivo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public byte[] getArquivo() {
		return arquivo;
	}
	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
}
