import java.time.LocalDateTime;

public abstract class Relatorio {
	
	protected LocalDateTime dataSolicitacao;
	protected Pessoa solicitante;
	
	Relatorio (LocalDateTime dataSolicitacao, Pessoa pessoa){
		this.dataSolicitacao=dataSolicitacao;
		this.solicitante=pessoa;
	}
	
	public LocalDateTime getDataSolicitacao() {
		return dataSolicitacao;
	}
	
	public Pessoa getSolicitante() {
		return solicitante;
	}

}
