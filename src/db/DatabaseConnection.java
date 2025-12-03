package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Instancia Singleton para reusar la conexión
    private static DatabaseConnection instance;
    private Connection connection;

    // Configuración de la base de datos (credenciales sensibles)
    private static final String JDBC_URL = "jdbc:mysql://almacenitla-db-itla-3837.e.aivencloud.com:25037/almacenitlafinal";
    private static final String USERNAME = "avnadmin";
    private static final String PASSWORD = "AVNS_pPa2xcIg1UbjOzcsoMg";

    // Constructor privado: inicializa la conexión JDBC
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("No se encontró el controlador JDBC", e);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos", e);
        }
    }

    // Devuelve la instancia Singleton (crea si no existe)
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Devuelve la conexión JDBC para uso por repositorios
    public Connection getConnection() {
        return connection;
    }
}
