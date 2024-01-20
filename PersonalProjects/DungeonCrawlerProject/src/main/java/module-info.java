module com.example.dungeoncrawlerproject2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dungeoncrawlerproject2 to javafx.fxml;
    exports com.example.dungeoncrawlerproject2;
}