# Polinomi sparsi

# Polinomi densi

## Iterazione dei termini

*Dopo* aver sviluppato una soluzione per l'esercizio
per la rappresentazione di *polinomi sparsi in una sola variabile* a coefficienti interi,
aggiungete un *iteratore* `terms()`
che restituisca un *generatore* di tipo `Iterator<Poly.Term>`
che consenta di iterare i termini
(a coefficiente non nullo,
in ordine crescente di grado)
del polinomio
(con `Poly.Term` si intende il tipo che avete scelto per rappresentare un termine del polinomio sparso,
implementato come *nested static class*).


### La classe di test

Sarà vostro compito, inoltre,
creare una classe con nome `Test`,
che deve essere utilizzata per verificare il comportamento dell'iteratore implementato.
Tale classe legge dal flusso di ingresso una sequenza (non limitata) di coppie di interi
corrispondenti,
rispettivamente,
a coefficiente e grado dei termini di un polinomio (sparso);
al termine del flusso d'ingresso,
la classe stampa i termini del polinomio così ottenuto
nell'ordine in cui sono prodotti dall'iteratore.
