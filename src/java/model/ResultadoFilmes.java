package model;

import java.util.ArrayList;

public class ResultadoFilmes {
    private ArrayList<Movie> movie;
    
    public ResultadoFilmes(){
        this.movie = new ArrayList();
    }
    
    public void popula(Movie m){
        this.movie.add(m);
    }
    
    public String returnDados(int i){
        return  "<td>" + this.movie.get(i).getName() + "</td>" +
                "<td>" + this.movie.get(i).getYear() + "</td>";
    }
    
    public int size(){
        return this.movie.size();
    }
}
