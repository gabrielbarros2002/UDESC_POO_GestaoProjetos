import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Menu {

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
				"Sair"};
		String resposta;

		do {
			resposta = (String) JOptionPane.showInputDialog(
					null,
					"Selecione uma opção",
					"Gestão de Projetos",
					JOptionPane.INFORMATION_MESSAGE,
					null,
					lista,
					lista[0]);

			if (resposta != null) {
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
						adicionarRecurso();
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
				}
			}
		} while (resposta != null && !resposta.equals("Sair"));
	}

	private static void gerarRelatorio() {
		
		if(GestaoProjetos.retornarListaProjetos() != null) {
			
			int tipo = EntradaSaidaDados.retornarInteiro("Informe o tipo de relatório desejado:"
					+ "\n 1 - Dados gerais do projeto"
					+ "\n 2 - Tarefas alocadas de um projeto"
					+ "\n 3 - Pessoas atribuídas a um projeto"
					+ "\n 4 - Recursos alocados de um projeto"
					+ "\n 5 - Tarefas finalizadas de um projeto",
					"Relatório");
				
				int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
				Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
				if(projetoEscolhido.getListaDePessoas().isEmpty()) {
					int resposta = JOptionPane.showConfirmDialog(null,
							"Não há pessoas cadastradas.\nDeseja cadastrar uma pessoa para este projeto?", "Aviso", JOptionPane.YES_NO_OPTION);

					if(resposta == JOptionPane.YES_NO_OPTION) {
						adicionarPessoa(projetoEscolhido);
					} else {
						exibirMenu();
					}
				}
				int posicaoSolicitante = EntradaSaidaDados.escolherPessoa(projetoEscolhido.retornarListaPessoas(), "Escolha o solicitante do relatório", "Escolher solicitante");
				Pessoa solicitante = projetoEscolhido.retornarPessoa(posicaoSolicitante);	
				LocalDateTime dataSolcitacao = LocalDateTime.now();
				RelatorioGeral relatorio = new RelatorioGeral(dataSolcitacao, solicitante);
				
				switch (tipo) {			
				case 1:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDadosGerais(projetoEscolhido));
					break;
				case 2:					
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeTarefasAlocadas(projetoEscolhido));
					break;
				case 3:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDePessoasNoProjeto(projetoEscolhido));
					break;
				case 4:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeRecursosNoProjeto(projetoEscolhido));
					break;
				case 5:
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeTarefasFinalizadasDoProjeto(projetoEscolhido));
					break;
				}
		} else {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		}
		
		
	}

	private static void adicionarRecurso() {
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto= EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			String nome = EntradaSaidaDados.retornarTexto("Informe o nome do recurso", "Recurso");
			double valor = EntradaSaidaDados.retornarReal("Informe o preço do recurso", "Recurso");
			Recurso recurso = new Recurso(nome, valor);
			projetoEscolhido.adicionarRecurso(recurso);
		}
	}

	private static void alocarTarefa() {
		Pessoa pessoaEscolhida = null;
		Tarefa tarefaEscolhida = null;
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			if(projetoEscolhido.retornarListaTarefas() == null) {
				int resposta = JOptionPane.showConfirmDialog(null,
						"Não há tarefas cadastradas.\nDeseja cadastrar uma tarefa para este projeto?", "Aviso", JOptionPane.YES_NO_OPTION);

				if(resposta == JOptionPane.YES_NO_OPTION) {
					adicionarTarefa(projetoEscolhido);
				} else {
					return;
				}
			} else {
				int posicaoTarefa = EntradaSaidaDados.escolherTarefa(projetoEscolhido.retornarListaTarefas());
				tarefaEscolhida = projetoEscolhido.retornarTarefa(posicaoTarefa);
			}

			if(projetoEscolhido.retornarListaPessoas() == null) {
				int resposta = JOptionPane.showConfirmDialog(null,
						"Não há pessoas cadastradas.\nDeseja cadastrar uma pessoa para este projeto?", "Aviso", JOptionPane.YES_NO_OPTION);

				if(resposta == JOptionPane.YES_NO_OPTION) {
					adicionarPessoa(projetoEscolhido);
				} else {
					return;
				}
			} else {
				int posicaoPessoa = EntradaSaidaDados.escolherPessoa(projetoEscolhido.retornarListaPessoas(),"Escolha a pessoa", "Escolher pessoa");
				pessoaEscolhida = projetoEscolhido.retornarPessoa(posicaoPessoa);
			}
			projetoEscolhido.alocarTarefa(pessoaEscolhida, tarefaEscolhida);
		}
	}

	private static void adicionarPessoa() {
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto= EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			String nome = EntradaSaidaDados.retornarTexto("Informe o nome da pessoa", "Adicionar Pessoa");
			String sobrenome = EntradaSaidaDados.retornarTexto("Informe o sobrenome da pessoa", "Adicionar Pessoa");
			Cargo cargo = new Cargo(EntradaSaidaDados.retornarTexto("Informe o cargo da pessoa no projeto", "Adicionar Pessoa"));
			Pessoa p = new Pessoa(nome, sobrenome, cargo);
			projetoEscolhido.adicionarPessoa(p);
		}
	}

	private static void adicionarPessoa(Projeto projeto) {
		String nome = EntradaSaidaDados.retornarTexto("Informe o nome da pessoa", "Adicionar Pessoa");
		String sobrenome = EntradaSaidaDados.retornarTexto("Informe o sobrenome da pessoa", "Adicionar Pessoa");
		Cargo cargo = new Cargo(EntradaSaidaDados.retornarTexto("Informe o cargo da pessoa no projeto", "Adicionar Pessoa"));
		Pessoa pessoa = new Pessoa(nome, sobrenome, cargo);
		projeto.adicionarPessoa(pessoa);
	}

	private static void adicionarTarefa() {			
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			String nome = EntradaSaidaDados.retornarTexto("Informe o nome da tarefa", "Adicionar Tarefa");
			LocalDate prazo;
			do {
				prazo = EntradaSaidaDados.retornarData("Informe o prazo da tarefa (DD/MM/AAAA)", "Adicionar Tarefa");
			} while(prazo == null);
			int prioridade = EntradaSaidaDados.escolherPrioridade();
			Tarefa tarefa = new Tarefa(nome, prazo, prioridade);
			projetoEscolhido.adicionarTarefa(tarefa);
		}
	}

	private static void adicionarTarefa(Projeto projeto) {
		String nome = EntradaSaidaDados.retornarTexto("Informe o nome da tarefa", "Adicionar Tarefa");
		LocalDate prazo;
		do {
			prazo = EntradaSaidaDados.retornarData("Informe o prazo da tarefa (DD/MM/AAAA)", "Adicionar Tarefa");
		} while(prazo == null);
		int prioridade = EntradaSaidaDados.escolherPrioridade();
		Tarefa tarefa = new Tarefa(nome, prazo, prioridade);
		projeto.adicionarTarefa(tarefa);
	}

	private static void criarProjeto() {
		String titulo = EntradaSaidaDados.retornarTexto("Informe o título do projeto", "Adicionar Projeto");
		if(titulo == null){
			return;
		}
		String cliente = EntradaSaidaDados.retornarTexto("Informe o cliente do projeto", "Adicionar Projeto");
		if(cliente == null) {
			return;
		}
		LocalDate dataInicial;
		LocalDate dataFinal;
		do {
			 dataInicial = EntradaSaidaDados.retornarData("Informe a data inicial do projeto (DD/MM/AAAA)", "Adicionar Projeto");
		} while(dataInicial == null);
		do {
			dataFinal = EntradaSaidaDados.retornarData("Informe a data final do projeto (DD/MM/AAAA)", "Adicionar Projeto");
		} while(dataFinal == null);
		Projeto p = new Projeto(titulo, cliente, dataInicial, dataFinal);
		p.setStatus(1); // Status 1 - Em andamento
		GestaoProjetos.adicionarProjeto(p);
	}

	private static void excluirRecurso() {
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto= EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			if(projetoEscolhido.retornarListaRecursos() == null) {
				EntradaSaidaDados.mostrarMensagem("Não há recursos para excluir!", "Aviso");
				return;
			}
			int posicaoRecurso = EntradaSaidaDados.escolherRecurso(projetoEscolhido.retornarListaRecursos(), "Escolher recurso");
			Recurso recursoEscolhido = projetoEscolhido.retornarRecurso(posicaoRecurso);
			projetoEscolhido.excluirRecurso(recursoEscolhido);
			EntradaSaidaDados.mostrarMensagem("Recurso excluído com sucesso", "Sucesso");
		}
	}

	private static void alterarStatusProjeto() {
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto= EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			int statusEscolhido = EntradaSaidaDados.escolherStatusProjeto();
			projetoEscolhido.setStatus(statusEscolhido);
		}
	}

	private static void alterarDataFinalProjeto() {
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto= EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			LocalDate novaDataFinal = EntradaSaidaDados.retornarData("Informe a nova data", "Alterar data final do projeto");
			projetoEscolhido.setDataFinal(novaDataFinal);
		}
	}

	private static void alterarStatusTarefa() {
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			if(projetoEscolhido.retornarListaTarefas() == null) {
				int resposta = JOptionPane.showConfirmDialog(null,
						"Não há tarefas cadastradas.\nDeseja cadastrar uma tarefa para este projeto?", "Aviso", JOptionPane.YES_NO_OPTION);

				if(resposta == JOptionPane.YES_NO_OPTION) {
					adicionarTarefa(projetoEscolhido);
				} else {
                    return;
                }
			} else {
				int posicaoTarefa = EntradaSaidaDados.escolherTarefa(projetoEscolhido.retornarListaTarefas());
				Tarefa tarefaEscolhida = projetoEscolhido.retornarTarefa(posicaoTarefa);
				int statusEscolhido = EntradaSaidaDados.escolherStatusTarefa();
				tarefaEscolhida.setStatus(statusEscolhido);
			}
		}
	}

}
