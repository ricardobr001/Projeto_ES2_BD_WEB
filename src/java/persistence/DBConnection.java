package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {
    protected Connection conn;
    protected Statement stmt;
    
    public DBConnection(){
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String connection="jdbc:postgresql://localhost:5433/banco";
            String user="usuario_comum", password="usuario";
            
            conn = DriverManager.getConnection(connection, user, password);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: something went wrong...");
        }
    }
}
