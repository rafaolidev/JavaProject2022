package JavaProject2022;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/*
 Faça um jogo da memória que mostra ao jogador
 um tabuleiro de 16 peças numeradas de 1 a 8 e 
 sorteadas aleatoriamente. O jogo inicia com todas as 
 peças desviradas e aguarda 5 segundos para o jogador 
 memorizar a posição das peças. Depois, o jogador vai desvirando as peças, 
 tentando encontrar as peças iguais. Para cada acerto, 
 o programa soma 5 pontos na pontuação do jogador, para cada 
 erro, o programa tira 3 pontos. No final, o programa mostra a pontuação final do jogador.
*/

public class JogoDaMemoria extends JFrame{
 
    int pontos = 0;  
 
    Random RandomNumber = new Random();
    int Aleatorio[] = new int [16];
    int Posicao_do_vetor_Aleatorio[] = new int [16];
 
    // Barra de Ferramenta
    private JToolBar Barra_Ferramenta = new JToolBar();
    private JButton Button_Novo_Jogo = new JButton("Novo Jogo");
    private JButton Button_Re_Iniciar_Jogo = new JButton("Reiniciar partida");
 
    private JPanel Panel = new JPanel();
    private GridLayout Layout_do_Jogo = new GridLayout(4,4);
    private Font Fonte = new Font("Lucida Console", Font.BOLD, 36);
    private JButton Escolha[] = new JButton[16];
 
    private JPanel Barra_de_Status = new JPanel();
    private JLabel Pontuacao_do_Jogador = new JLabel("Pontos: 0");
 
    public JogoDaMemoria() {
        super("Jogo da Memória");
     
        Barra_Ferramenta.add(Button_Novo_Jogo);
        Barra_Ferramenta.add(Button_Re_Iniciar_Jogo);
        add(Barra_Ferramenta, BorderLayout.NORTH);
     
        for (int i=0; i<16; ++i){
            Escolha[i] = new JButton();
            Panel.add(Escolha[i]);
            Escolha[i].setFont(Fonte);
            Escolha[i].setVisible(true);
        }
     
        Panel.setLayout(Layout_do_Jogo);
        add(Panel, BorderLayout.CENTER);
     
        Barra_de_Status.add(Pontuacao_do_Jogador);
        add(Barra_de_Status, BorderLayout.SOUTH);
     
     
        Eventos_JogoDaMemoria Handler = new Eventos_JogoDaMemoria();
        for (int i=0; i<16; ++i){
            Escolha[i].addActionListener(Handler);
        }
        Button_Novo_Jogo.addActionListener(Handler);
        Button_Re_Iniciar_Jogo.addActionListener(Handler);

     
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);    
        this.setSize(500,500);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
 
    private class Eventos_JogoDaMemoria implements ActionListener{
     
        int Cont_Acertos,Primeiro_Click,Segundo_Click;
        int Numero_Click, posi, cont, pontos_Anterior, Maior_Pontuacao_do_Jogador;
        int Partidas_jogadas = 0, Numero_de_Vitorias = 0;
        boolean Novo_Jogo = true;
        boolean Re_Iniciar = false;
        boolean Fim_de_Jogo = false;                      
     
     
        public void actionPerformed(ActionEvent event){                      

         
            if (event.getSource() == Button_Novo_Jogo){
                Novo_Jogo = true;
                Re_Iniciar = false;
            }
         
            if (event.getSource() == Button_Re_Iniciar_Jogo){
                Novo_Jogo = true;
                Re_Iniciar = true;
            }
         
            if (Novo_Jogo == true){
             
                Cont_Acertos = 0;
                Partidas_jogadas++;
                pontos_Anterior = pontos;
                pontos = 0;
                Numero_Click = 0;
                posi = 0; cont = 16;
                Primeiro_Click = 0;
                Segundo_Click = 0;
             
                for (int i=0; i<16; ++i){
                    Escolha[i].setText("");
                    Escolha[i].setEnabled(true);
                }
             
                if (Re_Iniciar == false){
                 
                    for (int i=0; i<16; ++i){
                        Posicao_do_vetor_Aleatorio[i] = i;
                    }
     
                    for (int i=0; i<8; ++i){
                        for (int j=0; j<2; ++j){
                            posi = RandomNumber.nextInt(cont);
                            Aleatorio[Posicao_do_vetor_Aleatorio[posi]] = i;
                     
                            if (posi < cont){
                                for (int q=(posi+1); q<(cont); ++q){
                                    Posicao_do_vetor_Aleatorio[q-1] = Posicao_do_vetor_Aleatorio[q];
                                }
                            } cont--;
                        }
                    }
                }
                Novo_Jogo = false;
            }
         
            for (int i=0; i<16; ++i){
             
                if (event.getSource() == Escolha[i]){
                                   
                    Escolha[i].setText(String.valueOf(Aleatorio[i]));
                    Escolha[i].setEnabled(false);
                    Escolha[i].setVisible(true);
                    Numero_Click++;
                 
                    if (Numero_Click == 1) Primeiro_Click = i;
                        if (Numero_Click == 2){
                            Segundo_Click = i;
                            if (Aleatorio[Primeiro_Click] != Aleatorio[Segundo_Click]){                                                      
                                pontos-=3;
                                JOptionPane.showMessageDialog(JogoDaMemoria.this, "Errado");
                                Escolha[Primeiro_Click].setText("");
                                Escolha[Segundo_Click].setText("");
                                Escolha[Primeiro_Click].setEnabled(true);
                                Escolha[Segundo_Click].setEnabled(true);                            
                         
                            }  else {
                                Cont_Acertos++;
                                pontos+=5;
                        }
                        Numero_Click = 0;
                    }
                }
            }
         
         
            if (Cont_Acertos == 8){
                Numero_de_Vitorias++;
                Cont_Acertos = 0;
                if (pontos > pontos_Anterior) Maior_Pontuacao_do_Jogador = pontos;
                    Fim_de_Jogo = true;
            }
         
         
            if (pontos < 0) pontos = 0;
            Pontuacao_do_Jogador.setText("Pontos: " + pontos);
         
         
            if (Fim_de_Jogo == true){
                Estatisticas_Jogo(Partidas_jogadas, Numero_de_Vitorias, Maior_Pontuacao_do_Jogador);
                Fim_de_Jogo = false;
            }          
        }
    }
 
 
    void Estatisticas_Jogo(int Partidas_jogadas, int Numero_de_Vitorias, int Maior_Pontuacao_do_Jogador){

        JOptionPane.showMessageDialog(JogoDaMemoria.this, "Partidas jogadas: " + Partidas_jogadas +
         "\nVitórias: " + Numero_de_Vitorias +"\nMaior Pontuação do Jogador: " + Maior_Pontuacao_do_Jogador);
    }
 
 
    public static void main(String [] args){
     
        new JogoDaMemoria();
     
    }  
}