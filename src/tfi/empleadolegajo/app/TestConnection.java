package tfi.empleadolegajo.app;

import tfi.empleadolegajo.config.DatabaseConnection;
import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Conexion OK!");
        } catch (Exception e) {
            System.out.println("Error en la conexión:");
            e.printStackTrace();
        }

    }
}