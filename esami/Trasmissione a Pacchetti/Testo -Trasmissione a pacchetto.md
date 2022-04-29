# Trasmissione a pacchetto

## Descrizione

Scopo della prova è progettare e implementare una gerarchia di oggetti utili a
gestire la trasmissione di alcuni *messaggi* tra una *sorgente* e un
*destinatario*.

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

### Sorgente, canale e destinatario

Nelle reti informatiche, la trasmissione delle informazioni avviene solitamente
da una **sorgente** a un **destinatario** attraverso un **canale** tramite
l'invio di *pacchetti* di dimensione fissata.

Per *trasmettere* un *messaggio* (che per semplicità assumeremo essere una
stringa), la sorgente si occupa di codificarlo in una sequenza di byte che
suddivide poi in una sequenza di *pacchetti*, chiede quindi al canale di
*inoltrarli* al destinatario che, una volta *ricevuti*, li decodificherà per
ricostruire il messaggio.

Ciascun **pacchetto** deve tener traccia di:

- un *numero di sequenza*, ossia del posto occupato nella sequenza di pacchetti
  corrispondenti al messaggio,
- un *contenuto*, ossia dei byte di cui è costituito, e
- di un *checksum*, o valore di controllo, ossia di un numero che consenta di
  capire se il contenuto del pacchetto è stato alterato.

La trasformazione di una stringa in una sequenza di *pacchetti* (operata dalla
sorgente) e il processo inverso con cui i pacchetti sono ricomposti per formare
la stringa (operata dal destinatario) avvengono pertanto in funzione di alcuni
**parametri** (che per semplicità assumeremo fissati univocamente dal
destinatario):

- la *dimensione* del pacchetto (quanti byte costituiscono il suo *contenuto*),
- il *charset* che è stato usato per codificare la stringa in byte,
- l'*algoritmo di checksum* usato per calcolare il numero di controllo.

Si osservi che il contenuto di ogni pacchetto deve essere costituito da un
numero di byte esattamente pari alla dimensione, tranne al più quello
dell'ultimo pacchetto, che potrebbe essere costituito da un numero di byte
inferiore a essa (ma in ogni caso maggiore di zero).

#### Vincoli sulle specifiche

Le tre principali entità descritte, ossia  **destinatario**, **canale** e
**sorgente**, *devono* essere rappresentate da oggetti che soddisfino le
seguenti bozze di interfacce:

``` java
public interface Destinatario {
  Parametri parametri();
  boolean ricevi(Pacchetto pacchetto);
}

public interface Canale {
  Parametri parametri();
  boolean inoltra(Pacchetto pacchetto);
}

public interface Sorgente {
  void trasmetti(String messaggio);
}
```

che possono eventualmente essere ampliate con altri metodi se lo riterrà
necessario. Le implementazioni concrete:

- del `Destinatario` (costruite a partire dai `Parametri` di trasmissione),
  dovranno tener traccia dei pacchetti ricevuti tramite il metodo `ricevi` per
  ricostruire il messaggio al termine della ricezione;
- del `Canale` (costruite a partire dal `Destinatario`) dovranno implementare il
  metodo `inoltra` invocando il metodo `ricevi` del destinatario (eventualmente
  alterando l'ordine o il contenuto dei pacchetti); infine, quelle
- della `Sorgente` (costruite a partire dal `Canale`), dovranno implementare il
  metodo `trasmetti` (invocato dal "client" della sorgente) suddividendo il
  messaggio in pacchetti da passare al canale invocando il suo metodo `inoltra`.

Per riassumere, la trasmissione avviene secondo la seguente modalità: ogni
entità svolge il proprio compito invocando un metodo dell'entità che le succede
nell'ordine "client", `Sorgente`, `Canale` e `Destinatario`.

Assumeremo infine la seguente convenzione per segnalare la terminazione del
messaggio:

- dopo l'ultimo pacchetto di un messaggio, la sorgente invocherà `inoltra(null)`
  sul canale,
- a fronte di tale invocazione, il canale invocherà il metodo `ricevi(null)` sul
  destinatario (dopo aver invocato il metodo `ricevi` con tutti i pacchetti del
  messaggio);
- a fronte di tale invocazione, il destinatario emetterà il messaggio
  ricostruito dai pacchetti ricevuti nel flusso d'uscita standard.

**Vedi anche**: la sezione [Pacchetti e Parametri](#pacchetti-e-parametri) (che trovate nel seguito
della traccia) per una indicazione sulla specificazione di tali entità.

#### La classe di test

A partire dalle tre interfacce descritte sopra, dovrà implementare la
gerarchia necessaria a poter eseguire una classe di test con il seguente `main`:

```java
public static void main(String[] args) {

  final int dimensione = Integer.parseInt(args[0]);
  final Charset charset = Charset.forName(args[1]);
  final Parametri parametri = new Parametri(dimensione, charset, new Adler32());

  final Destinatario destinatario = new ...(parametri);
  final Canale canale = new ..(destinatario);
  final Sorgente sorgente = new ...(canale);
  sorgente.trasmetti(args[2]);
}
```

dove al posto dei `...` avrà sostituito i costruttori delle classi concrete con
cui ha implementato le relative interfacce; l'invocazione della classe
`Soluzione` con tre parametri, il primo dei quali sia la dimensione, il secondo
il charset e il terzo il messaggio, deve produrre l'emissione del messaggio
ricevuto dal destinatario nel flusso d'uscita standard; ad esempio
```
java Soluzione 3 UTF-32 "Bien sûr"
```
deve emettere `Bien sûr` nel flusso d'uscita standard.

## Le modalità di trasmissione

Nelle seguenti sezioni vedremo tre *modalità* in cui le implementazioni concrete
possono realizzare il processo di trasmissione.

Per superare l'esame è **necessario implementare almeno la [prima
modalità](#caso-elementare)** tra quelle descritte di seguito; chi (certo di
aver implementato correttamente la prima modalità) intende ottenere una
valutazione considerevolmente più che sufficiente può implementare anche (una o
due tra) le successive modalità.

