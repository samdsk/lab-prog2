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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/*-
 * OVERVIEW: Le istanze di questa classe rappresentano mappe da Stringhe a interi.
 *           Gli oggetti di questo tipo sono mutabili.
 *           Una mappa tipica è {k_1 : v_1, k_2 : v_2, ..., k_n : v_n}, in cui
 *           {k_1, k_2, ..., k_n} sono chiavi uniche, pertanto k_i != k_j se i!=j.
 *
 *           A seguito di una operazione di inserimento di una nuova relazione k → v,
 *           la mappa sarà {k_1 : v_1, k_2 : v_2, ..., k_n : v_n, k:v} se k non è in {k_1, k_2, ..., k_n}
 *           oppure {k_1 : v_1, k_2 : v_2, ..., k : v, ..., k_n : v_n} se esiste j, 0 < j <= n, tale che k_j = k.
 */
public class SimpleMap {

  // ATTRIBUTI
  private final List<String> keys;
  private final List<Integer> values;

  /*-
   * ABS FUN: AF(keys, values) = { k_i: v_i | per ogni i = 1, ..., n, k_i = keys[i], v_i = values[i] }
   *
   * REP INV: keys non null
   *          values non null
   *          keys non contiene riferimenti null
   *          values non contiene riferimenti null
   *          keys.size() = values.size()
   *          keys non contiene elementi ripetuti
   *
   * ABS INV: Le chiavi all'interno di una SimpleMap sono uniche
   *          A ogni chiave corrisponde esattamente un elemento
   *          Il numero di associazioni k → v in ciascuna SimpleMap è maggiore o uguale a zero
   */

  // COSTRUTTORI
  /*-
   * Post-condizioni: Inizializza this affinché rappresenti la SimpleMap vuota
   *
   * Preservazione RI: keys e values sono inizializzati con valori non null
   *                   keys e values non contengono alcun valore
   *                   keys.size() = values.size() = 0
   *
   * Correttezza: AF(keys,values) = {}
   *
   * Preservazione AI: this è vuota
   *                   size = 0
   */
  public SimpleMap() {
    keys = new LinkedList<>();
    values = new LinkedList<>();
  }

  // METODI
  /*-
   * Effetti collaterali: Modifica this aggiungendo il mapping k → v.
   * Post-condizioni: Aggiunge l'associazione k → v in this.
   *                  Se la chiave k è presente, sovrascrive il valore associato con v.
   *                  Solleva un'eccezione di tipo NullPointerException se la chiave specificata come paramentro è null.
   *
   * Correttezza: Assumo AF(keys, values) = { k_i: v_i | per ogni i = 1, ..., n, k_i = keys[i], v_i = values[i] } valida prima dell'invocazione.
   *              Se k è una chiave non presente in keys,
   *              la mappa sarà {k_1 : v_1, k_2 : v_2, ..., k_n : v_n, k_n+1:v_n+1},
   *              dove n+1 è la dimensione di this dopo invocazione del metodo;
   *              altrimenti {k_1 : v_1, k_2 : v_2, ..., k : v, ..., k_n : v_n} se esiste un index, 0 < index <= n, tale che k_index = k.
   *
   * Preservazione RI: se k == null, viene sollevata un'eccezione e l'associazione non è inserita in this
   *                   se k in keys, la dimensione di keys e values non è modificata
   *                   altrimenti, la dimensione di keys e values incrementa di uno
   *
   * Preservazione AI: se k in keys, il valore associato a k è rimpiazzato da v
   *                   se k in keys, la dimensione della mappa non è modificata
   *                   altrimenti, incrementa di uno
   */
  public void put(String k, int v) {
    Objects.requireNonNull(k, "Keys must be not null.");

    int index = keys.indexOf(k);
    if (index != -1) values.set(index, v);
    else {
      keys.add(k);
      values.add(v);
    }
  }

  /*-
   * Post-condizioni: Se l'associazione k → v è presente in this, restituisce v.
   *                  In caso contrario, solleva un'eccezione di tipo NoSuchElementException.
   *
   * Correttezza: Se k è una chiave presente in keys,
   *              dato che teniamo in memoria le liste "allineate" (alla chiave keys[i] corrisponde il valore values[i]),
   *              e che gli elementi in keys sono unici,
   *              il valore restituito è quello presente in values[index], dove keys[index] == k
   */
  public int get(String k) {
    int index = keys.indexOf(k);
    if (index == -1)
      throw new NoSuchElementException("Key not found. The key " + k + " is not in the map.");
    return values.get(index);
  }

