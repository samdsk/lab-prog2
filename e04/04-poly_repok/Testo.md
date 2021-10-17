# Polinomi densi

## Descrizione

*Dopo* aver sviluppato una soluzione per l'esercizio 
per la rappresentazione di *polinomi in una sola variabile* a coefficienti interi,
fornire 
un'implementazione di `toString()` 
in modo che restituisca una stringa idonea a rappresentare lo stato dei polinomi
(o, in altri termini, che implementi la _funzione di astrazione_
dopo averne fornito una descrizione),
del metodo `repOK()`,
che
dovrà fornire un'appropriata implementazione dell'_invariante di rappresentazione_
(dopo averla specificata),
e del metodo `equals(Object o)`.

### La classe di test

La classe di test `TestRunner` (fornita dal docente) legge dal flusso di ingresso due numeri `p` e `q`, legge quindi `p` coppie di interi corrispondenti a coefficiente e grado di altrettanti monomi che somma per ottenere (sommandoli) un primo polinomio, quindi legge `q` coppie e forma un secondo polinomio. Emette quindi nel flusso d'uscita standard varie operazioni tra i polinomi (stampando per ciascun risultato le coppie di coefficiente e grado per tutti i termini da 0 al grado del polinomio).
