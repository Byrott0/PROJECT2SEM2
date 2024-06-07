module org.example.project2sem2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.project2sem2 to javafx.fxml;
    exports org.example.project2sem2;
}