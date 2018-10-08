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

        String nomeFile = "C:\\Users\\Giuseppe\\Desktop\\Accessori.txt";

        EsercizioDAO allenamento = new EsercizioDAO();

        List<Esercizio> accessori = new ArrayList(allenamento.leggiFileAccessori(nomeFile));

        nomeFile = "C:\\Users\\Giuseppe\\Desktop\\Fondamentali.txt";

        List<Esercizio> fondamentali = new ArrayList(allenamento.leggiFileFondamentali(nomeFile));

        List<Esercizio> esercizi = new ArrayList(fondamentali);
        esercizi.addAll(accessori);
        
             LocalDate dataInizio = LocalDate.of(2018, Month.SEPTEMBER, 03);
        LocalDate dataFine = LocalDate.of(2018, Month.SEPTEMBER, 9);
        
        List<Sessione> sessioni = new ArrayList(allenamento
                .generaSessioneSettimanale(dataInizio,dataFine,esercizi));
        
        
        

    }

   
}
