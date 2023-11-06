package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener { // KeyListener получаем ивенты клавиатуры

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, isWalking;

    //DEBUG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            isWalking = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            isWalking = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            isWalking = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            isWalking = true;
        }

        if (code == KeyEvent.VK_C) {
            if(checkDrawTime == false){
                checkDrawTime = true;
            }else {
                checkDrawTime =false;
            }

        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            } else if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        isWalking = false;
    }
}
