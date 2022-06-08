package com.rafaoli.graficos;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import com.rafaoli.commons.Constantes;
import com.rafaoli.gameLogic.GameLogic;

public class GameScreen extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JToolBar gameScreenToolBar = new JToolBar();
    private JPanel gameScreenPanel = new JPanel();
    private JPanel gameScreenStatusBar = new JPanel();
    private JButton buttonNewGame = new JButton("Novo Jogo");
    private JButton buttonRestart = new JButton("Reiniciar partida");
    private JButton escolhas[] = Constantes.ESCOLHAS_MAX;
    private JButton escolhasVO[] = Constantes.ESCOLHAS_MAX;
    private JLabel playerPoint = new JLabel("Pontos: 0");

   
    public GameScreen() {
        super(Constantes.TITULO_JOGO);
     
        gameScreenToolBar.add(buttonNewGame);
        gameScreenToolBar.add(buttonRestart);
        add(gameScreenToolBar, BorderLayout.NORTH);
     
        for (int i=0; i<16; ++i){
        	escolhas[i] = new JButton();
        	escolhasVO[i] = new JButton();
            gameScreenPanel.add(escolhas[i]);
            gameScreenPanel.add(escolhasVO[i]);
            escolhasVO[i].setFont(Constantes.FONT_LUCONS_BOLD_36);
            escolhasVO[i].setVisible(true);
            escolhas[i].setFont(Constantes.FONT_LUCONS_BOLD_36);
            escolhas[i].setVisible(true);
            
        }
     
        gameScreenPanel.setLayout(Constantes.GRID_LAYOUT_JOGO);
        add(gameScreenPanel, BorderLayout.CENTER);
     
        gameScreenStatusBar.add(playerPoint);
        add(gameScreenStatusBar, BorderLayout.SOUTH);
     
        GameLogic gameLogic = new GameLogic(escolhas,escolhasVO, buttonNewGame, buttonRestart, GameScreen.this);
        for (int i=0; i<16; ++i){
            escolhas[i].addActionListener(gameLogic);
        }
        buttonNewGame.addActionListener(gameLogic);
        buttonRestart.addActionListener(gameLogic);
        gameLogic.actionPerformed(null);
     
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);    
        this.setSize(Constantes.LARGURA_TELA,Constantes.ALTURA_TELA);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }


	public JLabel getPlayerPoint() {
		return playerPoint;
	}


	public void setPlayerPoint(JLabel playerPoint) {
		this.playerPoint = playerPoint;
	}


	public JPanel getGameScreenPanel() {
		return gameScreenPanel;
	}


	public void setGameScreenPanel(JPanel gameScreenPanel) {
		this.gameScreenPanel = gameScreenPanel;
	}
    
}
