import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        /*
        Alias a = new Alias("prova", new HashSet<>(), new Dominio("unimi.it"));
        a.aggiungi(new Locale("prova"));
        a.aggiungi(new Locale("234"));
        a.aggiungi(new Locale("345"));
        Iterator<Indirizzo> i = a.iterator();
        while(i.hasNext()) {
            System.out.println(i.next().toString());
        }
        */

        List<Alias> list = new LinkedList<>();
        List<ListeDiDistribuzione> listD = new LinkedList<>();



        try(Scanner s = new Scanner(System.in)) {
            while(s.hasNextLine()) {
                String line = s.nextLine();
                // comando di creazione
                if (line.contains(" = ")) {
                    if (line.contains("@")) {
                        list.add(new Alias(line));
                    } else if (line.contains("=") && !line.contains("+") && !line.contains("-")) {
                        String[] parti = line.split(" ");
                        ListeDiDistribuzione l = new ListeDiDistribuzione(parti[0]);
                        for (Alias a : list) {
                            if (a.nome().equals(parti[2])) {
                                l.aggiungi(a);
                            }
                        }
                        listD.add(l);
                    } else if(line.contains("+")) {
                        String[] parti = line.split(" ");
                        ListeDiDistribuzione addendo1 = new ListeDiDistribuzione();
                        ListeDiDistribuzione addendo2 = new ListeDiDistribuzione();
                        for (ListeDiDistribuzione lista : listD) {
                            if (lista.nome.equals(parti[2]))
                                addendo1 = lista;
                        }
    
                        for (ListeDiDistribuzione lista : listD) {
                            if (lista.nome.equals(parti[3]))
                            addendo2 = lista;
                        }
                        
                        ListeDiDistribuzione nuova = addendo1.Somma(parti[0], addendo2);
                        listD.add(nuova);
                    } else {
                        String[] parti = line.split(" ");
                        ListeDiDistribuzione sottraendo1 = new ListeDiDistribuzione();
                        ListeDiDistribuzione sottraendo2 = new ListeDiDistribuzione();
                        for (ListeDiDistribuzione lista : listD) {
                            if (lista.nome.equals(parti[2]))
                            sottraendo1 = lista;
                        }
    
                        for (ListeDiDistribuzione lista : listD) {
                            if (lista.nome.equals(parti[3]))
                            sottraendo2 = lista;
                        }
                        ListeDiDistribuzione nuova = sottraendo1.Sottrazione(parti[0], sottraendo2);
                        listD.add(nuova);
                    }
                // comandi di mutazione
                } else if (line.contains("=")) {
                    String[] parti = line.split(" ");
                    Alias primo = new Alias();
                    Alias secondo = new Alias();
                    for (Alias a : list) {
                        if (a.nome.equals(parti[0]))
                            primo = a;
                        if (a.nome.equals(parti[2]))
                            secondo = a;
                    }
                    if (line.contains("+=")) {
                        primo.addizione(secondo);
                    } else {
                        primo.sottrazione(secondo);
                    }
                // comandi di ispezione
                } else {
                    for (Alias l : list) {
                        if (l.nome.equals(line)) {
                            System.out.println("Alias " + l.nome + " =");
                            Iterator<Indirizzo> i = l.iterator();
                            while(i.hasNext()) {
                                System.out.println("\t" + i.next().toString());
                            }
                        }
                    }
                    for (ListeDiDistribuzione l : listD) {
                        if (l.nome.equals(line)) {
                            System.out.println("Lista " + l.nome + " =");
                            Iterator<Indirizzo> i = l.iterator();
                            while(i.hasNext()) {
                                System.out.println("\t" + i.next().toString());
                            }
                        }
                    }
                }
            }
        }
    }
}