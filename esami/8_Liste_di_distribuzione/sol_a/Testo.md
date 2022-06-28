# Liste di distribuzione

## Descrizione

Lo scopo della prova è progettare e implementare una gerarchia di oggetti utili a
gestire un *sistema di gestione* di elenchi di *indirizzi email*.

Per portare a termine il lavoro dovrà decidere se e quali interfacce e classi
(concrete o astratte) implementare. Per ciascuna di esse **dovrà descrivere**
(preferibilmente in formato Javadoc, ma comunque solo attraverso commenti
presenti nel codice) le scelte relative alla **rappresentazione** dello stato
(con particolare riferimento all'*invariante di rappresentazione* e alla
*funzione di astrazione*) e ai **metodi** (con particolare riferimento a
*pre/post-condizioni* ed *effetti collaterali*, soffermandosi a illustrare le
ragioni della *correttezza* solo per le implementazioni che riterrà più
critiche). Osservi che l'esito di questa prova si baserà tanto su questa
documentazione quanto sul codice sorgente.

Presti particolare attenzione agli *errori di compilazione: l'intero contenuto
dei file che il compilatore si rifiuta di compilare non sarà affatto esaminato*.
Se riscontrasse errori di compilazione che non è in grado di correggere, valuti
la possibilità di racchiudere le porzioni di codice che li causano all'interno
di commenti; resta inteso che tale codice commentato non sarà valutato, ma
almeno verrà esaminato il resto del codice del file.

### Indirizzi e alias

Un **indirizzo** email è una stringa costituita da una parte *locale* e da un
*dominio* separati dal carattere `@`; la parte **locale** deve soddisfare
l'espressione regolare Java `^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*$` mentre il
**dominio** quella `^[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$` (si
veda la sezione sui dettagli implementativi per un suggerimento su come
controllare tale condizione). Un esempio di indirizzo è
`massimo.santini@unimi.it` dove `massimo.santini` è la parte locale e `unimi.it`
è il dominio. Due indirizzi sono *uguali* se e solo se sono rispettivamente
uguali (come stringhe) le loro parti locali e il loro dominio. 

Dato che spesso è necessario indirizzare un messaggio a più destinatari di uno
stesso dominio è comodo avere degli *alias*. Un **alias** è un elenco di parti
locali di indirizzo email a cui è associato un *nome* (dato da una stringa
qualunque, purché non vuota) e un *dominio*. In molti programmi di gestione
dell'email è possibile usare un alias al posto di un elenco di indirizzi. Ad
esempio, se è definito l'alias di nome `studenti` con dominio
`studenti.unimi.it` contenente gli indirizzi locali `mario.rossi`, `carla.bruni`
e `giuseppe.verdi`, facendo uso dell'alias `studenti` è possibile indirizzare un
messaggio agli indirizzi `mario.rossi@studenti.unimi.it`,
`carla.bruni@studenti.unimi.it` e `giuseppe.verdi@studenti.unimi.it`. Due alias
sono *uguali* se e solo se i loro nomi sono uguali (come stringhe). È
ragionevole aspettarsi che sia possibile *aggiungere* e *rimuovere* le parti
locali di indirizzo a un alias, così come sia possibile sapere se un alias ne
*contiene* uno dato, oppure se contiene un indirizzo dato (cosa possibile
se e solo se il dominio dell'indirizzo coincide con quello dell'alias). Infine,
è necessario che un alias sia in grado di *elencare* (con un iteratore) i suoi
indirizzi (comprensivi di dominio).

### Liste di distribuzione

