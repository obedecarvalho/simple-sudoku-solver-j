package com.sss;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class SssApplication {//Simple Sudoku Solver

	public static void main(String[] args) {
		SpringApplication.run(SssApplication.class, args);
	}

	@Bean(name = "sudokuSolverExecutor")
	public Executor asyncExecutor() {
		final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(8);
		//executor.setQueueCapacity(32);
		executor.setThreadNamePrefix("sudokuSolverExecutor-");
		executor.initialize();
		return executor;
    }
}
