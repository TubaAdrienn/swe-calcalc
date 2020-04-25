package caloriescalc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class PrimaryController {

    @FXML
    private TextField nameText;

    @FXML
    private Label errorText;

    public void startAction(ActionEvent actionEvent) throws IOException, JAXBException {
        if (nameText.getText().isEmpty()) {
            errorText.setText("Give me a good username!");
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/secondary.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<SecondaryController>getController().initdata(nameText.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }

    }


}
