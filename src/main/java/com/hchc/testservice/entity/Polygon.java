package com.hchc.testservice.entity;

import lombok.Data;

import java.util.List;

@Data
public class Polygon {
    private List<Point> pointList;
}