package com.studentmanagement.service;

import com.studentmanagement.dao.StudentDao;
import com.studentmanagement.entity.Student;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StudentService {

    private StudentDao studentDao = new StudentDao();
    private Scanner scanner = new Scanner(System.in);
    public void findById() {
        try {
            System.out.println("Search By id");
            System.out.print("Enter id: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Student student = studentDao.findById(id);
            System.out.println(student);
        } catch (SQLException sqlException) {
            System.out.println("sql exception: " + sqlException.toString());
        }
    }

    public void findAll() {
        try {
            System.out.println("Find all");
            List<Student> students = studentDao.findAll();
            if (students.isEmpty()) {
                System.out.println("List is empty");
            }
            for (Student student: students) {
                System.out.println(student);
            }
        } catch (SQLException sqlException) {
            System.out.println("sql exception");
        }
    }

    public void updateScore() {
        try {
            System.out.println("Search By id");
            System.out.print("Enter id to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Student student = studentDao.findById(id);
            System.out.println("Enter new score: ");
            double score = scanner.nextDouble();
            student.setAverageScore(score);
            studentDao.updateScore(student);
        } catch (SQLException sqlException) {
            System.out.println("sql exception: " + sqlException.toString());
        }
    }
    public void addStudent(){
        try {
            System.out.println("Start Adding Students");
            System.out.println("Enter ID:");
            int ID = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter LastName");
            String last_name = scanner.nextLine();
            System.out.println("Enter First Name");
            String first_name = scanner.nextLine();
            System.out.println("Enter City");
            String city = scanner.nextLine();
            System.out.println("Enter Average Score ");
            double average_score = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter Gender");
            char gender = scanner.nextLine().charAt(0);
            System.out.println("Enter Dob With Format yyyy-mmm-dd");
            String dob = scanner.nextLine();
            LocalDate localDate = LocalDate.parse(dob);
            Student student = new Student(ID,last_name,first_name,city,average_score,gender,localDate);
            studentDao.addStudent(student);
        }catch (SQLException sqlException){
            System.out.println("Error : "+ sqlException.getMessage());
        }catch (DateTimeParseException dateTimeParseException){
            System.out.println("Error"+dateTimeParseException.getMessage());
            System.out.println("Please Recheck Format Of LocalDate You Enter");
        }
    }
        public void findByName(){
    try {
        System.out.println("Enter Name You Need Find");
        List<Student> studentList= studentDao.findByName(scanner.nextLine());
        if (studentList.isEmpty()) {
            System.err.println("List Is Empty");
        }
        for(Student student:studentList){
            System.out.println(student);
        }
    }catch (SQLException exception){
        System.out.println(exception.getMessage());
        }
    }
    public void deleteStudent(){
        try{
            System.out.println("Start Deleting Students");
            System.out.println("Enter ID Need Remove !!");
            int inputID = Integer.parseInt(scanner.nextLine());
            scanner.nextLine();
            studentDao.deleteStudent(inputID);
        }catch (Exception exception){
            System.out.println("Error"+exception.getMessage());
            System.out.println("Please Try Again !!!");
        }finally {
            studentDao.closeConnection();
            System.out.println("Deleted Successful !!");
        }
    }

    public void findByCities(){
       try{
           System.out.println("Start Find Student By City");
           System.out.println("Enter City 1");
           String city1 = scanner.nextLine();
           System.out.println("Enter City 2");
           String city2 = scanner.nextLine();
           List<Student> students = studentDao.findByCities(city1,city2);
           if(students.isEmpty()){
               System.out.println("Not Found Student City "+city1+" And Student "+city2);
           }
           for(Student student : students){
               System.out.println(student);
           }
       }catch (Exception exception){
           System.out.println("Error: "+ exception.getMessage());
           System.out.println("Please Try Again !!!");
       }
    }
    public void updateDob(){
        try {
            System.out.println("Start Update Dob ");
            System.out.println("Enter ID You Need Update");
            int id = Integer.parseInt(scanner.nextLine());
           Student student = studentDao.findById(id);
            System.out.println("Enter New Dob");
            String dob = scanner.nextLine();
            LocalDate localDate = LocalDate.parse(dob);
            student.setDob(localDate);
            studentDao.updateDob(student);
        }catch (Exception exception){
            System.out.println("Error: "+ exception.getMessage());
            System.out.println("Update Successful");
        }
    }
    public void searchByMajor(){
        try{
            System.out.println("Enter Major You Need");
            String major = scanner.nextLine();
            List<Student> students = studentDao.searchByMajor(major);
            if(students.isEmpty()){
                System.out.println("No Student Is The Right Choose.");
            }
            for(Student student : students){
                System.out.println(student);
            }

        }catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
            System.out.println("-------------------------------");
            System.out.println("Please Try Again");
        }
    }
    public void averageScoreStudent(){
        System.out.println("-------------------");
        try {
            studentDao.averageScoreStudent();
        } catch (SQLException sqlException) {
            System.err.println("Error: "+sqlException.getMessage());
        }
    }
    public void updateAllScore(){
        try{
            System.out.println("Start Update !!");
            System.out.println("---------------");
            System.out.println("Enter Score For All Student");
            studentDao.updateAllScore(scanner.nextDouble());
        }catch (Exception exception){
            System.err.println("Error"+exception.getMessage());
        }
    }
    public void modifyDataStudent(){
        try{
            System.out.println("Enter ID You Need Update");
            int id = Integer.parseInt(scanner.nextLine());
            Student student = studentDao.findById(id);
            System.out.println("Enter Last Name Student");
            String lastname = scanner.nextLine();
            student.setLastName(lastname);
            System.out.println("Enter First Name Of Student");
            String firstname = scanner.nextLine();
            student.setFirstName(firstname);
            System.out.println("Enter City Of Student");
            String city = scanner.nextLine();
            student.setCity(city);
            System.out.println("Enter Average Score");
            double averageScore = scanner.nextDouble();
            student.setAverageScore(averageScore);
            scanner.nextLine();
            System.out.println("Enter Gender");
            char gender = scanner.nextLine().charAt(0);
            student.setGender(gender);
            System.out.println("Enter Dob");
            String dob = scanner.nextLine();
            LocalDate localDate = LocalDate.parse(dob);
            student.setDob(localDate);
            System.out.println("Enter Major:");
            String major = scanner.nextLine();
            student.setMajor(major);
            studentDao.modifyDataStudent(student);
        }catch (Exception exception){
            System.err.println("Error: "+ exception.getMessage());
        }
    }
    public void sortByScore(){
        try{
            Student students = studentDao.sortByScore();
            System.out.println(students);
        }catch (Exception exception){
            System.err.println("Error: "+ exception.getMessage());
        }
    }
    public void maxScore(){
        try{
            Student student = studentDao.scoreMax();
            System.out.println(student);
        }catch (Exception exception){
            System.err.println("Error: "+ exception.getMessage());
        }
    }
}
