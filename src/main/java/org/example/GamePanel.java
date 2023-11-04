package org.example;

import entity.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // Олдскульный размер тайла
    final int scale = 3; // поскольку тайл 16*16 мелкий мы увеличим на 3

    final public int tileSize = originalTileSize * scale; // итого 48*48
    public final int maxScreenCol = 16; // тайлов в ширину
    public final int maxScreenRow = 12; // тайлов в высоту
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //WORLD SETTING
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize; // данные переменные создаем что бы экономить память!!!! GameDev
    public final int worldHeight = maxWorldRow * tileSize;

    int FPS = 60;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public Player player = new Player(this, keyH); // Вот таким макаром можно передать текущий класс в другой класс!!!


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // устанавливаем размер панели, передаем размерность через Dimension, куда передаем ширину и высоту
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // позволяет GamePanel ловить клавиатуры и передавать в KeyHandler
        this.setFocusable(true); // Позволяет быть в "фокусе" для считывания клавиатуры

    }

    public void startGameThread() {
        gameThread = new Thread(this);  /*
                    Вот тут нихера не понятно gameThread объявил там, создал тут... ппц
                    мы передаем этот класс (GamePanel) в конструктор new Thread так мы инициализируем Thread
                    и дальше запускаем этот поток
                     */

        gameThread.start(); // метод потока start() автоматически вызовет метод класса run()
    }


    /**
     * Основной цикл игры
     */
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) { //пока существует поток gameThread цикл повторяется

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0 ){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void update() {
        player.update();

    }

    /**
     * paintComponent() на самом деле встроенный метод java
     * это один из стандартных методов рисования JPanel.
     * Написание "super.paintComponent(g);" установленное разработчиками Java.
     * Сам же метод paintComponent() вызывается методом repaint().
     */
    public void paintComponent(Graphics g) { // Graphics class имеет множество функций отрисовки на экране
        super.paintComponent(g); //super указывает на родительский класс  JPanel

        Graphics2D g2 = (Graphics2D) g; // Graphics2D имеет дополнительный функционал отрисовки

        tileM.draw(g2);
        player.draw(g2);

        g2.dispose(); // Уничтожаем объект. Чуть раньше освобождаем память, Хороший тон

    }

}
