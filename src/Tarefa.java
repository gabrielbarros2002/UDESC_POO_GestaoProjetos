import java.time.LocalDate;

public class Tarefa {
	
	private String nome;
	private LocalDate prazo;
	private int prioridade;
	private int status;

	public String getNome() {
		return nome;
	}

	public LocalDate getPrazo() {
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

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	Tarefa(String nome, LocalDate prazo, int prioridade){
		this.nome = nome;
		this.prazo = prazo;
		this.prioridade = prioridade;
		this.status = 1;
	}

}
