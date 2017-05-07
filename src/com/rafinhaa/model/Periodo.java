package com.rafinhaa.model;

public enum Periodo {
	Matutino,
	Vesperino,
	Noturno;
	
	public static Periodo converte(String string){
		
		if(string.equals(Periodo.Matutino.toString()))
			return Periodo.Matutino;
		else if(string.equals(Periodo.Vesperino.toString()))
			return Periodo.Vesperino;
		else
			return Periodo.Noturno;
	}
	
	public static int obtemId(String string){
		if(string.equals(Periodo.Matutino.toString()))
			return 0;
		else if(string.equals(Periodo.Vesperino.toString()))
			return 1;
		else
			return 2;
	}
}
