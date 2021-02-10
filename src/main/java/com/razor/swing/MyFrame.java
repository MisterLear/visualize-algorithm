package com.razor.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;


public class MyFrame extends JFrame {

    // 默认使用屏幕长宽设定画布大小
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private String titleParam;
    private int canvasWidthParam = screenSize.width;
    private int canvasHeightParam = screenSize.height;

    public MyFrame build() {
        return this;
    }

    private void drawOval() {
        MyOvalCanvas myOvalCanvas = new MyOvalCanvas();
        setContentPane(myOvalCanvas);
        initialize();
    }

    private void draw2D() {
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

    private class MyOvalCanvas extends JPanel {

        @Override
        public void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            // 屏幕左上角为x,y原点
            graphics.drawOval(0, 0, 100, 200);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(getCanvasWidth(), getCanvasHeight());
        }
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

            int strokeWidth = 5;
            // CAP是断点，JOIN是拐点，ROUND是圆角，比较好看
            graphics2D.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            Ellipse2D circle;

            graphics2D.setColor(Color.BLACK);
            circle = new Ellipse2D.Double(50, 50, 300, 300);
            graphics2D.draw(circle);

            graphics2D.setColor(Color.RED);
            circle = new Ellipse2D.Double(50, 50, 300, 300);
            graphics2D.fill(circle);
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

    public static void main(String[] args) {
        // 使用EventQueue的原因是，SWING包基于线程不安全，在函数没有return之前，SWING的显示不会响应
        // 如果执行时间长甚至会像死机一样，原因是该函数和主函数的执行处于同一个线程上，函数的执行占用了CPU
        // 因此，建议所有需要同步显示画面的利用EventQueue.invokeLater重新开启一个线程
        EventQueue.invokeLater(() -> {
            MyFrame myFrame = new MyFrame().title("Hello, Swing").width(500).height(500).build();
            myFrame.draw2D();
            myFrame.drawOval();
        });
    }
}
