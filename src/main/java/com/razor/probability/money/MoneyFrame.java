package com.razor.probability.money;

import com.razor.template.AlgoVisualizerHelper;

import javax.swing.*;
import java.awt.*;

public class MoneyFrame extends JFrame {

    // 默认使用屏幕长宽设定画布大小
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private String titleParam;
    private int canvasWidthParam = screenSize.width;
    private int canvasHeightParam = screenSize.height;

    // 定义数据
    private int[] money;

    public MoneyFrame build() {
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


    public MoneyFrame title(String title) {
        this.titleParam = title;
        return this;
    }

    public MoneyFrame width(int width) {
        this.canvasWidthParam = width;
        return this;
    }

    public MoneyFrame height(int height) {
        this.canvasHeightParam = height;
        return this;
    }

    public MoneyFrame() {
        // Singleton
    }

    // 设置数据
    public void render(int[] money) {
        draw();
        this.money = money;
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
            AlgoVisualizerHelper.setColor(graphics2D, Color.BLUE);

            // 每个条形柱所占宽度
            int width = canvasWidthParam / money.length;

            // 条形柱的间距
            int interval = width / 4;

            for (int i=0; i<money.length; i++) {
                // 由于计算机视觉的x,y从左上角开始,条形住的高度坐标实际等于canvasHeight-条形柱实际想要的长度
                AlgoVisualizerHelper.fillRectangle(graphics2D, i*width+interval, canvasHeightParam-money[i], width-interval, money[i]);
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
