module org.example.project2sem2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires org.slf4j;

    opens org.example.project2sem2 to javafx.fxml;
    exports org.example.project2sem2;
    exports org.example.project2sem2.Controller;
    opens org.example.project2sem2.Controller to javafx.fxml;
    exports org.example.project2sem2.Utils;
    opens org.example.project2sem2.Utils to javafx.fxml;
}