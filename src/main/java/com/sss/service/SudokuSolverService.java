package com.sss.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.sss.model.Casa;
import com.sss.model.ConjuntoCasa;
import com.sss.model.Sudoku;
import com.sss.model.SudokuValores;
import com.sss.model.SudokuValoresRepository;

@Service
public class SudokuSolverService {
	
	@Autowired
	private SudokuValoresRepository sudokuValoresRepository;

	@Async("sudokuSolverExecutor")
	public CompletableFuture<Boolean> resolver(Sudoku s, SudokuValores sv) throws InterruptedException {
		
		//System.err.println(s);
		//print(s);
		
		Boolean alterado = true;
		
		while (alterado) {
			//System.err.println("gerarSudoku#");
			alterado = false;

			alterado = algoritmoUnicaOpcao(s) || alterado;
			alterado = algoritmoUnicaOpcaoConjunto(s) || alterado;
		}

		//System.err.println(s);
		//print(s);
		
		boolean incorreto = isIncompleto(s);
		
		if (incorreto) {
			System.err.println("isIncompleto: " + sv.getId());
		}
		
		//
		sv.setIncompleto(incorreto);
		sudokuValoresRepository.save(sv);
		//
		
		return CompletableFuture.completedFuture(incorreto);
	}

	private boolean isIncompleto(Sudoku sudoku) {
		boolean invalido = false;
		
		for (ConjuntoCasa cc : sudoku.getLinhas().values()) {
			invalido = isIncompleto(cc) || invalido;
		}
		for (ConjuntoCasa cc : sudoku.getColunas().values()) {
			invalido = isIncompleto(cc) || invalido;
		}
		for (ConjuntoCasa cc : sudoku.getQuadrantes().values()) {
			invalido = isIncompleto(cc) || invalido;
		}
		return invalido;
	}
	
	private boolean isIncompleto(ConjuntoCasa conjuntoCasa) {
		List<Integer> valores = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		valores.removeAll(conjuntoCasa.getValores());
		if (valores.size() > 0) return true;
		return false;
	}
	
	private Boolean algoritmoUnicaOpcaoConjunto(Sudoku sudoku) {
		Boolean alteradoGlobal = false, alterado = true;
		
		while (alterado) {
			//System.err.println("algoritmoUnicaOpcaoConjunto#");
			alterado = false;
			for (ConjuntoCasa cc : sudoku.getLinhas().values()) {
				alterado = algoritmoUnicaOpcaoConjunto(cc) || alterado;
			}
			for (ConjuntoCasa cc : sudoku.getColunas().values()) {
				alterado = algoritmoUnicaOpcaoConjunto(cc) || alterado;
			}
			for (ConjuntoCasa cc : sudoku.getQuadrantes().values()) {
				alterado = algoritmoUnicaOpcaoConjunto(cc) || alterado;
			}
			alteradoGlobal = alteradoGlobal || alterado;
		}
		return alteradoGlobal;
	}
	
	private Boolean algoritmoUnicaOpcaoConjunto(ConjuntoCasa conjuntoCasa) {

		Map<Integer, List<Casa>> valorCasaOpcoes = conjuntoCasa.getValorCasaOpcoes();
		List<Casa> casas = null;
		Integer valor = null;
		Casa casa = null;
		Boolean alteradoGlobal = false;

		for (Integer key : valorCasaOpcoes.keySet()) {
			casas = valorCasaOpcoes.get(key);

			if (casas.size() == 1) {
				valor = key;
				casa = casas.get(0);
				casa.setValor(valor);
				casa.getOpcoes().clear();
				casa.getLinha().removerOpcoesCasas(valor);
				casa.getColuna().removerOpcoesCasas(valor);
				casa.getQuadrante().removerOpcoesCasas(valor);
				alteradoGlobal = true;
			}
		}

		return alteradoGlobal;
	}

	private Boolean algoritmoUnicaOpcao(Sudoku sudoku) {
		Integer valor = null;
		Boolean alterado = true, alteradoGlobal = false;
		
		while (alterado) {
			//System.err.println("algoritmoUnicaOpcao#");
			alterado = false;
			for (Casa casa : sudoku.getCasas()) {
				if (casa.getValor() == null && casa.getOpcoes().size() == 1) {
					valor = casa.getOpcoes().get(0);
					casa.setValor(valor);
					casa.getOpcoes().clear();
					casa.getLinha().removerOpcoesCasas(valor);
					casa.getColuna().removerOpcoesCasas(valor);
					casa.getQuadrante().removerOpcoesCasas(valor);
					alterado = true;
					alteradoGlobal = true;
				}
			}
		}
		return alteradoGlobal;
	}
	
	public void print(Sudoku sudoku) {
		StringBuilder str = new StringBuilder();
		for (ConjuntoCasa l : sudoku.getLinhas().values()) {
			for (Casa casa : l.getCasas()) {
				str.append(casa.getNumero() + ":" + casa.getOpcoes());
				str.append("\n");
			}
			//str.append("\n");
		}
		System.err.println(str.toString());
	}
}
