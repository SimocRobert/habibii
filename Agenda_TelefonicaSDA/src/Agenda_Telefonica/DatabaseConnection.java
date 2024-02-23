package Agenda_Telefonica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Aceasta clasa incearca sa imi faca conexiunea la baza de date
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/phonebook";
    private static final String USER = "username"; // Utilizatorul MySQL
    private static final String PASSWORD = "password"; // Parola MySQL

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
