package mainclass;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import student.Student;
import student.StudentDAO;

import java.util.List;
import java.util.Scanner;

public class StudentMainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating Hibernate session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // Creating StudentDAO instance
        StudentDAO studentDAO = new StudentDAO(sessionFactory);

        int choice;
        do {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Find Student by ID");
            System.out.println("3. List All Students");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Enter student details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    Student newStudent = new Student(name);
                    studentDAO.saveStudent(newStudent);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    System.out.print("Enter student ID to find: ");
                    long studentId = scanner.nextLong();
                    Student foundStudent = studentDAO.findById(studentId);
                    if (foundStudent != null) {
                        System.out.println("Found Student: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    List<Student> allStudents = studentDAO.getAllStudents();
                    if (!allStudents.isEmpty()) {
                        System.out.println("All Students:");
                        for (Student student : allStudents) {
                            System.out.println(student);
                        }
                    } else {
                        System.out.println("No students found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID to update: ");
                    long updateStudentId = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character

                    // Fetch student by ID
                    Student updateStudent = studentDAO.findById(updateStudentId);

                    if (updateStudent != null) {
                        System.out.println("Enter new details for the student:");
                        System.out.print("New Name: ");
                        String newName = scanner.nextLine();

                        // Update student details
                        updateStudent.setName(newName);

                        studentDAO.updateStudent(updateStudent);
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter student ID to delete: ");
                    long deleteStudentId = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character

                    // Fetch student by ID
                    Student deleteStudent = studentDAO.findById(deleteStudentId);

                    if (deleteStudent != null) {
                        // Delete student
                        studentDAO.deleteStudent(deleteStudent);
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid operation.");
            }
        } while (choice != 6);

        // Closing the session factory
        sessionFactory.close();
        scanner.close();
    }
}

