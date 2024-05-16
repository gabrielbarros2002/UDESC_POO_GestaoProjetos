import java.util.List;
import java.util.Set;

public class TarefaAlocada {
	
	private Set<Pessoa> pessoaSet;
	private Tarefa tarefa;

	public Set<Pessoa> getPessoaSet() {
		return pessoaSet;
	}

	public void setPessoaSet(Set<Pessoa> pessoaSet) {
		this.pessoaSet = pessoaSet;
	}

	public Tarefa getTarefa() {
		return tarefa;
	}

	public void setTarefa(Tarefa tarefa) {
		this.tarefa = tarefa;
	}
	
	TarefaAlocada(Set<Pessoa> pessoaList, Tarefa tarefa){
		this.pessoaSet = pessoaList;
		this.tarefa = tarefa;
	}

}
