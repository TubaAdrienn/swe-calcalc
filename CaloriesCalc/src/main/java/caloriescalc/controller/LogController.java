package caloriescalc.controller;

import caloriescalc.model.DataLog;
import caloriescalc.model.Food;
import caloriescalc.model.FoodList;
import caloriescalc.model.LogList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;

public class LogController {

    @FXML
    private TableView<DataLog> logTable;

    @FXML
    private TableColumn<DataLog, String> nameCol;

    @FXML
    private TableColumn<DataLog, String> calCol;

    @FXML
    private TableColumn<DataLog, String> fatCol;

    @FXML
    private TableColumn<DataLog, String> carbCol;

    @FXML
    private TableColumn<DataLog, String> protCol;

    @FXML
    private TableColumn<DataLog, String> dateCol;

    private String username;
    private LogList logList;

    public void onClickButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/secondary.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<SecondaryController>getController().initdata(username);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void initdata(LogList log, String username){
        this.username=username;
        this.logList = log;

        System.out.println(this.logList + " is the loglist.");

        nameCol.setCellValueFactory(new PropertyValueFactory<DataLog, String>("username"));
        calCol.setCellValueFactory(new PropertyValueFactory<DataLog, String>("cal"));
        fatCol.setCellValueFactory(new PropertyValueFactory<DataLog, String>("fat"));
        protCol.setCellValueFactory(new PropertyValueFactory<DataLog, String>("prot"));
        carbCol.setCellValueFactory(new PropertyValueFactory<DataLog, String>("carb"));
        dateCol.setCellValueFactory(new PropertyValueFactory<DataLog, String>("date"));

        logTable.getItems().setAll(logList.getData());
    }

}