package com.razor.animation;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class VisualizerHelper {
    private VisualizerHelper() {}

    // 画一个空心圆-内层和背景一样颜色的小圆，外层一个大圆
    public static void strokeCircle(Graphics2D graphics2D, double x, double y, double r) {

        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        graphics2D.draw(circle);
    }

    // 将空心圆填色，使用鼠标控制
    public static void fillCircle(Graphics2D graphics2D, double x, double y, double r) {
        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        graphics2D.fill(circle);
    }

    // 填充颜色
    public static void setColor(Graphics2D graphics2D, Color color) {
        graphics2D.setColor(color);
    }

    public static void setStrokeWidth(Graphics2D graphics2D, int strokeWidth) {
        graphics2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void pause(int timeSleep) {
        try {
            Thread.sleep(timeSleep);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}
