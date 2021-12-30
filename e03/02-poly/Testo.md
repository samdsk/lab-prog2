# Polinomi

## Descrizione

Scopo dell'esercizio è progettare e implementare una gerarchia di oggetti utili
a rappresentare *polinomi in una sola variabile* a coefficienti interi.

Si dovrà procedere, quindi, con la creazione di una *classe* (concreta), il cui
nome è `Poly`, che deve avere un *costruttore* privo di parametri, che
consentirà di creare il polinomio zero, e un costruttore `Poly(int c, int n)`
che instanzierà il monomio \(cx^n\) e solleverà un'eccezione di tipo
`NegativeExponentException` se `n` è negativo.

Una volta che un oggetto esiste, dovrà essere fornita la possibilità — tramite
l'implementazione di opportuni metodi — di svolgere le operazioni algebriche tra
polinomi di somma (`Poly add(Poly q)`), differenza (`Poly sub(Poly q)`), e
prodotto (`Poly mul(Poly q)`), e di ottenere il polinomio opposto (`Poly
minus()`),

La classe deve inoltre avere dei metodi che consentano di reperire informazioni
riguardo allo stato delle istanze di `Poly`. In particolare, i metodi con
prototipi `int degree()` e `int coeff(int d)` deve restituire, rispettivamente,
il grado del polinomio e il coefficiente del termine di grado `d`.

Sovrascrivere, infine, il metodo `toString()` in modo che restituisca una
stringa che descriva in maniera significativa lo stato dell'istanza sul quale è
invocato.

### La classe NegativeExponentException

L'eccezione non controllata `NegativeExponentException` dovrà essere sollevata
qualora si tenti di creare un polinomio con coefficienti negativi.

### La classe di test

La classe di test `TestRunner` (fornita dal docente) legge dal flusso di
ingresso due numeri `p` e `q`, legge quindi `p` coppie di interi corrispondenti
a coefficiente e grado di altrettanti monomi che somma per ottenere (sommandoli)
un primo polinomio, quindi legge `q` coppie e forma un secondo polinomio. Emette
quindi nel flusso d'uscita standard varie operazioni tra i polinomi (stampando
per ciascun risultato le coppie di coefficiente e grado per tutti i termini da 0
al grado del polinomio).