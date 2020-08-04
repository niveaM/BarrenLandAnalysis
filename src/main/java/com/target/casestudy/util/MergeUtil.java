package com.target.casestudy.util;

import com.target.casestudy.model.Area;
import com.target.casestudy.model.Coordinates;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.target.casestudy.util.FarmUtil.between;

public class MergeUtil {

  public static Map<Coordinates, List<Area>> mergeAdjacent(List<Area> areas) {
    Map<Coordinates, List<Area>> mergedMap = new HashMap<>();

    areas.sort(new Comparator<Area>() {
      @Override
      public int compare(Area o1, Area o2) {
        return o1.min.y - o2.min.y;
      }
    });

    for (Area area : areas) {
      boolean match = false;
      for (Coordinates p  : mergedMap.keySet()) {
        match = mergeAdjacent(area, mergedMap.get(p));
        if (match) {
          mergedMap.get(p).add(area);
          mergedMap.remove(area.min);
          break;
        }
      }
      if (!match) {
        List<Area> list = new ArrayList<>();
        list.add(area);
        mergedMap.put(area.min, list);
        continue;
      }
    }
    for (Area area : areas) {
      List list = mergedMap.get(area.min);
      if (list != null && list.size() == 1){
        boolean match = false;
        for (Coordinates p1 : mergedMap.keySet()) {
          if (p1 != area.min) {
            match = mergeAdjacent(area, mergedMap.get(p1));
            if (match) {
              mergedMap.get(p1).add(area);
              break;
            }
          }
        }
        if (match) mergedMap.remove(area.min);
      }
    }


    return mergedMap;
  }

  public static boolean mergeAdjacent(Area area, List<Area> areas) {
    boolean match = false;
    for(Area b : areas) {
      match = mergeAdjacent(area, b);
      if (match) return match;
    }
    return  match;
  }

  public static boolean mergeAdjacent(Area a, Area b) {
    // right of a touch
    if (a.max.x == b.min.x) {
      if (between(a.min.y, b.min.y, b.max.y) || between(a.max.y, b.min.y, b.max.y)) {
        return true;
      }
    }

    // left of a touch
    if (a.min.x == b.max.x) {
      if (between(a.min.y, b.min.y, b.max.y) || between(a.max.y, b.min.y, b.max.y)) {
        return true;
      }
    }

    // top of a touch
    if (a.min.y == b.max.y) {
      if (between(a.min.x, b.min.x, b.max.x) || between(a.max.x, b.min.x, b.max.x)) {
        return true;
      }
    }

    // bottom of a touch
    if (a.max.y == b.min.y) {
      if (between(a.min.x, b.min.x, b.max.x) || between(a.max.x, b.min.x, b.max.x)) {
        return true;
      }
    }

    return false;
  }
}
