package com.razor.swing;

import javax.swing.*;
import java.awt.*;

public class HelloJFrame {
    public HelloJFrame() {
        // Singleton
    }

    public static void main(String[] args) {
        // 使用EventQueue的原因是，SWING包基于线程不安全，在函数没有return之前，SWING的显示不会响应
        // 如果执行时间长甚至会像死机一样，原因是该函数和主函数的执行处于同一个线程上，函数的执行占用了CPU
        // 因此，建议所有需要同步显示画面的利用EventQueue.invokeLater重新开启一个线程
        EventQueue.invokeLater(() -> {
            JFrame jFrame = new JFrame("welcome");
            jFrame.setVisible(true);
            jFrame.setSize(500, 500);
            jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            jFrame.setResizable(true);
        });
    }
}