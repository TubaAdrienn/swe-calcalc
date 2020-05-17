package caloriescalc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import java.io.IOException;

public class LaunchController {

    @FXML
    private TextField nameText;

    @FXML
    private Label errorText;


    public void startAction(ActionEvent actionEvent) throws IOException {
        if (nameText.getText().isEmpty()) {
            errorText.setText("Give me a username!");
            Logger.error("Empty username.");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/mainpage.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<CalculatorController>getController().initdata(nameText.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("Successful initialization of second stage.");
        }

    }


}
