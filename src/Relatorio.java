import java.sql.Date;

public abstract class Relatorio {
	
	protected String dataSolicitacao;
	protected Pessoa solicitante;
	
	Relatorio (String dataSolicitacao, Pessoa pessoa){
		this.dataSolicitacao=dataSolicitacao;
		this.solicitante=pessoa;
	}
	
	public String getDataSolicitacao() {
		return dataSolicitacao;
	}
	
	public Pessoa getSolicitante() {
		return solicitante;
	}	

}
