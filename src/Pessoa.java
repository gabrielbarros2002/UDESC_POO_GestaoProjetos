
public class Pessoa {
	
	private String nome;
	private String sobrenome;
	private Cargo cargo;
	
	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public Cargo getCargo() {
		return cargo;
	}

	Pessoa (String nome, String sobrenome, Cargo cargo) {
		this.nome=nome;
		this.sobrenome=sobrenome;
		this.cargo=cargo;
	}
	
	

}
