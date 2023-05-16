package Presentation;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import javafx.embed.swing.JFXPanel;

public class DashboardEtudiant extends JFrame {

	private Controlleur controlleur;
	private JFXPanel CreationEtudiantPanel;
	private EtudiantPanel menuEtudiant;

	public DashboardEtudiant(Controlleur c) {
		super();
		this.controlleur = c;
		init();
		action();
	}

	public void init() {
		CreationEtudiantPanel = new JFXPanel();
		menuEtudiant = new EtudiantPanel();
		CreationEtudiantPanel.setScene(menuEtudiant.getScene());

		this.add(CreationEtudiantPanel);
	}

	private void action() {
		// To change theme
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				try {
					// Set the selected theme using UIManager
					UIManager.setLookAndFeel(new FlatLightLaf());
					SwingUtilities.updateComponentTreeUI(rootPane);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

	}
}
