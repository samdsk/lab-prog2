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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Memory {
  private List<Integer> memoryCells;

  public Memory(List<Integer> program) {
    memoryCells = new ArrayList<>(program);
  }

  private void set(int index, int value) {
    int size = index - memoryCells.size() + 1;
    if (size > 0) memoryCells.addAll(Collections.nCopies(size, 0));
    memoryCells.set(index, value);
  }

  public int get(int index) {
    if (index >= memoryCells.size()) return 0;
    return memoryCells.get(index);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Memory : [");
    for (Integer m : memoryCells) sb.append(m).append(",");
    if (memoryCells.size() > 0) sb.deleteCharAt(sb.length() - 1);
    return sb.append("]").toString();
  }

  public Location[] prepareLocations(int params, int rawParameterModes, Registers registers) {
    final Location[] parameters = new Location[params];
    for (int i = 0; i < params; i++, rawParameterModes /= 10)
      parameters[i] =
          new Location(
              Mode.fromCode(rawParameterModes % 10),
              registers.instructionPointer + 1 + i,
              registers.relativeBasePointer);
    return parameters;
  }

  public class Location {
    private final Mode mode;
    private final int memoryIndex;
    private final int relativeBasePointer;

    public Location(Mode mode, int memoryIndex, int relativeBasePointer) {
      this.mode = Objects.requireNonNull(mode);
      this.memoryIndex = memoryIndex;
      this.relativeBasePointer = relativeBasePointer;
    }

    public void write(int v) {
      if (mode == Mode.IMMEDIATE) throw new IllegalStateException("Can't write in IMMEDIATE mode");
      int address = get(memoryIndex);
      if (mode == Mode.RELATIVE) address += relativeBasePointer;
      set(address, v);
    }

    public int read() {
      int address = get(memoryIndex);
      if (mode == Mode.IMMEDIATE) return address;
      if (mode == Mode.RELATIVE) address += relativeBasePointer;
      return get(address);
    }
  }
}
