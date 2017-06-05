/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *
 * @author ricardo
 */
public class DBConnection {
  //conn stores connection info from DBMS (Database Management System)
  private Connection conn;
  //stmt is used to send commands to the DBMS
  private Statement stmt;
  
  public DBConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      String connection="jdbc:mysql://localhost/ride";
      String user="root", password="";
      conn = DriverManager.getConnection(connection, user, password);
      stmt = conn.createStatement();
 
      System.out.println("Cool Bananas!");
 
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error: something went wrong...");
    }
  }
  
  public static void main(String args[]) {
    DBConnection t = new DBConnection();
  }

}
