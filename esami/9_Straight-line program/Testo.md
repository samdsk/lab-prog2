# Straight-line program

## Descrizione

Scopo della prova è progettare e implementare una gerarchia di oggetti utili a
implementare un *interprete* per *straight-line program* sugli interi.

Per portare a termine il lavoro dovrà decidere se e quali interfacce e classi
(concrete o astratte) implementare. Per ciascuna di esse **dovrà descrivere**
(preferibilmente in formato Javadoc, ma comunque solo attraverso commenti
presenti nel codice) le scelte relative alla **rappresentazione** dello stato
(con particolare riferimento all'*invariante di rappresentazione* e alla
*funzione di astrazione*) e ai **metodi** (con particolare riferimento a
*pre/post-condizioni* ed *effetti collaterali*, soffermandosi a illustrare le
ragioni della *correttezza* solo per le implementazioni che riterrà più
critiche). Osservi che l'esito di questa prova si baserà tanto su questa
documentazione quanto sul codice sorgente.

Presti particolare attenzione agli *errori di compilazione: l'intero contenuto
dei file che il compilatore si rifiuta di compilare non sarà affatto esaminato*.
Se riscontrasse errori di compilazione che non è in grado di correggere, valuti
la possibilità di racchiudere le porzioni di codice che li causano all'interno
di commenti; resta inteso che tale codice commentato non sarà valutato, ma
almeno verrà esaminato il resto del codice del file.

### Lo straight-line program

Uno *straight-line program* sugli interi è una *sequenza di istruzioni*, ciascuna
delle quali corrisponde all'assegnamento della valutazione di una *funzione*
(intera ad argomenti interi) a una *variabile*, con l'ulteriore restrizione che
gli argomenti della funzione siano numeri interi o variabili precedentemente
assegnate. Uno *straight-line program* si dice *valido* rispetto a un insieme
di funzioni se e solo se tutte le funzioni che compaiono in esso appartengono a
tale insieme e sono valutate sul corretto numero di argomenti. Data l'ovvia
semantica di ciascun assegnamento, il *valore* di uno *straight-line program*
valido è il valore della variabile assegnata nell'ultima istruzione (si osservi
che tale valore è ben definito, come si può banalmente dimostrare per induzione
sul numero di istruzioni nel programma).

Ad esempio, il seguente

    X = 1
    Y = 5
    Z = SUM(X, Y)
    W = MUL(Y, Z)

ha valore `30` in quanto

    W = MUL(Y, Z)
      = MUL(Y, SUM(X, Y))
      = MUL(5, SUM(1, 5))
      = MUL(5, 6)
      = 30

### Funzioni (e costanti)

Una *interfaccia* molto naturale per modellare una **funzione** intera ad
argomenti interi è data da

    public interface Funzione {
      int numeroArgomenti();
      void argomento(Funzione funzione, int posizione);
      int valuta();
    }

* il metodo `numeroArgomenti` restituisce il numero di argomenti della funzione
  (ad esempio `2` nel caso di `SUM`),
* il metodo `argomento` permette di definire quale altra `funzione` corrisponda
  all'argomento di `posizione` data,
* il metodo `valuta` restituisce il valore di questa funzione (una volta
  opportunamente ottenute e combinate le valutazioni delle funzioni
  corrispondenti ai suoi argomenti).

Come si nota, sebbene la definizione di *straight-line program* preveda che
alcuni argomenti possano essere numeri interi, l'interfaccia appena proposta
sembra non coprire questo caso. Questo è facilmente risolto considerando un
intero come una **funzione costante** (ossia a zero argomenti); ad esempio,
aggiungendo il seguente metodo statico all'interfaccia proposta

    static Funzione costante(final int valore) {
      return new Funzione() {
        public int numeroArgomenti() { return 0; }
        public void argomento(Funzione funzione, int posizione) {
          throw new UnsupportedOperationException();
        }
        public int valuta() { return valore; }
      };
    }

è possibile fabbricare, dato un `valore`, una funzione costante la cui
valutazione corrisponda a esso.

Ad esempio, il valore dello straight-line program

    A = 5
    B = 6
    X = SUM(A, B)

è `11` e può essere ottenuto grazie all'esecuzione del seguente segmento di
codice

    Funzione A = Funzione.costante(5);
    Funzione B = Funzione.costante(6);
    Funzione SUM = new Sum();
    SUM.argomento(A, 0);
    SUM.argomento(B, 1);

dove `Sum` è una implementazione dell'interfaccia `Funzione` che ha per valore
la somma dei suoi argomenti.

