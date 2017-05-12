package com.rafinhaa.builders;

import com.rafinhaa.model.Disciplina;
import com.rafinhaa.model.Periodo;

public class DisciplinaBuilder {

	private int Id;
	private String nome;
	private float cargaHoraria;
	private String cursoQueElaPertence;
	private int vagas;
	private Periodo periodo;
	
	public DisciplinaBuilder() {
	}
	
	public DisciplinaBuilder comId(int id){
		this.Id = id;
		return this;
	}
	public DisciplinaBuilder comNome(String nome){
		this.nome = nome;
		return this;
	}
	public DisciplinaBuilder comCurso(String cursoQueElaPertence){
		this.cursoQueElaPertence = cursoQueElaPertence;
		return this;
	
	}
	public DisciplinaBuilder comCargaHoraria(float cargaHoraria){
		this.cargaHoraria = cargaHoraria;
		return this;
	
	}
	public DisciplinaBuilder comVagas(int vagas){
		this.vagas = vagas;
		return this;
	}
	public DisciplinaBuilder comPeriodo(Periodo periodo){
		this.periodo = periodo;
		return this;
	}
	
	public Disciplina builder(){
		return new Disciplina(Id, nome, cargaHoraria, cursoQueElaPertence, vagas, periodo);
	}
	
}
