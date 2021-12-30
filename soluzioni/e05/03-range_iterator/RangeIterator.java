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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeIterator implements Iterable<Integer> {

  private final int to;
  private final int step;
  private int next;
  private boolean hasNext;

  RangeIterator(int from, int to, int step) {
    if (step == 0) throw new IllegalArgumentException("Step must be not 0");

    this.to = to;
    this.step = step;

    next = from;
    hasNext = step > 0 ? next < to : next > to;
  }

  RangeIterator(int to, int step) {
    this(0, to, step);
  }

  RangeIterator(int to) {
    this(to, 1);
  }

  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      @Override
      public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        hasNext = false;
        return next;
      }

      @Override
      public boolean hasNext() {
        if (!hasNext) {
          next += step;
          hasNext = step > 0 ? next < to : next > to;
        }
        return hasNext;
      }
    };
  }
}
