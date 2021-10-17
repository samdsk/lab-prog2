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

import java.util.ArrayList;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    List<Integer> program = new ArrayList<>();
    for (String s : args[0].split(",")) program.add(Integer.parseInt(s));
    IntcodeVM intcode = new IntcodeVM(program);
    intcode.run();
    System.out.println(intcode);
  }
}
