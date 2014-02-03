package dominio;

public class Arquivo {
	private Integer idArquivo;
	private String nome;
	private String tipo;
	private byte[] arquivo;
	
	public Arquivo(Integer idArquivo, String nome, String tipo, byte[] arquivo) {
		this.idArquivo = idArquivo;
		this.nome = nome;
		this.tipo = tipo;
		this.arquivo = arquivo;
	}
	public Arquivo() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIdArquivo() {
		return idArquivo;
	}
	public void setIdArquivo(Integer idArquivo) {
		this.idArquivo = idArquivo;
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
