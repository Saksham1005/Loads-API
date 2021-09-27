package com.spring_boot.loads_apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring_boot.loads_apis.dao.Load_Dao;

@SpringBootApplication
public class LoadsApisApplication implements CommandLineRunner{

	@Autowired
	private Load_Dao load_dao;
	
	public static void main(String[] args) {
		SpringApplication.run(LoadsApisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.load_dao.createTable();
		
	}

}
