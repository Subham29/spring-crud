package com.subham.cruddemo;

import com.subham.cruddemo.dao.api.StudentDAO;
import com.subham.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
//			createMultipleStudents(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
//			queryForStudentsByLastName(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			queryForStudents(studentDAO);
			deleteStudents(studentDAO);
			queryForStudents(studentDAO);
		};
	}

	private void deleteStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int deleteCount = studentDAO.deleteAllStudents();
		System.out.println("Total number of records deleted: " + deleteCount);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int id = 1;
		System.out.println("Deleting student with id: " + id);
		studentDAO.deleteStudent(id);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int id = 1;
		System.out.println("Searching for student with id: " + id);
		Student student = studentDAO.findById(id);
		if (student == null) {
			System.out.println("Student not found!");
			return;
		}
		System.out.println("Student found");
		System.out.println(student);

		System.out.println("Updating student");
		student.setEmail("abcd@gmail.com");
		studentDAO.update(student);

		System.out.println("Retrieving Updated Student with id: " + id);
		Student updatedStudent = studentDAO.findById(id);
		System.out.println(updatedStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		System.out.println("Querying students by First Name");
		List<Student> students = studentDAO.findBYFirstName("Siprashi");
		printStudents(students);
	}

	private void queryForStudents(StudentDAO studentDAO) {
		System.out.println("Listing all students");
		List<Student> allStudents = studentDAO.findAll();
		printStudents(allStudents);
	}

	private void printStudents(List<Student> allStudents) {
		if (allStudents == null || allStudents.isEmpty()) {
			System.out.println("No students found!");
			return;
		}
		for (Student student : allStudents) {
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Searching Student with id: 1");
		Student student = studentDAO.findById(4);
		System.out.println(student == null ? "Student not found!" : student.toString());
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
		Student tempStudent = new Student("Mary", "Kom", "mary.kom@temenos.com");

		System.out.println("Saving the student");
		studentDAO.save(tempStudent);

		System.out.println("Saved Student. Generated id: " + tempStudent.getId());
	}
}
