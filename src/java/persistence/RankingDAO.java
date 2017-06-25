package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Actor;
import model.Movie;
import model.ResultadoRanking;

public class RankingDAO {
    private DBConnection banco;
    public RankingDAO(){
        this.banco = new DBConnection();
    }
    
    public ResultadoRanking Ranking(String g1, String g2, String pagina){
        ResultSet rs = null;
        ResultadoRanking rr = new ResultadoRanking();
        int numPagina = 1;

        if (pagina != null) {
            numPagina = Integer.parseInt(pagina);
        }

        g1 = "'" + g1 + "'";
        g2 = "'" + g2 + "'";
        
        String SQL =  "SELECT * FROM busca_ranking(" + g1 + ", " + g2 + ", " + numPagina + ");";
        
        try { 
            banco.stmt.execute(SQL);
            rs = banco.stmt.getResultSet();

            /*Enquanto não chegar no fim do select recupera os dados*/
            while (rs.next()){
                // 1 - quantidade de filmes
                // 2 - nome
                // 3 - sexo
                Actor a = new Actor();
  
                a.setName(rs.getString(2));
                a.setSex(rs.getString(3));
                
                rr.popula(a, rs.getInt(1));
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rr;
    }
}
