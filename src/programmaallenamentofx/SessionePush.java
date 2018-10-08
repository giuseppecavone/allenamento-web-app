/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Giuseppe
 */
public class SessionePush extends Sessione {

    private static byte variante;

    public SessionePush(LocalDate data, List<Esercizio> esercizi) {
        super(data, esercizi);
        
        //esercizi.forEach(e -> System.out.println(e.getNome()));
        //System.out.println("");
        //sessione.forEach(e -> System.out.println(e.getNome()));
        System.out.println("\n\n");
        esercizi.forEach(s -> System.out.println(s.getNome()));
        gestioneSuperSerie();
        System.out.println("\n\n");
  esercizi.forEach(s -> System.out.println(s.getNome()));
    System.out.println("\n\n");
        switch (variante) {
            case 0:

                sessione.removeIf(p -> (p.getNome().equals("Bench pressA")
                        || p.getNome().equals("Overhead pressF")));

                break;
            case 1:
                sessione.removeIf(p -> (p.getNome().equals("Bench pressF")
                        || p.getNome().equals("Overhead pressA")));

                variante = -1;
                break;

        }
        variante++;
    }

    private void gestioneSuperSerie() {
        
        Esercizio lateralRaises;
        String[] prova = {"Overhead triceps extensions", "Triceps pushdowns"};
        int i;

        lateralRaises = sessione.stream()
                .filter(customer -> "Lateral raises".equals(customer.getNome()))
                .findAny()
                .orElse(null);

        sessione.removeAll(Collections.singleton(lateralRaises));

          //sessione.forEach(e -> System.out.println(e.getNome()));
        
        for (String s : prova) {
            sessione.add(i = sessione
                    .indexOf(sessione
                            .stream()
                            .filter(esercizio -> s.equals(esercizio.getNome()))
                            .findAny()
                            .orElse(null)) + 1,
                     lateralRaises);
          sessione.get(i-1).setNome(s.concat(" SS"));

  
        }

    }

}
