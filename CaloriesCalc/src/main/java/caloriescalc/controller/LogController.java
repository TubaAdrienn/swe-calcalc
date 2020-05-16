package caloriescalc.controller;

import caloriescalc.model.Journal;
import caloriescalc.model.JournalItem;
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
import org.tinylog.Logger;
import java.io.IOException;


public class LogController {

    @FXML
    private TableView<JournalItem> logTable;

    @FXML
    private TableColumn<JournalItem, String> nameCol;

    @FXML
    private TableColumn<JournalItem, String> calCol;

    @FXML
    private TableColumn<JournalItem, String> fatCol;

    @FXML
    private TableColumn<JournalItem, String> carbCol;

    @FXML
    private TableColumn<JournalItem, String> protCol;

    @FXML
    private TableColumn<JournalItem, String> dateCol;

    private String username;

    private Journal journal;

    public void goBackToCalculator(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/secondary.fxml"));
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
        nameCol.setCellValueFactory(new PropertyValueFactory<JournalItem, String>("username"));
        calCol.setCellValueFactory(new PropertyValueFactory<JournalItem, String>("cal"));
        fatCol.setCellValueFactory(new PropertyValueFactory<JournalItem, String>("fat"));
        protCol.setCellValueFactory(new PropertyValueFactory<JournalItem, String>("prot"));
        carbCol.setCellValueFactory(new PropertyValueFactory<JournalItem, String>("carb"));
        dateCol.setCellValueFactory(new PropertyValueFactory<JournalItem, String>("date"));
        logTable.getItems().setAll(journal.getData());
    }

}