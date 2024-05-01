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

	Tarefa(String nome, String prazo, int prioridade){
		this.nome=nome;
		this.prazo=prazo;
		this.prioridade=prioridade;
	}
	
}
