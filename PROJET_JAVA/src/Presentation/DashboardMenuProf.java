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

import Presentation.Menus.AccueilPanel_1;
import javafx.embed.swing.JFXPanel;

public class DashboardMenuProf extends JFrame {
	private JButton accueilButton, CreationDesProjetsButton, AvancementDesProjetsButton, Log_Out;
	private Controlleur controlleur;
	private JPanel leftMenuPanel, rightMenuPanel;
	private JFXPanel CreationProjetPanel;
	private CreationProjetMenu menuProjet;

	private AccueilPanel_1 accueilPanel;

	private CardLayout cardLayout;

	public DashboardMenuProf(Controlleur c) {

		this.controlleur = c;
		init();
		dessiner();
		action();
	}

	private void init() {
		Log_Out = new JButton("Log Out");
		rightMenuPanel = new JPanel();
		cardLayout = new CardLayout();
		leftMenuPanel = new JPanel();

		CreationProjetPanel = new JFXPanel();
		menuProjet = new CreationProjetMenu();

		accueilButton = new JButton("Accueil");
		CreationDesProjetsButton = new JButton("Creation des Projets");
		AvancementDesProjetsButton = new JButton("Avancement des projets");
		Log_Out=new JButton("Log Out");

		accueilPanel = new AccueilPanel_1();
	}

	private void dessiner() {
		leftMenuPanel.setPreferredSize(new Dimension(200, getHeight()));
		leftMenuPanel.setBackground(new Color(44, 62, 80));
		// Set up the left-hand menu panel

		// Set up the buttons

		accueilButton.setPreferredSize(new Dimension(190, 50));
		accueilButton.setBackground(new Color(52, 73, 94));
		accueilButton.setForeground(Color.WHITE);

		CreationDesProjetsButton.setPreferredSize(new Dimension(190, 50));
		CreationDesProjetsButton.setBackground(new Color(52, 73, 94));
		CreationDesProjetsButton.setForeground(Color.WHITE);

		AvancementDesProjetsButton.setPreferredSize(new Dimension(190, 50));
		AvancementDesProjetsButton.setBackground(new Color(52, 73, 94));
		AvancementDesProjetsButton.setForeground(Color.WHITE);

		Log_Out.setPreferredSize(new Dimension(190, 20));
		Log_Out.setBackground(new Color(150, 0, 0));
		Log_Out.setForeground(Color.WHITE);

		// Add the buttons to the left-hand menu panel
		leftMenuPanel.add(Log_Out);
		leftMenuPanel.add(accueilButton);
		leftMenuPanel.add(CreationDesProjetsButton);
		leftMenuPanel.add(AvancementDesProjetsButton);

		CreationProjetPanel.setScene(menuProjet.getScene());

		rightMenuPanel.setLayout(cardLayout);

		rightMenuPanel.add(accueilPanel, "accueilPanel");
		rightMenuPanel.add(CreationProjetPanel, "projetPanel");

		// Add the left-hand menu panel to the main frame
		this.add(leftMenuPanel, BorderLayout.WEST);
		this.add(rightMenuPanel, BorderLayout.CENTER);
	}

	private void action() {
		Log_Out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(DashboardMenuProf.this,
						"Êtes-vous sûr de vouloir quitter? ", "Confirmation", JOptionPane.YES_NO_OPTION);
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

		CreationDesProjetsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(rightMenuPanel, "projetPanel");
			}

		});
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
