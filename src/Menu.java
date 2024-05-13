import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.JOptionPane;

public class Menu {

	public static final String MENSAGEM_SEM_PROJETOS = "Não há projetos cadastrados.\nDeseja cadastrar um novo projeto?";
	public static final String MENSAGEM_SEM_TAREFAS = "Não há tarefas cadastradas.\nDeseja cadastrar uma tarefa para este projeto?";
	public static final String MENSAGEM_SEM_PESSOAS = "Não há pessoas cadastradas.\nDeseja cadastrar uma pessoa para este projeto?";
	public static final String MENSAGEM_SAIR = "Tem certeza que deseja sair?\nSeus dados serão perdidos";
	public static final String MENSAGEM_RELATORIO_INVALIDO = "Tipo de relatório inválido\nFavor digitar um tipo de relatório válido";

	public static void exibirMenu() {
		String[] lista = {
				"Criar projeto",
				"Adicionar tarefa",
				"Adicionar pessoa ao projeto",
				"Alocar tarefa",
				"Alocar recurso",
				"Excluir recurso",
				"Alterar status do projeto",
				"Alterar data final do projeto",
				"Alterar status da tarefa",
				"Gerar relatório",
				"Sair"
		};
		String resposta;
		boolean sair = false;

		do {
			resposta = (String) JOptionPane.showInputDialog(
					null,
					"Selecione uma opção",
					"Gestão de Projetos",
					JOptionPane.INFORMATION_MESSAGE,
					null,
					lista,
					lista[0]);

			switch (resposta) {
				case "Criar projeto":
					criarProjeto();
					break;
				case "Adicionar tarefa":
					adicionarTarefa();
					break;
				case "Adicionar pessoa ao projeto":
					adicionarPessoa();
					break;
				case "Alocar tarefa":
					alocarTarefa();
					break;
				case "Alocar recurso":
					alocarRecurso();
					break;
				case "Excluir recurso":
					excluirRecurso();
					break;
				case "Alterar status do projeto":
					alterarStatusProjeto();
					break;
				case "Alterar data final do projeto":
					alterarDataFinalProjeto();
					break;
				case "Alterar status da tarefa":
					alterarStatusTarefa();
					break;
				case "Gerar relatório":
					gerarRelatorio();
					break;
                case null:
					sair = sair();
					break;
				default:
					sair = sair();
					break;
			}
		} while (!sair);
	}

	// Criar projeto
	private static void criarProjeto() {
		String titulo = "Criar Projeto";
		String tituloProjeto = EntradaSaidaDados.retornarTexto("Informe o título do projeto", titulo);
		if(tituloProjeto == null){
			return;
		}

		String cliente = EntradaSaidaDados.retornarTexto("Informe o cliente do projeto", titulo);
		if(cliente == null) {
			return;
		}

		LocalDate dataInicial;
		dataInicial = EntradaSaidaDados.retornarData("Informe a data inicial do projeto (dd/MM/aaaa)", titulo);
		if(dataInicial == null) {
			return;
		}

		LocalDate dataFinal;
		dataFinal = EntradaSaidaDados.retornarData("Informe a data final do projeto (dd/MM/aaaa)", titulo);
		if(dataFinal == null) {
			return;
		}

		Projeto projeto = new Projeto(tituloProjeto, cliente, dataInicial, dataFinal);
		GestaoProjetos.adicionarProjeto(projeto);

		EntradaSaidaDados.mostrarMensagem("Projeto criado com sucesso!", "Sucesso");
	}

