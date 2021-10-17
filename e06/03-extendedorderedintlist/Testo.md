# ExtendedOrderedIntList

## Descrizione

Svolgete l'esercizio 7.3 del libro di testo di Barbara Liskov, *et al.*,
implementando la classe `ExtendedOrderedIntList`; non tralasciate di documentare
l'*invariante di rappresentazione* e la *funzione d'astrazione*.

### La classe di test

Create una classe con nome `Test`, che deve essere utilizzata per verificare il
comportamento della soluzione da voi implementata.

Tale classe legge dal flusso di ingresso una sequenza (non limitata) di interi
(non necessariamente distinti) e li aggiunge a una `ExtendedOrderedIntList`
opportunamente istanziata; al termine del flusso d'ingresso, la classe stampa
gli elementi della lista nell'ordine in cui sono prodotti dall'iteratore
`bigToSmall`.
