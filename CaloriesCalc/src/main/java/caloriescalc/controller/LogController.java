package caloriescalc.controller;

import caloriescalc.model.Journal;
import caloriescalc.model.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;


public class LogController {

    @FXML
    private TableView<UserData> logTable;

    @FXML
    private TableColumn<UserData, String> nameCol;

    @FXML
    private TableColumn<UserData, String> calCol;

    @FXML
    private TableColumn<UserData, String> fatCol;

    @FXML
    private TableColumn<UserData, String> carbCol;

    @FXML
    private TableColumn<UserData, String> protCol;

    @FXML
    private TableColumn<UserData, String> dateCol;

    @FXML
    private TableColumn<UserData, String> BMICol;

    private String username;

    private Journal journal;

    public void goBackToCalculator(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/mainpage.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<CalculatorController>getController().initdata(username);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void initdata(Journal log, String username){
        this.username=username;
        this.journal = log;
        Logger.debug((this.journal + " is the loglist."));
        setCells();
    }

    public void setCells(){
        nameCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("username"));
        calCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("cal"));
        fatCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("fat"));
        protCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("prot"));
        carbCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("carb"));
        BMICol.setCellValueFactory(new PropertyValueFactory<UserData, String>("bmi"));
        dateCol.setCellValueFactory(new PropertyValueFactory<UserData, String>("date"));
        logTable.getItems().setAll(journal.getData());
    }

}