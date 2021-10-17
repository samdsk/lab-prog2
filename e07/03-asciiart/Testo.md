# ASCII Art

## Descrizione

Scopo dell'esercizio è progettare e implementare una gerarchia di oggetti utili
a realizzare un *interprete* per un semplice linguaggio di manipolazione di
immagini monocromatiche rappresentate tramite matrici di caratteri.

Dovrete decidere quali classi (concrete o astratte) e quali interfacce
implementare. Per ciascuna di esse dovrete descrivere (preferibilmente in
formato Javadoc, ma comunque attraverso commenti presenti nel codice) le scelte
relative alla rappresentazione dello stato (con particolare riferimento
all'*invariante di rappresentazione* e alla *funzione di astrazione*) e ai
metodi (con particolare riferimento a *pre-*/*post-condizioni* ed *effetti
collaterali*).

### Gli elementi grafici

Iniziamo con qualche semplice definizione.

Un **pixel** è un elemento di
immagine che può trovarsi di volta in volta in uno di due possibili stati:
*acceso* o *spento*.

Una **bitmap** di *dimensione* $b \times h$ (con $b >0$ e $h > 0$) è una matrice
di $b\times h$ pixel; chiameremo **coordinate** del pixel nella riga $r$ e
colonna $c$ di una bitmap la coppia di indici $(r, c)$ (dove sia le righe che le
colonne sono numerate a partire da $0$).

Una **figura** è data da un insieme finito di coordinate, alcuni esempi sono:

* il **segmento verticale** di dimensione $h$ con coordinate $(r,c)$, dato
  dall'insieme $\{(r,c), (r+1,c), \ldots, (r+h-1,c)\}$,

* il **segmento orizzontale** di dimensione $l$ con coordinate $(r,c)$, dato
  dall'insieme $\{(r,c), (r,c+1), \ldots, (r,c+l-1)\}$,

* il **rettangolo** di dimensioni $l\times h$ con coordinate $(r,c)$, dato
  dall'unione dei quattro segmenti:  due verticali di dimensione $h$ e
  coordinate $(r,c)$ e $(r,c+l-1)$ e due orizzontali di dimensione $l$ con
  coordinate $(r,c)$ e $(r+h-1,c)$,

* il **timbro** della matrice (per uniformità con quanto avviene nelle
  bitmap, gli indici di riga e colonna delle matrici partono da $0$)
  $A=(a_{i,j})$ con coordinate $(r,c)$, dato dall'insieme $\{(r+i,c+j) \mid a_{i,j}\neq0 \}$.

Data una bitmap e una figura, *disegnare* la figura nella bitmap significa
accendere i pixel della bitmap corrispondenti alle coordinate della figura
(eventualmente ignorando le coordinate cui non corrispondono pixel della
bitmap). In particolare, il disegno del timbro della matrice $A$ con coordinate
$(r,c)$ può essere definito informalmente come segue: per prima cosa si
"sovrappone" la matrice $A$ alla bitmap in modo che l'elemento di riga 0 e
colonna 0 della matrice si trovi "sopra" il pixel di coordinate $(r,c)$ della
bitmap, quindi si accendono i pixel della bitmap che si trovano "sotto" gli
elementi non nulli della matrice.

Concludiamo l'introduzione osservando come una bitmap sarà nel seguito
semplicemente raffigurata tramite $h$ righe di $b$ caratteri ciascuna in cui il
carattere nella colonna $c$ della riga $r$ rappresenti il pixel di coordinate
$(r,c)$, adottando la convenzione di indicare con uno spazio i pixel spenti e
con un asterisco quelli accesi.

Ad esempio, la raffigurazione seguente

|   | 0 | 1 | 2 | 3 | 4 |
| --|---|---|---|---|---|
| **0** | `X` | ␣ | ␣ | ␣ | ␣ |
| **1** | `X` | ␣ | `X` | `X` | ␣ |
| **2** | `X` | ␣ | ␣ | ␣ | ␣ |
| **3** | `X` | ␣ | ␣ | ␣ | ␣ |

corrisponde a una bitmap di dimensione $4\times 5$ (con tutti i pixel
inizialmente spenti) in cui sono stati disegnati il segmento verticale di
dimensione $4$ e coordinate $(0,0)$ e il segmento orizzontale di dimensione $2$
e coordinate $(1,2)$ (i numeri di riga e colonna nella prima riga e prima
colonna della tabella sono riportati solo per facilitare la lettura e non fanno
parte della raffigurazione della bitmap che è data solo dalle `X` e dagli spazi,
che qui sono rappresentati come `␣` per aumentarne la leggibilità).

### I comandi dell'interprete

L'interprete gestisce una bitmap di dimensioni assegnate e, a seguito di una
successione di **comandi**, esegue alcune manipolazioni su di essa tra le quali,
ad esempio, il disegno di alcune figure. In più, l'interprete gestisce  un
insieme di matrici (di varie dimensioni) che possono essere usate per i timbri.

L'interprete inizia l'esecuzione con una bitmap di dimensione $1\times 1$ e
tutti gli elementi di tutte le matrici sono nulli.

Ogni comando è dato da un *nome* (costituito da un carattere minuscolo) e, se
necessario, da un elenco di uno o più numeri interi detti *parametri*; nome e
parametri dei comandi dell'interprete sono

* `n`: $b$ $h$
* `c`:
* `i`:
* `x`: $r$   $c$
* `o`: $r$   $c$
* `h`: $l$   $r$   $c$
* `v`: $h$   $r$   $c$
* `r`: $h$   $l$   $r$   $c$
* `s`: $m$   $r$   $c$   $a_{0,0} \ldots a_{0,c-1} a_{1,0} \ldots a_{1,c-1} \dots a_{r-1,0} \dots a_{r-1,c-1}$
* `d`: $m$   $r$   $c$
* `p`:

Un primo insieme di comandi riguarda direttamente la bitmap. Il comando `n` con
parametri $b$ e $h$ crea una nuova bitmap di dimensione $b\times h$ in cui tutti
i pixel sono inizialmente spenti (se era presente una bitmap precedente, essa
viene eliminata; se uno dei parametri è maggiore di 1000, il comando viene
ignorato). Il comando `c` spegne tutti i pixel della bitmap, mentre il comando
`i` inverte lo stato dei pixel rendendo accesi quelli spenti e viceversa.

La manipolazione diretta dei pixel avviene tramite i comandi `x` e `o` che,
rispettivamente, accendono e spengono il pixel le cui coordinate sono
specificate dai parametri del comando. Se le coordinate eccedono la dimensione
della bitmap, il comando sarà ignorato.

Il disegno di figure è poi ottenuto grazie ai comandi `v`, `h` e `r` che
disegnano rispettivamente un segmento verticale, uno orizzontale e un
rettangolo con dimensioni e coordinate specificate dai parametri.

La gestione dei timbri si basa su due comandi. Il comando `s` legge
nell'$m$-esima matrice gestita dall'interprete una matrice di dimensione
$r\times c$ (le matrici hanno massima dimensione pari a $1000 \times 1000$ e
sono numerate da $0$ a $1000$ escluso); i valori degli elementi della matrice
sono specificati, come parametri, per riga (ossia: i primi $c$ parametri sono i
valori della prima riga, quindi i successivi $c$ parametri quelli della seconda
riga e così di seguito). Se era già stata letta la matrice $m$-esima, la nuova
matrice letta rimpiazzerà quella precedente. Il comando `d` disegna il timbro
dell'$m$-esima matrice alle coordinate specificate dai parametri. Anche nel caso
dei timbri, se i parametri relativi a numero o dimensione, eccedono i limiti
specificati, il comando sarà ignorato.

L'ultimo comando `p` produce la raffigurazione a caratteri della bitmap così
come illustrato nell'introduzione.

### Due possibili rappresentazioni

Osservate che sono possibili due distinte rappresentazioni per descrivere gli
elementi grafici.

La prima si basa su una serie di *insiemi di coordinate* usati sia per
rappresentare le figure che la bitmap; la seconda è invece incentrata su una
*matrice di pixel* sulla quale di volta in volta le figure disegnano se stesse.

Secondo la prima possibilità, ciascuna figura è rappresentata tramite l'insieme
delle coordinate che la caratterizzano e quindi la bitmap non è che l'unione di
tali insiemi. In tale rappresentazione gli insiemi possono essere
memorizzati sia in modo *estensivo* (ossia come un elenco di punti contenuti in
una apposita struttura dati), o in modo *implicito* o *intensivo* (ossia come
strutture dati in grado di enumerare a richiesta le coordinate). Osservate che
una rappresentazione *implicita* della bitmap richiede un po' di attenzione per
via dell'operazione di inversione: potrebbe essere estremamente inefficiente
passare da una bitmap data da un solo pixel acceso a una con $b \times h - 1$
pixel accesi!

La seconda possibilità è più elementare, si tratta di mantenere una *matrice di
pixel* dotata di capacità elementari (come accendere, spegnere o invertire il
pixel) e lasciare che le figure la utilizzino per disegnare se stesse. Questa
soluzione crea un maggior legame tra figure e matrice, ma è molto più semplice
da implementare.

## Classe di test

Scrivete un metodo statico `main` nella classe che ritenete più opportuna che
legga dal flusso di ingresso una sequenza di comandi dell'interprete e li
esegua.

Potete assumere che l'input abbia il formato specificato nella sezione sui
comandi dell'interprete e che tutti i numeri coinvolti nell'esecuzione del
codice siano interi (e possano essere rappresentati da variabili di tipo `int`)
e che le dimensioni della bitmap siano tali da consentire che venga memorizzata in memoria centrale.

Eseguendo `soluzione` e avendo

    n 4 4
    s 1 2 3 1 0 0 0 2 0
    d 1 0 0
    p
    d 1 2 2
    p

nel flusso di ingresso, il programma emette nel flusso d'uscita

    *...
    .*..
    ....
    ....
    *...
    .*..
    ..*.
    ...*
