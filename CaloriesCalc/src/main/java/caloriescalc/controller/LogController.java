package caloriescalc.controller;

import caloriescalc.model.ConsumedFood;
import caloriescalc.model.FoodList;
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

import java.io.IOException;

public class LogController {

    @FXML
    private TableView<ConsumedFood> logTable;

    @FXML
    private TableColumn<ConsumedFood, String> nameCol;

    @FXML
    private TableColumn<ConsumedFood, String> calCol;

    @FXML
    private TableColumn<ConsumedFood, String> fatCol;

    @FXML
    private TableColumn<ConsumedFood, String> carbCol;

    @FXML
    private TableColumn<ConsumedFood, String> protCol;

    @FXML
    private TableColumn<ConsumedFood, String> dateCol;

    private String username;
    private FoodList logList;

    public void onClickButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/secondary.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<SecondaryController>getController().initdata(username);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void initdata(FoodList log, String username){
        this.username=username;
        this.logList = log;

        System.out.println(this.logList + " is the loglist.");

        nameCol.setCellValueFactory(new PropertyValueFactory<ConsumedFood, String>("name"));
        calCol.setCellValueFactory(new PropertyValueFactory<ConsumedFood, String>("calories"));
        fatCol.setCellValueFactory(new PropertyValueFactory<ConsumedFood, String>("fat"));
        protCol.setCellValueFactory(new PropertyValueFactory<ConsumedFood, String>("protein"));
        carbCol.setCellValueFactory(new PropertyValueFactory<ConsumedFood, String>("carbo"));
        dateCol.setCellValueFactory(new PropertyValueFactory<ConsumedFood, String>("date"));

        logTable.getItems().setAll(logList.getData());
    }

}
