import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class EntradaSaidaDados {
	
	
	public static String retornarTexto(String mensagem) {		
		return JOptionPane.showInputDialog(mensagem);
	}
	
	public static int retornarInteiro(String mensagem) {		
		return Integer.parseInt(JOptionPane.showInputDialog(mensagem));
	}
	
	public static double retornarReal(String mensagem) {		
		return Double.parseDouble(JOptionPane.showInputDialog(mensagem));
	}
	
	public static int  escolherProjeto(JComboBox<String> listaDeProjetos) {		
		JOptionPane.showInternalMessageDialog(null, listaDeProjetos, "Lista de projetos", 1, null);
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

	public static void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
		
	}

	public static void mostrarRelatorio(String dadosRelatorio) {
		JOptionPane.showMessageDialog(null, "Relatório\n"+dadosRelatorio);		
	}
	

}
