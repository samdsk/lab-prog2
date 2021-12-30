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
import java.util.LinkedList;
import java.util.List;

public class Soluzione {
  public static void main(String[] args) {

    int N = Integer.parseInt(args[0]);

    List<List<Integer>> scomposizioni = new ArrayList<>(N + 1);
    List<Integer> primi = new ArrayList<>(List.of(2));

    scomposizioni.add(List.of());
    scomposizioni.add(List.of());
    for (int n = 2; n <= N; n++) {
      List<Integer> fattori = new LinkedList<>();
      for (int p : primi)
        if (n % p == 0) {
          fattori.add(p);
          fattori.addAll(scomposizioni.get(n / p));
          break;
        } else if (p * p > n) {
          fattori.add(n);
          primi.add(n);
          break;
        }
      scomposizioni.add(fattori);
    }

    for (int n = 2; n <= N; n++) System.out.println(n + " => " + scomposizioni.get(n));
  }
}
