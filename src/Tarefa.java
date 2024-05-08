import java.sql.Date;

public class Tarefa {
	
	private String nome;
	private String prazo;
	private int prioridade;
	private int status;

	public String getNome() {
		return nome;
	}

	public String getPrazo() {
		return prazo;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public int getStatus() {
		return status;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPrazo(String prazo) {
		this.prazo = prazo;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	Tarefa(String nome, String prazo, int prioridade){
		this.nome = nome;
		this.prazo = prazo;
		this.prioridade = prioridade;
		this.status = 1;
	}

}
