package com.target.casestudy.service;

import com.target.casestudy.model.Area;
import com.target.casestudy.model.Farm;
import com.target.casestudy.model.LandType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.target.casestudy.service.LandAnalyzer.analyzeLand;
import static com.target.casestudy.util.FarmUtil.buildFarm;

/**
 * Unit tests to validate code with a smaller dimension grid.
 */
public class IntersectingFieldTest {

  @Test
  public void testCheckeredStripeEdgeSplit1() {
    Farm farm = new Farm(9, 9);
    buildFarm(farm, "{“2 4 8 6”}, {“3 2 4 8”}");

//    printFarm(farm);

    List<Area> fertileAreas = analyzeLand(farm);
    Assert.assertEquals(fertileAreas.size(), 8);
    Area a = fertileAreas.get(0);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 0);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 2);

    a = fertileAreas.get(1);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 2);
    Assert.assertEquals(a.max.x, 3);
    Assert.assertEquals(a.max.y, 4);

    a = fertileAreas.get(2);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 4);
    Assert.assertEquals(a.min.y, 2);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 4);

    a = fertileAreas.get(3);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 8);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 9);

    a = fertileAreas.get(4);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 6);
    Assert.assertEquals(a.max.x, 3);
    Assert.assertEquals(a.max.y, 8);

    a = fertileAreas.get(5);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 4);
    Assert.assertEquals(a.min.y, 6);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 8);

    a = fertileAreas.get(6);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 0);
    Assert.assertEquals(a.min.y, 4);
    Assert.assertEquals(a.max.x, 2);
    Assert.assertEquals(a.max.y, 6);

    a = fertileAreas.get(7);
    Assert.assertEquals(a.type.toString(), LandType.FERTILE.toString());
    Assert.assertEquals(a.min.x, 8);
    Assert.assertEquals(a.min.y, 4);
    Assert.assertEquals(a.max.x, 9);
    Assert.assertEquals(a.max.y, 6);
  }
}
