package com.rafaoli.commons;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;

public class Constantes {
	
	public static String TITULO_JOGO = "Jogo da Memória";
	public static Font FONT_LUCONS_BOLD_36 = new Font("Lucida Console", Font.BOLD, 36);
	public static JButton ESCOLHAS_MAX[] = new JButton[16];
	public static String NOVO_JOGO = "Novo Jogo";
	public static String REINICIAR_JOGO = "Reiniciar jogo";
	public static GridLayout GRID_LAYOUT_JOGO = new GridLayout(4,4);
	public static int LARGURA_TELA = 500;
	public static int ALTURA_TELA = 500;
	public static String PARTIDAS_JOGADAS = "Partidas Jogadas: ";
	public static String VITORIAS = "Vitórias: ";
	public static String MAX_SCORE = "Maior Pontuação: ";
	public static int DELAY = 5000;
	public static int PERIOD = 5000;
	public static Random RandomNumber = new Random();
	public static int random[] = new int [16];
	public static int randomArray[] = new int [16];

}
