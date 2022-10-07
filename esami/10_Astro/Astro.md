# Simulazioni astronomiche

_Questo esercizio è stato assegnato come **tema d'esame** nell'appello del 21
gennaio 2020._

## Descrizione

Scopo dell'esercizio è progettare e implementare una gerarchia di oggetti utili a
rappresentare e simulare il comportamento di un *sistema astronomico* composto
di alcuni *corpi celesti* (come *pianeti* e *stelle fisse*) soggetti alla
reciproca interazione gravitazionale.

Dovrete decidere quali classi (concrete, o astratte) e quali interfacce
implementare. Per ciascuna di esse dovrete descrivere (preferibilmente in
formato Javadoc, ma comunque attraverso commenti presenti nel codice) le scelte
relative alla rappresentazione dello stato (con particolare riferimento
all'*invariante di rappresentazione* e alla *funzione di astrazione*) e ai
metodi (con particolare riferimento a *pre-*/*post-condizioni* ed *effetti
collaterali*).

Un **corpo celeste** è caratterizzato da un *nome* e da una *posizione*,
descritta da un **punto tridimensionale** a coordinate intere. Assumeremo per
semplicità che ci siano solo due tipi di corpi celesti: le **stelle fisse** e i
**pianeti**. Le stelle non cambiano mai la loro posizione, a differenza dei
pianeti. Per questo, oltre che dalla posizione, sono caratterizzati dalla loro
*velocità*, descritta anch'essa da un punto tridimensionale. Ciascun corpo
celeste ha una *energia* data dal prodotto dell'*energia potenziale*,
corrispondente alla norma della sua posizione, per l'*energia cinetica*,
corrispondente dalla norma della sua velocità (ovviamente tale energia è nulla
nel caso delle stelle fisse).

I corpi celesti sono soggetti all'*attrazione gravitazionale* reciproca che ne
modifica la velocità e, indirettamente, la posizione come segue:

* dapprima ciascun pianeta *modifica la sua velocità* in funzione
  dell'attrazione verso tutti gli altri corpi celesti: dato il pianeta `p` e il
  corpo celeste `c`, in maniera indipendente per ognuna delle tre coordinate, la
  velocità di `p` cambia di +1 o -1 a seconda che tale coordinata sia,
  rispettivamente, minore o maggiore di quella di `c`;

* una volta calcolata la nuova velocità per tutti i pianeti, ciascun pianeta
  *modifica la sua posizione* aggiungendo il valore della
  sua velocità a quello della sua posizione (come se per una unità di tempo il
  pianeta fosse soggetto a moto uniforme).

Per esempio, se il sistema comprendesse due soli pianeti e inizialmente la
coordinata `x` della posizione di Marte fosse 3 e quella di Giove fosse 5,
allora la coordinata `x` della velocità di Marte cambierebbe di +1 (perché 3 <
5) mentre quella di Giove cambierebbe di -1 (perché 5 > 3).
Dato che all'inizio le velocità sono nulle, dopo tale aggiornamento la
coordinata `x` della velocità di Marte sarebbe 1, mentre quella di Giove sarebbe
-1 e l'aggiornamento della posizione dei due pianeti porterebbe la coordinata
`x` della posizione di entrambi a 4. Di conseguenza, all'aggiornamento
successivo le loro due velocità (ma non le loro posizioni) rimarrebbero
immutate.

Un **sistema astronomico** è una collezione di pianeti e stelle fisse. Esso è
caratterizzato da uno *stato* che evolve a *tempo discreto*: al tempo 0 lo stato
è dato dall'elenco di tutti i corpi celesti che lo compongono, con la posizione
assegnata e la velocità nulla, quindi per ogni istante di tempo avvengono i due
aggiornamenti descritti prima:

* ciascun pianeta (interagendo con tutti i corpi celesti del sistema) aggiorna
  la sua velocità,
* dopo che le nuove velocità sono state calcolate per tutti i pianeti, ciascun
  pianeta procede con l'aggiornare la sua posizione.

Questo determina il nuovo stato, costituito dall'elenco di tutti i corpi celesti
con le posizioni e velocità opportunamente aggiornate. L'*energia totale* di un
sistema astronomico in un certo stato è data dalla somma dell'energia di tutti i
corpi celesti di cui è costituito.

## Classe di test

Scrivete un metodo statico `main` nella classe che ritenete più opportuna che
legga dal flusso di ingresso una sequenza di quintuple corrispondenti ai vari
corpi celesti; ogni quintupla è data da:

* un carattere che può essere `S` o `P` per indicare, rispettivamente, che il
  corpo celeste sia una stella o un pianeta,
* una stringa (che non contiene spazi) che indica il nome del corpo celeste,
* tre interi che indicano le coordinate iniziali del corpo celeste.

Tali quintuple vanno utilizzate per popolare un sistema astronomico che deve
essere fatto evolvere un numero di passi pari all'intero indicato come parametro
sulla linea di comando. Al termine dell'evoluzione, dev'essere stampato l'elenco
dei corpi celesti del sistema (in ordine alfabetico di nome e con i dati
relativi a posizione e velocità), infine deve essere stampata l'energia totale
del sistema.

## Vincoli

Potete assumere che l'input abbia il formato specificato, che tutti i numeri
coinvolti nell'esecuzione del codice siano interi (e possano essere
rappresentati da variabili di tipo `int` per quanto concerne posizione e
velocità, e di tipo `long` per quanto concerne le energie). Pertanto, un modo
plausibile di leggere le informazioni fornite in input è il seguente:

    Scanner s = new Scanner(System.in);
    while (s.hasNext()) {
      char pOrS = s.next().charAt(0); // can be P or S
      String name = s.next()
      int x = s.nextInt();
      int y = s.nextInt();
      int z = s.nextInt();
      …
    }

## Esempio

Eseguendo `soluzione 1` e avendo

    P Marte -8 -10 0
    P Giove 5 5 10
    P Saturno 2 -7 3
    P Venere 9 -8 -3

nel flusso di ingresso, il programma emette nel flusso d'uscita

    Pianeta, nome: Giove, pos: (4, 2, 7), vel: (-1, -3, -3)
    Pianeta, nome: Marte, pos: (-5, -7, 1), vel: (3, 3, 1)
    Pianeta, nome: Saturno, pos: (3, -8, 2), vel: (1, -1, -1)
    Pianeta, nome: Venere, pos: (6, -7, 0), vel: (-3, 1, 3)
    Energia totale: 312

Similmente, col medesimo input, eseguendo `soluzione 100` viene emesso

    Pianeta, nome: Giove, pos: (13, 16, -3), vel: (3, -11, -5)
    Pianeta, nome: Marte, pos: (8, -12, -9), vel: (-7, 3, 0)
    Pianeta, nome: Saturno, pos: (-29, -11, -1), vel: (-3, 7, 4)
    Pianeta, nome: Venere, pos: (16, -13, 23), vel: (7, 1, 1)
    Energia totale: 1940

nel flusso d'uscita. Similmente, eseguendo `soluzione 21` e avendo

    S Sole 0 0 0
    P Giove 1 0 0
    P Marte 0 1 0
    P Saturno 0 0 1

nel flusso di ingresso, il programma emette nel flusso d'uscita

    Pianeta, nome: Giove, pos: (-2, 0, 0), vel: (-3, 2, 2)
    Pianeta, nome: Marte, pos: (0, -2, 0), vel: (2, -3, 2)
    Pianeta, nome: Saturno, pos: (0, 0, -2), vel: (2, 2, -3)
    Stella fissa, nome: Sole, pos: (0, 0, 0)
    Energia totale: 42
