package com.razor.probability.money;

import com.razor.template.AlgoVisualizerHelper;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;


public class MoneyVisualizer {
    private final Random rd = new Random();
    private static final int DELAY = 10;
    private int[] money;
    private MoneyFrame moneyFrame;

    private void createMoney() {
        // 创建数据
        //房间内 100 个人，每人有 100 块，每分钟随机给另一个人 1 块，最后这个房间内的财富分布是怎样的？
        money = new int[100];
        Arrays.fill(money, 100);
    }

    public MoneyVisualizer(int canvasWidth, int canvasHeight) {
        EventQueue.invokeLater(() -> {
            moneyFrame = new MoneyFrame().title("Animation").width(canvasWidth).height(canvasHeight).build();
            // 通过myFrame.getCanvasWidth获取实际长宽
            // 其实直接用canvasWidth参数和canvasHeight参数也是一样的
            createMoney();
            new Thread(this::run).start();
        });
    }

    private void run() {
        // 一次绘制,确定初始化效果是否正确
        // moneyFrame.render(money);

        // 多次绘制使动画
        // 添加break条件,只执行seconds秒
        long startTime = System.currentTimeMillis();
        int seconds = 20;
        // 提高每一帧执行的轮数，一个for(int i=0;)是一轮，在这个for的外面套一层kNum
        int kNum = 50;
        while (System.currentTimeMillis() - startTime < 1000 * seconds) {
            // 排序，看得更清楚
            Arrays.sort(money);
            moneyFrame.render(money);
            AlgoVisualizerHelper.pause(DELAY);

            for (int k=0; k<kNum; k++) {
                for (int i=0; i<money.length; i++) {
                    // 如果i手上有钱，随机给j 1 块，看最后这个房间内的财富分布是怎样的
                    if (money[i] > 0){
                        int j = rd.nextInt(money.length);
                        money[i] -= 1;
                        money[j] += 1;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        // 设定画板大小
        int screenWidth = 800;
        int screenHeight = 500;
        new MoneyVisualizer(screenWidth, screenHeight);
    }
}
