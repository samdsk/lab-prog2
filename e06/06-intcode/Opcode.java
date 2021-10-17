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

public enum Opcode {
  ADD(1, 3) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new Add(parameters);
    }
  },
  MULTIPLY(2, 3) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new Multiply(parameters);
    }
  },
  READ(3, 1) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new Read(parameters);
    }
  },
  WRITE(4, 1) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new Write(parameters);
    }
  },
  JUMP_IF_TRUE(5, 2) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new JumpIfTrue(parameters, registers);
    }
  },
  JUMP_IF_FALSE(6, 2) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new JumpIfFalse(parameters, registers);
    }
  },
  LESS_THAN(7, 3) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new LessThan(parameters);
    }
  },
  EQUALS(8, 3) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new Equals(parameters);
    }
  },
  ADJUST_RELATIVE_BASE_REGISTER(9, 1) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new AdjustRelativeBaseRegister(parameters, registers);
    }
  },
  HALT(99, 0) {
    @Override
    Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new Halt();
    }
  };

  public final int op;
  public final int params;

  private Opcode(int op, int params) {
    this.op = op;
    this.params = params;
  }

  public static Opcode fromOp(int op) {
    for (Opcode o : values()) if (o.op == op) return o;

    throw new IllegalArgumentException("Invalid Opcode: " + op);
  }

  abstract Instruction toInstruction(Memory.Location[] parameters, Registers registers);
}
