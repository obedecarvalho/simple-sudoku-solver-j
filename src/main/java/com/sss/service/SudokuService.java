package com.sss.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sss.model.Sudoku;
import com.sss.model.SudokuFactory;
import com.sss.model.SudokuValores;
import com.sss.model.SudokuValoresRepository;

@Service
public class SudokuService {
	
	@Autowired
	private SudokuSolverService sudokuSolverService;

	@Autowired
	private SudokuValoresRepository sudokuValoresRepository;

	public void gerarSudoku() throws InterruptedException {
		
		Optional<SudokuValores> sudokuValores = sudokuValoresRepository.findById(1l);
		
		Sudoku s = SudokuFactory.gerarSudoku(sudokuValores.get().getValores());

		sudokuSolverService.resolver(s, sudokuValores.get());

	}
	
	public void gerarSudokuFull() throws InterruptedException {
		List<SudokuValores> sudokus = sudokuValoresRepository.findAll();

		//sudokuValoresRepository.findByNivel("VERY_HARD");//EASY, HARD, MEDIUM, VERY_HARD
		
		for (SudokuValores sudokuValores : sudokus) {
			Sudoku s = SudokuFactory.gerarSudoku(sudokuValores.getValores());
			sudokuSolverService.resolver(s, sudokuValores);
		}

	}
}
