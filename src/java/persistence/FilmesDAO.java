package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Movie;
import model.ResultadoFilmes;

public class FilmesDAO {
    private DBConnection banco;
    
    public FilmesDAO(){
        this.banco = new DBConnection();
    }
    
    public ResultadoFilmes buscaFilmes(String nome){
        ResultSet rs = null;
        ResultadoFilmes rf = new ResultadoFilmes();
        
        String SQL = "SELECT * FROM busca_ajax('" + nome + "');";
        
        try { 
            banco.stmt.execute(SQL);
            rs = banco.stmt.getResultSet();

            /*Enquanto não chegar no fim do select recupera os dados*/
            int i = 0;
            while (rs.next()){
                // 1 - title
                // 2 - year
                Movie m = new Movie();
                
                m.setName(rs.getString(1));
                m.setYear(rs.getString(2));
                
                rf.popula(m);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rf;
    }
}