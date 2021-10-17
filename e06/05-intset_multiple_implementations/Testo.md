# Implementazioni multiple di IntSet

## Descrizione

Svolgete l'esercizio 7.7 del libro di testo di Barbara Liskov, *et al.*,
realizzando una interfaccia `IntSet` e quindi (almeno) due implementazioni di
essa adatte a gestire il caso di insiemi rispettivamente di piccole, o grandi,
dimensioni; non tralasciate di documentare l'*invariante di rappresentazione* e
la *funzione d'astrazione*.

### La classe di test

Create una classe con nome `Test`, che deve essere utilizzata per verificare il
comportamento della soluzione da voi implementata.

Tale classe riceve come parametro dalla linea di comando un carattere che può
essere `S` o `B` a seconda del quale istanzia, rispettivamente, un insieme
adatto a contenere pochi, o molti elementi. La classe legge quindi dal flusso di
ingresso una sequenza (non limitata) di interi (non necessariamente distinti) e
li aggiunge all'insieme precedentemente istanziato; al termine del flusso
d'ingresso, la classe stampa la dimensione dell'insieme.
