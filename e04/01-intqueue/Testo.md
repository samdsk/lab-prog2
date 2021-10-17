# Code di Interi

## Descrizione

Scopo dell'esercizio è progettare e implementare una gerarchia di oggetti utili
a rappresentare *code di interi*.
Le *code* sono strutture dati
che consentono la memorizzazione e l'accesso alle informazioni in modalità _FIFO_
(First-In/First-Out).

In questo caso si dovrà procedere,
con la creazione di una *classe* (concreta),
il cui nome è `IntQueue`,
che deve avere un *costruttore* con un solo parametro `n`,
che consentirà di creare una coda vuota
con una capienza massima pari a `n` numeri interi.

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
i seguenti dati:

* un numero `n`,
  seguito da
* una sequenza
  (non limitata)
  di `+1` e `-1`.

Dopo aver instanziato una coda di dimensione `n`,
dovranno essere eseguite le seguenti operazioni fino alla chiusura del flusso d'ingresso
o fino al raggiungimento di uno stato non valido:

* ogni volta che sarà letto un `+1`,
  bisognerà eseguire `enqueue(numEnqueue)`,
  dove `numEnqueue` sarà il numero di `+1` letti fino al momento dell'invocazione,
  mentre
* ogni volta che sarà letto un `-1`,
  bisognerà eseguire una operazione di `dequeue`,
  e stampare e il numero estratto dalla coda.

Dopo la chiusura del flusso d'ingresso,
qualora il numero di `-1` ecceda quello di `+1`,
oppure se si tenta di inserire più elementi della capienza massima della coda,
dovrà essere stampato il contenuto della coda,
utilizzando il metodo `toString()`,
seguito dal numero degli elementi presenti nella coda.
