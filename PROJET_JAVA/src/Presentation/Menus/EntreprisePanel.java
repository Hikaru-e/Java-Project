package Presentation.Menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Metier.Entreprise;
import Metier.Gestionnaires.Gestionnaire_Entreprise;

public class EntreprisePanel extends JPanel {

	private DefaultTableModel model;
	private JPanel buttonsPanel, upperbuttonsPanel;
	private ArrayList<Entreprise> entreprises;
	private Gestionnaire_Entreprise gestionnaire_entreprise = new Gestionnaire_Entreprise();
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private JButton ajouterButton, supprimerButton, modifierButton, searchButton;

	/// to imnplement
	private JTextField searchField;
	private TableRowSorter<DefaultTableModel> sorter;

	public EntreprisePanel() {
		super();
		initialiser();
		dessiner();
		action();
	}

	public void initialiser() {
		buttonsPanel = new JPanel();
		upperbuttonsPanel = new JPanel();

		model = new DefaultTableModel() {
			// override isCellEditable to make specific cells non-editable
			@Override
			public boolean isCellEditable(int row, int column) {
				// make all cells non-editable
				return false;
			}
		};

		sorter = new TableRowSorter<>(model);
		table = new JTable(model);
		table.setRowSorter(sorter);
		scrollPane = new JScrollPane(table);

		supprimerButton = new JButton(new ImageIcon(EtudiantPanel.class.getResource("/images/delete.png")));
		supprimerButton.setText("Supprimer");

		ajouterButton = new JButton(new ImageIcon(EtudiantPanel.class.getResource("/images/add.png")));
		ajouterButton.setText("Ajouter");

		modifierButton = new JButton(new ImageIcon(EtudiantPanel.class.getResource("/images/edit.png")));
		modifierButton.setText("Modifier");

		searchButton = new JButton("Rechercher");
		searchField = new JTextField(26);
	}

