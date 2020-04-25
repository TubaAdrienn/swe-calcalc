package caloriescalc.controller;

import caloriescalc.dao.Database;
import caloriescalc.model.BmiCalc;
import caloriescalc.model.ConsumedFood;
import caloriescalc.model.FoodList;
import caloriescalc.util.Rounder;
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
import java.time.LocalDate;
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

    private FoodList foodList, loggedFoodList=new FoodList();
    private String userName;
    private double currentCal=0, currentFat=0, currentProt=0, currentCarb=0;

    public void initdata(String userName){
        this.userName = userName;
        System.out.println(this.userName + "is the username.");
    }


    @FXML
    public void  initialize() throws Exception {
        foodList = new FoodList();
        File databasePath = new File("food.xml");
        File logPath=new File("consumedfood.xml");
        foodList = (FoodList) Database.loadFood(foodList.getClass(), databasePath);
        List<String> lista=foodList.getData().stream().map(ConsumedFood::getName).sorted().collect(Collectors.toList());
        foodBox.getItems().addAll(lista);
        if(logPath.length()!=0) {
            loggedFoodList = Database.loadFood(FoodList.class, logPath);
            System.out.println("EZ ITT AMIT BETÖLTÖTTEM: ");
            System.out.println(loggedFoodList);
        } else{
            System.out.println("Ez üres, babey");
        }
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
        allCaloriesText.setText(Double.toString(Rounder.roundOff(currentCal)));
        allCarbText.setText(Double.toString(Rounder.roundOff(currentCarb)));
        allFatText.setText(Double.toString(Rounder.roundOff(currentFat)));
        allProteinText.setText(Double.toString(Rounder.roundOff(currentProt)));
    }

    public void zeroValues(ActionEvent actionEvent) {
        currentCal=currentFat=currentProt=currentCarb=0;
        allCaloriesText.setText("0");
        allCarbText.setText("0");
        allFatText.setText("0");
        allProteinText.setText("0");
    }

    public void loadLogScene(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/useraddedlog.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<LogController>getController().initdata(loggedFoodList, userName);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void goToLogs(ActionEvent actionEvent) throws IOException {
        loadLogScene(actionEvent);
    }

    public void calculateNewValues() throws Exception{
        if(foodBox.getValue()!=null)
            try {
                double gramsInput = Double.parseDouble(gramsField.getText());
                if (gramsInput >= 0) {
                    String chosenFood = (String) foodBox.getValue();
                    ConsumedFood foodItem = foodList.getFoodItemByName(chosenFood);
                    currentCal += foodItem.getCalPortion(gramsInput);
                    currentProt += foodItem.getProteinPortion(gramsInput);
                    currentFat += foodItem.getFatPortion(gramsInput);
                    currentCarb += foodItem.getCarboPortion(gramsInput);
                }
            } catch (Exception e){
                System.out.println("That's not right, buddy.");
        }
    }

    public void saveValues(ActionEvent actionEvent) throws Exception {

        if(currentCarb+currentFat+currentProt+currentCal>0){
            ConsumedFood foodToLog = new ConsumedFood(userName, Rounder.roundOff(currentCal),
                    Rounder.roundOff(currentFat), Rounder.roundOff(currentCarb),
                    Rounder.roundOff(currentProt), LocalDate.now());
            System.out.println("Food to log: ");
            System.out.println(foodToLog);
            System.out.println(loggedFoodList);
            System.out.println("Eddig megvagyok.");
            if(loggedFoodList.getData()==null){
                List<ConsumedFood> lista=List.of(foodToLog);
                loggedFoodList.setData(lista);
                System.out.println(loggedFoodList.getData());
                Database.saveLog(loggedFoodList);
            }
            else {
                loggedFoodList.getData().add(foodToLog);
                System.out.println(loggedFoodList.getData());
                Database.saveLog(loggedFoodList);
            }
            loadLogScene(actionEvent);
            currentCal=currentFat=currentProt=currentCarb=0;

        }
        else{
            System.out.println("Nincs mit menteni.");
        }

    }
}


