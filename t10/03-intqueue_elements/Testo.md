# Code di Interi

## Iterazione del contenuto

*Dopo* aver sviluppato una soluzione per l'esercizio
in cui era richiesto di rappresentare *code limitate di interi*,
aggiungete un *iteratore* `elements()` che restituisca un *generatore* di tipo
`Iterator<Integer>` che consenta di iterare sugli elementi contenuti nella coda
(dal primo all'ultimo in ordine di inserimento).


### La classe di test

Sarà vostro compito, inoltre,
creare una classe con nome `Test`,
che deve essere utilizzata per verificare il comportamento dell'iteratore implementato.
Tale classe deve leggere dal flusso di ingresso
i seguenti dati:

* un numero \(n\),
  seguito da
* una sequenza
  di (al più \(n + \lfloor n/3 \rfloor \)) interi.

Dopo aver istanziato una coda di dimensione \(n\),
la classe vi accoda gli interi letti,
invocando un `dequeue` ogni tre interi letti;
al termine del flusso d'ingresso,
la classe stampa gli elementi della coda nell'ordine in cui sono prodotto dall'iteratore.
