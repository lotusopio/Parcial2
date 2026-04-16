package co.edu.poli.examen2_Nustes.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    private ConexionBD() throws Exception {
    	String url = "jdbc:mysql://localhost:3307/examen_2_nustes?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String user = "root";
        String pass = "";

        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection(url, user, pass);
    }

    public static ConexionBD getInstancia() throws Exception {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }

    public Connection getConexion() throws Exception {
        if (conexion == null || conexion.isClosed()) {
            instancia = new ConexionBD();
            return instancia.conexion;
        }
        return conexion;
    }
}