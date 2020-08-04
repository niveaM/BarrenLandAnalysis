package com.target.casestudy.service;

import com.target.casestudy.model.Area;
import com.target.casestudy.model.LandType;
import com.target.casestudy.model.Coordinates;
import org.junit.Assert;
import org.junit.Test;

import static com.target.casestudy.util.MergeUtil.mergeAdjacent;

/**
 * Unit tests to land analyzer utilities.
 */
public class LandAnalyzerTest {

  @Test
  public void testMegeHorizontal() {
    Area a1 = new Area(new Coordinates(49, 0), new Coordinates(120, 51), LandType.BARREN);
    Area a2 = new Area(new Coordinates(120, 0), new Coordinates(399, 51), LandType.BARREN);

    boolean merge = mergeAdjacent(a1, a2);
    Assert.assertTrue(merge);
  }

  @Test
  public void testMegeVertical() {
    Area a1 = new Area(new Coordinates(49, 0), new Coordinates(120, 51), LandType.BARREN);
    Area a2 = new Area(new Coordinates(49, 51), new Coordinates(120, 251), LandType.BARREN);

    boolean merge = mergeAdjacent(a1, a2);
    Assert.assertTrue(merge);
  }
}
