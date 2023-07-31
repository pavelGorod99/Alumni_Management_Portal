package com.example.Alumni_Management_Portal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlumniManagementPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlumniManagementPortalApplication.class, args);
	}

	@Bean
<<<<<<< HEAD
	public ModelMapper modelMapper(){
=======
	public ModelMapper modelMapper() {
>>>>>>> 91c2f2e415dcf7d1640edc56f0dd721d92dcf25e
		return new ModelMapper();
	}
}
