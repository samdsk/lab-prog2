# OrderedIntList

## Descrizione

*Dopo* aver sviluppato la classe `OrderedIntList` descritta nella Sezione 6.6
"Ordered Lists" del libro di testo di Barbara Liskov, *et al.*, comprensiva
dell'iteratore `smallToBig`, completate l'implementazione realizzando:

* l'iteratore `bigToSmall` (specificato nella Sezione 6.6),
* i metodi `toString()` e `repOK()`

riflettendo bene sull'*invariante di rappresentazione* e sulla *funzione d'astrazione*.

### La classe di test

Sarà vostro compito, inoltre, creare una classe con nome `Test`,
che deve essere utilizzata per verificare il comportamento della classe da voi implementata.
Tale classe legge dal flusso di ingresso una sequenza (non limitata) di interi distinti
e li accoda a una `OrderedIntList` opportunamente istanziata;
al termine del flusso d'ingresso,
la classe stampa su una prima riga gli elementi della coda
nell'ordine in cui sono prodotti dall'iteratore `bigToSmall`
e quindi,
sulla riga successiva,
quelli prodotto da `smallToBig`.
