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
	private final int ID=0;
	private final int NOME=1;
	private final int CURSO=2;
	private final int PERIODO=3;
	private final int CARGA_HORARIA=4;
	private final int NUMERO_DE_VAGAS=5;
	
	private final String colunas[] = {"iD", "Nome", "Curso","Periodo","C.Horaria","N.Vagas"};
	private final List<Disciplina> dados;//usamos como dados uma lista genérica
	
	public DisciplinaTableModel(List<Disciplina> dados) {
		//seto os dados no construtor
		this.dados=dados;
	}
	
	@Override
	public int getColumnCount() {
		//retorna o total de colunas
		return colunas.length;
	}

	@Override
	public int getRowCount() {
		//retorna o total de linhas na tabela
		return dados.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		//retorna o tipo de dado, para cada coluna
		switch (columnIndex) {
		case ID:
			return int.class;
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
		Disciplina disciplina=dados.get(rowIndex);
		
		//retorna o valor da coluna
		switch (columnIndex) {
		case ID:
			return disciplina.getId();
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
		return dados.get(rowIndex);
	}
	
	/**
	 * retorna o indice do objeto
	 * @param disciplina
	 * @return
	 */
	public int indexOf(Disciplina disciplina) {
		return dados.indexOf(disciplina);
	}
	
	/**
	 * add um empregado á lista
	 * @param disciplina
	 */
	public void onAdd(Disciplina disciplina) {
		dados.add(disciplina);
		fireTableRowsInserted(indexOf(disciplina), indexOf(disciplina));
	}
	
	/**
	 * add uma lista de empregados
	 * @param dadosIn
	 */
	public void onAddAll(List<Disciplina> dadosIn) {
		dados.addAll(dadosIn);
		fireTableDataChanged();
	}
	
	/**
	 * remove um registro da lista, através do indice
	 * @param rowIndex
	 */
	public void onRemove(int rowIndex) {
		dados.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	/**
	 * remove um registro da lista, através do objeto
	 * @param disciplina
	 */
	public void onRemove(Disciplina disciplina) {
		dados.remove(disciplina);
		fireTableRowsDeleted(indexOf(disciplina), indexOf(disciplina));
	}
	
	/**
	 * remove todos registros da lista
	 */
	public void onRemoveAll() {
		dados.clear();
		fireTableDataChanged();
	}
	
}
