# Anagrammi

## Descrizione

Scopo dell'esercizio è progettare e implementare una piccola gerarchia di
oggetti utili a individuare gli anagrammi in un elenco di parole assegnate.

Dovrete decidere quali classi (concrete o astratte) e quali interfacce
implementare. Per ciascuna di esse dovrete descrivere (preferibilmente in
formato Javadoc, ma comunque attraverso commenti presenti nel codice) le scelte
relative alla rappresentazione dello stato (con particolare riferimento
all'*invariante di rappresentazione* e alla *funzione di astrazione*) e ai
metodi (con particolare riferimento a *pre-*/*post-condizioni* ed *effetti
collaterali*).

Una parola è un **anagramma** di un'altra se le due parole contengono gli stessi
caratteri (con la stessa frequenza); ad esempio "sbrendolato" è un anagramma di
"sbrodolante". Un modo semplice per verificare se due parole sono una
l'anagramma dell'altra è usare la **firma** di una parola, ossia la stringa che
si ottiene ordinando alfabeticamente le sue lettere: se due parole hanno la
stessa firma, allora sono una l'anagramma dell'altra. Nell'esempio di prima,
entrambe le parole hanno firma "abdelnoorst".

## Classe di test

Scrivete un metodo statico `main` nella classe che ritenete più opportuna che
legga dal flusso di ingresso una sequenza di parole ed emetta gli anagrammi in
essa contenuti nel flusso d'uscita.

Più precisamente, il programma determina gli insiemi di parole che sono
anagrammi di una stessa parola e li emette in ordine decrescente di taglia,
elencando le parole in ciascun insieme in ordine lessicografico crescente; nel
caso ci siano due insiemi della stessa taglia, li emette nell'ordine
lessicografico tra le prime parole di ciascun insieme.

Ad esempio, se il flusso d'ingresso contiene le parole

    aliberto
    arcetti
    arivetti
    aspite
    attrice
    bertolai
    caretti
    catteri
    paties
    peitas
    pesati
    rassetti
    reattivi
    restasti
    riavetti

il programma emette

    [arcetti, attrice, caretti, catteri]
    [aspite, paties, peitas, pesati]
    [arivetti, reattivi, riavetti]
    [aliberto, bertolai]
    [rassetti, restasti]

nel flusso d'uscita.
