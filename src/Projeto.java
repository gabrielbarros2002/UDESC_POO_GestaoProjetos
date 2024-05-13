import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

public class Projeto {
	
	private String titulo;
	private String cliente;
	private ArrayList<Recurso> listaDeRecursos = new ArrayList<>();
	private ArrayList<Pessoa> listaDePessoas = new ArrayList<>();
	private LocalDate dataInicial;
	private LocalDate dataFinal;
	private ArrayList<Tarefa> listaDeTarefas = new ArrayList<>();
	private ArrayList<TarefaAlocada> listaDeTarefasAlocadas = new ArrayList<>();
	private int status;
	
	Projeto (String titulo, String cliente, LocalDate dataInicial, LocalDate dataFinal){
		this.titulo = titulo;
		this.cliente = cliente;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.status = 1;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setArea(String cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Recurso> getListaDeRecursos() {
		return listaDeRecursos;
	}

	public void setListaDeRecursos(ArrayList<Recurso> listaDeRecursos) {
		this.listaDeRecursos = listaDeRecursos;
	}

	public ArrayList<Pessoa> getListaDePessoas() {
		return listaDePessoas;
	}

	public void setListaDePessoas(ArrayList<Pessoa> listaDePessoas) {
		this.listaDePessoas = listaDePessoas;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}

	public ArrayList<Tarefa> getListaDeTarefas() {
		return listaDeTarefas;
	}

	public void setListaDeTarefas(ArrayList<Tarefa> listaDeTarefas) {
		this.listaDeTarefas = listaDeTarefas;
	}

	public ArrayList<TarefaAlocada> getListaDeTarefasAlocadas() {
		return listaDeTarefasAlocadas;
	}

	public void setListaDeTarefasAlocadas(ArrayList<TarefaAlocada> listaDeTarefasAlocadas) {
		this.listaDeTarefasAlocadas = listaDeTarefasAlocadas;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void adicionarRecurso(Recurso recurso) {
		this.listaDeRecursos.add(recurso);
	}

	public void excluirRecurso(Recurso recurso) {
		this.listaDeRecursos.remove(recurso);
	}
	
	public void adicionarPessoa(Pessoa pessoa) {
		this.listaDePessoas.add(pessoa);
	}
	
	public void adicionarTarefa(Tarefa tarefa) {
		this.listaDeTarefas.add(tarefa);
	}


	public void alocarTarefa(Pessoa pessoa, Tarefa tarefa) {
		TarefaAlocada tarefaDesejada = null;
		for (TarefaAlocada tarefaAlocada : listaDeTarefasAlocadas) {
			if (tarefaAlocada.getTarefa() == tarefa) {
				tarefaDesejada = tarefaAlocada;
				break;
			}
		}

		List<Pessoa> pessoaList = new ArrayList<>();
		if (tarefaDesejada != null) {
			tarefaDesejada.getPessoaList().add(pessoa);
		} else {
			pessoaList.add(pessoa);
			TarefaAlocada tarefaAlocada = new TarefaAlocada(pessoaList, tarefa);
			this.listaDeTarefasAlocadas.add(tarefaAlocada);
		}
	}

	public JComboBox<String> retornarListaTarefas() {
		JComboBox<String> lista = new JComboBox<>();

		if(listaDeTarefas.isEmpty()) {
			return null;
		}
		for(Tarefa t : listaDeTarefas) {
			lista.addItem(t.getNome());
		}

		return lista;
	}

	public JComboBox<String> retornarListaPessoas() {
		JComboBox<String> lista = new JComboBox<>();

		if(listaDePessoas.isEmpty()) {
			return null;
		}
		for(Pessoa p : this.listaDePessoas) {
			lista.addItem(p.getNome());
		}

		return lista;
	}

	public JComboBox<String> retornarListaRecursos() {
		JComboBox<String> lista = new JComboBox<>();

		if(listaDeRecursos.isEmpty()) {
			return null;
		}
		for(Recurso recurso : listaDeRecursos) {
			lista.addItem(recurso.getNome());
		}

		return lista;
	}

	public Tarefa retornarTarefa(int posicaoTarefa) {
		return this.listaDeTarefas.get(posicaoTarefa);
	}
	
	public Pessoa retornarPessoa(int posicaoPessoa) {
		return this.listaDePessoas.get(posicaoPessoa);
	}

	public Recurso retornarRecurso(int posicaoRecurso) {
		return this.listaDeRecursos.get(posicaoRecurso);
	}

}