Si osservi che ciascuna modalità andrà realizzata implementandone, se
necessario, i *diversi comportamenti* (regolati dalle tre interfacce `Sorgente`,
`Canale` e `Destinatario`) in *diverse classi* concrete. Evidentemente la classe
di test andrà modificata quantomeno nell'invocazione dei costruttori. Si osservi
che *non è accettabile* ammassare comportamenti distinti relativi a più modalità
in una sola classe concreta per interfaccia.

### Caso elementare

Nel caso più semplice, la sorgente richiede al canale l'inoltro di ciascun
pacchetto (nell'ordine in cui li ricava dal messaggio) una sola volta e per
ciascun pacchetto il canale richiede al destinatario di riceverlo una sola
volta, nell'ordine in cui gli è stato richiesto l'inoltro, senza modificarne il
contenuto. *In tale circostanza i campi sequenza e checksum dei pacchetti
possono essere di fatto del tutto ignorati*.

**Vedi anche**: la sezione [Da stringhe a byte e
viceversa](#da-stringhe-a-byte-e-viceversa) (che trovate nel seguito della
traccia) contiene un suggerimento su come implementare codifica e decodifica.

### Alterazione dell'ordine di inoltro

Non è detto che il canale inoltri i pacchetti nell'ordine richiesto: potrebbe,
ad esempio, memorizzarne temporaneamente alcuni e invocarne la ricezione da
parte del destinatario in ordine di fatto casuale (in un caso reale questo
potrebbe accadere per via di una congestione).

In tal caso non è sufficiente che il destinatario concateni il contenuto dei
pacchetti in ordine di ricezione, dovrà bensì usare il numero di sequenza
(stabilito dalla sorgente) per rimettere in ordine i pacchetti. Prima di
emettere il messaggio deve anche verificare che non ci siano "buchi" tra i
numeri di sequenza dei pacchetti ricevuti (nel qual caso dovrebbe segnalare un
errore, ad esempio sollevando una eccezione).

Ricordate che la trasmissione del pacchetto `null` non può mai essere omessa e
il suo inoltro segue sempre quello di tutti gli altri pacchetti.

### Alterazione del contenuto

Una seconda eventualità è che il canale alteri (talvolta e in modo casuale) il
contenuto del messaggio prima di inoltrarlo al destinatario (ad esempio, a causa
di un errore transiente).

Se la sorgente aggiunge al pacchetto il checksum del suo contenuto prima della
trasmissione, alla ricezione il destinatario può rendersi conto se è avvenuta
una alterazione (ricalcolando il checksum del contenuto dopo la ricezione e
confrontandone il valore con quello contenuto nel pacchetto). In tal caso,
sorgente e destinatario possono usare il valore restituito dei metodi `inoltra`
e `ricevi` per segnalare se il pacchetto è stato ricevuto correttamente e
determinare, se necessario, un ulteriore inoltro.

In altre parole, se quando la sorgente invoca `inoltra` il canale altera il
messaggio, l'invocazione di `ricevi` sul destinatario (che grazie al checksum si
accorgerà del fatto) restituirà `false`, che il canale provvederà a girare alla
sorgente (come valore restituito da `inoltra`), in modo che la sorgente possa
tentare una nuova invocazione di `inoltra`.

Assuma che il canale comunichi fedelmente alla sorgente il risultato
dell'invio e che il pacchetto `null` venga sempre trasmesso come tale, dopo
tutti gli altri pacchetti.

