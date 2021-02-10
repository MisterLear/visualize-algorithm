package com.razor.probability.pai;

import com.razor.template.AlgoVisualizerHelper;
import com.razor.template.Circle;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class PaiFrame extends JFrame {

    // 默认使用屏幕长宽设定画布大小
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private String titleParam;
    private int canvasWidthParam = screenSize.width;
    private int canvasHeightParam = screenSize.height;

    // 定义数据
    private Circle circle;
    private List<Point> points;

    public PaiFrame build() {
        return this;
    }

    // 定义自己的Canvas
    private void draw() {
        MoneyCanvas moneyCanvas = new MoneyCanvas();
        setContentPane(moneyCanvas);
        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }


    public PaiFrame title(String title) {
        this.titleParam = title;
        return this;
    }

    public PaiFrame width(int width) {
        this.canvasWidthParam = width;
        return this;
    }

    public PaiFrame height(int height) {
        this.canvasHeightParam = height;
        return this;
    }

    public PaiFrame() {
        // Singleton
    }

    // 设置数据
    public void render(Circle circle, List<Point> points) {
        draw();
        this.circle = circle;
        this.points = points;
        repaint();
    }

    private class MoneyCanvas extends JPanel {

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

            // 绘制自己的数据
            AlgoVisualizerHelper.setStrokeWidth(graphics2D, 3);
            AlgoVisualizerHelper.setColor(graphics2D, Color.BLUE);
            AlgoVisualizerHelper.strokeCircle(graphics2D, circle.getX(), circle.getY(), circle.getR());

            // 如果点在圆内打红色点，否则绿色
            for (Point point: points) {
                if (circle.contain(point)) {
                    AlgoVisualizerHelper.setColor(graphics2D, Color.RED);
                } else {
                    AlgoVisualizerHelper.setColor(graphics2D, Color.GREEN);
                }
                // 点以圆的方式出现
                AlgoVisualizerHelper.fillCircle(graphics2D, point.x, point.y, 3);
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
