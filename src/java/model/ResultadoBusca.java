package model;

import java.util.ArrayList;

public class ResultadoBusca {
    public ArrayList<Actor> actor;
    public ArrayList<Movie> movie;
    
    public ResultadoBusca(){
        this.actor = new ArrayList();
        this.movie = new ArrayList();
    }
    
    public void popula(Actor a, Movie m){
        this.actor.add(a);
        this.movie.add(m);
    }
    
    public String returnMovie(int i){
        return  "<td>" + this.movie.get(i).getName() + "</td>" +
                "<td>" + this.movie.get(i).getYear() + "</td>" +
                "<td>" + this.movie.get(i).getLanguages() + "</td>" +
                "<td>" + this.movie.get(i).getGenres() + "</td>";
    }
    
    public String returnActor(int i){
        return  "<td>" + this.actor.get(i).getName() + "</td>" +
                "<td>" + this.actor.get(i).getCharacter() + "</td>";
    }
    
    public int size(){
        return this.actor.size();
    }
}
