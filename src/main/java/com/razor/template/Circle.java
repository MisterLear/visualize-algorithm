package com.razor.template;

import java.awt.*;

public class Circle {
    private final int x;
    private final int y;
    private final int r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public int getR() {
        return r;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean contain(Point point) {
        // 判断一个鼠标的坐标是否在圆以内
        // 如果圆心到点的距离小于半径，勾股定理
        return Math.pow((x-point.x), 2) + Math.pow((y-point.y), 2) <= Math.pow(r, 2);
    }
}
