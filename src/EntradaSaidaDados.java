import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class EntradaSaidaDados {

	public static final String MENSAGEM_DATA_INVALIDA = "Formato de data inválido! Por favor, insira no formato DD/MM/AAAA.";

	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	// MÉTODOS DE RETORNO

	// Texto
	public static String retornarTexto(String mensagem, String titulo) {
		return JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
	}

	// Inteiro
	public static Integer retornarInteiro(String mensagem, String titulo) {
		String retorno = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
		if (retorno == null) {
			return null;
		}
		if (retorno.isEmpty()) {
			return -1;
		}
		return Integer.parseInt(retorno);
	}

	// Real
	public static Double retornarReal(String mensagem, String titulo) {
		String retorno = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
		if (retorno == null) {
			return null;
		}
		return Double.parseDouble(retorno);
	}

	// Confirmação
	public static int retornarConfirmacao(String mensagem, String titulo) {
		return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}

	//Opção
	public static Integer retornarOpcao(String mensagem, String titulo, JComboBox<String> lista) {
		Object[] message = {mensagem, lista};

		int opcao = JOptionPane.showOptionDialog(null,
				message,
				titulo,
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE,
				null,
				null,
				null);

		if (opcao == JOptionPane.OK_OPTION) {
			int selectedIndex = lista.getSelectedIndex();
			if (selectedIndex == -1) {
				return null;
			} else {
				return selectedIndex;
			}
		} else {
			return null;
		}
	}

	// Data
	public static LocalDate retornarData(String mensagem, String titulo) {
		LocalDate date = null;
		boolean dataValida = false;

		while (!dataValida) {
			String userInput = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);

			if (userInput == null) {
				return null;
			}

			try {
				date = LocalDate.parse(userInput, dateFormatter);
				dataValida = true;
			} catch (DateTimeParseException e) {
				JOptionPane.showMessageDialog(null, MENSAGEM_DATA_INVALIDA);
			}
		}

		return date;
	}

	// MÉTODOS DE PRINT

	// Mensagem
	public static void mostrarMensagem(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	// Relatório
	public static void mostrarRelatorio(String dadosRelatorio, String tipoRelatorio) {
		JOptionPane.showMessageDialog(null, dadosRelatorio, "Relatório " + tipoRelatorio, JOptionPane.PLAIN_MESSAGE);
	}

	// MÉTODOS DE ESCOLHA

	// Projeto
	public static Projeto escolherProjeto(String titulo, JComboBox<String> listaDeProjetos) {
		Integer posicaoProjeto = retornarOpcao("Selecione o projeto:", titulo, listaDeProjetos);
		if(posicaoProjeto == null) {
			return null;
		}
		return GestaoProjetos.retornarProjeto(posicaoProjeto);
	}

	// Tarefa
	public static Integer escolherTarefa(String titulo, JComboBox<String> listaDeTarefas) {
		return retornarOpcao("Selecione a tarefa:", titulo, listaDeTarefas);
	}

	// Pessoa
	public static Integer escolherPessoa(String titulo, JComboBox<String> listaDePessoas) {
		return retornarOpcao("Selecione a pessoa:", titulo, listaDePessoas);
	}

	// Recurso
	public static Recurso escolherRecurso(String titulo, JComboBox<String> listaDeRecursos, Projeto projeto) {
		Integer posicaoRecurso = retornarOpcao("Selecione o recurso:", titulo, listaDeRecursos);
		if(posicaoRecurso == null) {
			return null;
		}
		return projeto.retornarRecurso(posicaoRecurso);
	}

	// Prioridade
	public static Integer escolherPrioridade(String titulo) {
		String[] lista = {"Nível 1", "Nível 2", "Nível 3", "Nível 4", "Nível 5"};
		JComboBox<String> listaDePrioridade = new JComboBox<>(lista);

		return retornarOpcao("Selecione a prioridade:", titulo, listaDePrioridade);
	}

	// Status de projeto
	public static Integer escolherStatusProjeto(String titulo) {
		String[] lista = {"1 - Em andamento", "2 - Finalizado", "3 - Cancelado"};
		JComboBox<String> listaStatus = new JComboBox<>(lista);

		Integer opcao = retornarOpcao("Selecione o status do projeto:", titulo, listaStatus);
		if(opcao == null) {
			return null;
		}
		return opcao + 1;
	}

	// Status de tarefa
	public static Integer escolherStatusTarefa(String titulo) {
		String[] lista = {"1 - Em aberto", "2 - Encerrada"};
		JComboBox<String> listaStatus = new JComboBox<>(lista);

		Integer opcao = retornarOpcao("Selecione o status da tarefa:", titulo, listaStatus);
		if(opcao == null) {
			return null;
		}
		return opcao + 1;
	}

}
