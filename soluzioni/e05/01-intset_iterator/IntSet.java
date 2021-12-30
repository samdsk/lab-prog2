/*

Copyright 2021 Luca Prigioniero, Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/*-
 * OVERVIEW: Le istanze di questa classe rappresentano insiemi (non limitati) di interi.
 *           Gli oggetti di questo tipo sono mutabili.
 */
public class IntSet implements Iterable<Integer> {
  // Campi

  /** La struttura dati contenente gli elementi dell'IntSet this. */
  private List<Integer> elements;

  /** I valori in elements sono ordinati in modo crescente sse questo flag è true */
  private boolean sorted;

  /** Se questo flag è true, elements è stato cambiato */
  private boolean changed;

  // Costruttori
  /*-
   * Post-condizioni: Inizializza this affinché rappresenti l'insieme vuoto.
   */
  public IntSet() {
    elements = new ArrayList<>();
    sorted = true;
  }

  // Metodi
  /*-
   * Effetti collaterali: this è modificato: this_post = this + {x}.
   * Mette a true changed, e a false sorted se l'inserimento rende elements non ordinato.
   * Post-condizioni: Aggiunge l'elemento x all'insieme this.
   */
  public void insert(int x) {
    if (sorted && size() > 0 && x < elements.get(size() - 1)) sorted = false;

    if (!this.contains(x)) {
      elements.add(x);
      changed = true;
    }
  }

  /*-
   * Effetti collaterali: this è modificato: this_post = this - {x}.
   * Post-condizioni: Rimuove l'elemento x dall'insieme this, se presente.
   * Mette a true sia sorted che changed.
   */
  public void remove(int x) {
    // elements.remove(Integer.valueOf(x));
    int index = elements.indexOf(x);
    if (index != -1) {
      int lastIndex = elements.size() - 1;
      elements.set(index, elements.get(lastIndex));
      elements.remove(lastIndex);
      sorted = false;
      changed = true;
    }
  }

  /*-
   * Post-condizioni: restituisce true se x è nell'insieme this, altrimenti restituisce false.
   */
  public boolean contains(int x) {
    return elements.contains(x);
  }

  /*-
   * Post-condizioni: restituisce la cardinalità dell'insieme this.
   */
  public int size() {
    return elements.size();
  }

  /*-
   * Post-condizioni: restituisce un elemento scelto arbitrariamente nell'insieme
   * 				   e solleva un'eccezione di tipo EmptyException se l'insieme è vuoto
   */
  public int choose() throws EmptyException {
    if (this.size() == 0)
      throw new EmptyException("Impossibile estrarre un elemento. Insieme vuoto.");
    return elements.get(elements.size() - 1);
  }

  /*-
   * Post-condizioni: restituisce una rappresentazione testuale dell'insieme this.
   */
  @Override
  public String toString() {
    StringBuilder r = new StringBuilder("IntSet : {");
    if (this.size() > 0) {
      for (int i = 0; i < elements.size() - 1; i++) r.append(elements.get(i)).append(", ");
      r.append(elements.get(elements.size() - 1));
    }
    r.append('}');
    return r.toString();
  }

  /**
   * Effetti collaterali: ordina gli elementi di this e o se non sono ordinati Mette sorted e
   * changed a true per this o o se non erano ordinati.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof IntSet)) return false;

    IntSet other = (IntSet) o;

    if (!sorted) {
      Collections.sort(this.elements);
      sorted = true;
      changed = true;
    }
    if (!other.sorted) {
      Collections.sort(other.elements);
      other.sorted = true;
      other.changed = true;
    }

    return elements.equals(other.elements);
  }

  /**
   * Effetti collaterali: ordina gli elementi di this se non sono ordinati quindi mette changed e
   * sorted a true
   */
  @Override
  public int hashCode() {
    if (!sorted) {
      Collections.sort(elements);
      sorted = true;
      changed = true;
    }

    int hashCode = 0;
    for (Integer i : elements) hashCode = 31 * hashCode + Integer.hashCode(i);
    return hashCode;
  }

  public Iterator<Integer> iterator() {
    changed = false;

    return new Iterator<Integer>() {
      int nextIndex = 0;

      @Override
      public Integer next() {
        if (changed) throw new ConcurrentModificationException();
        if (!hasNext()) throw new NoSuchElementException();

        return elements.get(nextIndex++);
      }

      @Override
      public boolean hasNext() {
        return nextIndex < size();
      }
    };
  }
}
