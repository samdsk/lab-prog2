# Insiemi di Interi

## Descrizione

Scopo dell'esercizio è progettare e implementare una gerarchia di oggetti utili
a rappresentare *insiemi di interi*, dove il termine *insieme* è inteso in senso
matematico.

Si dovrà procedere, quindi, con la creazione di una *classe* (concreta), il cui
nome è `IntSet`, che deve avere un *costruttore* privo di parametri, che
consentirà di creare un insieme vuoto.

Una volta che un oggetto esiste, dovrà essere fornita la possibilità — tramite
l'implementazione di opportuni metodi — di inserire e rimuovere elementi da un
insieme.

La classe deve inoltre avere dei metodi che consentano di reperire informazioni
riguardo allo stato delle istanze di `IntSet`. In particolare,

 * il metodo con prototipo `int choose()` deve restituire un intero scelto
   arbitrariamente tra gli elementi di una istanza, e sollevare un'eccezione di
   tipo `EmptyException` se l'insieme è vuoto;
 * il metodo `int size()` deve restituire la cardinalità dell'insieme;
 * il metodo `contains(int x)` deve restituire `true` se l'elemento `x` è
   nell'insieme, `false` altrimenti;
 * infine, il metodo `toString()` deve essere sovrascritto in modo che
   restituisca una stringa formata dai valori degli elementi contenuti
   nell'insieme racchiusi tra parentesi graffe e separati da virgola.

Si noti che questi ultimi metodi non cambiano lo stato degli oggetti istanziati,
né della classe.

### La classe EmptyException

L'eccezione non controllata `EmptyException`, dovrà essere sollevata qualora si
tenti di accedere agli elementi di un insieme vuoto.

### La classe di test

Sarà vostro compito, inoltre, creare una classe con nome `Test`, che deve essere
utilizzata per verificare il comportamento della classe da voi implementata.
Tale classe deve leggere dal flusso di ingresso un elenco (possibilmente vuoto)
di interi ed aggiungerli ad un insieme e quindi emettere nel flusso di uscita la
dimensione dell'insieme.
