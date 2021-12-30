# Filter iterator

## Descrizione

Realizzate un *iteratore filtro* che consenta, dati un iteratore *sorgente* e un
predicato *filtro*, di iterare sugli elementi del sorgente che soddisfano il
filtro.

### I predicati

Un
[`Predicate`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Predicate.html)
è una interfaccia Java che prescrive che le classi che la implementano abbiano
un metodo
[`test`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/function/Predicate.html#test(T))
a valori booleani che rappresenta un appunto un predicato logico.

Ad esempio, la seguente classe implementa un predicato che accetta solo i numeri
maggiori di una certa soglia (specificata in costruzione):
```java
public class IsGreaterThan implements Predicate <Integer> {
  public final int threshold;
  public IsGreaterThan(final int threshold) {
    this.threshold = threshold;
  }
  @Override
  public boolean test(Integer t) {
    return t > threshold;
  }
```
Essa può ad esempio essere adoperata come segue:
```java
  Predicate<Integer> isGreaterThanFive = new IsGreaterThan(5);
  for (int i = 0; i < 10; i++)
    if (isGreaterThanFive.test(i)) System.out.println(i);
```
per ottenere i numeri tra `6` e `9` (estremi inclusi).

### Un iteratore filtro

A partire da un iteratore sorgente, si può applicare un predicato per filtrare
solo alcuni elementi. In questo modo si può ottenere un nuovo  "iteratore
filtro" che enumeri solo gli elementi della sorgente che soddisfano il
predicato.

Potete costruirlo completando il codice mancante nello scheletro seguente:
```java
public class FilterIterator<T> implements Iterator<T> {
  ...
  public FilterIterator(final Iterator<T> source, Predicate<T> filter) {
    ...
  }
  @Override
  public boolean hasNext() {
    ...
  }
  @Override
  public T next() {
    ...
  }
}
```

### Uso dell'iteratore filtro

Il l'iteratore filtro sviluppato può essere usato con un filtro (ad esempio,
quello descritto sopra), come segue:
```java
Iterator<Integer> filtered = new FilterIterator<>(
  List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).iterator(),
  new IsGreaterThen(5)
);
while (filtered.hasNext()) System.out.println(filtered.next());
```
ottenendo il medesimo output dell'esempio precedente (ossia i numeri tra `6` e
`9`, estremi compresi).


**Nota bene**: Questo esercizio non prevede delle classi (e dei file) di test.
Può essere comunque opportuno svilupparne alcune.
