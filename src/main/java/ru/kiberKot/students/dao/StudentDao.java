package ru.kiberKot.students.dao;

import org.springframework.stereotype.Component;
import ru.kiberKot.students.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class StudentDao {

    private static int STUDENT_COUNT;

    //TODO надо убрать в отдельный файл
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Student> index() {
        List<Student> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Students";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Student student = new Student();

                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("firstname"));
                student.setMiddleName(resultSet.getString("middlename"));
                student.setLastName(resultSet.getString("lastname"));
                student.setDateOfBirth(resultSet.getString("dateofbirth"));
                student.setGroup(resultSet.getString("groupid"));

                people.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }

    public Student show(int id) {
        Student student = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Students WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setFirstName(resultSet.getString("firstname"));
            student.setMiddleName(resultSet.getString("middlename"));
            student.setLastName(resultSet.getString("lastname"));
            student.setDateOfBirth(resultSet.getString("dateofbirth"));
            student.setGroup(resultSet.getString("groupid"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }

    public void save(Student student){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Students VALUES(DEFAULT ,?,?,?,?,?)");
            preparedStatement.setString(1,student.getFirstName());
            preparedStatement.setString(2,student.getMiddleName());
            preparedStatement.setString(3,student.getLastName());
            preparedStatement.setString(4,student.getDateOfBirth());
            preparedStatement.setString(5,student.getGroup());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //TODO разобраться с update
    public void update(int id, Student updateStudent){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Students SET fisrtname=?, middlename=?, lastname=?, dateofbirth=?, groupid=? WHERE id=?");
            preparedStatement.setString(1,updateStudent.getFirstName());
            preparedStatement.setString(2,updateStudent.getMiddleName());
            preparedStatement.setString(3,updateStudent.getLastName());
            preparedStatement.setString(4,updateStudent.getDateOfBirth());
            preparedStatement.setString(5,updateStudent.getGroup());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id){
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM Students WHERE id=?");
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
