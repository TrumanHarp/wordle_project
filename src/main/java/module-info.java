module trumanharp {
    requires javafx.controls;
    requires javafx.fxml;

    opens trumanharp to javafx.fxml;
    exports trumanharp;
}
