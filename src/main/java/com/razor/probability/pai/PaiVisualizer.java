package com.razor.probability.pai;

import com.razor.template.AlgoVisualizerHelper;
import com.razor.template.Circle;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;


public class PaiVisualizer {

    private final Random rd = new Random();
    private static final int DELAY = 10;
    private Circle circle;
    private PaiFrame paiFrame;
    private LinkedList<Point> points = new LinkedList<>();
    private int numIterations;

    // 蒙特卡洛方法求Π值
    // 圆的面积 = PI*R*R
    // 方形面积 = (2*R)*(2*R) = 4*R*R
    // PI = 4*圆/方
    // PI = 4*红色点/总点数
    private void createDot(int num) {
        // 创建数据
        for (int i=0; i<num; i++) {
            int x = rd.nextInt(paiFrame.getCanvasWidth());
            int y = rd.nextInt(paiFrame.getCanvasHeight());

            points.add(new Point(x, y));
        }
    }

    public PaiVisualizer(int canvasWidth, int canvasHeight) {
        EventQueue.invokeLater(() -> {
            paiFrame = new PaiFrame().title("Animation").width(canvasWidth).height(canvasHeight).build();
            // 通过myFrame.getCanvasWidth获取实际长宽
            // 其实直接用canvasWidth参数和canvasHeight参数也是一样的
            new Thread(this::run).start();
        });
    }

    private void run() {
        // 一次绘制,确定初始化效果是否正确
        circle = new Circle(paiFrame.getCanvasHeight()/2, paiFrame.getCanvasHeight()/2, paiFrame.getCanvasHeight()/2);
        // createDot(40);
        // paiFrame.render(circle, points);

        // 多次绘制使动画
        // 提高每一帧执行的点数，一个for(int i=0;)是一轮，在这个for的外面套一层kNum
        int kNum = 50;
        for (int i=0; i<kNum; i++) {
            createDot(40);
            paiFrame.render(circle, points);
            AlgoVisualizerHelper.pause(DELAY);
        }
    }

    public static void main(String[] args) {
        // 设定画板大小
        int screenWidth = 800;
        int screenHeight = 500;
        new PaiVisualizer(screenWidth, screenHeight);
    }
}
