import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class EntradaSaidaDados {

	public static final int FECHAR_OU_CANCELAR = -1;

	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static String retornarTexto(String mensagem, String titulo) {
		return JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
	}

	public static int retornarInteiro(String mensagem, String titulo) {
		String retorno = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
		if (retorno == null) {
			return -1;
		} else {
			return Integer.parseInt(retorno);
		}
	}

	public static double retornarReal(String mensagem, String titulo) {
		String retorno = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
		if (retorno == null) {
			// Retorna -1.0 para indicar que a operação foi cancelada
			return -1.0;
		} else {
			return Double.parseDouble(retorno);
		}
	}

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
				JOptionPane.showMessageDialog(null, "Formato de data inválido! Por favor, insira no formato DD/MM/AAAA.");
			}
		}

		return date;
	}

	public static int escolherProjeto(JComboBox<String> listaDeProjetos) {
		Object[] message = {"Selecione o projeto:", listaDeProjetos};
		JOptionPane.showInternalMessageDialog(null, message, "Lista de projetos", JOptionPane.INFORMATION_MESSAGE, null);
		int selectedIndex = listaDeProjetos.getSelectedIndex();
		if (selectedIndex == -1) {
			return -1;
		} else {
			return selectedIndex;
		}
	}

	public static int  escolherTarefa(JComboBox<String> listaDeTarefas) {
		Object[] message = {"Selecione a tarefa:", listaDeTarefas};

		JOptionPane.showMessageDialog(null, message, "Escolher tarefa", JOptionPane.INFORMATION_MESSAGE);
		int selectedIndex = listaDeTarefas.getSelectedIndex();
		if (selectedIndex == -1) {
			return -1;
		} else {
			return selectedIndex;
		}
	}

	public static int escolherPrioridade() {
		String[] listaDePrioridades = {"Nível 1", "Nível 2", "Nível 3", "Nível 4", "Nível 5"};

		String resposta = (String) JOptionPane.showInputDialog(
				null,
				"Selecione a prioridade:",
				"Adicionar Tarefa",
				JOptionPane.INFORMATION_MESSAGE,
				null,
				listaDePrioridades,
				listaDePrioridades[0] // Seleção padrão
		);

		List<String> lista = Arrays.asList(listaDePrioridades);

		// Retorna o índice da prioridade selecionada
		if (resposta != null) {
			return lista.indexOf(resposta);
		}

		return -1;
	}
	
	public static int escolherPessoa(JComboBox<String> listaDePessoas, String mensagem, String titulo) {
		Object[] message = {mensagem, listaDePessoas};

		JOptionPane.showMessageDialog(null, message, titulo, JOptionPane.INFORMATION_MESSAGE);
		int selectedIndex = listaDePessoas.getSelectedIndex();
		if (selectedIndex == -1) {
			return -1;
		} else {
			return selectedIndex;
		}
	}

	public static void mostrarMensagem(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public static int mostrarConfirmacao(String mensagem, String titulo) {
		return JOptionPane.showConfirmDialog(null, mensagem, titulo, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	}

	public static void mostrarRelatorio(String dadosRelatorio, String tipoRelatorio) {
		JOptionPane.showMessageDialog(null, dadosRelatorio, "Relatório " + tipoRelatorio, JOptionPane.INFORMATION_MESSAGE);
	}

	public static int escolherStatusProjeto() {
		JComboBox<String> listaStatus = new JComboBox<>();
		listaStatus.addItem("1 - Em andamento");
		listaStatus.addItem("2 - Finalizado");
		JOptionPane.showMessageDialog(null, listaStatus, "Escolher status", JOptionPane.INFORMATION_MESSAGE);
		return listaStatus.getSelectedIndex() + 1;
	}

	public static int escolherStatusTarefa() {
		JComboBox<String> listaStatus = new JComboBox<>();
		listaStatus.addItem("1 - Em aberto");
		listaStatus.addItem("2 - Encerrada");
		JOptionPane.showMessageDialog(null, listaStatus, "Escolher status", JOptionPane.INFORMATION_MESSAGE);
		return listaStatus.getSelectedIndex() + 1;
	}

	public static int escolherRecurso(JComboBox<String> listaDeRecursos, String titulo) {
		JOptionPane.showMessageDialog(null, listaDeRecursos, titulo, JOptionPane.INFORMATION_MESSAGE);
		int selectedIndex = listaDeRecursos.getSelectedIndex();
		if (selectedIndex == -1) {
			return -1;
		} else {
			return selectedIndex;
		}
	}
}
