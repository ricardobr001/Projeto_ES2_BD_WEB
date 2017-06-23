package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.Actor;
import model.Movie;
import model.ResultadoBusca;

public class BuscaAvancadaDAO {
    private DBConnection banco;
    
    public BuscaAvancadaDAO(){
        this.banco = new DBConnection();
    }
    
    public ResultadoBusca buscaAvancada(String[] nome, String[] idioma, String pagina) {
        ResultSet rs = null;
        ResultadoBusca rb = new ResultadoBusca();
        int offset = 0;

        if (pagina != null) {
            offset = (Integer.parseInt(pagina) - 1) * 10;
        }

        
        String SQL =    "SELECT mlg.title, mlg.year, mlg.language, mlg.genre, act.name, ma.character " +
                        "FROM movieactor AS ma " +
                        "INNER JOIN ( " +
                        "            SELECT a.actorid, a.name " +
                        "            FROM actors AS a " +
                        "            WHERE a.name = ";
        
                        /*Laço para montar o SQL dinamicamente*/
                        for (int i = 0 ; i < nome.length ; i++){
                            if (i+1 == nome.length){
                                SQL = SQL + "'" + nome[i] + "'";
                            }
                            else {
                                SQL = SQL + "'" + nome[i] + "' OR a.name = ";
                            }   
                        }
                
                        SQL = SQL + "        ) AS act ON act.actorid = ma.actorid " +
                        "INNER JOIN ( " +
                        "        	SELECT m.movieid, m.title, m.year, lg.language, g.genre " +
                        "        	FROM movie AS m " +
                        "        	INNER JOIN ( " +
                        "            		    SELECT lm.movieid, lang.language " +
                        "            		    FROM languagesmovies AS lm, languages AS lang " +
                        "            		    WHERE lang.language = ";
                        
                        /*Laço para montar o SQL dinamicamente*/
                        for (int i = 0 ; i < idioma.length ; i++){
                            if (i+1 == idioma.length){
                                SQL = SQL + "'" + idioma[i] + "'";
                            }
                            else {
                                SQL = SQL + "'" + idioma[i] + "' OR lang.language = ";
                            }   
                        }
                        
                        SQL = SQL + "            		) AS lg ON lg.movieid = m.movieid " +
                        "            INNER JOIN genresmovies AS gm ON gm.movieid = m.movieid " +
                        "            INNER JOIN genres AS g ON g.genreid = gm.genreid " +
                        "	    ) AS mlg ON mlg.movieid = ma.movieid " +
                        "LIMIT 30 " +
                        "OFFSET " + offset + ";";
        
        try { 
            banco.stmt.execute(SQL);
            rs = banco.stmt.getResultSet();

            /*Enquanto não chegar no fim do select recupera os dados*/
            int i = 0;
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
                
                rb.popula(a, m);
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rb;
    }
}
