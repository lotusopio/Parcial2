module co.edu.poli.examen2_Nustes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires io.github.cdimascio.dotenv.java;

    opens co.edu.poli.examen2_Nustes.vista to javafx.fxml, javafx.graphics;
    opens co.edu.poli.examen2_Nustes.controlador to javafx.fxml;
    exports co.edu.poli.examen2_Nustes.controlador;
}