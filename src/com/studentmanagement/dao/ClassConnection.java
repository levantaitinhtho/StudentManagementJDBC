package com.studentmanagement.dao;

import com.studentmanagement.entity.Class;
import com.studentmanagement.entity.Student;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ClassConnection {
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

    public boolean addFullDatabase(Student student) throws  SQLException{
        String dob = student.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String query = "insert into student(id,first_name,last_name,city,average_score,gender,dob,class_id) values ("
                + student.getID() + ",'" + student.getFirstName() + "', '" + student.getLastName() + "', '" +
                student.getCity() + "', " + student.getAverageScore() + ", '" + student.getGender() + "', '" +
                dob + "', '"+student.getClassStudent().getID()+"')";
        Statement statement = getConnection().createStatement();
        int resultSet = statement.executeUpdate(query);
        if(resultSet != 0){
            return true;
        }
        return false;
    }
    public Double calculateAverageScoreByClassName(String className) throws SQLException {
        String query = "SELECT AVG(Student.average_score) AS average FROM " +
                "Student JOIN Class ON Student.class_id = Class.ID " +
                "WHERE class_name = '" + className +
                "' GROUP BY class_name";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()) {
            double average = resultSet.getDouble(1);
            return average;
        }
        return null;
    }
    public Integer studentNumberClass(String className) throws  SQLException{
        String query ="SELECT COUNT(Student.ID) AS NumberStudent FROM " +
                "Student JOIN Class ON Student.class_id = Class.ID " +
                "WHERE class_name = '" + className +
                "' GROUP BY class_name";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            int numberStudent = resultSet.getInt(1);
            return numberStudent;
        }
        return null;
    }
}
