package caloriescalc.controller;

import caloriescalc.dao.Database;
import caloriescalc.model.BmiCalc;
import caloriescalc.model.ConsumedFood;
import caloriescalc.model.FoodList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SecondaryController{

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private Label allCaloriesText;

    @FXML
    private Label allFatText;

    @FXML
    private Label allCarbText;

    @FXML
    private Label allProteinText;

    @FXML
    private TextField gramsField;

    @FXML
    private Label bmiValue;

    @FXML
    private ChoiceBox foodBox;

    private FoodList foodList;
    private String userName;
    private double currentCal=0, currentFat=0, currentProt=0, currentCarb=0;

    public void initdata(String userName){
        this.userName = userName;
        System.out.println(this.userName + "is the username.");
    }


    @FXML
    public void  initialize() throws Exception {
        foodList = new FoodList();
        Database dao = new Database();
        File databasePath = new File("food.xml");
        foodList = (FoodList) dao.loadFood(foodList.getClass(), databasePath);
        List<String> lista=foodList.getData().stream().map(ConsumedFood::getName).sorted().collect(Collectors.toList());
        foodBox.getItems().addAll(lista);

    }


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

    public void addPortion(ActionEvent setValues) throws Exception{
        calculateNewValues();
        allCaloriesText.setText(Double.toString(currentCal));
        allCarbText.setText(Double.toString(currentCarb));
        allFatText.setText(Double.toString(currentFat));
        allProteinText.setText(Double.toString(currentProt));
    }

    public void zeroValues(ActionEvent actionEvent) {
        currentCal=currentFat=currentProt=currentCarb=0;
        allCaloriesText.setText("0");
        allCarbText.setText("0");
        allFatText.setText("0");
        allProteinText.setText("0");
    }

    public void goToLogs(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/lasttenadded.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<SecondaryController>getController();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void calculateNewValues(){
        if(foodBox.getValue()!=null && !(gramsField.getText().isEmpty())){
            double gramsInput=-1;
            gramsInput=Double.parseDouble(gramsField.getText());
            if(gramsInput>=0) {
                String chosenFood = (String) foodBox.getValue();
                ConsumedFood foodItem = foodList.getFoodItemByName(chosenFood);
                currentCal += foodItem.getCalPortion(gramsInput);
                currentProt+=foodItem.getProteinPortion(gramsInput);
                currentFat+=foodItem.getFatPortion(gramsInput);
                currentCarb+=foodItem.getCarboPortion(gramsInput);
            }
        }
    }

}


