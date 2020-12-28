package com.hchc.testservice.test;

import com.alibaba.fastjson.JSON;
import com.hchc.testservice.entity.LocationUtils;
import com.hchc.testservice.entity.Point;
import com.hchc.testservice.entity.PolygonArea;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangrong
 * @date 2020-11-24
 */
public class AreaTest {

    public static void main(String[] args) {

        String areaStr = "[{\n" +
                "\t\t\"polygon\": {\n" +
                "\t\t\t\"pointList\": [{\n" +
                "\t\t\t\t\"lat\": 31.237820610717257,\n" +
                "\t\t\t\t\"lng\": 121.45548820495605\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"lat\": 31.24060928079197,\n" +
                "\t\t\t\t\"lng\": 121.49977684020996\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"lat\": 31.205084278078765,\n" +
                "\t\t\t\t\"lng\": 121.49909019470215\n" +
                "\t\t\t}, {\n" +
                "\t\t\t\t\"lat\": 31.20317553232384,\n" +
                "\t\t\t\t\"lng\": 121.45686149597168\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t\"deliveryFee\": \"6\",\n" +
                "\t\t\"area\": \"16092.37\"\n" +
                "\t}]";

        List<PolygonArea> polygonAreas = JSON.parseArray(areaStr, PolygonArea.class);
        Point point = new Point(31.220975, 121.479100);
        double deliveryFee = fetchPolygonFee(polygonAreas, 31.220975, 121.479100);
        System.out.println(deliveryFee);
    }

    private static Double fetchPolygonFee(List<PolygonArea> polygonAreas, Double lat, Double lng) {
        Point point = new Point(lat, lng);
        List<Double> feeList = new ArrayList<>();
        for (PolygonArea polygonArea : polygonAreas) {
            if (!LocationUtils.inside(polygonArea.getPolygon(), point)) {
                continue;
            }
            feeList.add(polygonArea.getDeliveryFee());
        }
        if (feeList.isEmpty()) {
            return null;
        }
        return feeList.stream().min(Double::compareTo).get();
    }
}
