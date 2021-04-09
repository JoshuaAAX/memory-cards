package arcaDeNoe;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Carta extends JButton {
	
	private int idImage;
	private int abierta;
	private boolean estado;
	private ImageIcon image;
	
	
	
	public Carta(int idImage){
		this.estado = false;
		this.abierta =1;
		this.idImage = idImage;
	}
	
	


	public int getIdImage() {
		return idImage;
	}
	
	public boolean isEstado() {
		return estado;
	}
	

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public int getAbierta() {
		return abierta;
	}

	public void setAbierta(int abierta) {
		this.abierta = abierta;
	}

	
}
