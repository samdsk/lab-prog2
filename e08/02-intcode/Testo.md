# Intcode

## Descrizione

Lo scopo dell'esercizio è progettare, fornire un'adeguata specificazione e
implementare una gerarchia di oggetti utili a rappresentare una macchina
virtuale per **Intcode**, un linguaggio di programmazione esoterico, che non ha
alcuna vera utilità nel mondo reale.

Per portare a termine il lavoro dovrà decidere se e quali interfacce e classi
(concrete o astratte) implementare. Per ciascuna di esse **dovrà descrivere**
(preferibilmente in formato Javadoc, ma comunque solo attraverso commenti
presenti nel codice) le scelte relative alla **rappresentazione** dello stato
(con particolare riferimento all'*invariante di rappresentazione* e alla
*funzione di astrazione*) e ai **metodi** (con particolare riferimento a
*pre/post-condizioni* ed *effetti collaterali*, soffermandosi a illustrare le
ragioni della *correttezza* solo per le implementazioni che riterrà più
critiche). Osservi che l'esito di questa prova, che le consentirà di accedere o
meno all'orale, si baserà tanto su questa documentazione quanto sul codice
sorgente.

Presti particolare attenzione agli *errori di compilazione*: il contenuto dei
file che il compilatore si rifiuta di compilare *non sarà affatto esaminato*. Se
riscontrasse errori di compilazione che non è in grado di correggere, valuti la
possibilità di racchiudere le porzioni di codice che li causano all'interno di
commenti; resta inteso che tale codice commentato non sarà valutato.

Per completare l'esercizio, aggiunga ad ogni classe il metodo `repOK()` dovrà
fornire un'appropriata implementazione dell'*invariante di rappresentazione*, e
dovranno essere sovrascritti in maniera opportuna i metodi `toString()`, (in
modo che restituisca una stringa idonea a rappresentare lo stato delle istanze
delle classi implementate o, in altri termini, che implementi la *funzione di
astrazione*), `equals()` e `hashCode()`.

### Struttura dei programmi Intcode

Un programma Intcode è costitituito da una lista di interi (positivi e negativi)
separati da virgole. Un esempio di programma è il seguente:

	1, 3, 4, 2, 99

Intcode implementa un'architettura di von Neumann con una memoria non limitata
di celle, con indici a partire da 0, che possono memorizzare numeri interi.

Prima dell'esecuzione il programma è copiato nelle celle iniziali della memoria,
mentre le celle rimanenti sono inizializzate con valore `0`. La macchina
virtuale di Intcode ha due registri: un *instruction pointer*, che indica
l'istruzione da eseguire, e un *relative base pointer* usato per
l'indirizzamento relativo. Inizialmente, entrambi i registri sono inizializzati
con valore `0`.

### Formato delle istruzioni

Il valore indicato dall'instruction pointer è interpretato come un valore
decimale con cifre `ABCDE`. Le cifre meno significative `DE` definiscono
l'*opcode*, mentre `C`, `B`, e `A` definiscono le *modalità di indirizzamento*
del primo, secondo, e terzo parametro, rispettivamente (se presenti).

#### Opcodes

| Op | Parametri | Descrizione                                                                                                                    |
|----|-----------|--------------------------------------------------------------------------------------------------------------------------------|
| 01 | 3         | Somma i primi due argomenti e memorizza il risultato nella cella di memoria indicata dal terzo argometo.                       |
| 02 | 3         | Moltiplica i primi due argomenti e memorizza il risultato nella cella di memoria indicata dal terzo argometo.                  |
| 03 | 1         | Legge in input (da standard input) un numero intero e lo memorizza nella cella di memoria indicata dall'argometo.              |
| 04 | 1         | Emette in output (su standard output) il primo argomento.                                                                      |
| 05 | 2         | Se il primo argomento è diverso da zero, imposta il valore dell'instruction pointer affinché sia uguale al secondo argomento.  |
| 06 | 2         | Se il primo argomento è uguale a zero, imposta il valore dell'instruction pointer affinché sia uguale al secondo argomento.    |
| 07 | 3         | Se il primo argomento è minore del secondo, scrive 1 nella cella di memoria indicata dal terzo argomento. Altrimenti scrive 0. |
| 08 | 3         | Se i primi due argomenti sono uguali, scrive 1 nella cella di memoria indicata dal terzo argomento. Altrimenti scrive 0.       |
| 09 | 1         | Aggiunge il valore indicato dal primo argomento al relative base pointer.                                                      |
| 99 | N/A       | Arresta l'esecuzione.                                                                                                          |

