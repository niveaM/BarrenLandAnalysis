package com.target.casestudy.model;

/**
 * Data model to keep track or barrn or fertile patches of land.
 */
public class Area {
   public final Coordinates min;
   public final Coordinates max;
   public final LandType type;

  public Area(Coordinates min, Coordinates max, LandType type) {
    if ((min.x >= max.x && min.y > max.y) || (min.x > max.x && min.y >= max.y)) {
      throw new IllegalArgumentException("Dimension are incorrect.");
    }
    this.min = min;
    this.max = max;
    this.type = type;
  }

  // no need for getters, all fields are final.

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Area{");
    sb.append("min=").append(min);
    sb.append(", max=").append(max);
    sb.append(", type=").append(type);
    sb.append('}');
    return sb.toString();
  }
}
