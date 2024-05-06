import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class EntradaSaidaDados {
	
	
	public static String retornarTexto(String mensagem, String titulo) {
		return JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static int retornarInteiro(String mensagem, String titulo) {
		return Integer.parseInt(JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE));
	}
	
	public static double retornarReal(String mensagem, String titulo) {
		return Double.parseDouble(JOptionPane.showInputDialog(null, mensagem, titulo, JOptionPane.QUESTION_MESSAGE));
	}
	
	public static int escolherProjeto(JComboBox<String> listaDeProjetos) {
		JOptionPane.showInternalMessageDialog(null, listaDeProjetos, "Lista de projetos", JOptionPane.INFORMATION_MESSAGE, null);
		return listaDeProjetos.getSelectedIndex();
	}

	public static int  escolherTarefa(JComboBox<String> listaDeTarefas) {		
		JOptionPane.showMessageDialog(null, listaDeTarefas);
		return listaDeTarefas.getSelectedIndex();
	}
	
	public static int  escolherPessoa(JComboBox<String> listaDePessoas) {		
		JOptionPane.showMessageDialog(null, listaDePessoas);
		return listaDePessoas.getSelectedIndex();
	}

	public static void mostrarMensagem(String mensagem, String titulo) {
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
		
	}

	public static void mostrarRelatorio(String dadosRelatorio) {
		JOptionPane.showMessageDialog(null, "Relatório\n"+dadosRelatorio);		
	}
	

}
