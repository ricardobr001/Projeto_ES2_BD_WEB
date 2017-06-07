/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

import java.sql.*;
import java.util.Vector;
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
      Class.forName("org.postgresql.Driver").newInstance();
      String connection="jdbc:postgresql://localhost:5432/banco_projeto";
      String user="postgres", password="1234";
      conn = DriverManager.getConnection(connection, user, password);
      stmt = conn.createStatement();
 
      System.out.println("Cool Bananas!");
 
      //conn.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error: something went wrong...");
    }
  }
  
  public static void main(String args[]) {
    DBConnection t = new DBConnection();
    t.buscaTeste();
    t.fecha();
    
    /*Executar esse arquivo para testar a conexão com o banco shit + F6*/
  }
  
  public void fecha(){
      try {
        conn.close();
      } catch (Exception e){
          e.printStackTrace();
      }
}
  
  public void buscaTeste(){
      ResultSet rs = null;
      Vector res = new Vector();
      String texto = "SELECT * FROM movie LIMIT 100;";
      //System.out.println(texto);
      
      try { 
          stmt.execute(texto);
         //System.out.println("Depois do stmt.execute");
          rs = stmt.getResultSet();
          
          while (rs.next()){
              // 1 - movieid
              // 2 - title
              // 3 - year
              System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
              //System.out.println(rs);
          }
      } 
      catch (SQLException e) {
          e.printStackTrace();
      }
  }

}
