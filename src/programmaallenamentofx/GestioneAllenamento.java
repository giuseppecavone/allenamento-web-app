/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

import java.io.IOException;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Giuseppe
 */
public class GestioneAllenamento {

    public static void main(String... args) throws IOException {

        String nomeFile = "C:\\Users\\Valentino\\Desktop\\Accessori.txt";

        EsercizioDAO allenamento = new EsercizioDAO();

        List<Esercizio> accessori = new ArrayList(allenamento.leggiFileAccessori(nomeFile));

        nomeFile = "C:\\Users\\Valentino\\Desktop\\Fondamentali.txt";
        
        List<Esercizio> fondamentali = new ArrayList(allenamento.leggiFileFondamentali(nomeFile));
          for (Esercizio e : fondamentali) {
                System.out.println(e.getNome() + ((Fondamentale) e).getInc());
                for (Serie serie : e.getSerie()) {
                    System.out.println(serie.getPeso() + " " + serie.getReps());

                }
            }
        List<Esercizio> esercizi = new ArrayList(fondamentali);
        esercizi.addAll(accessori);
        esercizi.forEach(e -> System.out.println(e.getNome()));
        System.out.println("\n\nSize: " + esercizi.size() + "\n\n");
        LocalDate dataInizio = LocalDate.of(2018, Month.SEPTEMBER, 03);
        LocalDate dataFine = LocalDate.of(2018, Month.DECEMBER, 3);

        List<Sessione> sessioni = new ArrayList(allenamento
                .generaSessioneSettimanale(dataInizio, dataFine, esercizi));

    }

}