Può capitare che sia necessario raggiungere con una email indirizzi in domini
diversi. A tale scopo è possibile definire delle **liste di distribuzione** di
indirizzi email (a cui, come nel caso degli alias, è associato un *nome*) che
possono essere costruite a partire da un *alias*, oppure facendo la *somma* o la
*differenza* (insiemistica) di due liste di distribuzione. Più precisamente, se
`L` e `M` sono due liste di distribuzione, la **somma** di `L` e `M` contiene
tutti e soli gli indirizzi che sono in `L` più quelli che sono in `M`, mentre la
**differenza** contiene tutti e soli gli indirizzi che sono in `L` ma non sono
in `M`. Due liste di distribuzione sono *uguali* se e solo se sono uguali (come
stringhe) i loro nomi. È necessario che una lista di distribuzione sia in grado
di *elencare* (con un iteratore) i suoi indirizzi (comprensivi di dominio e
senza ripetizioni); si osservi che, sebbene le liste di distribuzione siano
immutabili, non lo sono gli alias a partire dai quali vengono costruite, ragion
per cui l'elenco di indirizzi di una lista non può essere precalcolato alla sua
creazione.

### Gli alias estesi

*L'implementazione dell'entità descritta in questa sezione è del tutto
facoltativa.*

Un **alias esteso** è un alias che, oltre all'elenco di parti locali di
indirizzo può contenere un elenco di alias (normali, o estesi); si osservi che
siccome a sua volta un alias esteso può contenerne altri, questa definizione
darà luogo a una sorta di albero *n*-ario di elenchi di indirizzi. Oltre alle
competenze di un normale alias, quello esteso dev'essere in grado di
*aggiungere* e *rimuovere* un alias dal suo elenco. Attenzione particolare deve
essere invece posta alla nozione di contenimento (che diventa di fatto
ricorsiva): un alias esteso *contiene un indirizzo* se e solo se esso è compreso
nell'elenco delle parti locali o è contenuto in uno degli alias del suo elenco;
similmente esso *contiene un alias* se e solo se è compreso nell'elenco dei suoi
alias, o è contenuto in uno di essi. Infine, un alias esteso deve *elencare*
(con un iteratore) tutti gli indirizzi (comprensivi di dominio e senza
ripetizioni) che contiene direttamente o indirettamente (contenuti cioè in uno
degli alias del suo elenco).

#### La classe di test

A partire dalle entità descritte sopra, dovrà implementare la gerarchia
necessaria a poter eseguire una classe di test che, leggendo dal flusso di
ingresso una sequenza di comandi, li attui istanziando le necessarie entità ed
esercitandone i metodi.

Ogni linea del flusso di ingresso rappresenta un comando che può essere di
*creazione*, *mutazione* o *ispezione*. 

I comandi di creazione contengono il segno `=` e servono a creare un alias (nel
qual caso, a sinistra del segno `=` compare il segno `@`), oppure una lista.
Sono esempi di comandi di creazione:

    primo@dominio.baz = locale0, locale1, locale2
    secondo@dominio.baz = locale2, @primo, locale3
    terza = primo
    quarta = secondo
    somma = terza + quarta
    differenza = quarta - terza

Per attuare il primo comando è necessario creare l'alias di nome `primo` con
dominio `dominio.baz` e contenente le parti locali `locale0`, `locale1` e `locale2`.
Per il secondo (che va considerato solo nel caso in cui siano stati implementati
gli alias estesi) andrà creato l'alias esteso di nome `secondo` con dominio
`dominio.baz` e contenente le parti locali `locale2` e `locale3` e l'alias `primo`.
Per le restanti quattro righe, dovranno essere create delle liste di
distribuzione, due di nome `terza` e `quarta` (costruite rispettivamente a
partire dagli alias `primo` e `secondo`) e quindi una lista di nome `somma`
ottenuta facendo la somma delle due liste precedenti e per finire una di nome
`differenza` ottenuta facendo la differenza delle due liste `quarta` e `terza`.

I comandi di mutazione alterano il contenuto di un alias; ad esempio

    primo -= locale1
    secondo += locale4
    secondo -= @primo

Per attuare il primo comando va rimosso l'indirizzo `locale1` dall'alias di nome
`primo`; i restanti comandi operano sull'alias di nome `secondo`, per attuarli è
sufficiente aggiungere rispettivamente l'indirizzo `locale4` e rimuovere l'alias
di nome `primo`.

