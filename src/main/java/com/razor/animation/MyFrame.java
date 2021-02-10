package com.razor.animation;

import javax.swing.*;
import java.awt.*;


public class MyFrame extends JFrame {

    // 默认使用屏幕长宽设定画布大小
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private String titleParam;
    private int canvasWidthParam = screenSize.width;
    private int canvasHeightParam = screenSize.height;

    private Circle[] circles;

    public MyFrame build() {
        return this;
    }

    public MyFrame title(String title) {
        this.titleParam = title;
        return this;
    }

    public MyFrame width(int width) {
        this.canvasWidthParam = width;
        return this;
    }

    public MyFrame height(int height) {
        this.canvasHeightParam = height;
        return this;
    }

    public MyFrame() {
        // Singleton
    }

    public void render(Circle[] circles) {
        draw();
        this.circles = circles;
        repaint();
    }

    private void draw() {
        My2DCanvas my2DCanvas = new My2DCanvas();
        setContentPane(my2DCanvas);
        initialize();
    }

    private void initialize() {
        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private class My2DCanvas extends JPanel {

        @Override
        public void paintComponent(Graphics graphics) {
            // 双缓存，解决paint方法闪烁问题
            // update时清屏->设置前景色->重画，在1步和2步之间出现了空白的图像，就出现了闪烁
            // 双缓存首先在后台绘制好图片放入缓存，调用paint时只是copy图片
            super.setDoubleBuffered(true);
            super.paintComponent(graphics);

            Graphics2D graphics2D = (Graphics2D)graphics;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.addRenderingHints(hints);

            // 具体绘制，和 com.razor.swing.MyFrame中draw2D一样的操作
            // 不过方法被封装起来了
            VisualizerHelper.setStrokeWidth(graphics2D,1);
            VisualizerHelper.setColor(graphics2D, Color.RED);
            for (Circle circle: circles) {
                if (circle.getIsColored()) {
                    VisualizerHelper.fillCircle(graphics2D, circle.getX(), circle.getY(), circle.getR());
                } else {
                    VisualizerHelper.strokeCircle(graphics2D, circle.getX(), circle.getY(), circle.getR());
                }
            }


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(getCanvasWidth(), getCanvasHeight());
        }
    }


    public int getCanvasHeight() {
        return canvasHeightParam;
    }

    public int getCanvasWidth() {
        return canvasWidthParam;
    }

    @Override
    public String getTitle() {
        return titleParam;
    }

}
