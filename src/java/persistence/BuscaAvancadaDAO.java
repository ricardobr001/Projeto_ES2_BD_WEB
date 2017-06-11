/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

//import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import model.Actor;
import model.Movie;
/**
 *
 * @author ricardo
 */
public class BuscaAvancadaDAO {
    private Connection conn;
    
    /*Sahudy*/
    private Statement stmt;
    
    public BuscaAvancadaDAO() /*throws DAOException*/{
        /*Código do tutorial abaixo e ele usa o throws*/
        //this.conn = ConnectionFactory.getConnection();
        
        /*Código da Sahudy*/
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            String connection="jdbc:postgresql://localhost:5432/banco_projeto";
            String user="postgres", password="1234";
            conn = DriverManager.getConnection(connection, user, password);
            stmt = conn.createStatement();

            //System.out.println("Cool Bananas!");

            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: something went wrong...");
        }
    }
    
    public void busca() /*throws SQLException */{
        ResultSet rs = null;
        
        /*Código do tutorial abaixo e ele usa o throws*/
        //String SQL = "SELECT * FROM movie LIMIT 100;";
        //stmt = conn.prepareStatement(SQL);
        
        /*Sahudy*/
        //SQL para testar o select
        String SQL = "SELECT * FROM movie LIMIT 100;";
        try { 
            stmt.execute(SQL);
           //System.out.println("Depois do stmt.execute");
            rs = stmt.getResultSet();

            /*Enquanto não chegar no fim do select recupera os dados*/
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
        
        //return;
    }
    
    public void buscaAvancada(Vector res, String[] nomes, String[] idiomas) {
        ResultSet rs = null;
        
        String SQL =    "SELECT mlg.title, mlg.year, mlg.language, mlg.genre, act.name, ma.character " +
                        "FROM movieactor AS ma " +
                        "INNER JOIN ( " +
                        "            SELECT a.actorid, a.name " +
                        "            FROM actors AS a " +
                        "            WHERE a.name = '" + nomes[0] + "' OR a.name = '" + nomes[1] + "' " +
                        "        ) AS act ON act.actorid = ma.actorid " +
                        "INNER JOIN ( " +
                        "        	SELECT m.movieid, m.title, m.year, lg.language, gm.genre " +
                        "        	FROM movie AS m " +
                        "        	INNER JOIN ( " +
                        "            		    SELECT lm.movieid, lm.language " +
                        "            		    FROM languagesmovies AS lm " +
                        "            		    WHERE lm.language = '" + idiomas[0] +"' OR lm.language = '"+ idiomas[1] + "' " +
                        "            		) AS lg ON lg.movieid = m.movieid " +
                        "            INNER JOIN genresmovies AS gm ON gm.movieid = m.movieid " +
                        "	    ) AS mlg ON mlg.movieid = ma.movieid;";
        
        try { 
            stmt.execute(SQL);
            rs = stmt.getResultSet();

            /*Enquanto não chegar no fim do select recupera os dados*/
            while (rs.next()){
                // 1 - title
                // 2 - year
                // 3 - language
                // 4 - genre
                // 5 - name
                // 6 - character
                Actor a = new Actor();
                Movie m = new Movie();
                
                m.setName(rs.getString(1));
                m.setYear(rs.getString(2));
                m.setLanguages(rs.getString(3));
                m.setGenres(rs.getString(4));
                a.setName(rs.getString(5));
                a.setCharacter(rs.getString(6));
                
                res.addElement(m);
                res.addElement(a);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void fecha(){
        try {
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /*Teste de conexão com o banco*/
    /*public static void main(String args[]) {
        BuscaAvancadaDAO t = new BuscaAvancadaDAO();
        t.busca();
        t.fecha();

        //Executar esse arquivo para testar a conexão com o banco shit + F6
    }*/
}
