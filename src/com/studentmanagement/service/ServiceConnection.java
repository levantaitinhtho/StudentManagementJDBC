package com.studentmanagement.service;

import com.studentmanagement.dao.ClassConnection;
import com.studentmanagement.dao.ClassDao;
import com.studentmanagement.dao.StudentDao;
import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ServiceConnection {
    private ClassDao classDao = new ClassDao();
    private ClassConnection classConnection = new ClassConnection();
    private Scanner scanner = new Scanner(System.in);

    public void addFullDatabase() throws SQLException {
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

        System.out.println("Please Enter Choice Of You");
        List<Class> classes = classDao.findAllClass();
        for(Class clas : classes ){
            System.out.println(clas);
        }

        int inputID = Integer.parseInt(scanner.nextLine());
        Class clas = classDao.findByIDClass(inputID);
        if(clas == null){
            System.out.println("Not Found ID Of Class");
            return;
        }
        Student student = new Student(ID,last_name,first_name,city,average_score,gender,localDate);
        student.setClassStudent(clas);
        classConnection.addFullDatabase(student);
    }
    public void averageBasedOnClass(){
        try{
            System.out.println("--------------------");
            System.out.println("Enter Class Name");
            String class_name = scanner.nextLine();
            double averageScoreByClassName = classConnection.calculateAverageScoreByClassName(class_name);
            System.out.println(averageScoreByClassName);
        }catch (Exception exception){
            System.out.println("Error: "+ exception.getMessage());
        }
    }
    public void studentNumberClass(){
        try{
            System.out.println("-----------------------");
            System.out.println("Enter Class Name");
            String class_name = scanner.nextLine();
            int classes = classConnection.studentNumberClass(class_name);
            System.out.println(classes);
        }catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
        }
    }
}
