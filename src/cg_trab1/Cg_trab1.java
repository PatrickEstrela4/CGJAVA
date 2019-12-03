/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cg_trab1;

import cg_trab1.controller.TelaController;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
//import java.awt.Image;
//import java.awt.image.ImageObserver;
//import java.awt.image.ImageProducer;
import javafx.scene.image.Image;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @authors Patrick, Rafael
 * @version 1.0
 * @since 2017
 */
public class Cg_trab1 extends Application {

    public static double widtht1;
    public static double heightt2;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));
        //AnchorPane rot = FXMLLoader.load(getClass().getResource("view/FXMLDocument.fxml"));

        Scene scene = new Scene(p);

        primaryStage.getIcons().add(new Image(getClass().getResource("view/images/geometry_3015.png").toExternalForm()));
        primaryStage.setTitle("Geometry 2.1");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



            
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
