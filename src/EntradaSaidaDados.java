import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntradaSaidaDados {

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
		String userInput = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);

		try {
			date = LocalDate.parse(userInput, dateFormatter);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Formato de data inválido!");
			e.printStackTrace();
		}
		return date;
	}

	public static int escolherProjeto(JComboBox<String> listaDeProjetos) {
		JOptionPane.showInternalMessageDialog(null, listaDeProjetos, "Lista de projetos", JOptionPane.INFORMATION_MESSAGE, null);
		int selectedIndex = listaDeProjetos.getSelectedIndex();
		if (selectedIndex == -1) {
			return -1;
		} else {
			return selectedIndex;
		}
	}

	public static int  escolherTarefa(JComboBox<String> listaDeTarefas) {
		JOptionPane.showMessageDialog(null, listaDeTarefas);
		int selectedIndex = listaDeTarefas.getSelectedIndex();
		if (selectedIndex == -1) {
			return -1;
		} else {
			return selectedIndex;
		}
	}

	public static int escolherPrioridade() {
		JComboBox<String> listaDePrioridades = new JComboBox<>();
		for(int i = 1; i <= 5; i++) {
			listaDePrioridades.addItem("Nível " + i);
		}
		JOptionPane.showMessageDialog(null, listaDePrioridades, "Adicionar Tarefa", JOptionPane.INFORMATION_MESSAGE);
		return listaDePrioridades.getSelectedIndex();
	}
	
	public static int  escolherPessoa(JComboBox<String> listaDePessoas) {
		JOptionPane.showMessageDialog(null, listaDePessoas);
		int selectedIndex = listaDePessoas.getSelectedIndex();
		if (selectedIndex == -1) {
			return -1;
		} else {
			return selectedIndex;
		}
	}

	public static int escolherRecurso(JComboBox<String> listaDeRecursos) {
		JOptionPane.showMessageDialog(null, listaDeRecursos);
		int selectedIndex = listaDeRecursos.getSelectedIndex();
		if (selectedIndex == -1) {
			// Retorna -1 para indicar que a operação foi cancelada
			return -1;
		} else {
			return selectedIndex;
		}
	}

	public static void mostrarMensagem(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void mostrarRelatorio(String dadosRelatorio) {
		JOptionPane.showMessageDialog(null, "Relatório\n" + dadosRelatorio);
	}

	public static int escolherStatusProjeto() {
		JComboBox<String> listaStatus = new JComboBox<>();
		listaStatus.addItem("1 - Em andamento");
		listaStatus.addItem("2 - Finalizado");
		JOptionPane.showMessageDialog(null, listaStatus, "Escolher status", JOptionPane.INFORMATION_MESSAGE);
		return listaStatus.getSelectedIndex();
	}

	public static int escolherStatusTarefa() {
		JComboBox<String> listaStatus = new JComboBox<>();
		listaStatus.addItem("1 - Em aberto");
		listaStatus.addItem("2 - Encerrada");
		JOptionPane.showMessageDialog(null, listaStatus, "Escolher status", JOptionPane.INFORMATION_MESSAGE);
		return listaStatus.getSelectedIndex();
	}
}
