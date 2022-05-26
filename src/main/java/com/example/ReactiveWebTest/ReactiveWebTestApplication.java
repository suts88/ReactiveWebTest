package com.example.ReactiveWebTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.r2dbc.core.DatabaseClient;

@SpringBootApplication
public class ReactiveWebTestApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReactiveWebTestApplication.class, args);
	}

}
