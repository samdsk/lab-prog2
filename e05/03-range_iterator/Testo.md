# Un iteratore che generi un *range* di interi

## Descrizione

Un *range* è una sequenza monotona equispaziata di interi univocamente
determinate da tre interi $(a, b, δ)$ con $δ \not= 0$ che corrisponde agli interi
$r_i = a + \delta i$ tali che $i \geq 0$ e $r_i < b$ se $δ > 0$, o $r_i > b$ se
$δ < 0$; il range è vuoto se $r_0$ non soddisfa le disuguaglianze precedenti.

Ad esempio il range determinato da $(5, 12, 2)$ è $[5, 7, 9, 11]$ mentre quello
determinato da $(-3, -6, -1)$ è $[-3, -4, -5]$.

Implementate un iteratore sugli interi che, dati i tre parametri $(a, b, δ)$, iteri sugli elementi $r_i$ del *range*.

Per portare a termine il lavoro dovrà decidere quali classi (eventualmente
interne) implementare e per tali classi **dovrà descrivere** le scelte relative
alla **rappresentazione** dello stato (con particolare riferimento
all'*invariante di rappresentazione* e alla *funzione di astrazione*).

**Nota bene**: Questo esercizio non prevede una classe (e dei file) di test. Può
essere comunque opportuno svilupparne una.
