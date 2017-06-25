/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Actor;
import model.Movie;
import model.ResultadoRanking;

/**
 *
 * @author ricardo
 */
public class RankingDAO {
    private DBConnection banco;
    public RankingDAO(){
        this.banco = new DBConnection();
    }
    
    public ResultadoRanking Ranking(String g1, String g2){
        ResultSet rs = null;
        ResultadoRanking rr = new ResultadoRanking();
        
        String SQL =  "SELECT COUNT (gm.movieid) AS quantidade, a.name, a.sex " +
                    "FROM movieactor AS ma " +
                    "INNER JOIN ( " +
                    "		SELECT gm.movieid " +
                    "		FROM genresmovies AS gm, genres AS g " +
                    "		WHERE g.genreid = gm.genreid " +
                    "		AND (g.genre = '" + g1 + "' OR g.genre = '" + g2 + "') " +
                    "	) AS gm ON ma.movieid = gm.movieid " +
                    "INNER JOIN actors AS a ON a.actorid = ma.actorid " +
                    "GROUP BY a.actorid " +
                    "ORDER BY quantidade DESC;";
        
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
