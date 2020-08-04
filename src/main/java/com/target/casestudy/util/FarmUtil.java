package com.target.casestudy.util;

import com.target.casestudy.model.Area;
import com.target.casestudy.model.Farm;
import com.target.casestudy.model.LandType;
import com.target.casestudy.model.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FarmUtil {

  /**
   * Utility takes in string input and builds a farm with barren land patches.
   *
   * @param farm
   * @param coordinates
   */
  public static void buildFarm(Farm farm, String coordinates) {
    String[] coordinateList = coordinates.replace("{", "")
        .replace("}", "")
        .replace("“", "")
        .replace("”", "")
        .trim()
        .split(",");
    List<Area> barrenLand = Arrays.stream(coordinateList).map(a -> {
      String[] coord = a.trim().split(" ");
      Area area = new Area(
          new Coordinates(Integer.parseInt(coord[0]), Integer.parseInt(coord[1])),
          new Coordinates(Integer.parseInt(coord[2]), Integer.parseInt(coord[3])), LandType.BARREN);
      return area;
    }).collect(Collectors.toList());
    farm.setBarrenLand(barrenLand);
  }

  /**
   * Detect if barren patch lies in the middle of a fertile patch
   *
   * @param r1
   * @param r2
   * @return
   */
  public static List<Area> containsArea(Area r1, Area r2) {
    if (r1.min.x <= r2.min.x // x: R2 starts after R1
        && r1.max.x >= r2.max.x // x: R1 ends after R2
        && r1.min.y <= r2.min.y // y: R2 starts after R1
        && r1.max.y >= r2.max.y) { // y: R1 ends after R2
      // top strip (area.min) => (area.max.x, b.min.y)
      Area top = new Area(r1.min, new Coordinates(r1.max.x, r2.min.y), r1.type);
      // bottom strip (area.min.x, b.max.y) => (area.max)
      Area bottom = new Area(new Coordinates(r1.min.x, r2.max.y), r1.max, r1.type);
      // left strip (area.min.x, b.min.y) => (b.min.x, b.max.y)
      Area left = new Area(new Coordinates(r1.min.x, r2.min.y), new Coordinates(r2.min.x, r2.max.y), r1.type);
      // right strip (b.max.x, b.min.y) => (area.max.x, b.max.y)
      Area right = new Area(new Coordinates(r2.max.x, r2.min.y), new Coordinates(r1.max.x, r2.max.y), r1.type);

      List<Area> splitAreaList = new ArrayList<Area>();
      if (hasDimension(top)) splitAreaList.add(top);
      if (hasDimension(bottom)) splitAreaList.add(bottom);
      if (hasDimension(left)) splitAreaList.add(left);
      if (hasDimension(right)) splitAreaList.add(right);

      return splitAreaList;
    }
    return null;
  }

  /**
   * Split if barren area overlaps with fertile rectangle.
   *
   * @param a
   * @param b
   * @return
   */
  public static List<Area> checkOverlap(Area a, Area b) {
    // If one rectangle is on left side of other
    if (a.min.x >= b.max.x || b.min.x >= a.max.x) {
      // left of a2 is in a1
      if (a.min.x <= b.min.x && b.min.x <= a.max.x) {
        // right of rectangle is in a1
      } else if (a.min.x <= b.max.x && b.max.x <= a.max.x) {
      } else {
        return null;
      }
    }

    Area top = new Area(a.min, new Coordinates(a.max.x, b.min.y), a.type);
    // bottom strip (area.min.x, b.max.y) => (area.max)
    Area bottom = new Area(new Coordinates(a.min.x, Integer.min(b.max.y, a.max.y)), a.max, a.type);
    // left strip (area.min.x, b.min.y) => (b.min.x, b.max.y)
    Area left = new Area(new Coordinates(a.min.x, b.min.y), new Coordinates(b.min.x, b.max.y), a.type);
    // right strip (b.max.x, b.min.y) => (area.max.x, b.max.y)
    Area right = new Area(new Coordinates(b.max.x, b.min.y), new Coordinates(a.max.x, b.max.y), a.type);

    // If one rectangle is above other
    if (a.min.y <= b.max.y || b.min.y <= a.max.y) {
      // top of rectangle is in a1
      if (a.min.y <= b.min.y && b.min.y <= a.max.y) {
        left = new Area(new Coordinates(a.min.x, b.min.y), new Coordinates(b.min.x, Integer.min(b.max.y, a.max.y)), a.type);
        right = new Area(new Coordinates(b.max.x, b.min.y), new Coordinates(a.max.x, Integer.min(b.max.y, a.max.y)), a.type);
        // bottom of rectangle is in a1
      } else if (a.min.y <= b.max.y && b.max.y <= a.max.y) {
        top = null;
        left = new Area(new Coordinates(a.min.x, Integer.max(b.min.y, a.min.y)), new Coordinates(b.min.x, b.max.y), a.type);
        right = new Area(new Coordinates(b.max.x, Integer.max(b.min.y, a.min.y)), new Coordinates(a.max.x, b.max.y), a.type);
        // check intersecting (a horizontal)
      } else if (checkIntesecting(a, b) || checkIntesecting(b, a)) {
        // only left and right will split
        top = null;
        bottom = null;
        left = new Area(new Coordinates(a.min.x, Integer.max(b.min.y, a.min.y)), new Coordinates(b.min.x, Integer.min(b.max.y, a.max.y)), a.type);
        right = new Area(new Coordinates(b.max.x, Integer.max(b.min.y, a.min.y)), new Coordinates(a.max.x, Integer.min(b.max.y, a.max.y)), a.type);
        // check intersecting
      } else {
        return null;
      }
    }

    List<Area> splitAreaList = new ArrayList<>();
    if (hasDimension(top)) splitAreaList.add(top);
    if (hasDimension(bottom)) splitAreaList.add(bottom);
    if (hasDimension(left)) splitAreaList.add(left);
    if (hasDimension(right)) splitAreaList.add(right);

    return splitAreaList;
  }

  /**
   * Check if split section has width or height
   * @param area
   * @return
   */
  private static boolean hasDimension(Area area) {
    if (area == null) return false;
    if (area.min.x == area.max.x || area.min.y == area.max.y) {
      return false;
    }
    return true;
  }

  /**
   * Check if barren area intersects with considered patch.
   *
   * @param a
   * @param b
   * @return
   */
  private static boolean checkIntesecting(Area a, Area b) {
    if (between(b.min.x, a.min.x, a.max.x) && between(b.max.x, a.min.x, a.max.x)) {
      if (between(a.min.y, b.min.y, b.max.y) && between(a.max.y, b.min.y, b.max.y)) {
        return true;
      }
    }
    return false;
  }

  public static boolean between(int aminy, int bminy, int bmaxy) {
    return (bminy <= aminy && aminy <= bmaxy);
  }

  public static int getArea(Area a, Farm farm) {
    int w = (a.max.x - a.min.x);
    int wd = ((w) * farm.WIDTH / farm.maxWidth);
    int h = (a.max.y - a.min.y);
    int hd = ((h) * farm.HEIGHT / farm.maxHeight);
    int area = wd * hd;
    return area;
  }

}
