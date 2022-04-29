package com.sss.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SudokuValoresRepository extends JpaRepository<SudokuValores, Long>{

	public List<SudokuValores> findByNivel(String nivel);

}
