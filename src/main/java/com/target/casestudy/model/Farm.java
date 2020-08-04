package com.target.casestudy.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Data representation for farm with barren patches of land
 */
public class Farm {
  public static final int MIN_WIDTH = 0;
  public static final int MIN_HEIGHT = 0;
  public static final int WIDTH = 400;
  public static final int HEIGHT = 600;

  public final int maxWidth;
  public final int maxHeight;

  public Farm() {
    this.maxWidth = 399;
    this.maxHeight = 599;
  }

  public Farm(int maxWidth, int maxHeight) {
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
  }

  List<Area> barrenLand = new ArrayList<Area>();

  public List<Area> getBarrenLand() {
    return barrenLand;
  }

  public void setBarrenLand(List<Area> barrenLand) {
    this.barrenLand = barrenLand;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Farm{");
    sb.append("barrenLand=").append(barrenLand);
    sb.append('}');
    return sb.toString();
  }
}
