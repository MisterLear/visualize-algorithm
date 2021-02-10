package com.razor.animation;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Visualizer {
    private Circle[] circles;
    private MyFrame myFrame; // 视图
    private boolean isAnimated = true;

    private void createCircles(int canvasWidth, int canvasHeight, int numCircles) {
        // 创造N个圆
        circles = new Circle[numCircles];
        int r = 50;
        for (int i=0; i<numCircles; i++) {
            // 随机创建分布在 canvasWidth * canvasHeight 边框上的圆心
            // Math.random产生0-1之间的随机数
            // 通过以下公式保证圆最多与边框相切
            int x = new Random().nextInt(canvasWidth-2*r)+ r;
            int y = new Random().nextInt(canvasHeight-2*r)+ r;
            // 生成[-5, 5]的整数随机数
            // 负数控制方向为左和上，数字大小控制移动速度
            int vx = new Random().nextInt(11) - 5;
            int vy = new Random().nextInt(11) - 5;
            circles[i] = new Circle(x, y, r, vx, vy);
        }
    }

    public Visualizer(int canvasWidth, int canvasHeight, int numCircles) {
        EventQueue.invokeLater(() -> {
            myFrame = new MyFrame().title("Animation").width(canvasWidth).height(canvasHeight).build();
            myFrame.addKeyListener(new MyKeyListener());
            myFrame.addMouseListener(new MyMouseListener());
            // 通过myFrame.getCanvasWidth获取实际长宽
            // 其实直接用canvasWidth参数和canvasHeight参数也是一样的
            createCircles(myFrame.getCanvasWidth(), myFrame.getCanvasHeight(), numCircles);
            new Thread(this::run).start();
        });
    }

    public Visualizer(int numCircles) {
        // 不传入长宽时会默认用电脑屏幕大小
        EventQueue.invokeLater(() -> {
            myFrame = new MyFrame().title("Animation").build();
            myFrame.addKeyListener(new MyKeyListener());
            myFrame.addMouseListener(new MyMouseListener());
            // 通过myFrame.getCanvasWidth获取实际长宽
            createCircles(myFrame.getCanvasWidth(), myFrame.getCanvasHeight(), numCircles);
            new Thread(this::run).start();
        });
    }

    private void run() {
        // 一次性绘制
        // myFrame.render(circles);
        // 多次绘制使动画
        while (true) {
            myFrame.render(circles);
            VisualizerHelper.pause(20);
            // 使用空格键控制暂停
            if (isAnimated) {
                for(Circle circle: circles) {
                    circle.move(0, 0, myFrame.getCanvasWidth(), myFrame.getCanvasHeight());
                }
            }
        }
    }

    private class MyKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            // 使用空格键暂停
            if (event.getKeyChar() == ' ') {
                isAnimated = !isAnimated;
            }
        }
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            // 点击填色
            // 每次鼠标点击生成一个x,y坐标
            // 如果圆的坐标系包含了这个鼠标的坐标，染色/去色
            event.translatePoint(0, -(myFrame.getBounds().height - myFrame.getCanvasHeight()));
            for ( Circle circle: circles ) {
                if (circle.contain(event.getPoint())){
                    circle.setIsColored(!circle.getIsColored());
                }
            }
        }
    }

    public static void main(String[] args) {
        // 设定画板大小
        int screenWidth = 800;
        int screenHeight = 500;
        new Visualizer(screenWidth, screenHeight, 10);
        // 不设定画板大小
        // new Visualizer(10);
    }

}
