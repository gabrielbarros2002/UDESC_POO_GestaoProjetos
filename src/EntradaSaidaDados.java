import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntradaSaidaDados {

	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static String retornarTexto(String mensagem, String titulo) {
		String texto = JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);

		if(texto == null) {
			Menu.exibirMenu();
		}
		return texto;
	}
	
	public static int retornarInteiro(String mensagem, String titulo) {
		return Integer.parseInt(JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE));
	}
	
	public static double retornarReal(String mensagem, String titulo) {
		return Double.parseDouble(JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE));
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
		return listaDeProjetos.getSelectedIndex();
	}

	public static int  escolherTarefa(JComboBox<String> listaDeTarefas) {
		JOptionPane.showMessageDialog(null, listaDeTarefas);
		return listaDeTarefas.getSelectedIndex();
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
		return listaDePessoas.getSelectedIndex();
	}

	public static int escolherRecurso(JComboBox<String> listaDeRecursos) {
		JOptionPane.showMessageDialog(null, listaDeRecursos);
		return listaDeRecursos.getSelectedIndex();
	}

	public static void mostrarMensagem(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
		
	}

	public static void mostrarRelatorio(String dadosRelatorio) {
		JOptionPane.showMessageDialog(null, "Relatório\n" + dadosRelatorio);
	}
	

}
