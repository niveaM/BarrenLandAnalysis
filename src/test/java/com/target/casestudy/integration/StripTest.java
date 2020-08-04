package com.target.casestudy.integration;

import com.target.casestudy.model.Farm;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.target.casestudy.service.LandAnalyzer.findFertileAreas;
import static com.target.casestudy.util.FarmUtil.buildFarm;

/**
 * End-2-2 tests. Builds farm from string coordinates and validates results for various scenarios.
 */
public class StripTest {

  @Test
  public void testStripes() {
    Farm farm = new Farm(9, 9);
    buildFarm(farm, "{“0 2 9 4”}, {“0 6 9 8”}");

    List<Integer> f = findFertileAreas(farm);
    Assert.assertEquals(3, f.size());
    Assert.assertEquals(26400, f.get(0).intValue());
    Assert.assertEquals(53200, f.get(1).intValue());
    Assert.assertEquals(53200, f.get(2).intValue());

//    int barrenArea = farm.getBarrenLand().stream().mapToInt(a -> getArea(a, farm)).sum();
//
//    int farea = f.stream().mapToInt(i -> i).sum();
//
//    Assert.assertEquals(240000, barrenArea + farea);
  }
}