### Istruzioni e interprete

Una **istruzione** è concettualmente data da:

* la *variabile* a cui assegnare la valutazione della funzione,
* la *funzione* da valutare,
* l'elenco delle variabili, o numeri, che costituiscono gli *argomenti* della
  funzione.

Dato che sarà necessario leggere le istruzioni dal flusso di ingresso, potrebbe
essere utile avere un modo per costruire una istruzione a partire da una stringa.
Per semplicità assumete che tale stringa sia una sequenza di *nomi* (sequenze
massimali di caratteri alfabetici maiuscoli che non contengono spazi) e *numeri*
interi, separati da *white-space*, come ad esempio

    X  SUM   Z        3

assumete, inoltre, che i primi due nomi (sempre presenti) siano quelli della
variabile e della funzione, mentre gli altri numeri o nomi siano gli eventuali
argomenti. Può essere comodo ricordare che il metodo `split` con argomento `" +"` applicato alla stringa dell'esempio restituisce il vettore di stringhe `"X",
"SUM", "Z", "3"`.

Un **interprete** per *straight-line program* deve essere in grado, dato un
programma valido rispetto all'insieme di funzioni ad esso note, di calcolarne il
valore.

Più precisamente, una volta inizializzato con una sequenza di istruzioni (ed
eventualmente una descrizione dell'insieme di funzioni implementate), esso deve
offrire un metodo di segnatura

    int valuta(int[] ingresso)

che, se `ingresso` contiene al più 26 interi (che chiameremmo nel seguito `i0`,
..., `i25`), restituisca il valore del programma ottenuto anteponendo

    A = i0
    B = i1
    C = i3
    ...
    Z = i25

alle istruzioni con cui è stato inizializzato, posto che il programma così
ottenuto sia valido (evidentemente, il numero di variabili definite come sopra
sarà pari al numero di valori in `ingresso`).

Per rendere un po' più "interessante" il comportamento dell'interprete,
implementate almeno le seguenti *funzioni* (nel senso di classi che implementino
l'interfaccia `Funzione`):

* `Mul`: funzione a due argomenti che ne restituisce il prodotto,
* `Max`: funzione a tre argomenti che ne restituisce il massimo,
* `Neg`: funzione a un argomento che lo restituisce col segno opposto.

Date tale implementazioni, una volta inizializzato l'interprete, ad esempio con
le istruzioni

    X = SUM(A, B)
    Y = SUM(C, D)
    Z = MUL(X, Y)

la chiamata di `valuta(new int[] {1, 2, 3, 4})` deve restituire `21` che è pari
al valore del programma

    A = 1
    B = 2
    C = 3
    D = 4
    X = SUM(A, B)
    Y = SUM(C, D)
    Z = MUL(X, Y)

### La classe di test

Scrivete una classe che abbia un metodo statico `main`  che legga dal flusso di
ingresso una sequenza di istruzioni (nel formato descritto in precedenza) e
riceva (al più 26) interi come parametri sulla linea di comando ed emetta nel
flusso d'uscita il valore ottenuto dall'interprete su tali interi, secondo
quanto discusso in precedenza.

## Vincoli

Le istruzioni in ingresso rispettano tutte il formato specificato e
costituiscono un programma valido (una volta prefissato dagli assegnamenti alle
variabili da `A` fino al più a `Z` coi valori ottenuti dai parametri sulla linea
di comando) e rispetto all'insieme di funzioni elencate in precedenza; il nome
delle funzioni nelle stringhe coincide con quello delle classi (a meno del
*case*).

## Esempio

Eseguendo `soluzione 1 2 3 4` e avendo

    X SUM A B
    Y SUM C D
    Z MUL X Y

nel flusso di ingresso, il programma emette `21` nel flusso d'uscita.

Eseguendo `soluzione 6 9 8 7` ed avendo

    X NEG A
    Y NEG B
    W NEG C
    Z NEG D
    MXYW MAX X Y W
    M MAX MXYW Z Z
    R NEG M

nel flusso di ingresso, il programma emette il `6` (il minimo degli argomenti)
nel flusso d'uscita.

## Osservazioni

Considerate che un approccio alternativo (per certi versi più complesso, ma
certamente più efficiente qualora siano richieste più valutazioni dello stesso
programma al variare delle variabili in ingresso) è realizzare un
**compilatore** che, dato uno *straight-line program*, restituisca una
(implementazione di) `Funzione` che abbia tanti argomenti quante le variabili
libere in `A`, `B`, … `Z` del programma, la cui valutazione coincida in valore
con la valutazione descritta per l'interprete.