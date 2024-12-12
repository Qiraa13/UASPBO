/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package projectuaspbo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Box;
import projectuaspbo.model.Karakter;
import projectuaspbo.model.User;
import projectuaspbo.model.Enemy;

/**
 * FXML Controller class
 *
 * @author advan
 */
public class GameController implements Initializable {

    @FXML
    private Box Enemy;

    @FXML
    private Box User;

    @FXML
    private Button attackMagic;

    @FXML
    private Button attackStrength;

    @FXML
    private Button blockAttack;

    @FXML
    private Button healUser;
    private User Player;

    @FXML
    void attackWithMagic(ActionEvent event) {
        
    }

    @FXML
    void attackWithStrength(ActionEvent event) {

    }

    @FXML
    void blockingAttack(ActionEvent event) {

    }

    @FXML
    void healing(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
