package com.studentmanagement.dao;

import com.studentmanagement.entity.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDao {
    private Connection conn;

    public Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root", "01642878233tai");
            return conn;
        }
        return conn;
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    public Class findByIDClass(int ID) throws  SQLException{
        String query = "SELECT * FROM Class WHERE ID ="+ID;
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            Class result = new Class(resultSet);
            return  result;
        }
        return null;
    }
    public boolean addInfoClass(Class classs) throws  SQLException{
        String query = "INSERT INTO CLass(`class_name`, `major`) VALUE('"+classs.getClassName()+"', '"+classs.getMajor()+"')";
        Statement statement = getConnection().createStatement();
        int resultSet = statement.executeUpdate(query);
        if(resultSet != 0){
            return true;
        }
        return false;
    }
    public Class findByMajor(String major) throws  SQLException{
        String query = "SELECT * FROM Class WHERE major='"+major+"'";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            Class aclass = new Class(resultSet);
            return aclass;
        }
        return null;
    }
    public List<Class> findAllClass() throws  SQLException{
        String query = "SELECT * FROM Class";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Class> classes = new ArrayList<>();
        while(resultSet.next()){
            Class result = new Class(resultSet);
            classes.add(result);
        }
        return classes;
    }
}
