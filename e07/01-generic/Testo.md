# Implementazioni generiche di insiemi, code e mappe

Sulla scorta delle implementazioni esplorate negli esercizi precedenti per
insiemi, code e mappe, realizzate delle versioni *generiche* di tali astrazioni.
Più precisamente, dovrete dare una, o più, implementazioni concrete
dell'interfaccia

    public interface Set<E> extends Iterable<E> {
        int size();
        boolean isEmpty();
        boolean contains(Object o);
        E choose();
        boolean add(E e);
        boolean remove(Object o);
    }

per gli insiemi, dell'interfaccia

    public interface Queue<E> extends Iterable<E> {
        int size();
        boolean isEmpty();
        boolean enqueue(E e);
        E dequeue();
    }

per le code e, infine, delle interfacce

    public interface Map<K, V> extends Iterable<Entry<K,V>> {
        int size();
        boolean isEmpty();
        boolean containsKey(Object key);
        V get(Object key);
        V put(K key, V value);
        V remove(Object key);
        interface Entry<K, V> {
            K getKey();
            V getValue();
            V setValue(V value);
        }
    }

per le mappe. Per ciascun tipo di entità dovrete decidere tramite quali classi
(concrete o astratte) implementare le tre interfacce sopra abbozzate. Per
ciascuna di esse dovrete (preferibilmente in formato Javadoc, ma comunque
attraverso commenti presenti nel codice):

* dettagliare i comportamenti dei metodi delle varie interfacce (anche rispetto
  alle *eccezioni* eventualmente sollevate),

* descrivere le scelte relative alla rappresentazione dello stato (con
  particolare riferimento all'*invariante di rappresentazione* e alla *funzione
  di astrazione*) e ai metodi (con particolare riferimento a
  *pre-*/*post-condizioni* ed *effetti collaterali*),

* provvedere la dimostrazione di correttezza dei metodi che ritenete più critici
  (al grado di dettaglio che ritenete opportuno, rispetto alla complessità del
  metodo e alla criticità della sua correttezza).

Ogni implementazione deve anche sovrascrivere i metodi `equals`, `hashCode` e
`toString` di `Object`; è possibile provvedere diverse implementazioni per
ciascuna interfaccia (come ad esempio una versione per insiemi di taglia
piccola, o grande, oppure una versione di coda limitata, o illimitata).

Nelle vostre implementazioni **non** potrete utilizzare nessuna classe delle
Collections fatta eccezione di quelle che implementano l'interfaccia `List`.

## Classe di test

Dovrete scrivere una classe di test che, ricevuto un carattere come argomento da
riga di comando, si comporti come segue:

* se il carattere è `M`: legga dal flusso di ingresso una sequenza di coppie
  costituite ciascuna da una _parola_ (sequenza massimale di caratteri non
  contenenti *white-space*) e da un _intero_ e le aggiunga a una _mappa_ (da
  stringhe a interi); al termine dell'esecuzione emetta il numero corrispondente
  alla prima parola letta;

* se il carattere è `Q`: aggiunga a una coda gli interi contenuti nel flusso di
  ingresso e, alla fine dell'esecuzione, emetta il contenuto della coda nel
  flusso d'uscita;

* se il carattere è `S`: raccolga in un insieme le parole contenute nel flusso
  di ingresso e, alla fine dell'esecuzione, emetta nel flusso di uscita la
  dimensione dell'insieme così popolato.

Ad esempio, eseguendo `soluzione M` e avendo

    primo 1
    secondo 2
    primo 3
    quarto 4

nel flusso d'ingresso il programma emette `3` nel flusso d'uscita, similmente
eseguendo soluzione `Q` e avendo

    4
    3
    2
    1

nel flusso d'ingresso, il programma emette `4 3 2 1` nel flusso d'uscita.
Infine, eseguendo soluzione `S` e avendo

    primo
    secondo
    primo
    terzo

nel flusso d'ingresso, il programma emette `4` nel flusso d'uscita.