  /*-
   * Effetti collaterali: Modifica this se contiene l'associazione k → v.
   * Post-condizioni: Se l'associazione k → v è presente in this, la elimina.
   *
   * Correttezza: Assumo AF(keys, values) = { k_i: v_i | per ogni i = 1, ..., n, k_i = keys[i], v_i = values[i] } valida prima dell'invocazione.
   *              Se k è una chiave presente in keys in posizione index,
   *              dato che teniamo in memoria le liste "allineate" (alla chiave keys[i] corrisponde il valore values[i]),
   *              l'eliminazione di keys[index] e values[index] implica la correttezza del metodo.
   *
   * Preservazione RI: se k non è in keys, la dimensione di keys e values non è modificata
   * 	                 altrimenti un valore è rimosso da entrambe le liste,
   *                   pertanto keys.size()-1 = values.size()-1
   *
   * Preservazione AI: se k non è in keys, la dimensione della mappa non è modificata
   *                   altrimenti,
   *                   k in keys ⇒ size >= 1
   *                   quindi dopo l'eliminazione di un elemento size >= 0
   */
  public void remove(String k) {
    int index = keys.indexOf(k);
    if (index >= 0) {
      keys.remove(index);
      values.remove(index);
    }
  }

  /*-
   * Post-condizioni: Restituisce il numero di associazioni presenti in this.
   *
   * Correttezza: Assumo AF(keys, values) = { k_i: v_i | per ogni i = 1, ..., n, k_i = keys[i], v_i = values[i] } valida prima dell'invocazione.
   *              Dato che teniamo in memoria le liste "allineate" (alla chiave keys[i] corrisponde il valore values[i]),
   *              e che keys contiene valori unici,
   *              il numero di chiavi corrisponde al numero di associazioni presenti nella mappa
   */
  public int size() {
    return keys.size();
  }

  /*-
   * Post-condizioni: Restituisce true se this esiste un'associazione k → v in this,
   *                  false altrimenti.
   *
   * Correttezza: Assumo AF(keys, values) = { k_i: v_i | per ogni i = 1, ..., n, k_i = keys[i], v_i = values[i] } valida prima dell'invocazione.
   *              È verificata la presenza di k nella lista delle chiavi.
   */
  public boolean containsKey(String k) {
    return keys.contains(k);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("SimpleMap : {");

    for (String k : keys) s.append('\"').append(k).append("\" : ").append(get(k)).append(", ");

    if (size() > 0) s.delete(s.length() - 2, s.length());

    return s.append('}').toString();
  }

  /*-
   * Correttezza: Assumo AF valida prima dell'invocazione per this e obj.
   *              Se obj non è una SimpleMap, le mappe non possono essere uguali.
   *              Per ciascuna associazione k → v nella mappa this è verificata dapprima la presenza in obj di k,
   *              e poi che l'associazione k → v sia presente in obj.
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof SimpleMap)) return false;
    SimpleMap other = (SimpleMap) obj;

    if (size() != other.size()) return false;

    for (String k : keys) if (!other.containsKey(k) || get(k) != other.get(k)) return false;

    return true;
  }

  /*-
   * Correttezza: Assumo AF valida prima dell'invocazione.
   *              Dato che l'ordine delle chiavi non è rilevante al fine della rappresentazione delle SimpleMap,
   *              al fine di garantire x.equals(y) ⇒ x.hashCode() == y.hashCode(),
   *              si ordina prima la lista delle chiavi e poi calcola l'hashCode per ciascuna chiave ed elemento corrispondente
   */
  @Override
  public int hashCode() {

    List<String> l = new LinkedList<>(keys);
    Collections.sort(l);

    // [EJ 3.11]
    int result = Integer.hashCode(l.size());

    for (int i = 0; i < l.size(); i++) {
      String k = l.get(i);
      result = 31 * result + k.hashCode();
      result = 31 * result + Integer.hashCode(get(k));
    }

    return result;
  }
}
