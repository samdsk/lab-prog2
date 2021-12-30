# Code non limitate di Interi

## Descrizione

Dopo aver implementato le code (limitate) di interi,
si fornisca un'implementazione nel caso in cui non ci sia un limite sulla
capienza massima di queste strutture dati.

Pertanto,
lo scopo dell'esercizio è progettare e implementare una gerarchia di oggetti utili
a rappresentare *code di interi unbounded*,
ricordando che,
per ciascuna classe sviluppata,
**dovranno essere descritte**
(attraverso commenti presenti nel codice)
le scelte relative alla **rappresentazione** dello stato
(con particolare riferimento all'*invariante di rappresentazione*
e alla *funzione di astrazione*)
e ai **metodi**
(con particolare riferimento a *pre/post-condizioni* ed *effetti collaterali*).

In questo caso
si dovrà procedere con la creazione di una *classe*
(concreta),
il cui nome è `UnboundedIntQueue`,
che deve avere un *costruttore* privo di parametri,
che consentirà di creare una coda vuota.

Una volta che un oggetto di questo tipo esiste,
dovrà essere fornita la possibilità
— tramite l'implementazione di opportuni metodi —
di inserire (`enqueue`) e rimuovere (`dequeue`) elementi da una coda.

Inoltre,
dovrà essere sovrascritto il metodo `toString()` 
in modo che restituisca una stringa idonea a rappresentare lo stato della coda
(o, in altri termini, che implementi la _funzione di astrazione_)
e il metodo `repOK()`
dovrà fornire un'appropriata implementazione dell'_invariante di rappresentazione_.


### La classe di test

Sarà vostro compito, inoltre,
creare una classe con nome `Test`,
che deve essere utilizzata per verificare il comportamento della classe implementata.
Tale classe deve leggere dal flusso di ingresso 
una sequenza
(non limitata)
di `+1` e `-1`.

Dopo aver instanziato una coda
dovranno essere eseguite le seguenti operazioni fino alla chiusura del flusso d'ingresso:

 - ogni volta che sarà letto un `+1`,
   bisognerà eseguire `enqueue(numEnqueue)`,
   dove `numEnqueue` sarà il numero di `+1` letti fino al momento dell'invocazione,
   mentre
 - ogni volta che sarà letto un `-1`,
   bisognerà eseguire una operazione di `dequeue`,
   e stampare e il numero estratto dalla coda.

Dopo la chiusura del flusso d'ingresso,
o qualora il numero di `-1` ecceda quello di `+1`,
dovrà essere stampato il contenuto della coda,
utilizzando il metodo `toString()`,
e il numero di elementi che contiene.
