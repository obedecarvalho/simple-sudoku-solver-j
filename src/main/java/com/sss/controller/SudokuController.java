package com.sss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sss.service.SudokuService;

@RestController
public class SudokuController {

	@Autowired
	private SudokuService sudokuService;

	@GetMapping("/test")
	public void test() throws InterruptedException {
		sudokuService.gerarSudoku();
	}

	@GetMapping("/testFull")
	public void testFull() throws InterruptedException {
		sudokuService.gerarSudokuFull();
	}
}
