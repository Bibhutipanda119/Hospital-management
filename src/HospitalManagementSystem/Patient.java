package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;


    }

    public void addPatient() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.next();
        System.out.print(" Enter Patient Age");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender");
        String gender = scanner.next();
        try{
            String query = " INSERT INTO patients(name,age,gender) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,name);//placeholder
            preparedStatement.setInt(2,age);//placeholder
            preparedStatement.setString(3,gender);//placeholder
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient added succesfully");
            }else{
                System.out.println("Failed to add Patient!!");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void viewPatient(){
        String query = "select *from patients";
        try{PreparedStatement preparedStatement= connection.prepareStatement(query);
            ResultSet resultSet= preparedStatement.executeQuery();
            System.out.println("Patients: ");
            // we use result set  interface beacuse of to create a pointer to print values or assiggn values in local variable
            System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                int age= resultSet.getInt("AGE");
                String gender= resultSet.getString("GENDER");
                System.out.printf("| %-10s | %-18s | %-8s | %-10s |\n", id, name, age, gender);
                System.out.println("+------------+--------------------+----------+------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE ID =?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();

    }
        return false;

}}
