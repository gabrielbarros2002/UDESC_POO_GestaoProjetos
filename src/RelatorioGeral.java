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
		} else if(projeto.getStatus() == 2) {
			statusProjeto = "Finalizado";
		} else {
			statusProjeto = "Cancelado";
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
		if(projeto.getListaDeTarefasAlocadas().isEmpty()) {
			dados += "\n\n-- Projeto sem tarefas alocadas --";
		} else {
			for(TarefaAlocada tarefaAlocada : projeto.getListaDeTarefasAlocadas()) {
				dados += "\n\n " + tarefaAlocada.getTarefa().getNome() + ":";
				for(Pessoa pessoa : tarefaAlocada.getPessoaList()) {
					dados += "\n  - " + pessoa.getNome() + " " + pessoa.getSobrenome() + " | " + pessoa.getCargo().getNome();
				}
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
		if(projeto.getListaDeRecursos().isEmpty()) {
			dados += "\n\n-- Projeto sem recursos --";
		} else {
			for(Recurso recurso : projeto.getListaDeRecursos()) {
				dados += "\n- " + recurso.getNome() + ": " + String.format("R$ %.2f", recurso.getValor());
			}
		}
		dados += gerarRodapeRelatorio();
		return dados;
	}

	public String gerarRelatorioDeTarefasFinalizadasDoProjeto(Projeto projeto) {
		String dados = "Tarefas finalizadas do projeto " + projeto.getTitulo();
		if(projeto.getListaDeTarefas().isEmpty()) {
			dados += "\n\n-- Projeto sem tarefas --";
		} else {
			for(Tarefa tarefa : projeto.getListaDeTarefas()) {
				if(tarefa.getStatus() == 2) {
					dados += "\n- " + tarefa.getNome();
					dados += "\n   Prazo: " + dateTimeFormatter.format(tarefa.getPrazo());
					dados += "\n   Prioridade: " + tarefa.getPrioridade();
				}
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
