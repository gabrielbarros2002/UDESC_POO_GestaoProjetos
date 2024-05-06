import java.sql.Date;

public class RelatorioGeral extends Relatorio{
	
	RelatorioGeral(String dataSolicitacao, Pessoa pessoa) {
		super(dataSolicitacao, pessoa);		
	}

	
	public String gerarRelatorioDeTarefasAlocadas(Projeto projeto) {
		String dados = "Tarefas alocadas do projeto "+projeto.getTitulo();
		for(TarefaAlocada t : projeto.getListaDeTarefasAlocadas()) {
			dados+="\nNome da tarefa "+t.getTarefa().getNome();
			dados+="\nPessoa alocada para a tarefa "+t.getPessoa().getNome();			
		}
		
		return dados;
	}
	
	public String gerarRelatorioDadosGerais(Projeto projeto) {
		String dados = "Dados do projeto " + projeto.getTitulo();
		dados += "\n Data inicial " + projeto.getDataInicial();
		dados += "\n Data final " + projeto.getDataFinal();
		dados += "\n Cliente " + projeto.getCliente();
		dados += "\n Status do projeto " + projeto.getStatus();
		return dados;
	}
	
	

}