Fatta eccezione per le operazioni di *salto* (ossia le istruzioni con codici
operativi 5 e 6, che modificano il contenuto dell'instruction pointer),
l'instruction pointer è fatto avanzare sino alla prossima istruzione in seguito
al completamento dell'istruzione corrente.

#### Modalità d'accesso

Le modalità d'accesso influenzano come gli argomenti delle istruzioni sono letti
o scritti. Sono definiti tre modalità:

* *Modalità 0*, indirizzamento diretto: il parametro è l'indirizzo di una cella
  che dev'essere acceduta per la scrittura o la lettura.
* *Modalità 1*, valore: il parametro è il valore letto. (Questa modalità non è
  mai usata per le scritture.)
* *Modalità 2*, indirizzamento indiretto: il parametro è aggiunto al relative
  base register per ottenere l'indirizzo della cella che dev'essere acceduta per
  la scrittura o la lettura.


### Le classi per le eccezioni

Dovranno essere implementate classi per le eccezioni (controllate o non
controllate) che si riterranno necessarie.

## La classe di test

Sarà vostro compito, inoltre, creare una classe di test, che deve essere
utilizzata per verificare il comportamento delle classi implementate. Tale
classe deve leggere da riga di comando una sequenza (non limitata) di interi
separati da virgola e deve simulare l'esecuzione del programma su una macchina
virtuale per Intcode.

Al termine dell'esecuzione, dovrà emettere quindi nel flusso d'uscita standard,
lo stato della macchina virtuale, rappresentato dal contenuto della memoria e
dei suoi registri.

### Esempi d'esecuzione

Si supponga di avere in input il seguente programma

	1,0,0,0,99

Al termine dell'esecuzione,
la memoria della macchina conterrà

	2,0,0,0,99

Infatti:

 * Al momento dell'inizializzazione, l'instruction pointer punta alla prima
   cella di memoria (posizione 0), contenente l'opcode `1` (che corrisponde
   all'operazione di somma).
 * Il valore in posizione `0` (primo parametro, in posizione 1) è sommato al
   valore in posizione `0` (secondo parametro, in posizione 2) e il risultato è
   scritto in posizione `0` (terzo parametro, in posizione 3).
 * Dopo aver eseguito l'operazione di somma, l'instruction pointer viene
   incrementato al fine di indicare il prossimo opcode, che è 99 (che
   corrisponde all'istruzione di terminazione dell'esecuzione). Si noti che, in
   questo caso, l'instruction pointer viene incrementato fino al superamento le
   celle di memoria contenenti i tre parametri dell'istruzione `1` (in altri
   termini, è incrementato di 4).

Pertanto, al termine dell'esecuzione, lo stato della virtual machine sarà

    IntcodeVM :
      Memory : [2,0,0,0,99]
      Registers : [IP = 5, RBP = 0]



Il seguente programma, invece, stampa il valore decimale dei caratteri ASCII
della stringa `Hello, World!`

    4,3,101,72,14,3,101,1,4,4,5,3,16,99,29,7,0,3,-67,-12,87,-8,3,-6,-8,-67,-23,-10

Al termine dell'esecuzione, lo stato della virtual machine sarà

    IntcodeVM :
      Memory : [4,3,101,0,28,3,101,1,4,4,5,3,16,99,29,7,0,3,-67,-12,87,-8,3,-6,-8,-67,-23,-10]
      Registers : [IP = 14, RBP = 0]

Infine, il seguente programma

    4,17,4,19,1001,17,1,17,8,17,18,16,1006,16,0,99,-1,1,11,32

stampa i numeri da `1` a `10` separati da spazio (che, secondo la codifica
ASCII, corrisponde al valore decimale 32). Al termine dell'esecuzione, lo stato
della macchina virtuale sarà

    IntcodeVM :
      Memory : [4,17,4,19,1001,17,1,17,8,17,18,16,1006,16,0,99,1,11,11,32]
      Registers : [IP = 16, RBP = 0]
