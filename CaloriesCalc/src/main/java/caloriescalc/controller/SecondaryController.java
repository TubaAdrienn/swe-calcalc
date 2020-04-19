package caloriescalc.controller;

import caloriescalc.model.BmiCalc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private Label bmiValue;


    public void bmiCalc(ActionEvent calcBMIEvent) throws IOException {

            if (weightField.getText().isEmpty() || heightField.getText().isEmpty()) {
                bmiValue.setText("Nincs érték.");
                bmiValue.setStyle("-fx-text-fill: red;");
            } else {
                try {
                    int weight = Integer.parseInt(weightField.getText());
                    int height = Integer.parseInt(heightField.getText());
                    if (!(weight > 595 || height> 272 || weight<25 || height<50)) {
                        double bmiVal=new BmiCalc().bmiCalculation(weight,height);
                        bmiValue.setText(Double.toString(bmiVal));
                        bmiValue.setStyle("-fx-text-fill: black;");
                    } else {
                        bmiValue.setText("Irreális érték.");
                        bmiValue.setStyle("-fx-text-fill: red;");
                    }
                }catch (Exception e) {
                    bmiValue.setText("Számot adj meg.");
                    bmiValue.setStyle("-fx-text-fill: red;");
                }
            }
        }

    public void goToLogs(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/lasttenadded.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<SecondaryController>getController();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}


