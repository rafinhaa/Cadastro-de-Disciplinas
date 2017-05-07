package com.rafinhaa.model;

public class Disciplina {
	
	private int Id;
	private String nome;
	private float cargaHoraria;
	private String cursoQueElaPertence;
	private int vagas;
	private Periodo periodo;
	
	public Disciplina(String nomeDaDiciplina, float cargaHoraria, String cursoQueElaPertence, int numeroDeVagas, Periodo periodo) {
		this.nome = nomeDaDiciplina;
		this.cargaHoraria = cargaHoraria;
		this.cursoQueElaPertence = cursoQueElaPertence;
		this.vagas = numeroDeVagas;
		this.periodo = periodo;
	}

	public Disciplina(int Id, String nomeDaDiciplina, float cargaHoraria, String cursoQueElaPertence, int numeroDeVagas, Periodo periodo) {
		this.Id = Id;
		this.nome = nomeDaDiciplina;
		this.cargaHoraria = cargaHoraria;
		this.cursoQueElaPertence = cursoQueElaPertence;
		this.vagas = numeroDeVagas;
		this.periodo = periodo;
	}
	
	public String getNomeDaDiciplina() {
		return nome;
	}

	public float getCargaHoraria() {
		return cargaHoraria;
	}

	public String getCursoQueElaPertence() {
		return cursoQueElaPertence;
	}

	public int getNumeroDeVagas() {
		return vagas;
	}

	public String getPeriodo() {
		return periodo.toString();
	}
	
	public int getId(){
		return Id;
	}

	@Override
	public String toString() {
		return "Disciplina [Id=" + Id + ", nome=" + nome + ", cargaHoraria=" + cargaHoraria + ", cursoQueElaPertence="
				+ cursoQueElaPertence + ", vagas=" + vagas + ", periodo=" + periodo + "]";
	}
}
