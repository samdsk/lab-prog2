# Numeri Razionali

## Descrizione

Lo scopo dell'esercizio è progettare e implementare una gerarchia di oggetti
utili a rappresentare *numeri razionali*,
ricordando che,
per ciascuna classe sviluppata,
**dovranno essere descritte**
(attraverso commenti presenti nel codice)
le scelte relative alla **rappresentazione** dello stato
(con particolare riferimento all'*invariante di rappresentazione*
e alla *funzione di astrazione*)
e ai **metodi**
(con particolare riferimento a *pre/post-condizioni* ed *effetti collaterali*).

Una volta che un oggetto di questo tipo esiste,
dovrà essere fornita la possibilità
— tramite l'implementazione di opportuni metodi —
di svolgere le classiche operazioni algebriche tra numeri razionali.

Inoltre,
il metodo `repOK()`
dovrà fornire un'appropriata implementazione dell'_invariante di rappresentazione_,
e dovranno essere sovrascritti in maniera opportuna i metodi
`toString()`,
(in modo che restituisca una stringa idonea a rappresentare lo stato
delle istanze di questa classe
o, in altri termini, che implementi la _funzione di astrazione_),
`equals()` e `hashCode()`.

### Le classi per le eccezioni

Dovranno essere implementate classi per le eccezioni
(controllate o non controllate)
che si riterranno necessarie.

## La classe di test

Sarà vostro compito, inoltre,
creare una classe di nome `Test`,
che deve essere utilizzata per verificare il comportamento della classe implementata.
Tale classe deve leggere dal flusso di ingresso
una sequenza
(non vuota e non limitata)
di coppie di interi
che rappresentano numeratori e denominatori di numeri razionali (validi).
Deve emettere quindi
nel flusso d'uscita standard
il risultato delle operazioni di somma, moltiplicazione e divisione tra i numeri razionali letti,
e il numero di razionali equivalenti nella sequenza inserita.
