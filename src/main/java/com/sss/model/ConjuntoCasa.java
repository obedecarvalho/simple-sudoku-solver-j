package com.sss.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConjuntoCasa {

	private Long id;
	
	private List<Casa> casas;
	
	private Integer numero; 
	
	private Sudoku sudoku;

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

	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void removerOpcoesCasas(Integer valor) {
		for (Casa c : this.casas) {
			c.getOpcoes().remove(valor);
		}
	}

	public List<Integer> getValores() {
		return casas.stream().map(c -> c.getValor()).collect(Collectors.toList());
	}

	public Map<Integer, List<Casa>> getValorCasaOpcoes() {

		Map<Integer, List<Casa>> map = new HashMap<Integer, List<Casa>>();

		List<Casa> cs = null;

		for (Casa c : casas) {
			for (Integer o : c.getOpcoes()) {

				cs = map.get(o);

				if (cs == null) {
					cs = new ArrayList<Casa>();
				}

				cs.add(c);
				map.put(o, cs);
			}
		}

		return map;
	}

	public void addCasa(Casa casa) {
		if (casas == null) {
			casas = new ArrayList<Casa>();
		}
		casas.add(casa);
	}

	@Override
	public String toString() {
		return numero + " : " + casas;
	}
}
