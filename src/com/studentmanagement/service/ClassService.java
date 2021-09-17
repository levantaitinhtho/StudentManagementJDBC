package com.studentmanagement.service;

import com.studentmanagement.dao.ClassDao;
import com.studentmanagement.entity.Class;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ClassService {
    private ClassDao classDao = new ClassDao();
    private Scanner scanner = new Scanner(System.in);

    public void FindAll()  {
        try{
            List<Class> classes = classDao.findAllClass();
            System.out.println(classes);
        }catch (Exception exception){
            System.out.println("Error: "+ exception.getMessage());
        }
    }
    public void findByIDClass(){
        try{
            System.out.println("Enter ID You Need Search");
            int inputID = scanner.nextInt();
            Class classs = classDao.findByIDClass(inputID);
            System.out.println(classs);
        }catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
        }
    }
    public void addInfoClass(){
        try{
            System.out.println("Enter Class Name");
            String className = scanner.nextLine();
            System.out.println("Enter Major");
            String major = scanner.nextLine();
            Class aclass = new Class(className,major);
            classDao.addInfoClass(aclass);
        }catch (Exception exception){
            System.err.println("Error: "+ exception.getMessage());
        }
    }
    public void findByMajor(){
        try{
            System.out.println("Enter Major You Need");
            String major = scanner.nextLine();
            Class cla = classDao.findByMajor(major);
            System.out.println(cla);
        }catch (Exception exception){
            System.out.println("Error: "+exception.getMessage());
        }
    }
}
