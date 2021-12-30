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

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Test {
  public static void main(String[] args) {
    Set<RationalNumber> l = new HashSet<>();
    RationalNumber s, p, d;
    int n = 1;

    try (Scanner in = new Scanner(System.in)) {
      RationalNumber r = new RationalNumber(in.nextInt(), in.nextInt());

      l.add(r);
      s = p = d = r;

      while (in.hasNextInt()) {
        n++;
        r = new RationalNumber(in.nextInt(), in.nextInt());
        l.add(r);

        s = s.add(r);
        p = p.mul(r);
        d = d.div(r);
      }
    }

    System.out.println(s);
    System.out.println(p);
    System.out.println(d);
    System.out.println(n - l.size());
  }
}
