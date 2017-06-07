/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ricardo
 */
public class ConnectionFactory {    
    public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) throws DAOException {
        close(conn, ps, rs);
    }
        
    public static void closeConnection(Connection conn, PreparedStatement ps) throws DAOException {
        close(conn, ps, null);
    }

    public static void closeConnection(Connection conn) throws DAOException {
        close(conn, null, null);
    }

    private static void close(Connection conn, PreparedStatement ps, ResultSet rs) throws DAOException {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } 
        catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception.fillInStackTrace());
        }
    }

    public static Connection getConnection() throws DAOException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            
            /*Se necessário renomear o banco!!*/
            String connection="jdbc:postgresql://localhost:5432/banco_projeto";
            
            /*Se necessário mudar os valores de user e password!!*/
            String user="postgres", password="1234";

            Connection conn = DriverManager.getConnection(connection, user, password);
            return conn;
        } 
        catch (SQLException exception) {
            throw new DAOException(exception.getMessage(), exception.fillInStackTrace());
        } 
        catch (ClassNotFoundException exception) {
            throw new DAOException(exception.getMessage(), exception.fillInStackTrace());
        } 
        catch (InstantiationException exception) {
            throw new DAOException(exception.getMessage(), exception.fillInStackTrace());
        } 
        catch (IllegalAccessException exception) {
            throw new DAOException(exception.getMessage(), exception.fillInStackTrace());
        }
    }
}