**Vedi anche**: la sezione [Il calcolo del checksum](#il-calcolo-del-checksum)
(che trovate nel seguito della traccia) contiene un suggerimento su come
ottenere il valore del checksum.

### Altre modalità

Dal momento che il meccanismo di segnalazione di ricezione errata vale solo per
l'ultimo pacchetto inoltrato/ricevuto, limitandosi alle interfacce suggerite,
*non è possibile gestire il caso in cui il canale alteri al contempo sia
l'ordine di consegna che il contenuto dei messaggi*.

Durante l'esame pertanto *non tentate di combinare le due modalità descritte in
precedenza in una sola implementazione*. Eventualmente, successivamente alla
conclusione della prova pratica e in vista dell'orale, provate a riflettere su
come potreste modificare la gerarchia per consentire di gestire anche questo
caso.

## Suggerimenti implementativi

In questa sezione sono contenuti dei suggerimenti implementativi che potrebbero
risultare utili al fine di velocizzare lo svolgimento della prova.

### Pacchetti e parametri

Stando alla descrizione fatta in precedenza, `Pacchetto` e `Parametri` possono essere
banalmente realizzati come i due
[record](https://docs.oracle.com/en/java/javase/17/language/records.html)

```java
record Pacchetto(int sequenza, byte[] contenuto, long checksum) {}
record Parametri(int dimensione, Charset charset, Checksum algoritmo) {}
```

a cui basterà aggiungere un costruttore che ne verifichi l'invariante di
rappresentazione (ed eventuali altri metodi che riterrete necessari).

### Da stringhe a byte e viceversa

Le funzioni di *codifica* (trasformazione di una stringa in una sequenza di
pacchetti contenenti byte) e *decodifica* (trasformazione di una sequenza di
pacchetti contenenti byte in una stringa) possono essere svolti direttamente
dalla sorgente e dal destinatario, oppure essere delegati a due apposite entità
(un **encoder** e un **decoder**) che assolvano esclusivamente tale compito.

La codifica di una stringa in una sequenza di byte, o il processo inverso di
decodifica dai byte alla stringa, richiede che venga fissato un *charset* (che
per questa ragione è una delle informazioni contenute nei `Parametri`) come ad
esempio: `US-ASCII`, `ISO-LATIN-1`, `UTF-32` e via discorrendo; in Java tale
informazione è rappresentata da oggetti della classe
[java.nio.charset.Charset](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/charset/Charset.html).
È possibile ottenere un oggetto del genere a partire dal suo nome invocando un
metodo statico
```java
Charset charset = Charset.forName("UTF-8");
```
una volta ottenuto il charset, è possibile ottenere un array di byte da una
stringa
```java
String messaggio = "Bien sûr";
byte[] bytes = messaggio.getBytes(charset);
```
o, viceversa, una stringa da un array di byte
```java
byte[] bytes = { 66, 105, 101, 110, 32, 115, -61, -69, 114 };
String messaggio = new String(bytes, charset);
```

**Nota bene**: la traduzione da byte a stringa va fatta una volta raccolti
*tutti* i pacchetti! Alcune codifiche usano un numero fissato di byte per
carattere, quindi pacchetti di lunghezza non multipla di tale valore
condurrebbero a errori di decodifica. Se l'esempio finale della sezione [La
classe di test](#la-classe-di-test) sembra non funzionare, ma funziona se il parametro
corrispondente alla dimensione, passato da riga di comando, viene portato a `4`,
potrebbe essere un'indicazione del fatto che state decodificando un pacchetto
alla volta, invece che aver atteso tutti i byte del messaggio. Infine,
nell'implementazione della codifica e decodifica dei messaggi, si ricordi che il
contenuto dell'ultimo pacchetto trasmesso potrebbe avere una dimensione
inferiore rispetto a quella dichiarata nei parametri.

Per concatenare tra loro i contenuti dei pacchetti è possibile usare un
[java.io.ByteArrayOutputStream](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/ByteArrayOutputStream.html)
come mostrato nel seguente frammento di codice
```java
List<Pacchetto> pacchetti = ...;
byte[] bytes;
try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
  for (final Pacchetto p: pacchetti) baos.write(p.contenuto());
  bytes = baos.toByteArray();
} catch (IOException cantHappen) {
  bytes = null;
}
```
dove `pacchetti` costituisce la sequenza di pacchetti da concatenare e `bytes` è
il risultato della concatenazione dei loro contenuti. Evidentemente, chi non
intende avventurarsi nell'uso di un `ByteArrayOutputStream` può procedere a
concatenare il contenuto dei pacchetti mediante un semplice ciclo `for` che copi
man mano i byte dai pacchetti ad un array precedentemente allocato per
contenerne la concatenazione.

### Il calcolo del checksum

Il calcolo del *checksum* di un array di byte può essere ottenuto grazie
all'interfaccia
[java.util.zip.Checksum](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/zip/Checksum.html)
tramite una delle sue tre implementazioni concrete: `Adler32`, `CRC32` o
`CRC32C` (anche questa informazione è parte dei `Parametri`). Il seguente
frammento di codice mostra un esempio:
```java
byte[] bytes = { 66, 105, 101, 110, 32, 115, -61, -69, 114 };
Checksum algorithm = new CRC32();
algorithm.reset();
algorithm.update(bytes);
long checksum =  algorithm.getValue();
```
che produce il checksum `2686576347`.

Osservi che il calcolo del *checksum* implementa una funzione deterministica,
questo fatto consente di concludere che *se il valore del checksum di due array
di bytes è diverso, allora il contenuto degli array è diverso*; osservate che il
viceversa non è vero, dal momento che la funzione non può evidentemente essere
iniettiva.