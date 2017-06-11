/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ricardo
 */
public class ResultadoBusca {
    private Actor[] actor;
    private Movie[] movie;
    private int size;
    
    public ResultadoBusca(){
        this.size = 0;
    }
    
    public void popula(Actor a, Movie m){
        this.actor[this.size] = new Actor();
        this.movie[this.size] = new Movie();
        this.actor[this.size] = a;
        this.movie[this.size] = m;
        this.size++;
    }
    
    public int size(){
        return this.size;
    }
    
    public String returnMovie(int i){
        return  "<td>" + this.movie[i].getName() + "</td>" +
                "<td>" + this.movie[i].getYear() + "</td>" +
                "<td>" + this.movie[i].getLanguages() + "</td>" +
                "<td>" + this.movie[i].getGenres() + "</td>";
    }
    
    public String returnActor(int i){
        return  "<td>" + this.actor[i].getName() + "</td>" +
                "<td>" + this.actor[i].getCharacter() + "</td>";
    }
    
    public boolean vazio(){
        if (this.size == 0){
            return true;
        }
        
        return false;
    }
    
    public int tamanho(){
        return this.size;
    }
}
