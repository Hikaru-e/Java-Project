package Presentation;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import Presentation.Menus.AccueilPanel;
import Presentation.Menus.DepartementPanel;
import Presentation.Menus.EntreprisePanel;
import Presentation.Menus.EtudiantPanel;
import Presentation.Menus.FilierePanel;
import Presentation.Menus.LaboratoirePanel;
import Presentation.Menus.ProfesseurPanel;
import Presentation.Menus.ProjetPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DashboardMenuCoordFiliere extends JFrame {
	private JButton accueilButton, etudiantsButton, projetsButton, entreprisesButton,Log_Out;
	private JPanel leftMenuPanel, rightMenuPanel;
	private CardLayout cardLayout;
	private Controlleur controlleur;
	
	private AccueilPanel accueilPanel;
	private EtudiantPanel panel_etd;
	private EntreprisePanel panel_entreprise;
	private ProjetPanel panel_projet;
	
	public DashboardMenuCoordFiliere(Controlleur c) {

		super();
		this.controlleur = c;
		this.initialiser();
		this.dessiner();
		this.action1();

	}

	public void initialiser() {
		// Set up the left-hand menu panel
		leftMenuPanel = new JPanel();
		rightMenuPanel = new JPanel();
		cardLayout = new CardLayout();

		// Set up the buttons
		accueilButton = new JButton("Accueil");
		etudiantsButton = new JButton("Gestion des étudiants");
		projetsButton = new JButton("Gestion des projets");
		entreprisesButton = new JButton("Gestion des entreprises");
		Log_Out=new JButton("Log Out");
		
		// here where to initiate all the panels for the buttons on left in the
		// dashboard
		accueilPanel = new AccueilPanel();
		panel_etd = new EtudiantPanel();
		panel_projet = new ProjetPanel();
		panel_entreprise = new EntreprisePanel();

	}

	public void dessiner() {

		leftMenuPanel.setPreferredSize(new Dimension(200, getHeight()));
		leftMenuPanel.setBackground(new Color(44, 62, 80));

		accueilButton.setPreferredSize(new Dimension(190, 50));
		accueilButton.setBackground(new Color(52, 73, 94));
		accueilButton.setForeground(Color.WHITE);

		etudiantsButton.setPreferredSize(new Dimension(190, 50));
		etudiantsButton.setBackground(new Color(52, 73, 94));
		etudiantsButton.setForeground(Color.WHITE);

		projetsButton.setPreferredSize(new Dimension(190, 50));
		projetsButton.setBackground(new Color(52, 73, 94));
		projetsButton.setForeground(Color.WHITE);

		entreprisesButton.setPreferredSize(new Dimension(190, 50));
		entreprisesButton.setBackground(new Color(52, 73, 94));
		entreprisesButton.setForeground(Color.WHITE);
		
		Log_Out.setPreferredSize(new Dimension(190, 20));
		Log_Out.setBackground(new Color(150, 0, 0));
		Log_Out.setForeground(Color.WHITE);

		// Add the buttons to the left-hand menu panel
		leftMenuPanel.add(Log_Out);
		leftMenuPanel.add(accueilButton);
		leftMenuPanel.add(etudiantsButton);
		leftMenuPanel.add(projetsButton);
		leftMenuPanel.add(entreprisesButton);

		rightMenuPanel.setLayout(cardLayout);

		rightMenuPanel.add(accueilPanel, "accueilPanel");
		rightMenuPanel.add(panel_etd, "EtudiantPanel");
		rightMenuPanel.add(panel_projet, "ProjetPanel");
		rightMenuPanel.add(panel_entreprise, "EntreprisePanel");

		// Add the left-hand menu panel and right to the main frame
		this.add(leftMenuPanel, BorderLayout.WEST);
		this.add(rightMenuPanel, BorderLayout.CENTER);

	}

	// button Gestion des étudiants
	public void action1() {

		// so basically repeat this with each gestionnaire button where just the name of
		// the button changes and also what the cardLayout will show
		
		Log_Out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(DashboardMenuCoordFiliere.this,
						"Êtes-vous sûr de vouloir quitter? " ,"Confirmation", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
				dispose();
				controlleur.demmarrerApplication();
				}
			}

		});
		
		accueilButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(rightMenuPanel, "accueilPanel");
			}

		});
		etudiantsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing
				cardLayout.show(rightMenuPanel, "EtudiantPanel");
			}

		}

		);
		projetsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing

				cardLayout.show(rightMenuPanel, "ProjetPanel");

			}

		}

		);
		entreprisesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing

				cardLayout.show(rightMenuPanel, "EntreprisePanel");

			}

		}

		);

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