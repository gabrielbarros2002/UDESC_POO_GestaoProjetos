import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Menu {
	public static void exibirMenu() {
		String[] lista = {"Criar projeto", "Adicionar tarefa", "Adicionar pessoa ao projeto", "Alocar tarefa", "Alocar recurso", "Gerar relatório", "Sair"};
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
					case "Gerar relatório":
						gerarRelatorio();
						break;
				}
			}
		} while (resposta != null && !resposta.equals("Sair"));
	}

	private static void gerarRelatorio() {
		
		if(GestaoProjetos.retornarListaProjetos()!=null) {
			
			int tipo = EntradaSaidaDados.retornarInteiro("Informe o tipo de relatório desejado:"
					+ "\n 1 - Dados gerais do projeto"
					+ "\n 2 - Tarefas alocadas de um projeto", "Relatório");
				
				int posicaoProjeto= EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
				Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
				int posicaoSolicitante= EntradaSaidaDados.escolherPessoa(projetoEscolhido.retornarListaPessoas());
				Pessoa solicitante = projetoEscolhido.retornarPessoa(posicaoSolicitante);	
				LocalDate dataSolcitacao = LocalDate.now();		
				String data = dataSolcitacao.toString();
				RelatorioGeral relatorio = new RelatorioGeral(data, solicitante);
				
				switch (tipo) {			
				case 1:			
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDadosGerais(projetoEscolhido));
					break;
				case 2:					
					EntradaSaidaDados.mostrarRelatorio(relatorio.gerarRelatorioDeTarefasAlocadas(projetoEscolhido));
					break;	
				}
		} else {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		}
		
		
	}

	private static void adicionarRecurso() {
		if(GestaoProjetos.retornarListaProjetos()==null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		}else {	
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
				EntradaSaidaDados.mostrarMensagem("Adicione tarefas ao projeto", "Aviso");
				adicionarTarefa();
			}else {
				int posicaoTarefa = EntradaSaidaDados.escolherTarefa(projetoEscolhido.retornarListaTarefas());
				 tarefaEscolhida = projetoEscolhido.retornarTarefa(posicaoTarefa);
			}
			
			if(projetoEscolhido.retornarListaPessoas() == null) {
				EntradaSaidaDados.mostrarMensagem("Adicione pessoas ao projeto", "Aviso");
				adicionarPessoa();
			}else {
				int posicaoPessoa = EntradaSaidaDados.escolherPessoa(projetoEscolhido.retornarListaPessoas());
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
			String nome = EntradaSaidaDados.retornarTexto("Informe o nome da pessoa", "Adicionar Pessoa");
			String sobrenome = EntradaSaidaDados.retornarTexto("Informe o sobrenome da pessoa", "Adicionar Pessoa");
			Cargo cargo = new Cargo(EntradaSaidaDados.retornarTexto("Informe o cargo da pessoa no projeto", "Adicionar Pessoa"));
			Pessoa p = new Pessoa(nome, sobrenome, cargo);	
			int posicaoProjeto= EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			projetoEscolhido.adicionarPessoa(p);
		}
	}

	private static void adicionarTarefa() {			
		if(GestaoProjetos.retornarListaProjetos() == null) {
			EntradaSaidaDados.mostrarMensagem("Adicione um projeto", "Aviso");
			criarProjeto();
		} else {
			int posicaoProjeto = EntradaSaidaDados.escolherProjeto(GestaoProjetos.retornarListaProjetos());
			Projeto projetoEscolhido = GestaoProjetos.retornarProjeto(posicaoProjeto);
			String nome = EntradaSaidaDados.retornarTexto("Informe o nome da tarefa", "Adicionar Tarefa");
			String prazo = EntradaSaidaDados.retornarTexto("Informe o prazo da tarefa", "Adicionar Tarefa");
			int prioridade = EntradaSaidaDados.retornarInteiro("Informe a prioridade da tarefa", "Adicionar Tarefa");
			Tarefa t = new Tarefa(nome, prazo, prioridade);
			projetoEscolhido.adicionarTarefa(t);
		}
	}

	private static void criarProjeto() {
		String titulo = EntradaSaidaDados.retornarTexto("Informe o título do projeto", "Adicionar Projeto");
		String cliente = EntradaSaidaDados.retornarTexto("Informe o cliente do projeto", "Adicionar Projeto");
		String dataInicial = EntradaSaidaDados.retornarTexto("Informe a data inicial do projeto", "Adicionar Projeto");
		String dataFinal = EntradaSaidaDados.retornarTexto("Informe a data final do projeto", "Adicionar Projeto");
		Projeto p = new Projeto(titulo, cliente, dataInicial, dataFinal);
		GestaoProjetos.adicionarProjeto(p);				
	}

}
