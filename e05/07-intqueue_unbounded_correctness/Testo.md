# Code non limitate di Interi

## Dimostrazioni di correttezza

*Dopo* aver sviluppato una soluzione per l'esercizio 
in cui era richiesto di rappresentare *code non limitate di interi*,
e 
aver fornito 
un'implementazione di `toString()`,
`repOK()`,
`equals()` e `hashcode()`,
motivare informalmente
(tramite commenti nel codice)
perché la soluzione sviluppata è corretta e
fornire una dimostrazione formale di correttezza dell'implementazione.
In particolare,
si dovrà mostrare
che l'_invariante di rappresentazione sia preservato_,
che le _operazioni_ sviluppate siano _adeguate_
e svolgano in maniera _corretta_ ciò per cui sono state definite,
e che valga l'_invariante d'astrazione_.

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