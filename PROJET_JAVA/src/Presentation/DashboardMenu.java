package Presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

import Presentation.Menus.AccueilPanel;
import Presentation.Menus.DepartementPanel;
import Presentation.Menus.EntreprisePanel;
import Presentation.Menus.EtudiantPanel;
import Presentation.Menus.FilierePanel;
import Presentation.Menus.LaboratoirePanel;
import Presentation.Menus.ProfesseurPanel;
import Presentation.Menus.ProjetPanel;

public class DashboardMenu extends JFrame {
	private JButton accueilButton, etudiantsButton, professeursButton, projetsButton, departementsButton,
			filieresButton, laboratoiresButton, entreprisesButton,Log_Out;

	private JPanel leftMenuPanel, rightMenuPanel;
	private CardLayout cardLayout;

	// you create the panels here for each button
	private AccueilPanel accueilPanel;
	private EtudiantPanel panel_etd;
	private ProfesseurPanel panel_prf;
	private ProjetPanel panel_projet;
	private DepartementPanel panel_deprt;
	private FilierePanel panel_filiere;
	private LaboratoirePanel panel_labo;
	private EntreprisePanel panel_entreprise;
	private Controlleur controlleur;

	public DashboardMenu(Controlleur c) {
		super();
		controlleur = c;
		this.initialiser();
		this.dessiner();
		this.action();

	}

	public void initialiser() {
		// Set up the left and right-hand menu panel
		leftMenuPanel = new JPanel();
		rightMenuPanel = new JPanel();
		cardLayout = new CardLayout();
		// Set up the buttons
		accueilButton = new JButton("Accueil");
		etudiantsButton = new JButton("Gestion des étudiants");
		professeursButton = new JButton("Gestion des professeurs");
		projetsButton = new JButton("Gestion des projets");
		departementsButton = new JButton("Gestion des départements");
		filieresButton = new JButton("Gestion des filières");
		laboratoiresButton = new JButton("Gestion des laboratoires");
		entreprisesButton = new JButton("Gestion des entreprises");
		Log_Out=new JButton("Log Out");

		// here where to initiate all the panels for the buttons on left in the
		// dashboard
		accueilPanel = new AccueilPanel();
		panel_etd = new EtudiantPanel();
		panel_prf = new ProfesseurPanel();
		panel_projet = new ProjetPanel();
		panel_deprt = new DepartementPanel();
		panel_filiere = new FilierePanel();
		panel_labo = new LaboratoirePanel();
		panel_entreprise = new EntreprisePanel();

	}

	public void dessiner() {

		// Set up the left-hand menu panel
		leftMenuPanel.setPreferredSize(new Dimension(200, getHeight()));
		leftMenuPanel.setBackground(new Color(44, 62, 80));

		// Set up the buttons
		accueilButton.setPreferredSize(new Dimension(190, 50));
		accueilButton.setBackground(new Color(52, 73, 94));
		accueilButton.setForeground(Color.WHITE);

		etudiantsButton.setPreferredSize(new Dimension(190, 50));
		etudiantsButton.setBackground(new Color(52, 73, 94));
		etudiantsButton.setForeground(Color.WHITE);

		professeursButton.setPreferredSize(new Dimension(190, 50));
		professeursButton.setBackground(new Color(52, 73, 94));
		professeursButton.setForeground(Color.WHITE);

		projetsButton.setPreferredSize(new Dimension(190, 50));
		projetsButton.setBackground(new Color(52, 73, 94));
		projetsButton.setForeground(Color.WHITE);

		departementsButton.setPreferredSize(new Dimension(190, 50));
		departementsButton.setBackground(new Color(52, 73, 94));
		departementsButton.setForeground(Color.WHITE);

		filieresButton.setPreferredSize(new Dimension(190, 50));
		filieresButton.setBackground(new Color(52, 73, 94));
		filieresButton.setForeground(Color.WHITE);

		laboratoiresButton.setPreferredSize(new Dimension(190, 50));
		laboratoiresButton.setBackground(new Color(52, 73, 94));
		laboratoiresButton.setForeground(Color.WHITE);

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
		leftMenuPanel.add(professeursButton);
		leftMenuPanel.add(projetsButton);
		leftMenuPanel.add(departementsButton);
		leftMenuPanel.add(filieresButton);
		leftMenuPanel.add(laboratoiresButton);
		leftMenuPanel.add(entreprisesButton);

//		JFXPanel fxPanel = new JFXPanel();
//		test111 javaFXApp = new test111();
//		Main2 javaFXApp = new Main2();
//		fxPanel.setScene(javaFXApp.getScene());
		
		// here where to add each gestionnaire panel, see CardLayoutORIGINAl to
		// understand how card layouts work

		rightMenuPanel.setLayout(cardLayout);

//		rightMenuPanel.add(fxPanel, "hh");
		rightMenuPanel.add(accueilPanel, "accueilPanel");
		rightMenuPanel.add(panel_etd, "EtudiantPanel");
		rightMenuPanel.add(panel_prf, "ProfesseurPanel");
		rightMenuPanel.add(panel_projet, "ProjetPanel");
		rightMenuPanel.add(panel_deprt, "DepartementPanel");
		rightMenuPanel.add(panel_filiere, "filierePanel");
		rightMenuPanel.add(panel_labo, "laboratoirePanel");
		rightMenuPanel.add(panel_entreprise, "EntreprisePanel");

		this.add(leftMenuPanel, BorderLayout.WEST);
		this.add(rightMenuPanel, BorderLayout.CENTER);
	}

	// button Gestion des étudiants
	public void action() {

		// so basically repeat this with each gestionnaire button where just the name of
		// the button changes and also what the cardLayout will show

		Log_Out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(DashboardMenu.this,
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
		});
		professeursButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing
				cardLayout.show(rightMenuPanel, "ProfesseurPanel");
			}

		});

		projetsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing

				cardLayout.show(rightMenuPanel, "ProjetPanel");
			}
		});

		departementsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing

				cardLayout.show(rightMenuPanel, "DepartementPanel");
			}
		});

		filieresButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing
				cardLayout.show(rightMenuPanel, "filierePanel");
			}
		});

		entreprisesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing
				cardLayout.show(rightMenuPanel, "EntreprisePanel");
			}
		});

		laboratoiresButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// show the card layout thing
				cardLayout.show(rightMenuPanel, "laboratoirePanel");
			}
		});

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
