
public class TarefaAlocada {
	
	private Pessoa pessoa;
	private Tarefa tarefa;
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	TarefaAlocada(Pessoa pessoa, Tarefa tarefa){
		this.pessoa=pessoa;
		this.tarefa=tarefa;
	}

}
