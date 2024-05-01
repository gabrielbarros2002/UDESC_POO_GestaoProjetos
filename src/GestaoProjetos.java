import java.util.ArrayList;

import javax.swing.JComboBox;

public class GestaoProjetos {
	
	private static ArrayList<Projeto> listaDeProjetos = new ArrayList<Projeto>();
	
	public static void adicionarProjeto(Projeto projeto) {
		listaDeProjetos.add(projeto);
	}
	
	public static ArrayList<Projeto> getListaDeProjetos(){
		return listaDeProjetos;
	}
	
	
	public static JComboBox retornarListaProjetos() {
		JComboBox<String> lista = new JComboBox<String>();
		if(listaDeProjetos.isEmpty()==false) {				
			for(Projeto p : listaDeProjetos) {			
				lista.addItem(p.getTitulo());
			}
		
		}
		
		return lista;			
	}
	
	public static Projeto retornarProjeto(int posicaoProjetoEscolhido) {
		return listaDeProjetos.get(posicaoProjetoEscolhido);
	}

}
