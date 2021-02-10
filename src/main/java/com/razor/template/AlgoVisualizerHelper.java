package com.razor.template;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class AlgoVisualizerHelper {

    private AlgoVisualizerHelper() {}

    public static void strokeCircle(Graphics2D graphics2D, double x, double y, double r) {
        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        graphics2D.draw(circle);
    }

    public static void fillCircle(Graphics2D graphics2D, double x, double y, double r) {
        Ellipse2D circle = new Ellipse2D.Double(x-r, y-r, 2*r, 2*r);
        graphics2D.fill(circle);
    }

    public static void strokeRectangle(Graphics2D graphics2D, double x, double y, double w, double h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        graphics2D.draw(rectangle);
    }

    public static void fillRectangle(Graphics2D graphics2D, double x, double y, double w, double h) {
        Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
        graphics2D.fill(rectangle);
    }

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

    public static void putImage(Graphics2D graphics2D, int x, int y, String imageURL) {
        ImageIcon imageIcon = new ImageIcon(imageURL);
        Image image = imageIcon.getImage();
        graphics2D.drawImage(image, x, y, null);

    }

    public static void drawText(Graphics2D graphics2D, String text, int centerX, int centerY) {
        if (text == null) {
            throw new IllegalArgumentException("Text is null in drawText function!");
        }
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int w = fontMetrics.stringWidth(text);
        int h = fontMetrics.getDescent();
        graphics2D.drawString(text, centerX - w/2, centerY + h);
    }


}
