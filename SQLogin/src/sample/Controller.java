package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class Controller {

    private static Connection connection;
    private static Statement command;
    private static String connectionString = "jdbc:mysql://url:portnum/databasename";


    @FXML
    private TextField textFieldName;

    @FXML
    private Button buttonSignUp;

    @FXML
    private TextField textFieldSurname;

    @FXML
    void SignUp(ActionEvent event) {
        String sql = "select * from users where name=? and surname=?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString,"username","password");
            command = connection.createStatement();
            //ResultSet rs = command.executeQuery("INSERT INTO info VALUES('"+name+"','"+surname+"','"+email+"')");
           ResultSet rs = command.executeQuery("SELECT * FROM users WHERE name ='"+textFieldName.getText()+"' and surname='"+textFieldSurname.getText()+"';");
            if(rs.next()){
                System.out.println("Succesfull Login");
                Stage za = new Stage();
                VBox ey = new VBox(20);
                Scene az = new Scene(ey,600,300);
                za.setScene(az);
                za.showAndWait();
            }
            else{
                System.out.println("Login failed");
            }


            command.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }
}

