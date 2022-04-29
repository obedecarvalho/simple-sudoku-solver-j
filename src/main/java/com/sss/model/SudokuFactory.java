package com.sss.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sss.service.Constantes;

public class SudokuFactory {

	public static final String BASE_TESTE = "379000014060010070080009005435007000090040020000800436900700080040080050850000249";
	
	/*public static void x() {
		for (int numero = 1; numero<= 81; numero++) {
			System.err.println(String.format("%d [%d x %d x %d]", numero, getLinha(numero), getColuna(numero),
					getQuadrante(numero)));
		}
	}*/

	public static Sudoku gerarSudoku() {
		return gerarSudoku(BASE_TESTE);
	}

	public static Sudoku gerarSudoku(String sudokuStr) {
		
		if (sudokuStr.length() != Constantes.SUDOKU_TOTAL_CASAS) return null;//TODO
		
		String[] valores = sudokuStr.split("");
		
		Sudoku sudoku = new Sudoku();
		Casa casa = null;
		Integer numero = 1, valor = null;
		List<Casa> casas = new ArrayList<Casa>();
		
		inicializar(sudoku);
		
		for (String v : valores) {
			
			casa = new Casa();
			casa.setNumero(numero);
			valor = Integer.valueOf(v);
			if (valor != 0) {
				casa.setValor(valor);
			}
			casa.setSudoku(sudoku);

			ConjuntoCasa linha = getLinha(sudoku, numero);
			linha.addCasa(casa);
			casa.setLinha(linha);
			ConjuntoCasa coluna = getColuna(sudoku, numero);
			coluna.addCasa(casa);
			casa.setColuna(coluna);
			ConjuntoCasa quandrante = getQuadrante(sudoku, numero);
			quandrante.addCasa(casa);
			casa.setQuadrante(quandrante);
			
			casas.add(casa);
			
			numero++;
		}
		
		sudoku.setCasas(casas);
		calcularOpcoes(sudoku);
		
		return sudoku;
	}

	private static void calcularOpcoes(Sudoku sudoku) {
		for (Casa c : sudoku.getCasas()) {
			if (c.getValor() != null) {
				c.getOpcoes().clear();
			} else {
				c.getOpcoes().removeAll(c.getLinha().getValores());
				c.getOpcoes().removeAll(c.getColuna().getValores());
				c.getOpcoes().removeAll(c.getQuadrante().getValores());
			}
		}
	}
	
	private static Integer getLinha(Integer numero) {
		return Math.floorDiv((numero-1), Constantes.SUDOKU_DIMENSAO)+1;
	}
	
	private static ConjuntoCasa getLinha(Sudoku sudoku, Integer numero) {
		return sudoku.getLinhas().get(getLinha(numero));
	}
	
	private static Integer getColuna(Integer numero) {
		return ((numero-1)%Constantes.SUDOKU_DIMENSAO)+1;
	}
	
	private static ConjuntoCasa getColuna(Sudoku sudoku, Integer numero) {
		return sudoku.getColunas().get(getColuna(numero));
	}
	
	private static Integer getQuadrante(Integer numero) {
		return (Math.floorDiv(getLinha(numero)-1, Constantes.SUDOKU_DIMENSAO_QUADRANTE) * Constantes.SUDOKU_DIMENSAO_QUADRANTE) + Math.floorDiv(getColuna(numero)-1, Constantes.SUDOKU_DIMENSAO_QUADRANTE) + 1;
	}
	
	private static ConjuntoCasa getQuadrante(Sudoku sudoku, Integer numero) {
		return sudoku.getQuadrantes().get(getQuadrante(numero));
	}
	
	private static void inicializar(Sudoku sudoku) {
		
		ConjuntoCasa conjuntoCasa = null;
		Map<Integer, ConjuntoCasa> conjunto = new HashMap<Integer, ConjuntoCasa>();
		
		for (int i=1; i<=Constantes.SUDOKU_DIMENSAO; i++) {
			conjuntoCasa = new ConjuntoCasa();
			conjuntoCasa.setNumero(i);
			conjuntoCasa.setSudoku(sudoku);
			conjunto.put(i, conjuntoCasa);
		}
		sudoku.setColunas(conjunto);
		
		conjunto = new HashMap<Integer, ConjuntoCasa>();
		
		for (int i=1; i<=Constantes.SUDOKU_DIMENSAO; i++) {
			conjuntoCasa = new ConjuntoCasa();
			conjuntoCasa.setNumero(i);
			conjuntoCasa.setSudoku(sudoku);
			conjunto.put(i, conjuntoCasa);
		}
		
		sudoku.setLinhas(conjunto);
		
		conjunto = new HashMap<Integer, ConjuntoCasa>();
		
		for (int i=1; i<=Constantes.SUDOKU_DIMENSAO; i++) {
			conjuntoCasa = new ConjuntoCasa();
			conjuntoCasa.setNumero(i);
			conjuntoCasa.setSudoku(sudoku);
			conjunto.put(i, conjuntoCasa);
		}
		
		sudoku.setQuadrantes(conjunto);
	}
}
