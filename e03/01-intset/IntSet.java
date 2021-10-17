/*

Copyright 2020 Luca Prigioniero, Massimo Santini

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
import java.util.List;

/*-
 * OVERVIEW: Le istanze di questa classe rappresentano insiemi (non limitati) di interi.
 *           Gli oggetti di questo tipo sono mutabili.
 */
public class IntSet {
  // Campi
  /*- La struttura dati contenente gli elementi dell'IntSet this. */
  private List<Integer> elements;

  // Costruttori
  /*-
   * Post-condizioni: Inizializza this affinché rappresenti l'insieme vuoto.
   */
  public IntSet() {
    elements = new ArrayList<>();
  }

  // Metodi
  /*-
   * Effetti collaterali: this è modificato: this_post = this + {x}.
   * Post-condizioni: Aggiunge l'elemento x all'insieme this.
   */
  public void insert(int x) {
    if (!this.contains(x)) elements.add(x);
  }

  /*-
   * Effetti collaterali: this è modificato: this_post = this - {x}.
   * Post-condizioni: Rimuove l'elemento x dall'insieme this, se presente.
   */
  public void remove(int x) {
    // elements.remove(Integer.valueOf(x));
    int index = elements.indexOf(x);
    if (index != -1) {
      int lastIndex = elements.size() - 1;
      elements.set(index, elements.get(lastIndex));
      elements.remove(lastIndex);
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
    String r = "Intset : {";
    if (this.size() > 0) {
      for (int i = 0; i < elements.size() - 1; i++) r += elements.get(i) + ", ";
      r += elements.get(elements.size() - 1);
    }
    return r + "}";
  }
}
