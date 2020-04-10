module caloriescalc.main {
    requires javafx.controls;
    requires javafx.fxml;

    opens caloriescalc.main to javafx.fxml;
    exports caloriescalc.main;
}