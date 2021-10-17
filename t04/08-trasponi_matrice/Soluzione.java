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

import java.util.Scanner;

public class Soluzione {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int N = Integer.parseInt(args[0]), M = Integer.parseInt(args[1]);
    int[][] A = new int[N][M];
    for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) A[i][j] = s.nextInt();
    for (int j = 0; j < M; j++) {
      for (int i = 0; i < N; i++) System.out.print(A[i][j] + (i < N - 1 ? " " : ""));
      System.out.println();
    }
  }
}
