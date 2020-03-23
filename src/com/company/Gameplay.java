package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.lang.Integer.valueOf;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;

    private int firstPlayerY = 280;
    private int secondPlayerY = 280;

    private int ballPosX = 300;
    private int ballPosY = 300;
    private int ballDirX = -1;
    private int ballDirY = -1;

    private Timer timer;
    private int delay;
    public Gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect(0,0,1000,600);
        //granice
        g.setColor(Color.black);
        g.fillRect(0,0,5,600);
        g.fillRect(992,0,8,600);
        g.setColor(Color.white);
        g.fillRect(0,0,1000,8);
        g.fillRect(0,580,1000,20);
        g.fillRect(498,0,4,600);
        //paletki graczy
        g.fillRect(50,secondPlayerY,10,100);
        g.fillRect(940,firstPlayerY,10,100);
        //piłeczka
        g.setColor(Color.yellow);
        g.fillOval(ballPosX,ballPosY,20,20);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        timer.start();
        if(play){
            if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(940,firstPlayerY,10,100)) ||
            new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(50,secondPlayerY,10,100))){
                ballDirY = -ballDirY;
                ballDirX = -ballDirX;
            }
            ballPosX += ballDirX;
            ballPosY +=ballDirY;
            if(ballPosY < 0){
                ballDirY = -ballDirY;
            }
            if(ballPosY > 600){
                ballDirY = -ballDirY;
            }
            if(ballPosX < 0){
                ballPosX = 500;
                ballPosY = 300;
                firstPlayerScore +=1;
            }
            if(ballPosX > 1000){
                ballPosX = 500;
                ballPosY = 300;
                secondPlayerScore +=1;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}
    @Override
    public void keyReleased(KeyEvent keyEvent) {}
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            play = true;
            if(firstPlayerY < 0){
                firstPlayerY = 0;
            }else{
                firstPlayerY -=50;
            }
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            play = true;
            if(firstPlayerY > 500){
                firstPlayerY = 500;
            }
            else{
                firstPlayerY += 50;
            }
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_D){
            play = true;
            if(secondPlayerY < 0){
                secondPlayerY = 0;
            }else{
                secondPlayerY -= 50;
            }
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_A){
            play = true;
            if(secondPlayerY > 500){
                secondPlayerY = 500;
            }
            else{
                secondPlayerY += 50;
            }
        }
    }


}
