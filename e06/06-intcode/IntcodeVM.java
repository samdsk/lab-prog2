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

import java.util.List;

public class IntcodeVM {

  private final Memory memory;
  private final Registers registers;
  private boolean running;

  public IntcodeVM(List<Integer> program) {
    memory = new Memory(program);
    registers = new Registers();
  }

  public void run() {
    do {
      //   Fetch
      int fetchedInstruction = memory.get(registers.instructionPointer);
      //   Decode
      Instruction i = decode(fetchedInstruction);
      //   Execute
      running = execute(i);
    } while (running);
  }

  private boolean execute(Instruction i) {
    i.execute();
    return !i.isHalting();
  }

  private Instruction decode(int rawInstruction) {
    Opcode opcode = Opcode.fromOp(rawInstruction % 100);
    Memory.Location[] parameters =
        memory.prepareLocations(opcode.params, rawInstruction / 100, registers);
    registers.instructionPointer += opcode.params + 1;
    return opcode.toInstruction(parameters, registers);
  }

  @Override
  public String toString() {
    return new StringBuilder("IntcodeVM : \n\t")
        .append(memory)
        .append("\n\t")
        .append(registers)
        .toString();
  }
}
