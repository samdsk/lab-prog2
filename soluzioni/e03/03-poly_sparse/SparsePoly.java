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
import java.util.List;

// Le istanze di questa classe rappresentano polinomi (sparsi) a coefficienti interi.
// Gli oggetti di questo tipo sono immutabili.
public class SparsePoly {

  // Ciascun termine rappresenta coeff * x^degree.
  // Le istanze di questo record rappresentano termini di polinomi
  // Gli oggetti di questo tipo sono immutabili.
  public record Term(int coeff, int degree) {
    // Costruttori
    public Term {
      if (degree < 0) throw new NegativeExponentException("Il grado dev'essere positivo");
    }

    @Override
    public String toString() {
      return coeff + "x^" + degree;
    }
  }

  // Campi
  // La lista contenente i termini del polinomio, ordinati per grado.
  // esempio: Term(1,2), Term(3,4), Term(5,6) -> x^2 + 3x^4 + 5x^6
  private final List<Term> terms;

  // Costruttori
  /** Costruisce il polinomio zero */
  public SparsePoly() {
    terms = new ArrayList<>();
  }

  /** Costruisce il polinomio coeff * x ^ degree */
  public SparsePoly(int coeff, int degree) {
    this();
    if (coeff != 0) terms.add(new Term(coeff, degree));
  }

  // Metodi
  /** Post-condizioni: restituisce il grado del polinomio */
  public int degree() {
    return terms.size() > 0 ? terms.get(terms.size() - 1).degree : -1;
  }

  /** Post-condizioni: restituisce l'indice di del termine di this il cui il grado è degree */
  private int findByDegree(int degree) {
    int low = 0;
    int high = terms.size() - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1;
      int midVal = terms.get(mid).degree;

      if (midVal < degree) low = mid + 1;
      else if (midVal > degree) high = mid - 1;
      else return mid;
    }
    return -(low + 1);
  }

  /**
   * Pre-condizioni: degree >= 0 Post-condizioni: restituisce il coefficiente di x^degree se degree
   * >= 0
   */
  public int coeff(int degree) {
    int i = findByDegree(degree);
    if (i >= 0) return terms.get(i).coeff;
    return 0;
  }

  /** Pre-condizioni: p diverso da null Post-condizioni: restituisce il polinomio this + p */
  public SparsePoly add(SparsePoly q) throws NullPointerException {
    int indexThis = 0, indexQ = 0;
    SparsePoly result = new SparsePoly();

    while (indexThis < terms.size() && indexQ < q.terms.size()) {
      int diff = terms.get(indexThis).degree - q.terms.get(indexQ).degree;
      if (diff < 0) result.terms.add(terms.get(indexThis++));
      else if (diff > 0) result.terms.add(terms.get(indexQ++));
      else { // I gradi sono uguali
        int newCoeff = this.terms.get(indexThis).coeff + q.terms.get(indexQ).coeff;
        if (newCoeff != 0) result.terms.add(new Term(newCoeff, terms.get(indexThis).degree));
        indexThis++;
        indexQ++;
      }
    }

    while (indexThis < terms.size()) result.terms.add(terms.get(indexThis++));
    while (indexQ < q.terms.size()) result.terms.add(q.terms.get(indexQ++));

    return result;
  }

  /** Pre-condizioni: p diverso da null Post-condizioni: restituisce il polinomio this * p */
  public SparsePoly mul(SparsePoly other) throws NullPointerException {
    SparsePoly result = new SparsePoly();

    if (other.degree() == -1 || degree() == -1) return result;

    for (int indexThis = 0; indexThis < terms.size(); indexThis++)
      for (int indexOther = 0; indexOther < other.terms.size(); indexOther++) {
        int newCoeff = terms.get(indexThis).coeff * other.terms.get(indexOther).coeff;
        int newDegree = terms.get(indexThis).degree + other.terms.get(indexOther).degree;

        int index = result.findByDegree(newDegree);
        if (index >= 0) {
          newCoeff += result.terms.get(index).coeff;
          if (newCoeff == 0) result.terms.remove(index);
          else result.terms.set(index, new Term(newCoeff, newDegree));
        } else result.terms.add(-index - 1, new Term(newCoeff, newDegree));
      }

    return result;
  }

  /** Pre-condizioni: p diverso da null Post-condizioni: restituisce il polinomio -this */
  public SparsePoly minus() {
    SparsePoly result = new SparsePoly();
    for (SparsePoly.Term t : terms) result.terms.add(new Term(-t.coeff, t.degree));
    return result;
  }

  /** Pre-condizioni: p diverso da null Post-condizioni: restituisce il polinomio this - p */
  public SparsePoly sub(SparsePoly other) {
    return this.add(other.minus());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("SparsePoly: ");

    if (degree() == -1) sb.append('0');
    else {
      for (int i = 0; i < terms.size() - 1; i++) sb.append(terms.get(i).toString()).append(" + ");
      sb.append(terms.get(terms.size() - 1));
    }

    return sb.toString();
  }
}
