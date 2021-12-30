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

public class TestRunner {

  private static void print(Poly p) {
    final int degree = p.degree();
    for (int i = 0; i <= degree; i++) System.out.printf("%d %d\n", p.coeff(i), i);
    System.out.println();
  }

  public static void main(String[] args) {
    try (Scanner s = new Scanner(System.in)) {
      int termsP = s.nextInt(), termsQ = s.nextInt();
      Poly p = new Poly(), q = new Poly();
      for (int i = 0; i < termsP; i++) p = p.add(new Poly(s.nextInt(), s.nextInt()));
      print(p);
      for (int i = 0; i < termsQ; i++) q = q.add(new Poly(s.nextInt(), s.nextInt()));
      print(q);
      print(p.minus());
      print(p.add(q));
      print(p.sub(q));
      print(p.mul(q));
    }
  }
}
