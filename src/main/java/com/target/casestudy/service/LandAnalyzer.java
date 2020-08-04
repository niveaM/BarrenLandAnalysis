package com.target.casestudy.service;

import com.target.casestudy.model.Area;
import com.target.casestudy.model.Farm;
import com.target.casestudy.model.LandType;
import com.target.casestudy.model.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.target.casestudy.util.FarmUtil.*;
import static com.target.casestudy.util.MergeUtil.mergeAdjacent;

/**
 * This is the brain of the application. All utility methods are declared here.
 */
public class LandAnalyzer {

  /**
   * 1. Takes the barren patches of land and gradually adds them to splitting to track fertile patches.
   * 2. Merges adjacent rectangles to form one contiguous area
   * 3. Calculates area
   *
   * @param farm
   * @return
   */
  public static List<Integer> findFertileAreas(Farm farm) {
    List<Area> fertileAreas = LandAnalyzer.analyzeLand(farm);

    Map<Coordinates, List<Area>> megedAreas = mergeAdjacent(fertileAreas);

    List<Integer> dimensions = megedAreas.values().stream().map(l -> l.stream().mapToInt(a -> getArea(a, farm))
        .sum()).collect(Collectors.toList());
    dimensions.sort(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1.intValue() - o2.intValue();
      }
    });
    return dimensions;
  }

  /**
   * Creates a list of all fertile areas by adding barren patches to recursively split until
   * all barren and fertile patches are accounted for.
   *
   * @param farm
   * @return
   */
  public static List<Area> analyzeLand(Farm farm) {
    List<Area> areaList = new ArrayList<>();
    areaList.add(new Area(new Coordinates(farm.MIN_WIDTH, farm.MIN_HEIGHT), new Coordinates(farm.maxWidth, farm.maxHeight), LandType.FERTILE));
    for (Area area : farm.getBarrenLand()) {
      areaList = splitForBarrenArea(area, areaList);
    }
    return areaList;
  }

  /**
   * Introduces barren patches to find split patches of fertile land.
   * Initially the entire field is considered fertile as barren patches are introduced filed it split into
   * top, bottom, left & right.
   *
   * There are two scenarios considered here:
   * 1. barren patch lies in the middle of a fertile patch
   * 2. barren patch overlaps with one or more fertile patch(s)
   *
   * @param b
   * @param areaList
   * @return
   */
  private static List<Area> splitForBarrenArea(Area b, List<Area> areaList) {
    // barren area can be in one or more areas of the areaList => find areas and split.
    List<Area> area = areaList.stream().flatMap(a -> {
      List<Area> list = null;
      list = containsArea(a, b);
      if (list == null) {
        list = checkOverlap(a, b);
      }
      if (list == null) {
        return Arrays.asList(a).stream();
      }
      return list.stream();
    }).collect(Collectors.toList());
    return area;
  }
}
