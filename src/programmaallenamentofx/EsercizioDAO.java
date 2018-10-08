/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programmaallenamentofx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 *
 * @author Giuseppe
 */
public class EsercizioDAO {

    public List<Fondamentale> leggiFileFondamentali(String nomeFile) throws FileNotFoundException, IOException {

        List<Fondamentale> listFondamentali = new ArrayList();

        String nome;
        double peso;
        byte serieTotali;
        double incPeso;
        TipoEsercizio tipo;
        double minIncremento;

        BufferedReader reader = new BufferedReader(new FileReader(nomeFile));
        String line = reader.readLine();
        while (line != null) {

//Parsing del file di testo (Tokenizer?) e caricamento dei dati nella lista   
            StringTokenizer st = new StringTokenizer(line, ",");

            nome = st.nextToken();
            peso = Double.parseDouble(st.nextToken());
            serieTotali = Byte.parseByte(st.nextToken());

            ArrayList<Serie> serie = new ArrayList(creaSerieFondamentali(serieTotali, st, peso));
            incPeso = Double.parseDouble(st.nextToken());
            tipo = rilevaEsercizio(st);
            minIncremento = Double.parseDouble(st.nextToken());

            Fondamentale esercizio = new Fondamentale();

            esercizio.setSerie(serie);
            esercizio.setInc(incPeso);
            esercizio.setMinIncremento(minIncremento);
            esercizio.setNome(nome);
            esercizio.setTipo(tipo);

            Fondamentale copiaEsercizio = new Fondamentale(esercizio);

            listFondamentali.add(esercizio);

            line = reader.readLine();

        }

        return listFondamentali;
    }

    public List<Accessorio> leggiFileAccessori(String nomeFile) throws FileNotFoundException, IOException {
        List<Accessorio> listAccessori = new ArrayList();

        String nome;
        double peso;
        byte serieTotali;
        double percIncPeso;
        TipoEsercizio tipo;
        double minIncremento;

        BufferedReader reader = new BufferedReader(new FileReader(nomeFile));
        String line = reader.readLine();

        while (line != null) {

            StringTokenizer st = new StringTokenizer(line, ",");

            nome = st.nextToken();
            peso = Double.parseDouble(st.nextToken());
            serieTotali = Byte.parseByte(st.nextToken());

            ArrayList<Serie> serie = new ArrayList(creaSerieAccessori(serieTotali, st, peso));
            percIncPeso = Double.parseDouble(st.nextToken());
            tipo = rilevaEsercizio(st);
            minIncremento = Double.parseDouble(st.nextToken());

            Accessorio esercizio = new Accessorio();

            esercizio.setSerie(serie);
            esercizio.setPercent(percIncPeso);
            esercizio.setMinIncremento(minIncremento);
            esercizio.setNome(nome);
            esercizio.setTipo(tipo);

            listAccessori.add(esercizio);

            line = reader.readLine();
        }
        return listAccessori;
    }

    public List<Serie> creaSerieAccessori(int n, StringTokenizer st, double peso) {

        List<Serie> listSerie = new ArrayList(n);
        Serie s;

        for (int i = 0; i < n; i++) {
            s = new Serie();
            s.setReps(st.nextToken());
            s.setPeso(peso);
            listSerie.add(s);

        }

        return listSerie;
    }

    public List<Serie> creaSerieFondamentali(int n, StringTokenizer st, double peso) {

        List<Serie> listSerie = new ArrayList(n);
        Serie s;
        int i = 0;

        while (i < n) {
            s = new Serie();
            s.setReps(st.nextToken());
            listSerie.add(s);
            i++;
        }

        i = 0;

        for (Serie se : listSerie) {
            if (i++ < 4) {
                se.setPeso(peso * Double.parseDouble(st.nextToken()));
            } else {
                se.setPeso(peso);
            }
        }

        return listSerie;
    }

    public TipoEsercizio rilevaEsercizio(StringTokenizer st) {

        TipoEsercizio tipo;

        switch (st.nextToken()) {
            case "LEGS":
                tipo = TipoEsercizio.LEGS;
                break;

            case "PULL":
                tipo = TipoEsercizio.PULL;
                break;

            case "PUSH":
                tipo = TipoEsercizio.PUSH;
                break;

            case "MISC":
                tipo = TipoEsercizio.MISC;
                break;

            default:
                tipo = null;

        }
        return tipo;
    }

    public List<Esercizio> estraiEsercizi(List<Esercizio> lista, TipoEsercizio tipo) {

        List<Esercizio> esercizi = lista.stream()
                .filter(line -> (line.getTipo().equals(tipo)))
                .collect(Collectors.toList());

        return esercizi;
    }

    public Sessione creaSessione(LocalDate data, List<Esercizio> pull, List<Esercizio> push, List<Esercizio> legs) {

        Sessione sessione = null;
        switch (Sessione.getTipoSessione()) {

            case 1:

                return new SessionePull(data, pull.stream()
                        .filter(p -> !p.getNome().equals("Iperextension"))
                        .collect(Collectors.toList()));

            case 2:

                return new SessionePush(data, push);

            case 3:
                sessione = new SessioneLegs(data, legs);
                Sessione.setTipoEsercizio((byte) 1);
        }
        return sessione;
    }