	// Adicionar tarefa
	private static void adicionarTarefa() {
		String titulo = "Adicionar tarefa";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			String nome = EntradaSaidaDados.retornarTexto("Informe o nome da tarefa", titulo);
			if(nome == null) {
				return;
			}

			LocalDate prazo;
			prazo = EntradaSaidaDados.retornarData("Informe o prazo da tarefa (DD/MM/AAAA)", titulo);
			if(prazo == null) {
				return;
			}

			Integer prioridade = EntradaSaidaDados.escolherPrioridade(titulo);
			if(prioridade == null) {
				return;
			}

			Tarefa tarefa = new Tarefa(nome, prazo, prioridade);
			projetoEscolhido.adicionarTarefa(tarefa);

			EntradaSaidaDados.mostrarMensagem("Tarefa adicionada com sucesso!", "Sucesso");
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(
					MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	// Adicionar tarefa (com projeto como parâmetro)
	private static void adicionarTarefa(Projeto projeto) {
		String titulo = "Adicionar tarefa";
		String nome = EntradaSaidaDados.retornarTexto("Informe o nome da tarefa", titulo);
		if(nome == null) {
			return;
		}

		LocalDate prazo;
		prazo = EntradaSaidaDados.retornarData("Informe o prazo da tarefa (DD/MM/AAAA)", titulo);
		if(prazo == null) {
			return;
		}

		Integer prioridade = EntradaSaidaDados.escolherPrioridade(titulo);
		if(prioridade == null) {
			return;
		}

		Tarefa tarefa = new Tarefa(nome, prazo, prioridade);
		projeto.adicionarTarefa(tarefa);

		EntradaSaidaDados.mostrarMensagem("Tarefa adicionada com sucesso!", "Sucesso");
	}

	// Adicionar pessoa
	private static void adicionarPessoa() {
		String titulo = "Adicionar pessoa";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			String nome = EntradaSaidaDados.retornarTexto("Informe o nome da pessoa", titulo);
			if(nome == null) {
				return;
			}

			String sobrenome = EntradaSaidaDados.retornarTexto("Informe o sobrenome da pessoa", titulo);
			if(sobrenome == null) {
				return;
			}

			String nomeCargo = EntradaSaidaDados.retornarTexto("Informe o cargo da pessoa no projeto", titulo);
			if(nomeCargo == null) {
				return;
			}
			Cargo cargo = new Cargo(nomeCargo);

			Pessoa pessoa = new Pessoa(nome, sobrenome, cargo);
			projetoEscolhido.adicionarPessoa(pessoa);

			EntradaSaidaDados.mostrarMensagem("Pessoa adicionada com sucesso!", "Sucesso");
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(
					MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	// Adicionar pessoa (com projeto como parâmetro)
	private static void adicionarPessoa(Projeto projeto) {
		String titulo = "Adicionar pessoa";
		String nome = EntradaSaidaDados.retornarTexto("Informe o nome da pessoa", titulo);
		if(nome == null) {
			return;
		}

		String sobrenome = EntradaSaidaDados.retornarTexto("Informe o sobrenome da pessoa", titulo);
		if(sobrenome == null) {
			return;
		}

		String nomeCargo = EntradaSaidaDados.retornarTexto("Informe o cargo da pessoa no projeto", titulo);
		if(nomeCargo == null) {
			return;
		}
		Cargo cargo = new Cargo(nomeCargo);

		Pessoa pessoa = new Pessoa(nome, sobrenome, cargo);
		projeto.adicionarPessoa(pessoa);

		EntradaSaidaDados.mostrarMensagem("Pessoa adicionada com sucesso!", "Sucesso");
	}

	// Alocar tarefa
	private static void alocarTarefa() {
		String titulo = "Alocar tarefa";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Pessoa pessoaEscolhida;
			Tarefa tarefaEscolhida;

			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			if(projetoEscolhido.retornarListaTarefas() != null) {
				Integer posicaoTarefa = EntradaSaidaDados.escolherTarefa(titulo, projetoEscolhido.retornarListaTarefas());
				if(posicaoTarefa == null) {
					return;
				}

				tarefaEscolhida = projetoEscolhido.retornarTarefa(posicaoTarefa);
			} else {
				if(EntradaSaidaDados.retornarConfirmacao(
						MENSAGEM_SEM_TAREFAS, "Aviso") == JOptionPane.YES_OPTION) {
					adicionarTarefa(projetoEscolhido);
				}
				return;
			}

			if(projetoEscolhido.retornarListaPessoas() != null) {
				Integer posicaoPessoa = EntradaSaidaDados.escolherPessoa(titulo, projetoEscolhido.retornarListaPessoas());
				if(posicaoPessoa == null) {
					return;
				}

				pessoaEscolhida = projetoEscolhido.retornarPessoa(posicaoPessoa);
			} else {
				if(EntradaSaidaDados.retornarConfirmacao(
						MENSAGEM_SEM_PESSOAS, "Aviso") == JOptionPane.YES_OPTION) {
					adicionarPessoa(projetoEscolhido);
				}
				return;
			}

			projetoEscolhido.alocarTarefa(pessoaEscolhida, tarefaEscolhida);

			EntradaSaidaDados.mostrarMensagem("Tarefa alocada com sucesso!", "Sucesso");
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(
					MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	// Alocar recurso
	private static void alocarRecurso() {
		String titulo = "Alocar recurso";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			String nome = EntradaSaidaDados.retornarTexto("Informe o nome do recurso", titulo);
			if(nome == null) {
				return;
			}

			Double valor = EntradaSaidaDados.retornarReal("Informe o preço do recurso", titulo);
			if(valor == null) {
				return;
			}

			Recurso recurso = new Recurso(nome, valor);
			projetoEscolhido.adicionarRecurso(recurso);

			EntradaSaidaDados.mostrarMensagem("Recurso adicionado com sucesso!", "Sucesso");
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(
					MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	// Excluir recurso
	private static void excluirRecurso() {
		String titulo = "Excluir recurso";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			if(projetoEscolhido.retornarListaRecursos() == null) {
				EntradaSaidaDados.mostrarMensagemAviso("Não há recursos para excluir!");
				return;
			}

			Recurso recursoEscolhido = EntradaSaidaDados.escolherRecurso(
					titulo, projetoEscolhido.retornarListaRecursos(), projetoEscolhido);
			if(recursoEscolhido == null) {
				return;
			}

			projetoEscolhido.excluirRecurso(recursoEscolhido);

			EntradaSaidaDados.mostrarMensagem("Recurso excluído com sucesso!", "Sucesso");
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(
					MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	// Alterar status do projeto
	private static void alterarStatusProjeto() {
		String titulo = "Alterar status do projeto";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			Integer statusEscolhido = EntradaSaidaDados.escolherStatusProjeto(titulo);
			if(statusEscolhido == null) {
				return;
			}

			projetoEscolhido.setStatus(statusEscolhido);

			EntradaSaidaDados.mostrarMensagem("Status alterado com sucesso!", "Sucesso");
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(
					MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	// Alterar data final do projeto
	private static void alterarDataFinalProjeto() {
		String titulo = "Alterar data final do projeto";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			LocalDate novaDataFinal = EntradaSaidaDados.retornarData("Informe a nova data", titulo);
			if(novaDataFinal == null) {
				return;
			}

			projetoEscolhido.setDataFinal(novaDataFinal);

			EntradaSaidaDados.mostrarMensagem("Data final do projeto alterada com sucesso!", "Sucesso");
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(
					MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	// Alterar status da tarefa
	private static void alterarStatusTarefa() {
		String titulo = "Alterar status da tarefa";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			if(projetoEscolhido.retornarListaTarefas() == null) {
				int resposta = JOptionPane.showConfirmDialog(null,
						MENSAGEM_SEM_TAREFAS, "Aviso", JOptionPane.YES_NO_OPTION);

				if(resposta == JOptionPane.YES_NO_OPTION) {
					adicionarTarefa(projetoEscolhido);
				}
			} else {
				Integer posicaoTarefa = EntradaSaidaDados.escolherTarefa(titulo, projetoEscolhido.retornarListaTarefas());
				if(posicaoTarefa == null) {
					return;
				}

				Tarefa tarefaEscolhida = projetoEscolhido.retornarTarefa(posicaoTarefa);

				Integer statusEscolhido = EntradaSaidaDados.escolherStatusTarefa(titulo);
				if(statusEscolhido == null) {
					return;
				}

				tarefaEscolhida.setStatus(statusEscolhido);

				EntradaSaidaDados.mostrarMensagem("Status da tarefa " + tarefaEscolhida.getNome() + " alterado com sucesso!", "Sucesso");
			}
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(
					MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	// Gerar relatório
	private static void gerarRelatorio() {
		String titulo = "Gerar relatório";
		if(GestaoProjetos.retornarListaProjetos().getItemCount() > 0) {
			Integer tipo;
			boolean tipoValido = false;
			do{
				tipo = EntradaSaidaDados.retornarInteiro("Informe o tipo de relatório desejado:"
								+ "\n 1 - Dados gerais do projeto"
								+ "\n 2 - Tarefas alocadas de um projeto"
								+ "\n 3 - Pessoas atribuídas a um projeto"
								+ "\n 4 - Recursos alocados de um projeto"
								+ "\n 5 - Tarefas finalizadas de um projeto",
						"Relatório");
				if(tipo == null) {
					return;
				}
				if(tipo >= 1 && tipo <= 5) {
					tipoValido = true;
				}
				if(!tipoValido) {
					EntradaSaidaDados.mostrarMensagemAviso(MENSAGEM_RELATORIO_INVALIDO);
				}
			} while(!tipoValido);

			Projeto projetoEscolhido = EntradaSaidaDados.escolherProjeto(titulo, GestaoProjetos.retornarListaProjetos());
			if(projetoEscolhido == null) {
				return;
			}

			if(projetoEscolhido.getListaDePessoas().isEmpty()) {
				if(EntradaSaidaDados.retornarConfirmacao(
						MENSAGEM_SEM_PESSOAS, "Aviso") == JOptionPane.YES_OPTION) {
					adicionarPessoa(projetoEscolhido);
				}
				return;
			}

			Integer posicaoSolicitante = EntradaSaidaDados.escolherPessoa(titulo, projetoEscolhido.retornarListaPessoas());
			if(posicaoSolicitante == null) {
				return;
			}

			Pessoa solicitante = projetoEscolhido.retornarPessoa(posicaoSolicitante);

			LocalDateTime dataSolcitacao = LocalDateTime.now();
			RelatorioGeral relatorio = new RelatorioGeral(dataSolcitacao, solicitante);

			switch (tipo) {
				case 1:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDadosGerais(projetoEscolhido), "Geral");
					break;
				case 2:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeTarefasAlocadas(projetoEscolhido), "de Tarefas Alocadas");
					break;
				case 3:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDePessoasNoProjeto(projetoEscolhido), "de Pessoas no Projeto");
					break;
				case 4:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeRecursosNoProjeto(projetoEscolhido), "de Recursos no Projeto");
					break;
				case 5:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeTarefasFinalizadasDoProjeto(projetoEscolhido), "de Tarefas Finalizadas no Projeto");
					break;
			}
		} else {
			if(EntradaSaidaDados.retornarConfirmacao(MENSAGEM_SEM_PROJETOS, "Aviso") == JOptionPane.YES_OPTION) {
				criarProjeto();
			}
		}
	}

	public static Boolean sair() {
        return EntradaSaidaDados.retornarConfirmacao(MENSAGEM_SAIR, "Sair") == JOptionPane.YES_OPTION;
	}

}
