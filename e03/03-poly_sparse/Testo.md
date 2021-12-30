# Polinomi

## Descrizione

Scopo dell'esercizio è progettare e implementare una gerarchia di oggetti utili
a rappresentare *polinomi sparsi in una sola variabile* a coefficienti interi.

A differenza del precedente esercizio, si supponga di dover prendere delle
scelte implementative che consentano di modellare dei *polinomi sparsi*, ossia
dei polinomi di grado alto composti dalla somma di pochi monomi.

In questo caso, la precedente implementazione potrebbe non essere efficiente,
dato che l'array rappresentante lo stato di ciascun polinomio potrebbe essere di
dimensione elevata e pieno di zeri. Si provi a fornire una nuova
rappresentazione, partendo dalle stesse specificazioni della classe precedente,
che consenta una maggiore efficienza nel caso della rappresentazione di queste
strutture.

### La classe di test

La classe di test `TestRunner` (fornita dal docente) legge dal flusso di
ingresso due numeri `p` e `q`, legge quindi `p` coppie di interi corrispondenti
a coefficiente e grado di altrettanti monomi che somma per ottenere (sommandoli)
un primo polinomio, quindi legge `q` coppie e forma un secondo polinomio. Emette
quindi nel flusso d'uscita standard varie operazioni tra i polinomi (stampando
per ciascun risultato le coppie di coefficiente e grado per tutti i termini da 0
al grado del polinomio).
