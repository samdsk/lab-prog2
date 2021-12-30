# Mappe: Stringhe → Interi

## Descrizione

Lo scopo dell'esercizio è progettare,
fornire un'adeguata specificazione
e implementare una gerarchia di oggetti
utili a rappresentare una semplice *mappa da stringhe a interi*,
ossia una struttura dati che permette di associare,
a ciascuna stringa `k` (_chiave_),
un numero intero `v` (_valore_).

Si ricorda che,
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
di svolgere le usuali operazioni di inserimento (`put`) e rimozione (`remove`) di elementi dalla mappa,
tenendo a mente che
le mappe non possono contenere chiavi duplicate
e che
a ciascuna chiave può essere associato al più un valore.
Inoltre,
se l'associazione `k → v` è presente nella mappa,
dev'essere possibile ottenere (`get`) il valore `v` a partire da `k`.

Infine,
il metodo `repOK()`
dovrà fornire un'appropriata implementazione dell'_invariante di rappresentazione_,
e dovranno essere sovrascritti in maniera opportuna i metodi
`toString()`,
(in modo che restituisca una stringa idonea a rappresentare lo stato delle istanze di questa classe
o,
in altri termini,
che implementi la _funzione di astrazione_),
`equals()` e `hashCode()`.

### Le classi per le eccezioni

Dovranno essere implementate classi per le eccezioni
(controllate o non controllate)
che si riterranno necessarie.

## La classe di test

Sarà vostro compito, inoltre,
creare una classe di test,
che deve essere utilizzata per verificare il comportamento della classe implementata.
Tale classe deve leggere dal flusso di ingresso
una sequenza
(non limitata)
di comandi con il seguente formato

 - `+ str int`,
   che aggiunge alla mappa l'associazione `str → int`,
   oppure
 - `- str`,
   che rimuove dalla mappa l'associazione avente per chiave la stringa `str`
   e,
   se tale chiave era presente,
   stampa
   nel flusso d'uscita standard
   il valore che le corrispondeva.

Al termine della lettura,
deve emettere quindi
nel flusso d'uscita standard,
il numero di elementi contenuti della mappa.

