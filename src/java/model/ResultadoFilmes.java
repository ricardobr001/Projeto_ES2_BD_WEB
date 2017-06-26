/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ricardo
 */
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
