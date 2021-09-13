package com.studentmanagement.app;

import com.studentmanagement.dao.StudentDao;
import com.studentmanagement.entity.Student;
import com.studentmanagement.service.StudentService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final StudentService studentService = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDao studentDao = new StudentDao();
    public static void main(String[] args) throws SQLException {
        System.out.println("Welcome Student management system!");
        showMenu();
        while(true) {
            System.out.println("Enter Choose");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    studentService.findAll();
                    break;
                case 2:
                  studentService.addStudent();
                    break;
                case 3:
                   studentService.deleteStudent();
                    break;
                case 4:
                    studentService.modifyDataStudent();
                    break;
                case 5:
                    studentService.findById();
                    break;
                case 6:
                    studentService.findByName();
                    break;
                case 7:
                    showMenu();
                    break;
                case 8:
                      studentService.averageScoreStudent();
                    break;
                case 9:
                    studentService.searchByMajor();
                    break;
                case 10:
                    studentService.updateScore();
                    break;
                case 11:
                    studentService.updateDob();
                      break;
                case 12:
                        studentService.findByCities();
                    break;
                case 13:
                        studentService.updateAllScore();
                    break;
                case 14:
                    studentService.sortByScore();
                    break;
                case 15:
                    studentService.maxScore();
                    break;
                case 16:
                    System.out.println("Good bye.");
                    return;
                default:
                    System.out.println("Not a option. Please choose again");
            }
        }
    }

    public static void showMenu() {
        System.out.println("1. Show All Student In List");
        System.out.println("2. Add Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Modify Data Of Student ");
        System.out.println("5. Search Student By ID");
        System.out.println("6. Search Student By Name");
        System.out.println("7. Show Menu ");
        System.out.println("8. Average Score Of All Student");
        System.out.println("9. List Students By Major");
        System.out.println("10.Update Score Of Students");
        System.out.println("11.Update Dob Based On ID");
        System.out.println("12.Search Student Based On City");
        System.out.println("13.Update All Score In Database");
        System.out.println("14.Sort Date Based On Score");
        System.out.println("15. Display Max Score Of Student");
        System.out.println("16. Exits Program");
    }
}
