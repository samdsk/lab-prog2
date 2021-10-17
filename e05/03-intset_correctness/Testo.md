# Insiemi di Interi

## Dimostrazioni di correttezza

*Dopo* aver sviluppato una soluzione per l'esercizio 
per la rappresentazione di *insiemi di interi* (IntSet)
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

Sarà vostro compito,
inoltre,
creare una classe con nome `Test`,
che deve essere utilizzata per verificare il comportamento della classe da voi implementata.
Tale classe deve leggere dal flusso di ingresso un elenco
(possibilmente vuoto)
di interi
e aggiungerli a un insieme
e quindi emettere nel flusso di uscita la dimensione dell'insieme.