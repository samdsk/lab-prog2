/*

Copyright 2020 Luca Prigioniero

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

import java.util.Scanner;

public class Lychrel {
  /*
   *	Pre-condizioni: s è una stringa che rappresenta un intero in notazione decimale
   *	Post-condizioni: restituisce il numero (di tipo long) rappresentato da s
   */
  public static long fromStringToLong(String s) {
    long n = 0;
    for (int i = 0; i < s.length(); i++) {
      n *= 10;
      n += s.charAt(i) - '0';
    }
    return n;
  }

  /*
   *	Post-condizioni: restituisce una rappresentazione,
   *   				 sotto forma di stringa,
   *					 del numero fornito in input
   */
  public static String fromLongToString(long n) {
    return "" + n;
  }

  /*
   *	Pre-condizioni: s non è un riferimento null
   *	Post-condizioni: restituisce true se s è palindroma, false altrimenti
   */
  public static boolean isPalindrome(String s) {
    int len = s.length();
    if (len <= 1) return true;
    return s.charAt(0) == s.charAt(len - 1) && isPalindrome(s.substring(1, len - 1));
  }

  /*
   *	Pre-condizioni: s non è un riferimento null
   *	Post-condizioni: restituisce il reversal della stringa s (s "capovolta")
   */
  public static String reverse(String s) {
    int len = s.length();
    if (len <= 1) return s;
    return s.charAt(len - 1) + reverse(s.substring(1, len - 1)) + s.charAt(0);
  }

  /*
   *	Pre-condizioni: n è un numero positivo
   *	Post-condizioni: restituisce il valore successivo nella Sequenza di Lychrel
   */
  public static long lychrelStep(long n) {
    return n + fromStringToLong(reverse(fromLongToString(n)));
  }

  /*
   *	Pre-condizioni: n non è un numero di Lychrel
   *	Post-condizioni: stampa la Sequenza di Lychrel a partire da n
   */
  public static void lychrelSequence(long n) {
    while (!isPalindrome(fromLongToString(n))) {
      System.out.println(n);
      n = lychrelStep(n);
    }
    System.out.println(n);
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    lychrelSequence(s.nextLong());
  }
}
