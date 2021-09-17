package com.studentmanagement.dao;

import com.studentmanagement.entity.Student;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.text.html.HTML.Tag.S;
import static javax.swing.text.html.HTML.Tag.SELECT;

public class StudentDao {

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

    public boolean addStudent(Student student) throws SQLException {
        String dob = student.getDob().format(DateTimeFormatter.ISO_LOCAL_DATE);
        String query = "insert into student(id,first_name,last_name,city,average_score,gender,dob) values ("
                + student.getID() + ",'" + student.getFirstName() + "', '" + student.getLastName() + "', '" +
                student.getCity() + "', " + student.getAverageScore() + ", '" + student.getGender() + "', '" +
                dob +"')";
        Statement statement = getConnection().createStatement();
        int result = statement.executeUpdate(query);
        if(result != 0)  {
            System.out.println("Add Student Successfully");
            return true;
        }
        return false;
    }

    public Student findById(int id) throws SQLException {

        String query = "SELECT * FROM Student WHERE id = "+ id;
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.next()) {
            Student student = new Student(resultSet);
            return student;
        } else {
            System.out.println("Not Found ID");
            return null;
        }

    }

    public List<Student> findAll() throws SQLException {
        String query = "SELECT * FROM Student";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Student student = new Student(resultSet);
            students.add(student);
        }
        return students;
    }

    public boolean updateScore(Student student) throws SQLException {
        String query = "UPDATE Student SET average_score = " + student.getAverageScore()
        + "WHERE ID = " + student.getID();
        Statement statement = getConnection().createStatement();
        int result = statement.executeUpdate(query);
        if(result != 0) {
            return true;
        }
        return false;
    }

    public List<Student> findByName(String name) throws SQLException{
        String query ="SELECT * FROM Student WHERE last_name LIKE'"+name+"%'";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Student> studentList = new ArrayList<>();
        while (resultSet.next()){
            Student student = new Student(resultSet);
            studentList.add(student);
        }
        return studentList;
    }

    public boolean deleteStudent(int id) throws SQLException{
        String query = "DELETE * FROM Student WHERE ID='"+id+"'";
        Statement statement = getConnection().createStatement();
        int resultSet = statement.executeUpdate(query);
        if(resultSet != 0){
            System.out.println("Deleted!!");
            return true;
        }
        return false;
    }
    public List<Student> findByCities(String city1,String city2) throws SQLException{
        String query ="SELECT * FROM Student WHERE city ='"+city1+"'"+" OR city ='"+city2+"'";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Student> studentList = new ArrayList<>();
        while (resultSet.next()){
           Student student = new Student(resultSet);
            studentList.add(student);
        }
        return studentList;
    }

    public boolean updateDob(Student student) throws SQLException{
        String query = "UPDATE Student SET dob ='"+student.getDob()+"'"+ "WHERE ID="+student.getID();
        Statement statement = getConnection().createStatement();
        int resultSet = statement.executeUpdate(query);
        if(resultSet != 0){
            return true;
        }
        return false;
    }
    public boolean updateAllScore(double score) throws SQLException{
        int length = findAll().size()+1;
        String query = "UPDATE Student SET average_score ="+score+" WHERE ID BETWEEN 0 AND "+length;
        Statement statement = getConnection().createStatement();
        int resultSet = statement.executeUpdate(query);
        if(resultSet !=0){
            return true;
        }
        return false;
    }
    public int generateId() throws SQLException {
        String query = "select max(id) as maxId from student ";
        Statement stmt = getConnection().createStatement();
        ResultSet resultSet = stmt.executeQuery(query);
        if (resultSet.next()) {
            return resultSet.getInt("maxId") + 1;
        } else {
            return 0;
        }
    }
    public List<Student> searchByMajor(String major) throws SQLException{
        String query = "SELECT * FROM Student WHERE major='"+major+"'";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Student> studentList = new ArrayList<>();
        while(resultSet.next()){
            Student student = new Student();
            studentList.add(student);
        }
        return studentList;
    }

    public Double averageScoreStudent() throws  SQLException{
        String query = "SELECT AVG(average_score) as AVERAGE from Student";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            System.out.println("Average Score Is");
            double mediumScore = resultSet.getDouble(1);
            return mediumScore;
        }
        return null;
    }
    public boolean modifyDataStudent(Student student) throws SQLException{
       String query = "UPDATE Student SET last_name ='"+student.getLastName()+"', first_name ='"+student.getFirstName()+"',city ='"+student.getCity()+"', average_score ="+student.getAverageScore()+",gender='"+student.getGender()+
               "', dob='"+student.getDob()+"'"+" WHERE ID="+student.getID(); ;
        Statement statement = getConnection().createStatement();
        int resultSet = statement.executeUpdate(query);
        if(resultSet != 0){
            return true;
        }
        return false;
    }
    public Student sortByScore() throws SQLException{
        String query = "SELECT * FROM Student  ORDER BY average_score ASC";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            Student student1 = new Student(resultSet);
            System.out.println(student1);
        }
            return null;
    }

    public Student scoreMax() throws  SQLException{
        String query = "SELECT * FROM Student WHERE average_score IN (SELECT MAX(average_score) FROM Student)";
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
            Student student = new Student(resultSet);
            System.out.println(student);
        }
        return null;
    }
}
