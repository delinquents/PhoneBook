package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import sample.Datasource.Data;

import javax.swing.*;

import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.loadList();
        primaryStage.setTitle("Contact Data");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();
    }
    @Override
    public void init() {
       if(!Data.getInstance().open()){
           JOptionPane.showConfirmDialog(null,"Couldn't open connection!");
           Platform.exit();
       }
    }
    @Override
    public void stop() {
        Data.getInstance().close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
