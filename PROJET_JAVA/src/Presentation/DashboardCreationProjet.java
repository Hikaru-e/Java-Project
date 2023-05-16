package Presentation;

import javax.swing.JFrame;

import javafx.embed.swing.JFXPanel;

public class DashboardCreationProjet extends JFrame {

	private Controlleur controlleur;
	private JFXPanel CreationProjetPanel;
	private CreationProjetMenu menuProjet;

	public DashboardCreationProjet(Controlleur c) {
		super();
		controlleur = c;
		init();
	}
	
	public void init() {
		CreationProjetPanel = new JFXPanel();
		menuProjet = new CreationProjetMenu();
		CreationProjetPanel.setScene(menuProjet.getScene());
		this.add(CreationProjetPanel);
	}

}
