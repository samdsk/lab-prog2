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

import java.util.Scanner;

/*-
 * Soluzione
 */
public class Soluzione {

  public static void main(String[] args) {
    IntQueue queue;
    int nEnc = 0, nDec = 0;

    try (Scanner s = new Scanner(System.in)) {
      int n = s.nextInt();
      queue = new IntQueue(n);
      while (s.hasNextByte()) {
        byte op = s.nextByte();
        if (op == +1) {
          if (queue.size() == n) break;
          queue.enqueue(++nEnc);
        } else {
          if (nDec == nEnc) break;
          System.out.println(queue.dequeue());
          nDec++;
        }
      }
    }
    System.out.println(queue);
    System.out.println(queue.size());
  }
}
