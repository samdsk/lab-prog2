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

import java.util.Objects;

/*-
 * OVERVIEW: Le istanze di questa classe rappresentano numeri razionali.
 *           Gli oggetti di questo tipo non sono mutabili.
 *           Un numero razionale tipico è n/d, dove n e d sono numeri interi e d è diverso da 0.
 */
public class RationalNumber {

  // ATTRIBUTI
  /*-
   * Il numeratore e il denominatore del numero razionale
   */
  private int numerator, denominator;

  /*-
   * ABS FUN: AF(numerator, denominator) = numerator/denominator
   * REP INV: denominator > 0, gcd(numerator,denominator) = 1
   * ABS INV: denominator != 0 (la preservazione è implicata dalla preservazione della REP INV)
   */

  // COSTRUTTORI
  /*-
   * Post-condizioni: Inizializza this affinché rappresenti il RationalNumber num/den.
   *                  Se den == 0, solleva un'eccezione di tipo ArithmeticException.
   *
   * Preservazione RI: Se den == 0, this non è inizializzato,
   *                   altrimenti è utilizzato il suo valore assoluto (pertanto denominator > 0).
   *                   denominator > 0 implica denominator > 0 dopo l'operazione di riduzione.
   *                   Inoltre, il numero razionale è ridotto ai minimi termini in fase di costruzione.
   *
   * Correttezza: AF(numerator,denominator)
   *              = numerator/denominator
   *              = (numerator/cd)/(denominator/cd), dove cd = gcd(|numerator|, |denominator|)
   *              = -|numerator|/|denominator| se num * den < 0,
   *                 |numerator|/|denominator| altrimenti
   */
  public RationalNumber(int num, int den) {
    if (den == 0)
      throw new ArithmeticException(
          "Illegal rational number. The denominator must be a non-zero integer. Given: " + den);

    if (den < 0) {
      num = -num;
      den = -den;
    }

    int cd = gcd(Math.abs(num), den);
    numerator = num / cd;
    denominator = den / cd;

    assert repOK();
  }

  /*-
   * Post-condizioni: Restituisce il RationalNumber this + o.
   *                  Solleva un'eccezione di tipo NullPointerException se o è null.
   *
   * Preservazione RI: o.denominator > 0 & denominator > 0 implica o.denominator * denominator > 0.
   *
   * Correttezza: numerator/denominator + o.numerator/o.denominator = (numerator*o.denominator + o.numerator * denominator)/denominator*o.denominator
   */
  public RationalNumber add(RationalNumber o) {
    Objects.requireNonNull(o);
    return new RationalNumber(
        numerator * o.denominator + o.numerator * denominator, denominator * o.denominator);
  }

  /*-
   * Post-condizioni: Restituisce il RationalNumber -this.
   *
   * Preservazione RI: Il nuovo oggetto avrà lo stesso denominatore di this,
   *                   che è positivo per ipotesi induttiva.
   *
   * Correttezza: -(numerator/denominator) = (-numerator)/denominator
   */
  public RationalNumber minus() {
    return new RationalNumber(-numerator, denominator);
  }

  /*-
   * Post-condizioni: Restituisce il RationalNumber this - o.
   * 					Solleva un'eccezione di tipo NullPointerException se o è null.
   *
   * Preservazione RI: o.denominator > 0 ⇒ o.minus().denominator > 0
   *                   denominator > 0 & o.minus().denominator > 0 ⇒ this.add(o.minus()).denominator > 0
   *                   (dato che minus e add soddisfano la preservazione dell'RI).
   *
   * Correttezza: numerator/denominator - o.numerator/o.denominator
   *            = numerator/denominator + (-(o.numerator/o.denominator))
   */
  public RationalNumber sub(RationalNumber o) {
    Objects.requireNonNull(o);
    return add(o.minus());
  }

  /*-
   * Post-condizioni: Restituisce il RationalNumber this * o.
   *                  Solleva un'eccezione di tipo NullPointerException se o è null.
   *
   * Preservazione RI: o.denominator > 0 & denominator > 0 ⇒ o.denominator * denominator > 0.
   *
   * Correttezza: numerator/denominator * o.numerator/o.denominator
   *              = (numerator * o.numerator)/(denominator * o.denominator)
   */
  public RationalNumber mul(RationalNumber o) {
    Objects.requireNonNull(o);
    return new RationalNumber(numerator * o.numerator, denominator * o.denominator);
  }

  /*-
   * Post-condizioni: Restituisce il RationalNumber 1/this.
   *                  Solleva un'eccezione di tipo ArithmeticException se this == 0.
   *
   * Preservazione RI: se numerator == 0 il costruttore solleva un'eccezione
   *                   e il nuovo oggetto non è istanziato (né restituito).
   *
   * Correttezza: 1/(numerator/denominator) = denominator/numerator
   */
  public RationalNumber reciprocal() {
    return new RationalNumber(denominator, numerator);
  }

  /*-
   * Post-condizioni: Restituisce il RationalNumber this/o.
   *                  Solleva un'eccezione di tipo NullPointerException se o è null
   *                  e un'eccezione di tipo ArithmeticException se o == 0.
   *
   * Preservazione RI: se o.numerator == 0 il costruttore solleva un'eccezione
   *                   e il nuovo oggetto non è istanziato (né restituito),
   *                   altrimenti o.reciprocal().denominator > 0 ⇒ this.mul(o.reciprocal()).denominator > 0
   *                   (dato che reciprocal e mul soddisfano la preservazione dell'RI).
   *
   * Correttezza: (numerator/denominator) / (o.numerator/o.denominator)
   *            = (numerator/denominator) * 1/(o.numerator/o.denominator)
   *            = (numerator * o.denominator)/(denominator * o.numerator)
   */
  public RationalNumber div(RationalNumber o) {
    Objects.requireNonNull(o);
    return mul(o.reciprocal());
  }

  /*-
   * Post-condizioni: Restituisce il minimo massimo comun divisore tra a e b.
   *                  Solleva un'eccezione di tipo IllegalArgumentException se uno dei due termini è negativo.
   */
  private static int gcd(int a, int b) {
    if (a < 0 || b < 0) throw new IllegalArgumentException("Arguments must be positive integers.");

    while (b != 0) {
      int tmp = b;
      b = a % b;
      a = tmp;
    }

    return a;
  }

  private boolean repOK() {
    return denominator > 0 && gcd(numerator, denominator) == 1;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(numerator);
    if (denominator != 1) sb.append('/').append(denominator);
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof RationalNumber)) return false;
    RationalNumber other = (RationalNumber) obj;

    return denominator == other.denominator && numerator == other.numerator;
  }

  @Override
  public int hashCode() {
    return Objects.hash(numerator, denominator);
  }
}
