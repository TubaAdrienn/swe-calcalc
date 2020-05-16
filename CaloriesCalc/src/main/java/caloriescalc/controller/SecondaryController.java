package caloriescalc.controller;

import caloriescalc.dao.Database;
import caloriescalc.model.DataLog;
import caloriescalc.model.Food;
import caloriescalc.model.FoodList;
import caloriescalc.model.LogList;
import caloriescalc.util.BmiCalc;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.tinylog.Logger;
import javax.xml.bind.JAXBException;
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

    @FXML
    private ImageView maleImg;

    @FXML
    private ImageView femaleImg;

    private FoodList foodList=new FoodList();
    private LogList loglist;
    private DataLog dataLog=new DataLog();
    private String userName;
    private double currentCal=0, currentFat=0, currentProt=0, currentCarb=0;

    public void initdata(String userName){
        this.userName = userName;
        dataLog=new DataLog(userName);
        Logger.debug("Username is: {}",dataLog.getUsername());
    }

    public void setImages(String male, String female){
        maleImg.setImage(new Image(getClass().getResource(male).toExternalForm()));
        femaleImg.setImage(new Image(getClass().getResource(female).toExternalForm()));
        Logger.info("Images set.");
    }

    public void loadData(String foodData, String loggedData) throws JAXBException {
        foodList = (FoodList) Database.loadXML(FoodList.class,foodData );
        Logger.info("Food data loaded.");
        loglist = (LogList) Database.loadXML(LogList.class,loggedData);
        Logger.info("Logged data loaded");
        Logger.debug("Logged data {}", loglist);
    }

    @FXML
    public void  initialize() throws Exception {
        setImages("/images/male.png", "/images/female.png" );
        loadData("/data/food.xml", "/data/consumedfood.xml");
        List<String> lista=foodList.getData().stream().map(Food::getName).sorted().collect(Collectors.toList());
        foodBox.getItems().addAll(lista);
    }

    public void setTextToBMI(String text, String color){
        bmiValue.setText(text);
        bmiValue.setStyle("-fx-text-fill: "+color+";");
    }

    public void bmiCalc(ActionEvent calcBMIEvent) throws IOException {
        if (weightField.getText().isEmpty() || heightField.getText().isEmpty()) {
            setTextToBMI("Nincs érték.", "red");
            Logger.error("Empty field. Value required.");
        } else {
            try {
                int weight = Integer.parseInt(weightField.getText());
                int height = Integer.parseInt(heightField.getText());
                if (!(weight > 595 || height> 272 || weight<25 || height<50)) {
                    setTextToBMI(Double.toString(BmiCalc.bmiCalculation(weight,height)), "black");
                } else {
                    setTextToBMI("Irreális érték.", "red");
                    Logger.error("Irreal value. Good one required.");

                }
            }catch (Exception e) {
                setTextToBMI("Számot adj meg.","red");
                Logger.error("Typed value is not a number.");
            }
        }
    }

    public void zeroValues(ActionEvent actionEvent) {
        dataLog.zeroValues();
        setNutrients();
    }

    public void setNutrients(){
        allCaloriesText.setText(Double.toString(Rounder.roundOff(dataLog.getCal())));
        allCarbText.setText(Double.toString(Rounder.roundOff(dataLog.getCarb())));
        allFatText.setText(Double.toString(Rounder.roundOff(dataLog.getFat())));
        allProteinText.setText(Double.toString(Rounder.roundOff(dataLog.getProt())));
        Logger.info("New values set.");
    }

    public void addPortionToCurrent(Food foodItem, double gramsInput){
        dataLog.addToCal(foodItem.getCalPortion(gramsInput));
        dataLog.addToCarb(foodItem.getCarboPortion(gramsInput));
        dataLog.addToFat(foodItem.getFatPortion(gramsInput));
        dataLog.addToProt(foodItem.getProteinPortion(gramsInput));
    }

    public void setNewValues() throws Exception{
        if(foodBox.getValue()!=null)
            try {
                double gramsInput = Double.parseDouble(gramsField.getText());
                if (gramsInput >= 0) {
                    String chosenFood = (String) foodBox.getValue();
                    Food foodItem = foodList.getFoodItemByName(chosenFood);
                    addPortionToCurrent(foodItem, gramsInput);
                    setNutrients();
                }
            } catch (Exception e){
                Logger.error("Value is not right. Try again.");
            }
    }

    public void addPortion(ActionEvent setValues) throws Exception{
        setNewValues();
        setNutrients();
    }


    public void saveValues(ActionEvent actionEvent) throws Exception {
        if(!dataLog.isEverythingZero()){
            if(loglist.getData()==null){
                dataLog.setUsername(userName);
                List<DataLog> lista=List.of(dataLog);
                loglist.setData(lista);
                Logger.debug("Data to log: {}", loglist.getData());
                Database.saveXML(loglist);
            }
            else {
                dataLog.setUsername(userName);
                loglist.getData().add(dataLog);
                Logger.debug("Data to log: {}", loglist.getData());
                Database.saveXML(loglist);
            }
            loadLogScene(actionEvent);

        }
        else{
            System.out.println("Nincs mit menteni.");
        }

    }

    public void goToLogs(ActionEvent actionEvent) throws IOException {
        loadLogScene(actionEvent);
    }

    public void loadLogScene(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/useraddedlog.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<LogController>getController().initdata(loglist, userName);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        Logger.info("New scene successfully set.");
    }

}
