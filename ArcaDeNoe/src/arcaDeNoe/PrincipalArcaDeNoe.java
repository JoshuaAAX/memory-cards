package arcaDeNoe;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class PrincipalArcaDeNoe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			String javaLookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(javaLookAndFeel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Opps hay una daño en la JVM");
		} 
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				VistaGUI myVista = new VistaGUI();
			}	
		});

	}

}