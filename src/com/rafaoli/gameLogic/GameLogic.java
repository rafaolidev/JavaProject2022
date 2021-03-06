package com.rafaoli.gameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.rafaoli.commons.Constantes;
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
    private JButton arrayEscolhasVO[];
    Timer timer = new Timer();	
    private JButton buttonNewGame;
    private int pontos = 0;
    private GameScreen gameScreen;
    
	public GameLogic(JButton p_escolhas[],JButton p_escolhasVO[], JButton p_newGame, GameScreen p_gameScreen) {
		arrayEscolhas = p_escolhas;
		arrayEscolhasVO = p_escolhasVO;
		buttonNewGame = p_newGame;
		gameScreen = p_gameScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent event){
		
		if( event != null) {

        if (event.getSource() == buttonNewGame){
            isStartNewGame = true;
            isRestartGame = false;
        }
     
        if ( isStartNewGame ){
         
            cont_acertos = 0;
            gamesPlayed++;
            lastScore = pontos;
            pontos = 0;
            clickNumber = 0;
            firstClick = 0;
            secondClick = 0;
         
            for (int i=0; i<16; ++i){
                arrayEscolhas[i].setText("");
                arrayEscolhas[i].setEnabled(true);
                arrayEscolhas[i].setVisible(true);
            }
         

            isStartNewGame = false;
        }
        
     
        for (int i=0; i<16; ++i){
         
            if (event.getSource() == arrayEscolhas[i]){
            	
            	arrayEscolhas[i].setText(String.valueOf(Constantes.random[i]));         
            	arrayEscolhas[i].setEnabled(false);
            	arrayEscolhas[i].setVisible(true);
                clickNumber++;
             
                if (clickNumber == 1) firstClick = i;
                    if (clickNumber == 2){
                    	secondClick = i;
                        if (Constantes.random[firstClick] != Constantes.random[secondClick]){                                                      
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
                        clickNumber = 0;
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
        
        if (isEndGame) isEndGame = false;
		}else{                       
           	preencherVetor();
           }
            
    }
	
	public void timer() {

       
        for( int i = 0; i< Constantes.random.length;i++) {
        	arrayEscolhasVO[i].setText(String.valueOf(Constantes.random[i]));
        }
 
        timer.schedule (new TimerTask() {
			
			@Override
			public void run() {
				for( int i = 0; i< Constantes.random.length;i++) {
					arrayEscolhasVO[i].setText("");
				}	
				timer.cancel();
			}
		}, Constantes.DELAY, Constantes.PERIOD);            
	}
	
	public void preencherVetor() {
        position = 0; count = 16;

        for (int i=0; i<16; ++i){
        	Constantes.randomArray[i] = i;
        }

        for (int i=0; i<8; ++i){
            for (int j=0; j<2; ++j){
                position = Constantes.RandomNumber.nextInt(count);
                Constantes.random[Constantes.randomArray[position]] = i;
         
                if (position < count){
                    for (int q=(position+1); q<(count); ++q){
                    	Constantes.randomArray[q-1] = Constantes.randomArray[q];
                    }
                } count--;
            }
        }        
        timer();
	}

}
