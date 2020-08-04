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
public class CheckeredFieldTest {

  @Test
  public void testCheckeredStripeEdgeSplit1() {
    Farm farm = new Farm(9, 9);
    buildFarm(farm, "{“4 4 7 7”}");

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 4);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 4);

    a = fertileAreas.get(1);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 7);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 9);

    a = fertileAreas.get(2);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 4);
    Assert.assertEquals(a.max.x, 4);
    Assert.assertEquals(a.max.y, 7);

    a = fertileAreas.get(3);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 7);
    Assert.assertEquals(a.min.y, 4);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 7);

    Map<Coordinates, List<Area>> map = mergeAdjacent(fertileAreas);
    Assert.assertEquals(1, map.size());
  }

  @Test
  public void testCheckeredStripeEdgeSplit2() {
    Farm farm = new Farm(9, 9);
    buildFarm(farm, "{“4 4 7 7”}, {“0 2 2 5”}");

//    printFarm(farm);

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 6);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 2);

    a = fertileAreas.get(1);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 2);
    Assert.assertEquals(a.min.y, 2);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 4);

    a = fertileAreas.get(2);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 7);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 9);

    a = fertileAreas.get(3);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 5);
    Assert.assertEquals(a.max.x, 4);
    Assert.assertEquals(a.max.y, 7);

    a = fertileAreas.get(4);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 2);
    Assert.assertEquals(a.min.y, 4);
    Assert.assertEquals(a.max.x, 4);
    Assert.assertEquals(a.max.y, 5);

    a = fertileAreas.get(5);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 7);
    Assert.assertEquals(a.min.y, 4);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 7);

    Map<Coordinates, List<Area>> map = mergeAdjacent(fertileAreas);
    Assert.assertEquals(1, map.size());
  }

  @Test
  public void testCheckeredStripeCenterSplit1() {
    Farm farm = new Farm(9, 9);
    buildFarm(farm, "{“2 2 3 7”}");

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 4);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 2);

    a = fertileAreas.get(1);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 7);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 9);

    a = fertileAreas.get(2);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 2);
    Assert.assertEquals(a.max.x, 2);
    Assert.assertEquals(a.max.y, 7);

    a = fertileAreas.get(3);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 3);
    Assert.assertEquals(a.min.y, 2);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 7);
  }

  @Test
  public void testCheckeredStripeCenterSplit2() {
    Farm farm = new Farm(9, 9);
    buildFarm(farm, "{“2 2 3 7”}, {“4 1 5 5”}");

//    printFarm(farm);

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 8);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 1);

    a = fertileAreas.get(1);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 1);
    Assert.assertEquals(a.max.x, 4);
    Assert.assertEquals(a.max.y, 2);

    a = fertileAreas.get(2);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 5);
    Assert.assertEquals(a.min.y, 1);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 2);

    a = fertileAreas.get(3);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 7);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 9);

    a = fertileAreas.get(4);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 2);
    Assert.assertEquals(a.max.x, 2);
    Assert.assertEquals(a.max.y, 7);

    a = fertileAreas.get(5);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 3);
    Assert.assertEquals(a.min.y, 5);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 7);

    a = fertileAreas.get(6);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 3);
    Assert.assertEquals(a.min.y, 2);
    Assert.assertEquals(a.max.x, 4);
    Assert.assertEquals(a.max.y, 5);

    a = fertileAreas.get(7);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 5);
    Assert.assertEquals(a.min.y, 2);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 5);
  }
}
