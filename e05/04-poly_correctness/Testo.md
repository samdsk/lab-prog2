# Polinomi densi

## Descrizione

*Dopo* aver sviluppato una soluzione per l'esercizio 
per la rappresentazione di *polinomi in una sola variabile* a coefficienti interi
e 
aver fornito 
un'implementazione di `toString()`,
`repOK()`,
`equals()` e `hashcode()`,
motivare informalmente
(tramite commenti nel codice)
perché la soluzione sviluppata è corretta e
fornire una dimostrazione formale di correttezza dell'implementazione.
In particolare,
si dovrà mostrare
che l'_invariante di rappresentazione sia preservato_,
che le _operazioni_ sviluppate siano _adeguate_
e svolgano in maniera _corretta_ ciò per cui sono state definite,
e che valga l'_invariante d'astrazione_.

### La classe di test

La classe di test `TestRunner`
(fornita dal docente)
legge dal flusso di ingresso due numeri `p` e `q`,
legge quindi `p` coppie di interi
corrispondenti a coefficiente e grado di altrettanti monomi
che somma per ottenere un primo polinomio,
quindi legge `q` coppie e forma un secondo polinomio.
Emette quindi nel flusso d'uscita standard i risultati di varie operazioni tra i polinomi
(stampando per ciascun risultato le coppie di coefficiente e grado per tutti i termini da 0 al grado del polinomio).