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
public class SessionePull extends Sessione {

    private static byte variante;

    ArrayList<Esercizio> sessione;

    public SessionePull(LocalDate data, ArrayList<Esercizio> esercizi) {
        super(data, esercizi);

        sessione = new ArrayList(esercizi);

        Esercizio deadelift = sessione.remove(0);
        Esercizio barbell = sessione.remove(1);
        Esercizio latmachine = sessione.remove(2);
        Esercizio pullups = sessione.remove(3);
        Esercizio chinups = sessione.remove(4);

        switch (variante) {
            case 0:
                sessione.add(deadelift);
                sessione.add(latmachine);
                break;
            case 1:
                sessione.add(barbell);
                sessione.add(pullups);
                break;
            case 2:
                sessione.add(deadelift);
                sessione.add(chinups);
                break;
            case 3:
                sessione.add(barbell);
                sessione.add(latmachine);
                break;
            case 4:
                sessione.add(deadelift);
                sessione.add(pullups);
                break;
            case 5:
                sessione.add(barbell);
                sessione.add(chinups);
                variante=-1;
                break;

        }
        variante++;
    }

}
