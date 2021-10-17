# Polinomi densi

## Iterazione dei gradi

*Dopo* aver sviluppato una soluzione per l'esercizio
per la rappresentazione di *polinomi in una sola variabile* a coefficienti interi
aggiungete un *iteratore* `degrees()`
che restituisca un *generatore* di tipo `Iterator<Integer>`
che consenta di iterare
(in ordine crescente)
i gradi per cui nel polinomio c'è un termine a coefficiente non nullo.

### La classe di test

Sarà vostro compito,
inoltre,
creare una classe con nome `Test`,
che deve essere utilizzata per verificare il comportamento dell'iteratore implementato.
Tale classe legge dal flusso di ingresso una sequenza (non limitata) di coppie di interi
corrispondenti,
rispettivamente,
a coefficiente e grado dei termini di un polinomio;
al termine del flusso d'ingresso,
la classe stampa i gradi del plinomio così ottenuto
nell'ordine in cui sono prodotti dall'iteratore.
