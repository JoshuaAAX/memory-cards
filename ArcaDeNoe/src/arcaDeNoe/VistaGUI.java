package arcaDeNoe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import misComponentes.Titulos;



public class VistaGUI  extends JFrame {

	private Escucha escucha;
	private JPanel zonaJuego, zonaResultados;
	private List<JButton> botones = new ArrayList<JButton>();
	private JLabel puntos;
	private JTextField valorPuntos;
	private Titulos titulo;
	private JButton salir;
	private ImageIcon imagen;
	private ControlArcaDeNoe controlArcaDeNoe;
	
	private int contador = 0 ;
	private int indexCard1=0;   
	private int indexCard2=0;   
	private Temporizador temporizador = new Temporizador();
	
	private JFrame vistaGridBagLayout;
	
	
	public VistaGUI() 
	{
	    initGUI();
	
	    this.setUndecorated(true);
	    this.setTitle("Juego Del Arca De Noe");
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGUI() {
		
		//set up window container - Layout
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints contraints = new GridBagConstraints();
		
		//create Control, Listener and supports classes
		escucha = new Escucha();
		controlArcaDeNoe = new ControlArcaDeNoe();
		vistaGridBagLayout=this;
	
		
		//set up Graphic Components
		
	    //title
		titulo = new Titulos(" Juego Del Arca de noe ", 17, new Color(56,57,59));
		titulo.addMouseListener(escucha);
		titulo.addMouseMotionListener(escucha);
		contraints.gridx=0;
		contraints.gridy=0;	
		contraints.gridwidth=1;
		contraints.fill=GridBagConstraints.HORIZONTAL;
		
		add(titulo,contraints);
		
		
		//exit
		salir = new JButton();
		imagen = new ImageIcon("src/imagenes/salir.png");
        salir.setIcon(imagen);
		salir.setBorder(null);
		salir.setFocusPainted(false);
        salir.setBackground(new Color(56,57,59));
		salir.addActionListener(escucha);
		contraints.gridx=1;
		contraints.gridy=0;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		
		add(salir, contraints);

		
		//game zone
		zonaJuego = new JPanel();
		zonaJuego.setLayout(new GridLayout(2, 6));
		
		
		boardCards();
		
        contraints.gridx=0;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.CENTER;
		
		add(zonaJuego,contraints);
		
		
		//results zone
		zonaResultados = new JPanel();
		zonaResultados.setLayout(new GridLayout(0,1));
		puntos = new JLabel("Puntos");
		puntos.setHorizontalAlignment(JLabel.CENTER);
		valorPuntos = new JTextField();
		valorPuntos.setHorizontalAlignment(JLabel.CENTER);
		valorPuntos.setEditable(false);
		valorPuntos.setText(String.valueOf(controlArcaDeNoe.getPuntos()));
		zonaResultados.add(puntos);
		zonaResultados.add(valorPuntos);
		contraints.gridx=1;
		contraints.gridy=1;
		contraints.gridwidth=1;
		contraints.gridheight=1;
		contraints.fill=GridBagConstraints.NONE;
		contraints.anchor=GridBagConstraints.PAGE_START;
		
		add(zonaResultados,contraints);
			
	}
	
	
	private void addCard() {
		if(botones.size()<12) {
			botones.add(new JButton());
			botones.get(botones.size()-1).setBorder(null);
			botones.get(botones.size()-1).setFocusPainted(false);
			zonaJuego.add(botones.get(botones.size()-1));
		}
		
	}
	
	
	private void boardCards() {
		
		for(int index = 0; index<controlArcaDeNoe.Size() ; index++ ) {
			botones.add(new JButton());
				
			ImageIcon cartas = new ImageIcon("src/imagenes/reverse.png");
			botones.get(index).setIcon(cartas);
			botones.get(index).setBorder(null);
			botones.get(index).setFocusPainted(false);
				
			zonaJuego.add(botones.get(index));
		}	
		agregarEscuchas();	
		
	}

	
	
	private void agregarEscuchas() {

		for(int index = 0; index<controlArcaDeNoe.Size() ; index++ ) {//
			botones.get(index).addActionListener(escucha);
		}	
		
	}
	
	
	private void newBoard() {	
		

		indexCard1=0;   
		indexCard2=0; 
		contador=0;
		addCard();
		addCard();
		
		for(int index = 0; index<controlArcaDeNoe.Size(); index++ ) {
			
			ImageIcon cartas = new ImageIcon("src/imagenes/reverse.png");
			botones.get(index).setIcon(cartas);
		}	
		
		agregarEscuchas();
		
	    pack();
	    this.setLocationRelativeTo(null);

	}
	
	                                                                                           
	private void voltear(int index)                                                                     
	{    
		
		//System.out.print(contador);                                                                                            
		if(contador==0 &&  controlArcaDeNoe.getEstadoCard(index)==false) {                                     
                                                                                                   
			imagen = new ImageIcon("src/imagenes/"+ controlArcaDeNoe.getCard(index)+".png");                   
			botones.get(index).setIcon(imagen);                                                         
			botones.get(index).removeActionListener(escucha);                                          
			indexCard1= index;   

			
			contador++;                                                                             
		}                                                                                           
		else if(contador==1 && controlArcaDeNoe.getEstadoCard(index)==false) {                                
                                                                                                   
            //System.out.println("quiero morir");                                                                                       
			imagen = new ImageIcon("src/imagenes/"+controlArcaDeNoe.getCard(index)+".png");                   
			botones.get(index).setIcon(imagen);                                                         
			botones.get(index).removeActionListener(escucha);                                          
			indexCard2= index;                                                                          
			
			contador++;                                                                             
			                                                                                        
			controlArcaDeNoe.cartasIguales(indexCard1,indexCard2);
			
			valorPuntos.setText(String.valueOf(controlArcaDeNoe.getPuntos()));
			
			temporizador.tiempo();
			                                                                                                                                                                                                                                                  
		}                                                                                           
	                                                                                                
   }                                                                                                   
                                                                                                   
	
	private class Escucha extends MouseAdapter implements ActionListener {

		private int x, y;
		@Override
		public void actionPerformed(ActionEvent eventAction) {
			
			if(eventAction.getSource()==salir) {
				System.exit(0);
			}

			for(int unBoton = 0; unBoton<controlArcaDeNoe.Size(); unBoton++) {              
				
				if(eventAction.getSource() == botones.get(unBoton)) {                                     
            
					voltear(unBoton);                                                                                                                                           		                                                                            
			}   	
			
		}
	
	  }
		
	  public void mouseDragged(MouseEvent eventMouseMotion) 
	  {
			// TODO Auto-generated method stub
			setLocation(vistaGridBagLayout.getLocation().x+eventMouseMotion.getX()-x,
				        vistaGridBagLayout.getLocation().y+eventMouseMotion.getY()-y);
			
	  }
	  
	  public void mousePressed(MouseEvent eventMouse) 
		{
			// TODO Auto-generated method stub
			x = eventMouse.getX();
			y = eventMouse.getY();
			
		}
	
   }
	
	
   public class Temporizador {
	
		public void tiempo() {
			Timer timer = new Timer();
			TimerTask voltearTiempo = new TimerTask() {

				public void run() {
	 
					if (controlArcaDeNoe.getEstadoCard(indexCard1)==false && controlArcaDeNoe.getEstadoCard(indexCard2)==false && contador==2 )
					{
						//System.out.println("hola");
						imagen = new ImageIcon("src/imagenes/reverse.png");
						botones.get(indexCard1).setIcon(imagen);
						botones.get(indexCard2).setIcon(imagen);
						contador = 0;
						botones.get(indexCard1).addActionListener(escucha);	
						botones.get(indexCard2).addActionListener(escucha);	
						
						controlArcaDeNoe.getCardOnly(indexCard1).setAbierta(controlArcaDeNoe.getCardOnly(indexCard1).getAbierta()+1);
						controlArcaDeNoe.getCardOnly(indexCard2).setAbierta(controlArcaDeNoe.getCardOnly(indexCard2).getAbierta()+1);
				       
						System.out.println(controlArcaDeNoe.getCardOnly(indexCard1).getAbierta());
						System.out.println(controlArcaDeNoe.getCardOnly(indexCard2).getAbierta());
						

					} else {
                        
					    imagen = new ImageIcon("src/imagenes/null.png");
						botones.get(indexCard1).setIcon(imagen);
						botones.get(indexCard2).setIcon(imagen);
						contador=0;
						controlArcaDeNoe.determinarJuego();
						
						
						if(controlArcaDeNoe.getEstadoJuego() == 2) {
							
							//controlArcaDeNoe.recorre();
							controlArcaDeNoe.resetBoard();
							//controlArcaDeNoe.recorre();
							newBoard();	
							
						}	
					}
				}
			};
			
			timer.schedule(voltearTiempo,600);
			}

		}	
	
}