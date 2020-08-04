package com.target.casestudy.integration;

import com.target.casestudy.model.Farm;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static com.target.casestudy.service.LandAnalyzer.findFertileAreas;
import static com.target.casestudy.util.FarmUtil.buildFarm;

/**
 * End-2-2 tests. Builds farm from string coordinates and validates results for various scenarios.
 * More comples scenarios are validated here.
 */
public class FieldTests {

  @Test
  public void test0() {
    Farm farm = new Farm();
    buildFarm(farm, "{“0 292 399 307”}");

    List<Integer> areaList = findFertileAreas(farm);
    Assert.assertEquals(2, areaList.size());
    Assert.assertEquals(new Integer(116800), areaList.get(0));
    Assert.assertEquals(new Integer(116800), areaList.get(1));
  }


  @Test
  public void test1() {
    Farm farm = new Farm();
    buildFarm(farm, "{“48 192 351 207”}");

    List<Integer> areaList = findFertileAreas(farm);

    Assert.assertEquals(1, areaList.size());
    Assert.assertEquals(new Integer(235040), areaList.get(0));
  }


  @Test
  public void test2() {
    Farm farm = new Farm();
    buildFarm(farm, "{“48 192 351 207”, “48 392 351 407”}");

    List<Integer> areaList = findFertileAreas(farm);

    Assert.assertEquals(1, areaList.size());
    Assert.assertEquals(new Integer(230480), areaList.get(0));
  }


  @Test
  public void test3() {
    Farm farm = new Farm();
    buildFarm(farm, "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”}");

    List<Integer> areaList = findFertileAreas(farm);

    Assert.assertEquals(1, areaList.size());
    Assert.assertEquals(new Integer(223040), areaList.get(0));
  }


  @Test
  public void test4() {
    Farm farm = new Farm(399, 599);
    buildFarm(farm, "{“48 192 351 207”, “48 392 351 407”, “120 52 135 547”, “260 52 275 547”}");

    List<Integer> areaList = findFertileAreas(farm);

    Assert.assertEquals(2, areaList.size());
    Assert.assertEquals(new Integer(23125), areaList.get(0));
    Assert.assertEquals(new Integer(192940), areaList.get(1));
  }
}
