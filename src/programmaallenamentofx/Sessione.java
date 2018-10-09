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
public abstract class Sessione {

   private List<Esercizio> sessione;

    private static byte tipoSessione = 1;

    public static byte getTipoSessione() {
        return tipoSessione;
    }

    public static void setTipoEsercizio(byte tipoSessione) {
        Sessione.tipoSessione = tipoSessione;
    }

    private LocalDate data;

    public Sessione(LocalDate data, List<Esercizio> esercizi) {

        esercizi.forEach(e -> {
            if (e instanceof Fondamentale) {
                EsercizioDAO.aggiornaProgressione((Fondamentale) e);
//                e.getSerie().forEach(
//                                     serie -> System.out.println("PESOPESOPESO !!!!!!!!! "+serie.getPeso()));
            }

        });

        this.data = data;

        sessione = EsercizioDAO.copiaEsercizi(esercizi);

        tipoSessione += 1;
    }

//    public Sessione(Sessione sessione) {
//        this(sessione.getData(), new ArrayList(sessione.getSessione()));
//    }
   
    public  List<Esercizio> getSessione() {
        return sessione;
    }

    public LocalDate getData() {
        return data;
    }
}
