module cda.bibliotheque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;

    opens cda.bibliotheque.controller to javafx.fxml;
    opens cda.bibliotheque.controller.Author to javafx.fxml;
    opens cda.bibliotheque.controller.Book to javafx.fxml;
    opens cda.bibliotheque.controller.Editor to javafx.fxml;
    opens cda.bibliotheque.controller.Client to javafx.fxml;
    opens cda.bibliotheque.controller.Genre to javafx.fxml;

    exports cda.bibliotheque;
}
