package projectuaspbo;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import projectuaspbo.model.User;

public class FXMLDocumentController implements Initializable {


    @FXML
    private ProgressBar healthBar;

    @FXML
    private Label statMeter;

    @FXML
    private Label healthLabel;

    @FXML
    private ProgressBar magicBar;

    @FXML
    private Label magicLabel;

    @FXML
    private AnchorPane layerScene;

    @FXML
    private ProgressBar strengthBar;

    @FXML
    private Label strengthLabel;
    
    private User currentUSer;

    BigDecimal progress1 = new BigDecimal(String.format("%.2f", 0.0));
    BigDecimal progress2 = new BigDecimal(String.format("%.2f", 0.0));
    BigDecimal progress3 = new BigDecimal(String.format("%.2f", 0.0));

    int statMeters = 240;
    @FXML
    private Button addStrength;
    @FXML
    private Button addMagic;
    @FXML
    private Button addHealth;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    protected int StrengthStat;
    protected int MagicStat;
    protected int HealthStat;
    
    
     @FXML
    private void startBtn(ActionEvent event) throws IOException {
       
        root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("No Stats Left");
        alert.setHeaderText(null);
        alert.setContentText("There's no stats left!");
        alert.showAndWait();
    }

    @FXML
    void increaseHealth(ActionEvent event) {
        if (statMeters == 0) {
            showAlert();
            return;
        }
        if (progress1.doubleValue() < 1 && statMeters >= 10) {
            progress1 = new BigDecimal(String.format("%.2f", progress1.doubleValue() + 0.1));
            healthBar.setProgress(progress1.doubleValue());
            healthLabel.setText("Health : " + Integer.toString((int) Math.round(progress1.doubleValue() * 100)) + "%");
            statMeters -= 10;
            HealthStat += 10;
            statMeter.setText("Stats Left: " + statMeters);
        }
        layerScene.requestFocus();
    }

    @FXML
    void increaseMagic(ActionEvent event) {
        if (statMeters == 0) {
            showAlert();
            return;
        }
        if (progress2.doubleValue() < 1 && statMeters >= 10) {
            progress2 = new BigDecimal(String.format("%.2f", progress2.doubleValue() + 0.1));
            magicBar.setProgress(progress2.doubleValue());
            magicLabel.setText("Magic : " + Integer.toString((int) Math.round(progress2.doubleValue() * 100)));
            statMeters -= 10;
            MagicStat += 10;
            statMeter.setText("Stats Left: " + statMeters);
        }
        layerScene.requestFocus();
    }

    @FXML
    void increaseStrength(ActionEvent event) {
        if (statMeters == 0) {
            showAlert();
            return;
        }
        if (progress3.doubleValue() < 1 && statMeters >= 10) {
            progress3 = new BigDecimal(String.format("%.2f", progress3.doubleValue() + 0.1));
            strengthBar.setProgress(progress3.doubleValue());
            strengthLabel.setText("Strength : " + Integer.toString((int) Math.round(progress3.doubleValue() * 100)));
            statMeters -= 10;
            StrengthStat += 10;
            statMeter.setText("Stats Left: " + statMeters);
        }
        layerScene.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        layerScene.requestFocus();
    }



   


}
