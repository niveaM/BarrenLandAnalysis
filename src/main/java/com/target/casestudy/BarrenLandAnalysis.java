package com.target.casestudy;

import com.target.casestudy.model.Farm;

import java.util.List;

import static com.target.casestudy.service.LandAnalyzer.findFertileAreas;
import static com.target.casestudy.util.FarmUtil.buildFarm;

/**
 * Use this file to start up the application.
 */
public class BarrenLandAnalysis {

  public static void main(String[] args) {
    if (args.length <= 0) {
      System.out.println("Provide set of rectangles that contain the barren land.");
    } else {
      Farm farm = new Farm();

      if (!args[0].trim().endsWith("}")) {
        System.out.println("Input is invalid. Please try again.");
        System.exit(0);
      }
      buildFarm(farm, args[0]);

      List<Integer> areaList = findFertileAreas(farm);

      System.out.println(areaList);
    }
  }
}
