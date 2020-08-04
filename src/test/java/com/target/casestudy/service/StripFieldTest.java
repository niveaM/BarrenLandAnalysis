package com.target.casestudy.service;

import com.target.casestudy.model.Area;
import com.target.casestudy.model.Farm;
import com.target.casestudy.model.LandType;
import com.target.casestudy.model.Coordinates;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.target.casestudy.service.LandAnalyzer.analyzeLand;
import static com.target.casestudy.util.FarmUtil.buildFarm;
import static com.target.casestudy.util.MergeUtil.mergeAdjacent;

/**
 * Unit tests to validate code with a smaller dimension grid.
 */
public class StripFieldTest {

  @Test
  public void testTopStripe() {
    Farm farm = new Farm(9, 6);
    buildFarm(farm, "{“0 0 9 3”}");

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 1);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 3);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 6);
  }

  @Test
  public void testBottomStripe() {
    Farm farm = new Farm(9, 6);
    buildFarm(farm, "{“0 3 9 6”}");

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 1);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 3);
  }

  @Test
  public void testStripes() {
    Farm farm = new Farm(9, 9);
    buildFarm(farm, "{“0 2 9 4”}, {“0 6 9 8”}");

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 3);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 2);

    a = fertileAreas.get(1);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 4);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 6);

    a = fertileAreas.get(2);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 8);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 9);

    Map<Coordinates, List<Area>> map = mergeAdjacent(fertileAreas);
    Assert.assertEquals(3, map.size());

  }

  @Test
  public void testVerticalStripes() {
    Farm farm = new Farm(9, 9);
    buildFarm(farm, "{“2 0 3 9”}, {“6 0 7 9”}");

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 3);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 2);
    Assert.assertEquals(a.max.y, 9);

    a = fertileAreas.get(1);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 3);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 6);
    Assert.assertEquals(a.max.y, 9);

    a = fertileAreas.get(2);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 7);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 9);
  }
}
