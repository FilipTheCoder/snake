module com.trampota.snakegame {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                            
    opens com.trampota.snakegame to javafx.fxml;
    exports com.trampota.snakegame;
}