package caloriescalc.controller;

import caloriescalc.dao.DatabaseXML;
import caloriescalc.dao.JournalDao;
import caloriescalc.model.FoodItem;
import caloriescalc.model.FoodList;
import caloriescalc.model.Journal;
import caloriescalc.model.UserData;
import caloriescalc.util.CalculationHelper;
import com.mysql.cj.log.Log;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalculatorController {

    private JournalDao journal;

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
    private UserData userData;
    private String userName;

    @FXML
    public void  initialize() throws Exception {
        journal = JournalDao.getInstance();
        setImages("/images/male.png", "/images/female.png" );
        loadData();
        List<String> lista=foodList.getData().stream()
                .map(FoodItem::getName).sorted().collect(Collectors.toList());
        foodBox.getItems().addAll(lista);
    }

    public void initdata(String userName){
        this.userName = userName;
        userData =new UserData(userName);
        Logger.debug("Username is: {}", userData.getUsername());
    }

    /**
     * Sets the images of the male and female value tables
     *
     * @param male male image path
     * @param female female image path
     */
    private void setImages(String male, String female){
        maleImg.setImage(new Image(getClass().getResource(male).toExternalForm()));
        femaleImg.setImage(new Image(getClass().getResource(female).toExternalForm()));
        Logger.info("Images set.");
    }

    /**
     * Loads the data that contains the food items and the journal
     *
     * @throws JAXBException if a problem occurs during the loading
     */
    private void loadData() throws Exception {
        foodList = DatabaseXML.loadFood();
        Logger.info("Food data loaded.");
    }

    /**
     * Sets text to BMI {@link Label}
     *
     * @param text the text to be set
     * @param color the color of the text
     */
    private void setTextToBMI(String text, String color){
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
                    setTextToBMI(Double.toString(CalculationHelper.bmiCalculation(weight,height)), "black");
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
        userData.zeroValues();
        updateNutrients();
    }

    /**
     * Updates the texts representing the nutrients
     */
    private void updateNutrients(){
        allCaloriesText.setText(Double.toString(userData.getCal()));
        allCarbText.setText(Double.toString(userData.getCarb()));
        allFatText.setText(Double.toString(userData.getFat()));
        allProteinText.setText(Double.toString(userData.getProt()));
    }

    /**
     * Adds calculatied portions of the given {@link FoodItem}'s attributes to the {@link UserData}'s attributes.
     *
     * @param foodItem the food to calculate portions of
     * @param gramsInput amount of food
     */
    private void addPortionToCurrent(FoodItem foodItem, double gramsInput){
        userData.addToCal(foodItem.getCalPortion(gramsInput));
        userData.addToCarb(foodItem.getCarboPortion(gramsInput));
        userData.addToFat(foodItem.getFatPortion(gramsInput));
        userData.addToProt(foodItem.getProteinPortion(gramsInput));
    }

    private void setNewValues(){
        if(foodBox.getValue()!=null)
            try {
                double gramsInput = Double.parseDouble(gramsField.getText());
                if (gramsInput >= 0) {
                    String chosenFood = (String) foodBox.getValue();
                    FoodItem foodItem = foodList.getFoodItemByName(chosenFood);
                    addPortionToCurrent(foodItem, gramsInput);
                    updateNutrients();
                }
            } catch (Exception e){
                Logger.error("Value is not right. Try again.");
            }
    }

    public void addPortion(ActionEvent setValues) throws Exception{
        setNewValues();
    }

    public void setBMI(){
        if (bmiValue.getText().compareTo("0")!=0){
            userData.setBmi(bmiValue.getText());
            Logger.debug("BMI value is: {}", bmiValue.getText());
        }
        else{
            userData.setBmi("Nincs adat.");
        }
    }

    public void saveValues(ActionEvent actionEvent) throws Exception {
        if(!userData.isEverythingZero()){
                setBMI();
                Logger.debug("Userdata: {}"+userData);
                journal.persist(userData);
                loadLogScene(actionEvent);

        }
        else{
            Logger.debug("Nothing to save.");
        }

    }

    public void goToLogs(ActionEvent actionEvent) throws IOException {
        loadLogScene(actionEvent);
    }

    private void loadLogScene(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxmlfiles/logpage.fxml"));
        Parent root = fxmlLoader.load();
        fxmlLoader.<LogController>getController().initdata(userName);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        Logger.info("New scene successfully set.");
    }

}