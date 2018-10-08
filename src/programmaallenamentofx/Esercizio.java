/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

import java.util.ArrayList;


/**
 *
 * @author Giuseppe
 */
public abstract class Esercizio {
    
    static int count;
    private ArrayList<Serie> serie;
    private String nome;
    private TipoEsercizio tipo;
    private double minIncremento;
    
    abstract void aggiornaProgressione();

    public ArrayList<Serie> getSerie() {
        return new ArrayList(serie);
    }

    public void setSerie(ArrayList<Serie> serie) {
        this.serie = serie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEsercizio getTipo() {
        return tipo;
    }

    public void setTipo(TipoEsercizio tipo) {
        this.tipo = tipo;
    }

    public double getMinIncremento() {
        return minIncremento;
    }

    public void setMinIncremento(double minIncremento) {
        this.minIncremento = minIncremento;
    }
    
    
    
    
}
