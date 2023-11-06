package org.example;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame(); // создаем окно с помощью JFrame
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // не изменяемый размер окна
        window.setTitle("test Game2D"); // заголовок окна

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // собирает окно

        window.setLocationRelativeTo(null); // положение окна по центру экрана
        window.setVisible(true); //отображение окна

        gamePanel.setupGame();
        gamePanel.startGameThread();


    }
}