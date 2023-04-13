package com.subham.cruddemo;

import com.subham.cruddemo.dao.api.StudentDAO;
import com.subham.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
		};
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating new student objects");
		Student tempStudent1 = new Student("Subham", "Patel", "subham.patel@temenos.com");
		Student tempStudent2 = new Student("Siprashi", "Nayak", "siprashi.nayak@temenos.com");
		Student tempStudent3 = new Student("Enrique", "Iglesias", "enrique.iglesias@temenos.com");

		System.out.println("Saving the students");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		System.out.println("Saved students!");
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object");
		Student tempStudent = new Student("Subham", "Patel", "subham.patel@temenos.com");

		System.out.println("Saving the student");
		studentDAO.save(tempStudent);

		System.out.println("Saved Student. Generated id: " + tempStudent.getId());
	}
}
