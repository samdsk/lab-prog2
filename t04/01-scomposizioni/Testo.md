Scomposizioni in fattori primi
==============================

Descrizione
-----------

Ogni numero intero maggiore di 1 può essere *scomposto* nel prodotto di
*fattori* opportunamente scelti tra i *numeri primi*. Ad esempio, questa è una
rappresentazione testuale delle scomposizioni degli interi tra 2 e 13

    2 => [2]
    3 => [3]
    4 => [2, 2]
    5 => [5]
    6 => [2, 3]
    7 => [7]
    8 => [2, 2, 2]
    9 => [3, 3]
    10 => [2, 5]
    11 => [11]
    12 => [2, 2, 3]
    13 => [13]

in cui è stato sempre omesso il fattore 1. Scrivete un programma che, dato un
numero come parametro sulla linea di comando, emetta nel flusso di uscita la
sequenza di scomposizioni, fino al numero dato (compreso).


Vincoli
-------

Si può assumere che i numeri ottenuti dalle trasformazioni possano sempre essere
rappresentati con una variabile di tipo `int`.


Esempio
-------

Eseguendo `soluzione` e avendo `20` come parametro sulla linea di comando, il
programma emette

    2 => [2]
    3 => [3]
    4 => [2, 2]
    5 => [5]
    6 => [2, 3]
    7 => [7]
    8 => [2, 2, 2]
    9 => [3, 3]
    10 => [2, 5]
    11 => [11]
    12 => [2, 2, 3]
    13 => [13]
    14 => [2, 7]
    15 => [3, 5]
    16 => [2, 2, 2, 2]
    17 => [17]
    18 => [2, 3, 3]
    19 => [19]
    20 => [2, 2, 5]

nel flusso d'uscita.

Suggerimenti
------------

Onde ridurre il numero di divisioni da effettuare per risolvere questo problema è
possibile memorizzare le scomposizioni in una lista, l'elemento `n`-esimo della
quale corrisponda alla lista di fattori primi di `n`.

Per popolare la lista delle scomposizioni è sufficiente osservare che,
procedendo in ordine crescente, il suo elemento `n`-esimo può essere ottenuto:

* determinando il più piccolo intero 1 < `d` < `n` che divide `n`, quindi
* costruendo una lista di fattori contenente `d` seguita dalla scomposizione di
  `n / d` (che sarà stata calcolata in precedenza, dato che `n / d` < `n` ed è
  ovviamente intero).

  Osservate che potrebbe essere d'aiuto anche memorizzare una lista dei numeri
  primi man mano individuati.
