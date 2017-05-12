package com.rafinhaa.util;

import java.sql.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.rafinhaa.model.Disciplina;
import com.rafinhaa.model.Periodo;

/**
 * classe Table Model "modelo de tabela" p/entidade Empregado
 * @author Roberto Silva
 */
public class DisciplinaTableModel extends AbstractTableModel{
	
	//constantes p/identificar colunas
	private final int NOME=0;
	private final int CURSO=1;
	private final int PERIODO=2;
	private final int CARGA_HORARIA=3;
	private final int NUMERO_DE_VAGAS=4;
	
	private final String colunas[] = {"Nome", "Curso","Periodo","C.Horaria","N.Vagas"};
	private final List<Disciplina> linhas;//usamos como linhas uma lista genérica
	
	public DisciplinaTableModel(List<Disciplina> dados) {
		//seto os dados no construtor
		this.linhas=dados;
	}
	
	@Override
	public int getColumnCount() {
		//retorna o total de colunas
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		//retorna o total de linhas na tabela
		return linhas.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		//retorna o tipo de dado, para cada coluna
		switch (columnIndex) {
		case NOME:
			return String.class;
		case CURSO:
			return String.class;
		case PERIODO:
			return Periodo.class;
		case CARGA_HORARIA:
			return float.class;
		case NUMERO_DE_VAGAS:
			return int.class;
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//retorna o valor conforme a coluna e linha
		
				//pega o dados corrente da linha
		Disciplina disciplina=linhas.get(rowIndex);
		
		//retorna o valor da coluna
		switch (columnIndex) {
		case NOME:
			return disciplina.getNomeDaDiciplina();
		case CURSO:
			return disciplina.getCursoQueElaPertence();
		case PERIODO:
			return disciplina.getPeriodo();
		case CARGA_HORARIA:
			return disciplina.getCargaHoraria();
		case NUMERO_DE_VAGAS:
			return disciplina.getNumeroDeVagas();
		default:
			throw new IndexOutOfBoundsException("Coluna Inválida!!!");
		}
	}
	
			
	/*Métodos abaixo são para manipulação de dados*/
	
	/**
	 * retorna o valor da linha indicada
	 * @param rowIndex
	 * @return
	 */
	public Disciplina getValue(int rowIndex){
		return linhas.get(rowIndex);
	}
	
	/**
	 * retorna o indice do objeto
	 * @param disciplina
	 * @return
	 */
	public int indexOf(Disciplina disciplina) {
		return linhas.indexOf(disciplina);
	}
	
	/**
	 * add um empregado á lista
	 * @param disciplina
	 */
	public void onAdd(Disciplina disciplina) {
		linhas.add(disciplina);
		fireTableRowsInserted(indexOf(disciplina), indexOf(disciplina));
	}
	
	/**
	 * add uma lista de empregados
	 * @param dadosIn
	 */
	public void onAddAll(List<Disciplina> dadosIn) {
		linhas.addAll(dadosIn);
		// Pega a quantidade de registros e subtrai 1 para
	    // achar o último índice. A subtração é necessária
	    // porque os índices começam em zero.
	    //int ultimoIndice = getRowCount() - 1;
		fireTableDataChanged();
	}
	
	/**
	 * Adiciona uma lista de disciplinas no final da lista, segundo modo testar
	 * @param disciplinas
	 */
	public void addListaDeDisciplinas(List<Disciplina> disciplinas) {
		// Pega o tamanho antigo da tabela, que servirá
		// como índice para o primeiro dos novos registros
		int indice = getRowCount();
		
		// Adiciona os registros.
		linhas.addAll(disciplinas);
		
		// Notifica a mudança.
		fireTableRowsInserted(indice, indice + disciplinas.size());
	}
	
	/**
	 * remove um registro da lista, através do indice
	 * @param rowIndex
	 */
	public void onRemove(int rowIndex) {
		linhas.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	/**
	 * remove um registro da lista, através do objeto
	 * @param disciplina
	 */
	public void onRemove(Disciplina disciplina) {
		linhas.remove(disciplina);
		fireTableRowsDeleted(indexOf(disciplina), indexOf(disciplina));
	}
	
	/**
	 * remove todos registros da lista
	 */
	public void onRemoveAll() {
		linhas.clear();
		fireTableDataChanged();
	}
}

