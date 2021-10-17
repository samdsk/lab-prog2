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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Kaprekar {

  /**
   * Effetti collaterali: potrebbe modificare l'ordine degli elementi di a Post-condizioni: inverte
   * l'ordine degli elementi di a solleva un'eccezione di tipo NullPointerException se a è null
   */
  public static void reverse(byte[] a) {
    if (a == null) throw new NullPointerException();

    for (int i = 0; i < a.length / 2; i++) {
      byte tmp = a[i];
      a[i] = a[a.length - 1 - i];
      a[a.length - 1 - i] = tmp;
    }
  }

  /**
   * Post-condizioni: restituisce un array contenente le cifre di n solleva un'eccezione di tipo
   * IllegalArgumentException se n ha più di digits cifre
   */
  public static byte[] numToDigits(int n, int digits) {
    byte[] a = new byte[digits];
    for (int i = digits - 1; i >= 0; i--, n /= 10) a[i] = (byte) (n % 10);

    if (n > 0)
      throw new IllegalArgumentException(
          "Il numero passato come argomento ha più di " + digits + " cifre");

    return a;
  }

  /**
   * Post-condizioni: converte il numero memorizzato (in cifre) in a in un intero solleva
   * un'eccezione di tipo NullPointerException se a è null e solleva un'eccezione di tipo
   * IllegalArgumentException se qualche elemento di a non è una cifra decimale
   */
  public static int digitsToNum(byte[] a) {
    if (a == null) throw new NullPointerException();
    int n = 0;

    for (byte b : a) {
      if (b < 0 || b > 9)
        throw new IllegalArgumentException(
            "Valori attesi per argomento compresi tra 0 e 9. Trovato: " + b);
      n = n * 10 + b;
    }

    return n;
  }

  /**
   * Effetti collaterali: Modifica System.out Post-condizioni: Stampa la "sequenza di Kaprekar"
   * solleva un'eccezione di tipo IllegalArgumentException se n non è nell'intervallo [1,9999] e
   * solleva un'eccezione di tipo IllegalArgumentException se n non ha almeno due cifre diverse
   */
  public static void printKaprekar(int n) {
    if (n < 1 || n > 9999)
      throw new IllegalArgumentException(
          "Il numero in input deve avere al più 4 cifre e dev'essere positivo.");

    System.out.println(n);
    while (n != 6174) {
      n = stepKaprekar(n);
      if (n == 0)
        throw new IllegalArgumentException(
            "Almeno due cifre dl numero in input deve essere diverse.");
      System.out.println(n);
    }
  }

  /**
   * Post-condizioni: esegue un passo della "sequenza di Kaprekar" solleva un'eccezione di tipo
   * IllegalArgumentException se n non è nell'intervallo [1,9999]
   */
  public static int stepKaprekar(int n) {
    if (n < 1 || n > 9999)
      throw new IllegalArgumentException(
          "Il numero in input deve avere al più 4 cifre e dev'essere positivo.");

    byte[] digits = numToDigits(n, 4);
    Arrays.sort(digits);
    n = -digitsToNum(digits);
    reverse(digits);
    return n + digitsToNum(digits);
  }

  public static void main(String[] args) {
    InputStream is;
    try {
      is =
          new URL(
                  "https://www.randomasds.org/integers/?num=1&min=1&max=9999&col=1&base=10&format=plain&rnd=new")
              .openStream();
    } catch (MalformedURLException e) {
      e.printStackTrace();
      System.out.println("URL non valido");
      is = System.in;
    } catch (IOException e) {
      e.printStackTrace();
      is = System.in;
    }

    Scanner s = new Scanner(is);

    printKaprekar(s.nextInt());
    s.close();
  }
}
