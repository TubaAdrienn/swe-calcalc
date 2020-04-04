module calories.calc {
    requires javafx.controls;
    requires javafx.fxml;

    opens calories.calc to javafx.fxml;
    exports calories.calc;
}