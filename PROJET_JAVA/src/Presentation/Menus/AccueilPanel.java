package Presentation.Menus;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AccueilPanel extends JPanel {

	private AccueilPanel_1 accueil1;
	private AccueilPanel_2 accueil2;
	private JButton next1, next2;
	private JPanel buttonsPanel1, buttonsPanel2, outerPanel1, outerPanel2;
	private CardLayout cardLayout;

	public AccueilPanel() {
		init();
		dessiner();
		action();

	}

	private void init() {

		cardLayout = new CardLayout();

		accueil1 = new AccueilPanel_1();
		accueil2 = new AccueilPanel_2();

		next1 = new JButton(new ImageIcon(EtudiantPanel.class.getResource("/images/next.png")));
		next1.setBackground(new Color(242,242,242));
		
		next2 = new JButton(new ImageIcon(EtudiantPanel.class.getResource("/images/next.png")));
		next2.setBackground(new Color(242,242,242));

		buttonsPanel1 = new JPanel();
		buttonsPanel2 = new JPanel();
		outerPanel1 = new JPanel();
		outerPanel2 = new JPanel();

	}

	private void dessiner() {

		buttonsPanel1.add(next1);
		buttonsPanel1.setBorder(new EmptyBorder(0, 0, 50, 0));

		buttonsPanel2.add(next2);
		buttonsPanel2.setBorder(new EmptyBorder(0, 0, 50, 0));

		outerPanel1.setLayout(new BorderLayout());
		outerPanel1.add(accueil1, BorderLayout.CENTER);
		outerPanel1.add(buttonsPanel1, BorderLayout.SOUTH);

		outerPanel2.setLayout(new BorderLayout());
		outerPanel2.add(accueil2, BorderLayout.CENTER);
		outerPanel2.add(buttonsPanel2, BorderLayout.SOUTH);

		this.setLayout(cardLayout);
		this.add(outerPanel1, "accueil1");
		this.add(outerPanel2, "accueil2");
	}

	private void action() {
		next1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(AccueilPanel.this, "accueil2");

			}
		});
		next2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(AccueilPanel.this, "accueil1");

			}
		});

	}

}
