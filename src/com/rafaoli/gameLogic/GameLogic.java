package com.rafaoli.gameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.rafaoli.graficos.GameScreen;

public class GameLogic implements ActionListener {
	
	private int cont_acertos;
	private int firstClick;
	private int secondClick;
	private int clickNumber;
    private int position;
    private int count;
    private int lastScore;
    private int highestScore;
    private int gamesPlayed = 0;
    private int victoryNumber= 0;
    private boolean isStartNewGame = true;
    private boolean isRestartGame = false;
    private boolean isEndGame = false;
    private JButton arrayEscolhas[];
    private JButton buttonNewGame;
    private JButton buttonRestartGame;
    private int pontos = 0;
    private GameScreen gameScreen;
    
	public GameLogic(JButton p_escolhas[], JButton p_newGame, JButton p_restartGame, GameScreen p_gameScreen) {
		arrayEscolhas = p_escolhas;
		buttonNewGame = p_newGame;
		buttonRestartGame = p_restartGame;
		gameScreen = p_gameScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent event){
		
		
		Random RandomNumber = new Random();
	    int random[] = new int [16];
	    int randomArray[] = new int [16];
        
        if (event.getSource() == buttonNewGame){
            isStartNewGame = true;
            isRestartGame = false;
        }
     
        if (event.getSource() == buttonRestartGame){
        	isStartNewGame = true;
        	isRestartGame = true;
        }
     
        if ( isStartNewGame ){
         
            cont_acertos = 0;
            gamesPlayed++;
            lastScore = pontos;
            pontos = 0;
            clickNumber = 0;
            position = 0; count = 16;
            firstClick = 0;
            secondClick = 0;
         
            for (int i=0; i<16; ++i){
                arrayEscolhas[i].setText("");
                arrayEscolhas[i].setEnabled(true);
            }
         
            if (isRestartGame == false){
             
                for (int i=0; i<16; ++i){
                	randomArray[i] = i;
                }
 
                for (int i=0; i<8; ++i){
                    for (int j=0; j<2; ++j){
                        position = RandomNumber.nextInt(count);
                        random[randomArray[position]] = i;
                 
                        if (position < count){
                            for (int q=(position+1); q<(count); ++q){
                            	randomArray[q-1] = randomArray[q];
                            }
                        } count--;
                    }
                }
            }
            isStartNewGame = false;
        }
     
        for (int i=0; i<16; ++i){
         
            if (event.getSource() == arrayEscolhas[i]){
                               
            	arrayEscolhas[i].setText(String.valueOf(random[i]));
            	arrayEscolhas[i].setEnabled(false);
            	arrayEscolhas[i].setVisible(true);
                clickNumber++;
             
                if (firstClick == 1) firstClick = i;
                    if (secondClick == 2){
                    	secondClick = i;
                        if (random[firstClick] != random[secondClick]){                                                      
                            pontos-=3;
                            JOptionPane.showMessageDialog(gameScreen, "Errado");
                            arrayEscolhas[firstClick].setText("");
                            arrayEscolhas[secondClick].setText("");
                            arrayEscolhas[firstClick].setEnabled(true);
                            arrayEscolhas[secondClick].setEnabled(true);                            
                     
                        }  else {
                            cont_acertos++;
                            pontos+=5;
                    }
                        firstClick = 0;
                }
            }
        }
     
     
        if (cont_acertos == 8){
            victoryNumber++;
            cont_acertos = 0;
            if (pontos > lastScore) highestScore = pontos;
                isEndGame = true;
        }
     
     
        if (pontos < 0) pontos = 0;
        gameScreen.getPlayerPoint().setText("Pontos: " + pontos);
            
    }

}