I comandi di ispezione sono costituiti da un nome di alias, o lista (potete
assumere che nei comandi di creazione ogni nome venga usato una sola volta) e la
loro attuazione corrisponde ad emettere nel flusso d'uscita il risultato
dell'iterazione sui loro indirizzi (prefissata dal tipo e nome dell'elenco). Ad
esempio, qualora i comandi

    primo
    secondo
    somma
    differenza

seguano immediatamente quelli di creazione dati in precedenza, la classe deve
emettere

    Alias primo =
      locale2@dominio.baz
      locale1@dominio.baz
      locale0@dominio.baz
    Alias secondo =
      locale3@dominio.baz
      locale2@dominio.baz
      locale1@dominio.baz
      locale0@dominio.baz
    List somma =
      locale2@dominio.baz
      locale1@dominio.baz
      locale0@dominio.baz
      locale3@dominio.baz
    List differenza =
      locale3@dominio.baz

se eseguiti nuovamente dopo i comandi di mutazione, devono invece produrre

    Alias primo =
      locale2@dominio.baz
      locale0@dominio.baz
    Alias secondo =
      locale4@dominio.baz
      locale3@dominio.baz
      locale2@dominio.baz
    List somma =
      locale2@dominio.baz
      locale0@dominio.baz
      locale4@dominio.baz
      locale3@dominio.baz
    List differenza =
      locale4@dominio.baz
      locale3@dominio.baz

## Suggerimenti implementativi

In questa sezione sono contenuti dei suggerimenti implementativi che potrebbero
risultare utili al fine di velocizzare lo svolgimento della prova.

### Indirizzi e loro parti

Non è sensato rappresentare gli indirizzi e le loro parti con semplici stringhe
perché è necessario che (ovunque utilizzati) essi soddisfino i vincoli dati
dalle espressioni regolari indicate; questo richiede l'uso di opportune *classi
immutabili*, o *record*.

Per verificare che la stringa a cui si riferisce la variabile `part` soddisfi il
vincolo espresso dall'espressione regolare data dalla stringa a cui si riferisce
la variabile `er` è possibile costruire una volta per tutte un oggetto della
classe `java.util.function.Predicate` tramite il metodo di fabbricazione
`compile` della classe `java.util.regex.Pattern` come segue:

    final static Predicate<String> VALIDO = Pattern.compile(er).asPredicate();

Una volta ottenuto il predicato, è possibile sapere se è soddisfatto (al variare
della stringa da verificare) invocando il metodo `test` come

    VALIDO.test(part)

che restituisce `true` se e solo se la stringa a cui si riferisce `part`
soddisfa l'espressione regolare definita dalla stringa a cui si riferisce `er`.

### Gli iteratori

Sia gli alias che le liste di distribuzione devono offrire un iteratore che
consenta di elencare *senza ripetizioni* gli indirizzi che contengono. 

Nel caso degli alias estesi e delle liste di distribuzione ottenute per somma la
richiesta che non vengano prodotti duplicati è particolarmente impegnativa da
implementare.

Dato che un punto cruciale dell'astrazione iterazione è quello di consentire un
accesso al contenuto di una collezione di elementi omogenei in modo efficiente
sia dal punto di vista del tempo che dello spazio, si precisa che **non**
saranno ritenute accettabili le implementazioni banalmente derivate dall'uso di
un `Set` preventivamente allocato e riempito con tutti gli indirizzi dell'alias,
o lista di distribuzione. Codice del genere del seguente

    public Iterator<Indirizzo> iterator() {
      final Set<Indirizzo> indirizzi;
      for (final Indirizzo indirizzo: …) indirizzi.add(indirizzo);
      return indirizzi.iterator();
    }

dove la parte `…` è tale da elencare tutti gli indirizzi contenuti negli alias
contenuti nell'alias esteso, o nella lista somma, non saranno ritenuti una
soluzione accettabile e daranno anzi luogo ad una penalizzazione della
valutazione.

Piuttosto è preferibile ignorare la richiesta che l'iteratore non produca
duplicati, implementandone una versione che quantomeno non richieda
l'allocazione aggiuntiva di una quantità non costante di memoria.