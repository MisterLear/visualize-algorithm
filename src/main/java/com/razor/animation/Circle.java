package com.razor.animation;

import java.awt.*;

public class Circle {
    private int x;
    private int y;
    private final int r;

    private int vx; // 速度*距离，圆圈撞击后速度会改变
    private int vy;

    private boolean isColored = false;

    public Circle(int x, int y, int r, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
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

    public boolean getIsColored() {
        return isColored;
    }

    public void setIsColored(boolean value) {
        isColored = value;
    }

    public void move(int minx, int miny, int maxx, int maxy) {
        x += vx;
        y += vy;
        checkCollision(minx, miny, maxx, maxy);
    }

    private void checkCollision(int minx, int miny, int maxx, int maxy) {
        // 圆左边与下边与边界相切
        if (x - r < minx) {
            x = r;
            vx = -vx;
        }
        if (y - r < miny) {
            y = r;
            vy = -vy;
        }
        // 圆右边与上边与边界相切
        if (x + r >= maxx) {
            x = maxx - r;
            vx = -vx;
        }
        if (y + r >= maxy) {
            y = maxy - r;
            vy = -vy;
        }
    }

    public boolean contain(Point point) {
        // 判断一个鼠标的坐标是否在圆以内
        // 如果圆心到点的距离小于半径，勾股定理
        return Math.pow((x-point.x), 2) + Math.pow((y-point.y), 2) <= Math.pow(r, 2);
    }
}
