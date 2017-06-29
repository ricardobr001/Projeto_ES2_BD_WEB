package model;

import java.util.ArrayList;

public class ResultadoRanking {
    private ArrayList<Actor> actor;
    private ArrayList<Integer> quant;
    
    public ResultadoRanking(){
        this.actor = new ArrayList();
        this.quant = new ArrayList();
    }
    
    public void popula(Actor a, int i){
        this.actor.add(a);
        this.quant.add(i);
    }
    
    public String returnDados(int i){
        String nome = this.actor.get(i).getName();
        
        return  "<td>" + this.quant.get(i) + "</td>" + 
                "<td><form method=\"GET\" action=\"BuscaFilmes\"><a href=\"javascript:;\" onclick=\"parentNode.submit();\">" + nome + "</a><input type=\"hidden\" name=\"ator\" value=\"" + nome + "\"/></form></td>" +
                "<td>" + this.actor.get(i).getSex() + "</td>";
    }
    
    public int size(){
        return this.actor.size();
    }
}