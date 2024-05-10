import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RelatorioGeral extends Relatorio{

	RelatorioGeral(LocalDateTime dataSolicitacao, Pessoa solicitante) {
		super(dataSolicitacao, solicitante);
	}

	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");

	public String gerarRelatorioDadosGerais(Projeto projeto) {
		String statusProjeto;
		if(projeto.getStatus() == 1) {
			statusProjeto = "Em andamento";
		} else {
			statusProjeto = "Finalizado";
		}
		String dados = "Dados do projeto " + projeto.getTitulo();
		dados += "\n\n Data inicial: " + dateFormatter.format(projeto.getDataInicial());
		dados += "\n Data final: " + dateFormatter.format(projeto.getDataFinal());
		dados += "\n Cliente: " + projeto.getCliente();
		dados += "\n Status do projeto: " + statusProjeto;
		dados += gerarRodapeRelatorio();
		return dados;
	}
	
	public String gerarRelatorioDeTarefasAlocadas(Projeto projeto) {
		String dados = "Tarefas alocadas do projeto " + projeto.getTitulo();
		for(TarefaAlocada tarefaAlocada : projeto.getListaDeTarefasAlocadas()) {
			dados += "\n\n " + tarefaAlocada.getTarefa().getNome() + ":";
			for(Pessoa pessoa : tarefaAlocada.getPessoaList()) {
				dados += "\n  - " + pessoa.getNome() + " " + pessoa.getSobrenome() + " | " + pessoa.getCargo().getNome();
			}
		}
		dados += gerarRodapeRelatorio();
		return dados;
	}

	public String gerarRelatorioDePessoasNoProjeto(Projeto projeto) {
		String dados = "Pessoas do projeto " + projeto.getTitulo();
		for(Pessoa pessoa : projeto.getListaDePessoas()) {
			dados += "\n- " + pessoa.getNome() + " " + pessoa.getSobrenome() + ": " + pessoa.getCargo().getNome();
		}
		dados += gerarRodapeRelatorio();
		return dados;
	}

	public String gerarRelatorioDeRecursosNoProjeto(Projeto projeto) {
		String dados = "Recursos do projeto " + projeto.getTitulo();
		for(Recurso recurso : projeto.getListaDeRecursos()) {
			dados += "\n- " + recurso.getNome() + ": " + recurso.getValor();
		}
		dados += gerarRodapeRelatorio();
		return dados;
	}

	public String gerarRelatorioDeTarefasFinalizadasDoProjeto(Projeto projeto) {
		String dados = "Tarefas finalizadas do projeto " + projeto.getTitulo();
		for(Tarefa tarefa : projeto.getListaDeTarefas()) {
			if(tarefa.getStatus() == 2) {
				dados += "\n- " + tarefa.getNome();
				dados += "\n   Prazo: " + dateTimeFormatter.format(tarefa.getPrazo());
				dados += "\n   Prioridade: " + tarefa.getPrioridade();
			}
		}
		dados += gerarRodapeRelatorio();
		return dados;
	}

	public String gerarRodapeRelatorio() {
		return "\n\n-----------------"
				+ "\n Solicitante: " + this.solicitante.getNome() + " "
				+ "\n Data e Hora: " + dateTimeFormatter.format(this.dataSolicitacao);
	}

}
