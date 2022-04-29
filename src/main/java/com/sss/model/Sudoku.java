package com.sss.model;

import java.util.List;
import java.util.Map;

public class Sudoku {

	private Long id;
	
	private List<Casa> casas;
	
	private Map<Integer, ConjuntoCasa> linhas;
	
	private Map<Integer, ConjuntoCasa> colunas;
	
	private Map<Integer, ConjuntoCasa> quadrantes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Casa> getCasas() {
		return casas;
	}

	public void setCasas(List<Casa> casas) {
		this.casas = casas;
	}

	public Map<Integer, ConjuntoCasa> getLinhas() {
		return linhas;
	}

	public void setLinhas(Map<Integer, ConjuntoCasa> linhas) {
		this.linhas = linhas;
	}

	public Map<Integer, ConjuntoCasa> getColunas() {
		return colunas;
	}

	public void setColunas(Map<Integer, ConjuntoCasa> colunas) {
		this.colunas = colunas;
	}

	public Map<Integer, ConjuntoCasa> getQuadrantes() {
		return quadrantes;
	}

	public void setQuadrantes(Map<Integer, ConjuntoCasa> quadrantes) {
		this.quadrantes = quadrantes;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (ConjuntoCasa l : linhas.values()) {
			str.append(l);
			str.append("\n");
		}
		
		/*str.append("\n");
		
		for (ConjuntoCasa l : colunas.values()) {
			str.append(l);
			str.append("\n");
		}
		
		str.append("\n");
		
		for (ConjuntoCasa l : quadrantes.values()) {
			str.append(l);
			str.append("\n");
		}*/
		return str.toString();
	}

}
