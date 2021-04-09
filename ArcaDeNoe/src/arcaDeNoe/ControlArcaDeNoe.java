package arcaDeNoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlArcaDeNoe {
	
	private int identificador;
	private int estadoJuego; 
	private int puntos;
	private int tiro;
	private ArrayList<Carta> cards = new ArrayList<Carta>();
	
	
	public ControlArcaDeNoe() {
		this.identificador=0;
		this.puntos=0;
		this.tiro = 1;
		createBoard();
	
	}
	
	
	public void recorre() {
		
		for(Carta carta: cards ) {
			System.out.println(carta.isEstado());
		}

	}
	
	
	
	public void createBoard() {
		
		for(int index = 0; index < 4 ; index = index + 2) {
			cards.add(new Carta(identificador));
			cards.add(new Carta(identificador));
			identificador++;
		}
		shuffle(cards);
		
	}
	
	
	public void shuffle(List<Carta> tarjetas) {
		Collections.shuffle(tarjetas);
	}
	
	
    public void resetBoard() {
		
		estadoJuego = 0;
		tiro = 1;
		
		addCards();
	    
		for(Carta carta: cards) {
			if(carta.isEstado()) {
				carta.setEstado(false);
			}
		}
		
		shuffle(cards);
		
		for(Carta carta: cards) {
			carta.setAbierta(1);
		}
		
	}
    
	
	public void addCards() {
		
		if(cards.size()<12) {
			cards.add(new Carta(identificador));
			cards.add(new Carta(identificador));
			identificador++;
		}		
	}
	
	
	public void cartasIguales(int indexCard1, int indexCard2) {
		if ( (getCard(indexCard1) == getCard(indexCard2)) &&  (indexCard1!=indexCard2) ) {
			
			cards.get(indexCard1).setEstado(true);
			cards.get(indexCard2).setEstado(true);
			
			puntos++;
			
		}else {
			
			cards.get(indexCard1).setEstado(false);
			cards.get(indexCard2).setEstado(false);
			
			if(cards.get(indexCard1).getAbierta()>1 || cards.get(indexCard2).getAbierta()>1) {
				puntos--;
			}
			tiro++;
		}

	}
	
	
	public void determinarJuego()
	{
		for(int index = 0; index<cards.size(); index++) 
		{
			if (cards.get(index).isEstado() == false) 
			{
				estadoJuego = 1;
				index = cards.size();
				
			}else if ( index + 1 == cards.size() && cards.get(cards.size()-1).isEstado() == true)
			{
				estadoJuego = 2;
			}
			
		}
	}


	public int getEstadoJuego() {
		return estadoJuego;
	}

	
	public int getCard(int index) 
	{
		return cards.get(index).getIdImage();
	}
	
	
	public boolean getEstadoCard(int index) 
	{
		return cards.get(index).isEstado();
	}
	
	
	public int Size() {
		return cards.size();
	}
	
	
	public int getPuntos() {
		return puntos;
	}
	
	public Carta getCardOnly(int index) 
	{
		return cards.get(index);
	}

	
}
