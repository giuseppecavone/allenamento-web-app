/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Giuseppe
 */
public class SessionePush extends Sessione {
    
      private static byte variante;

    ArrayList<Esercizio> sessione;

    public SessionePush(LocalDate data, ArrayList<Esercizio> esercizi) {
        super(data, esercizi);

        sessione = new ArrayList(esercizi);

        Esercizio benchpressF = sessione.remove(0);
        Esercizio overheadpressF = sessione.remove(1);
        Esercizio benchpressA = sessione.remove(2);
        Esercizio overheadpressA = sessione.remove(3);
       

        switch(variante) {
            case 0:
                sessione.add(benchpressF);
                sessione.add(overheadpressA);
                break;
            case 1:
                sessione.add(overheadpressF);
                sessione.add(benchpressA);
                variante=-1;
                break;
            
        }
        variante++;
    }
    
}