    public List<Sessione> generaSessioneSettimanale(LocalDate dataInizio, LocalDate dataFine, List<Esercizio> esercizi) {

        List<Esercizio> eserciziMisc = new ArrayList(estraiEsercizi(esercizi, TipoEsercizio.MISC));
        List<Esercizio> eserciziLegs = new ArrayList(estraiEsercizi(esercizi, TipoEsercizio.LEGS));
        List<Esercizio> eserciziPull = new ArrayList(estraiEsercizi(esercizi, TipoEsercizio.PULL));
        List<Esercizio> eserciziPush = new ArrayList(estraiEsercizi(esercizi, TipoEsercizio.PUSH));

        eserciziPull.addAll(eserciziMisc);
        eserciziLegs.addAll(eserciziMisc);

        LocalDate dataInc;

        dataInc = dataInizio.plusWeeks(1);
        List<Sessione> sessione = new ArrayList();
        List<Esercizio> accessori = esercizi.stream()
                .filter(e -> e instanceof Accessorio)
                .collect(Collectors.toList());

        for (LocalDate date = dataInizio; date.isBefore(dataFine.plusDays(1)); date = date.plusDays(1)) {
//            eserciziPush.forEach(s -> System.out.println(s.getNome()));
//            System.out.println("Size: "+eserciziPush.size());
            if (dataInc.equals(date)) {
                for (Esercizio e : esercizi) {
                    System.out.println(e.getNome());
                    for (Serie serie : e.getSerie()) {
                        System.out.println(serie.getPeso() + " " + serie.getReps());

                    }
                }

                aggiornaProgressione(accessori);
//                aggiornaProgressione(eserciziPush);
//                aggiornaProgressione(eserciziPull);
//                aggiornaProgressione(eserciziLegs);
                dataInc = dataInc.plusWeeks(1);
            }

            if (date.getDayOfWeek().equals(DayOfWeek.THURSDAY)
                    || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                continue;
            }

            sessione.add(creaSessione(date, eserciziPull, eserciziPush, eserciziLegs));

        }

        return sessione;

    }

    public static List<Esercizio> copiaEsercizi(List<Esercizio> esercizi) {

        List<Esercizio> eserciziCopy = new ArrayList();

        esercizi.forEach(e -> {
            if (e instanceof Fondamentale) {
                eserciziCopy.add(new Fondamentale(e));
            } else {
                eserciziCopy.add(new Accessorio(e));
            }
        }
        );

        return eserciziCopy;
    }

    public void aggiornaProgressione(List<Esercizio> esercizi) {

        Esercizio legPress = esercizi.stream()
                .filter(customer -> "Leg press".equalsIgnoreCase(customer.getNome()))
                .findAny()
                .orElse(null);

        for (Esercizio e : esercizi) {
            switch (e.getNome()) {

                case "Incline Crunch":
                    e.getSerie().forEach((s) -> {
                        s.setReps(Byte.toString(((byte) (Byte.parseByte(s.getReps()) + 1))));
                    });
                    break;

                case "Hanging Leg Raises":

                    e.getSerie().forEach((s) -> {
                        s.setReps(Byte.toString(((byte) (Byte.parseByte(s.getReps()) + 1))));

                    });
                    break;

                case "Pullups":
                    if (legPress.getSerie().get(1).getReps().equals("12")) {
                        e.getSerie().forEach((s) -> {
                            s.setReps(Byte.toString(((byte) (Byte.parseByte(s.getReps()) + 1))));

                        });
                    }
                    break;

                case "Chinups":
                    System.out.println(legPress);
                    System.out.println(legPress.getSerie());
                    System.out.println(legPress.getSerie().get(1));
                    System.out.println(legPress.getSerie().get(1).getReps());
                    if (legPress.getSerie().get(1).getReps().equals("12")) {
                        e.getSerie().forEach((s) -> {
                            s.setReps(Byte.toString(((byte) (Byte.parseByte(s.getReps()) + 1))));

                        });
                    }
                    break;

                default:
                    if (e instanceof Accessorio) {
                        if (e.getSerie().get(1).getReps().equals("12")) {
                            e.getSerie().forEach((s) -> {
                                s.setReps("8");
                                s.setPeso(s.getPeso() + s.getPeso() * ((Accessorio) e).getPercent());
                            });
                        } else {
                            e.getSerie().forEach((s) -> {
                                s.setReps(Byte.toString(((byte) (Byte.parseByte(s.getReps()) + 2))));
                                s.setPeso(s.getPeso() + s.getPeso() * ((Accessorio) e).getPercent());
                            });
                        }
                    }

            }
        }
    }

    public static void aggiornaProgressione(Fondamentale f) {

        f.getSerie().forEach((s) -> {
            s.setPeso(s.getPeso() + f.getInc());
            System.out.println(s.getPeso());
        });
    }

}
