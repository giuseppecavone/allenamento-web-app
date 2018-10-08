/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Giuseppe
 */
public abstract class Sessione {
    
    List<Esercizio> sessione;
    
   private static byte tipoSessione=1;

    public static byte getTipoSessione() {
        return tipoSessione;
    }

    public static void setTipoEsercizio(byte tipoSessione) {
        Sessione.tipoSessione = tipoSessione;
    }

    LocalDate data;
    

    public Sessione(LocalDate data, List<Esercizio> esercizi) {

        esercizi.forEach(e -> {
            if (e instanceof Fondamentale) 
                EsercizioDAO.aggiornaProgressione((Fondamentale)e);
            
        });
        
        this.data = data;
        
        sessione= new ArrayList(esercizi);
        
        tipoSessione+=1;
    }
}
    

