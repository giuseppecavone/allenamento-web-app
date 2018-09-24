/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

/**
 *
 * @author Giuseppe
 */
public abstract class Sessione {

    LocalDate data;
    ArrayList<Esercizio> esercizi;

    public Sessione(LocalDate data, ArrayList<Esercizio> esercizi) {

        this.data = data;
        this.esercizi = esercizi;

    }
}
    
//    public ArrayList<Sessione> generaSessioneSettimanale(LocalDate data) {
//
//ArrayList<Sessione> sessione = null;
//ArrayList<Esercizio> esercizi = null;
//LocalDate dataInizio = LocalDate.of(2018, Month.SEPTEMBER, 03);
//LocalDate dataFine = LocalDate.of(2018, Month.DECEMBER, 03);
//
// for (LocalDate date = dataInizio; date.isBefore(dataFine); date = date.plusDays(3)) {
//
//     if (date.getDayOfWeek().equals(DayOfWeek.THURSDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) continue;
//	sessione.add(new Sessione(data,eserciziPull()));
//	sessione.add(new Sessione(data,eserciziPush));
//	sessione.add(new Sessione(data,eserciziLegs));
//
//	
//
//}
//
//}
