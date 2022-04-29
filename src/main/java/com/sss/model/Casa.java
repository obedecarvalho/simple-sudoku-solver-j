package com.sss.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Casa {

	private Long id;
	
	private Integer valor; //0-9
	
	private Integer numero; //1 a 81
	
	private List<Integer> opcoes; //[0-9]
	
	private ConjuntoCasa coluna;
	
	private ConjuntoCasa linha;
	
	private ConjuntoCasa quadrante;
	
	private Sudoku sudoku;
	
	public Casa() {
		this.opcoes = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Integer> getOpcoes() {
		return opcoes;
	}

	public void setOpcoes(List<Integer> opcoes) {
		this.opcoes = opcoes;
	}

	public ConjuntoCasa getColuna() {
		return coluna;
	}

	public void setColuna(ConjuntoCasa coluna) {
		this.coluna = coluna;
	}

	public ConjuntoCasa getLinha() {
		return linha;
	}

	public void setLinha(ConjuntoCasa linha) {
		this.linha = linha;
	}

	public ConjuntoCasa getQuadrante() {
		return quadrante;
	}

	public void setQuadrante(ConjuntoCasa quadrante) {
		this.quadrante = quadrante;
	}

	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "" + valor;
	}
}
