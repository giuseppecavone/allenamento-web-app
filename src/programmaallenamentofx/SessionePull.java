/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Giuseppe
 */
public class SessionePull extends Sessione {

    private static byte variante;

 

    public SessionePull(LocalDate data, List<Esercizio> esercizi) {
        super(data, esercizi);


        switch (variante) {
            case 0:
                sessione.removeIf(p -> 
                        (p.getNome().equals("Barbell Row") ||  p.getNome().equals("Pullups") || p.getNome().equals("Chinups")             
                        ));

                break;
            case 1:
                sessione.removeIf(p -> (p.getNome().equals("Deadlift")
                        || p.getNome().equals("Lat machine")
                        || p.getNome().equals("Chinups")));

                break;
            case 2:
                sessione.removeIf(p -> (p.getNome().equals("Barbell Row")
                        || p.getNome().equals("Pullups")
                        || p.getNome().equals("Lat machine")));

                break;
            case 3:
                sessione.removeIf(p -> (p.getNome().equals("Deadlift")
                        || p.getNome().equals("Pullups")
                        || p.getNome().equals("Chinups")));

                break;
            case 4:
                sessione.removeIf(p -> (p.getNome().equals("barbell")
                        || p.getNome().equals("latmachine")
                        || p.getNome().equals("chinups")));

                break;
            case 5:
                sessione.removeIf(p -> (p.getNome().equals("deadlift")
                        || p.getNome().equals("pullups")
                        || p.getNome().equals("latmachine")));

                variante = -1;
                break;

        }
        variante++;
    }

}