	private void dessiner() {
		model.addColumn("ID");
		model.addColumn("Raison sociale");
		model.addColumn("Adresse");
		model.addColumn("Tele");
		model.addColumn("Email");
		model.addColumn("Responsable");

		entreprises = gestionnaire_entreprise.getAllEntreprises();

		// Ajouter les données dans le modèle de table
		for (Entreprise Entreprise : entreprises) {
			Object[] rowData = { Entreprise.getId_ent(), Entreprise.getRaisone_sociale(), Entreprise.getAdresse_ent(),
					Entreprise.getTele_ent(), Entreprise.getEmail_ent(), Entreprise.getResponsable_ent() };
			model.addRow(rowData);
		}

		// Ajouter le JScrollPane contenant la JTable au JPanel

		// Créer un JPanel pour les boutons
		// ***********************************************************
		// hna fin tzid buttons dial lta7t

		buttonsPanel.add(ajouterButton);
		buttonsPanel.add(supprimerButton);
		buttonsPanel.add(modifierButton);

		// ***********************************************************

		searchField.setPreferredSize(new Dimension(20, 20));

		// ***********************************************************
		// hna fin tzid buttons dial lfo9
		upperbuttonsPanel.add(searchField, new FlowLayout(FlowLayout.CENTER));
		upperbuttonsPanel.add(searchButton, new FlowLayout(FlowLayout.CENTER));
		// ***********************************************************
		buttonsPanel.setBorder(new EmptyBorder(0, 200, 10, 200));
		upperbuttonsPanel.setBorder(new EmptyBorder(10, 200, 0, 200));
		scrollPane.setBorder(new EmptyBorder(10, 50, 0, 50));

		this.setLayout(new BorderLayout());

		this.add(upperbuttonsPanel, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(buttonsPanel, BorderLayout.SOUTH);

	}

	private void action() {
		supprimerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Obtenir l'index de la ligne sélectionnée dans la JTable
				int row = table.getSelectedRow();

				// Vérifier que l'utilisateur a sélectionné une ligne
				if (row == -1) {
					JOptionPane.showMessageDialog(EntreprisePanel.this,
							"Veuillez sélectionner une ligne pour supprimer.");
					return;
				}

				// Obtenir le CNE de l'étudiant à supprimer à partir de la ligne sélectionnée
				String id = (String) table.getValueAt(row, 0);

				// Demander à l'utilisateur de confirmer la suppression
				int response = JOptionPane.showConfirmDialog(EntreprisePanel.this,
						"Êtes-vous sûr de vouloir supprimer " + "l'enreprise avec le ID " + id + " ?",
						"Confirmation de la suppression", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					// Supprimer l'étudiant de la base de données
					boolean success = gestionnaire_entreprise.supprimerEntreprise(id);
					if (success) {
						JOptionPane.showMessageDialog(EntreprisePanel.this, "L'entreprise a été supprimé avec succès.");
						// Rafraîchir la JTable pour afficher les nouveaux résultats
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(EntreprisePanel.this,
								"Une erreur s'est produite lors de la suppression" + " de l'entreprise.");
					}
				}

			}
		});

		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Créer une boîte de dialogue pour saisir les informations de l'étudiant
				JDialog dialog = new JDialog();
				dialog.setTitle("Ajouter une entreprise");
				JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
				panel.add(new JLabel("ID:"));
				JTextField IdField = new JTextField();
				panel.add(IdField);
				panel.add(new JLabel("Raison sociale:"));
				JTextField RaisonSocialeField = new JTextField();
				panel.add(RaisonSocialeField);
				panel.add(new JLabel("Adresse:"));
				JTextField AdresseField = new JTextField();
				panel.add(AdresseField);
				panel.add(new JLabel("Tele:"));
				JTextField TeleField = new JTextField();
				panel.add(TeleField);
				panel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField();
				panel.add(emailField);
				panel.add(new JLabel("Responsable:"));
				JTextField ResponsableField = new JTextField();
				panel.add(ResponsableField);
				panel.setBorder(new EmptyBorder(10, 30, 10, 30));
				dialog.add(panel, BorderLayout.CENTER);

				// Ajouter un bouton dans le dialog pour ajouter l'étudiant
				JButton ajouterButton = new JButton("Ajouter");
				ajouterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Entreprise Entreprise = new Entreprise(IdField.getText(), RaisonSocialeField.getText(),
								AdresseField.getText(), TeleField.getText(), emailField.getText(),
								ResponsableField.getText());
						boolean success = gestionnaire_entreprise.ajouterEntreprise(Entreprise);
						if (success) {
							JOptionPane.showMessageDialog(EntreprisePanel.this, "Entrprise ajouté avec succès");
							dialog.dispose();
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(EntreprisePanel.this,
									"Une erreur s'est produite lors de l'ajout de l'enreprise");
						}
					}
				});
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(ajouterButton);
				dialog.add(buttonPanel, BorderLayout.SOUTH);

				dialog.pack();
				dialog.setLocationRelativeTo(EntreprisePanel.this);
				dialog.setVisible(true);
			}
		});

		modifierButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Récupérer l'entreprise sélectionné dans la JTable
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex < 0) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un entreprise à modifier.");
					return;
				}
				String id = (String) table.getValueAt(selectedRowIndex, 0);
				Entreprise entreprise = gestionnaire_entreprise.getEntrepriseById(id);
				if (entreprise == null) {
					JOptionPane.showMessageDialog(null, "L'entreprise sélectionné n'existe pas.");
					return;
				}

				// Afficher une boîte de dialogue pour modifier les informations de l'entreprise
				JPanel dialogPanel = new JPanel(new GridLayout(0, 1));
				dialogPanel.add(new JLabel("ID:"));
				JTextField IdField = new JTextField(entreprise.getId_ent());
				dialogPanel.add(IdField);
				dialogPanel.add(new JLabel("Raison sociale:"));
				JTextField RaisonSocialeField = new JTextField(entreprise.getRaisone_sociale());
				dialogPanel.add(RaisonSocialeField);
				dialogPanel.add(new JLabel("Adresse:"));
				JTextField AdresseField = new JTextField(entreprise.getAdresse_ent());
				dialogPanel.add(AdresseField);
				dialogPanel.add(new JLabel("Tele:"));
				JTextField TeleField = new JTextField(entreprise.getTele_ent());
				dialogPanel.add(TeleField);
				dialogPanel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField(entreprise.getEmail_ent());
				dialogPanel.add(emailField);
				dialogPanel.add(new JLabel("Responsable:"));
				JTextField ResponsableField = new JTextField(entreprise.getResponsable_ent());
				dialogPanel.add(ResponsableField);

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Modifier un entreprise",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					// Mettre à jour les informations de l'entreprise dans la base de données
					entreprise.setId_ent(IdField.getText());
					entreprise.setAdresse_ent(AdresseField.getText());
					entreprise.setEmail_ent(emailField.getText());
					entreprise.setRaisone_sociale(RaisonSocialeField.getText());
					entreprise.setResponsable_ent(ResponsableField.getText());
					entreprise.setTele_ent(TeleField.getText());

					boolean success = gestionnaire_entreprise.modifierEntreprise(entreprise);
					if (success) {
						JOptionPane.showMessageDialog(null, "L'entreprise a été modifié avec succès.");
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(null,
								"Une erreur s'est produite lors de la modification de l'entreprise.");
					}
				}

			}

		});

		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = searchField.getText();
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
				table.setRowSorter(sorter);
				sorter.setRowFilter(RowFilter.regexFilter(searchText));
			}
		});

	}

	private void refreshTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Effacer les lignes existantes
		ArrayList<Entreprise> entreprises = gestionnaire_entreprise.getAllEntreprises();
		for (Entreprise Entreprise : entreprises) {
			Object[] rowData = { Entreprise.getId_ent(), Entreprise.getRaisone_sociale(), Entreprise.getAdresse_ent(),
					Entreprise.getTele_ent(), Entreprise.getEmail_ent(), Entreprise.getResponsable_ent() };
			model.addRow(rowData);
		}

	}

}
