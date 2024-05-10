import java.util.List;

public class TarefaAlocada {
	
	private List<Pessoa> pessoaList;
	private Tarefa tarefa;

	public List<Pessoa> getPessoaList() {
		return pessoaList;
	}

	public void setPessoaList(List<Pessoa> pessoaList) {
		this.pessoaList = pessoaList;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	TarefaAlocada(List<Pessoa> pessoaList, Tarefa tarefa){
		this.pessoaList = pessoaList;
		this.tarefa = tarefa;
	}

}
