package com.razor.template;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {

    private Object data;
    private AlgoFrame algoFrame; // 视图

    private void createData() {
        // 创建数据
    }


    public AlgoVisualizer(int canvasWidth, int canvasHeight) {
        EventQueue.invokeLater(() -> {
            algoFrame = new AlgoFrame().title("Animation").width(canvasWidth).height(canvasHeight).build();
            algoFrame.addKeyListener(new AlgoKeyListener());
            algoFrame.addMouseListener(new AlgoMouseListener());
            // 通过myFrame.getCanvasWidth获取实际长宽
            // 其实直接用canvasWidth参数和canvasHeight参数也是一样的
            createData();
            new Thread(this::run).start();
        });
    }

    public AlgoVisualizer() {
        EventQueue.invokeLater(() -> {
            algoFrame = new AlgoFrame().title("Animation").build();
            algoFrame.addKeyListener(new AlgoKeyListener());
            algoFrame.addMouseListener(new AlgoMouseListener());
            // 通过myFrame.getCanvasWidth获取实际长宽
            // 其实直接用canvasWidth参数和canvasHeight参数也是一样的
            createData();
            new Thread(this::run).start();
        });
    }

    private void run() {
        // 动画逻辑
    }

    private class AlgoKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            // 自定义是否实现键盘交互
        }
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            // 自定义是否实现鼠标交互
        }
    }

    public static void main(String[] args) {
        // 设定画板大小
        int screenWidth = 800;
        int screenHeight = 500;
        new AlgoVisualizer(screenWidth, screenHeight);
        // 不设定画板大小
        new AlgoVisualizer();
    }

}
