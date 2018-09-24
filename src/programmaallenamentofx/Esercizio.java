/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

/**
 *
 * @author Giuseppe
 */
public abstract class Esercizio implements Fondamentale, Accessorio {
    
    static int count;
    int reps,peso;
    String nome;
    
    abstract void calcolaProgressione();
    
}
