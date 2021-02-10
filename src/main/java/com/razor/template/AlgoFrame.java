package com.razor.template;

import javax.swing.*;
import java.awt.*;


public class AlgoFrame extends JFrame {
    // 默认使用屏幕长宽设定画布大小
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private String titleParam;
    private int canvasWidthParam = screenSize.width;
    private int canvasHeightParam = screenSize.height;

    // TODO: 自定义数据，一般都是数组
    private Object data;

    public AlgoFrame build() {
        return this;
    }

    // 定义自己的Canvas
    private void draw() {
        AlgoCanvas algoCanvas = new AlgoCanvas();
        setContentPane(algoCanvas);
        pack();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }


    public AlgoFrame title(String title) {
        this.titleParam = title;
        return this;
    }

    public AlgoFrame width(int width) {
        this.canvasWidthParam = width;
        return this;
    }

    public AlgoFrame height(int height) {
        this.canvasHeightParam = height;
        return this;
    }

    public AlgoFrame() {
        // Singleton
    }

    // TODO: 设置数据
    public void render(Object data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

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

            // TODO: 绘制自己的数据
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
