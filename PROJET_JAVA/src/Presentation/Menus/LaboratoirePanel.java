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

import Metier.Laboratoire;
import Metier.Gestionnaires.Gestionnaire_Laboratoire;

public class LaboratoirePanel extends JPanel {

	private DefaultTableModel model;
	private JPanel buttonsPanel, upperbuttonsPanel;
	private ArrayList<Laboratoire> Laboratoires;
	private Gestionnaire_Laboratoire gestionnaire_labo = new Gestionnaire_Laboratoire();
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private JButton ajouterButton, supprimerButton, modifierButton, searchButton;

	private JTextField searchField;
	private TableRowSorter<DefaultTableModel> sorter;

	public LaboratoirePanel() {
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
		model.addColumn("Nom");
		model.addColumn("Email");

		Laboratoires = gestionnaire_labo.getAllLaboratoires();

		// Ajouter les données dans le modèle de table
		for (Laboratoire laboratoire : Laboratoires) {
			Object[] rowData = { laboratoire.getId_lab(), laboratoire.getNom_lab(), laboratoire.getEmail_lab() };
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
					JOptionPane.showMessageDialog(LaboratoirePanel.this,
							"Veuillez sélectionner une ligne pour supprimer.");
					return;
				}

				// Obtenir le CNE de laboratoire à supprimer à partir de la ligne sélectionnée
				String id = (String) table.getValueAt(row, 0);

				// Demander à l'utilisateur de confirmer la suppression
				int response = JOptionPane.showConfirmDialog(LaboratoirePanel.this,
						"Êtes-vous sûr de vouloir supprimer " + "laboratoire avec le ID " + id + " ?",
						"Confirmation de la suppression", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					// Supprimer laboratoire de la base de données
					boolean success = gestionnaire_labo.supprimerLaboratoire(id);
					if (success) {
						JOptionPane.showMessageDialog(LaboratoirePanel.this, "laboratoire a été supprimé avec succès.");
						// Rafraîchir la JTable pour afficher les nouveaux résultats
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(LaboratoirePanel.this,
								"Une erreur s'est produite lors de la suppression" + " de laboratoire.");
					}
				}

			}
		});

		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Créer une boîte de dialogue pour saisir les informations de laboratoire
				JDialog dialog = new JDialog();
				dialog.setTitle("Ajouter un Laboratoire");
				JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
				panel.add(new JLabel("ID:"));
				JTextField IdField = new JTextField();
				panel.add(IdField);
				panel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField();
				panel.add(nomField);
				panel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField();
				panel.add(emailField);
				panel.setBorder(new EmptyBorder(10, 30, 10, 30));
				dialog.add(panel, BorderLayout.CENTER);

				// Ajouter un bouton dans le dialog pour ajouter laboratoire
				JButton ajouterButton = new JButton("Ajouter");
				ajouterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Laboratoire Laboratoire = new Laboratoire(IdField.getText(), nomField.getText(),
								emailField.getText());
						boolean success = gestionnaire_labo.ajouterLaboratoire(Laboratoire);
						if (success) {
							JOptionPane.showMessageDialog(LaboratoirePanel.this, "Laboratoire ajouté avec succès");
							dialog.dispose();
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(LaboratoirePanel.this,
									"Une erreur s'est produite lors de l'ajout de laboratoire");
						}
					}
				});
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(ajouterButton);
				
				dialog.add(buttonPanel, BorderLayout.SOUTH);

				dialog.pack();
				dialog.setLocationRelativeTo(LaboratoirePanel.this);
				dialog.setVisible(true);
			}
		});

		modifierButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Récupérer l'laboratoire sélectionné dans la JTable
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex < 0) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un laboratoire à modifier.");
					return;
				}
				String id = (String) table.getValueAt(selectedRowIndex, 0);
				Laboratoire laboratoire = gestionnaire_labo.getLaboratoireById(id);
				if (laboratoire == null) {
					JOptionPane.showMessageDialog(null, "L'laboratoire sélectionné n'existe pas.");
					return;
				}

				// Afficher une boîte de dialogue pour modifier les informations de
				// l'laboratoire
				JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

				dialogPanel.add(new JLabel("ID:"));
				JTextField IdField = new JTextField(laboratoire.getId_lab());
				dialogPanel.add(IdField);
				dialogPanel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField(laboratoire.getNom_lab());
				dialogPanel.add(nomField);
				dialogPanel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField(laboratoire.getEmail_lab());
				dialogPanel.add(emailField);
				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Modifier un laboratoire",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					// Mettre à jour les informations de l'laboratoire dans la base de données
					laboratoire.setEmail_lab(emailField.getText());
					laboratoire.setId_lab(IdField.getText());
					laboratoire.setNom_lab(nomField.getText());

					boolean success = gestionnaire_labo.modifierLaboratoire(laboratoire);
					if (success) {
						JOptionPane.showMessageDialog(null, "L'laboratoire a été modifié avec succès.");
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(null,
								"Une erreur s'est produite lors de la modification de l'laboratoire.");
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
		Laboratoires = gestionnaire_labo.getAllLaboratoires();
		for (Laboratoire laboratoire : Laboratoires) {
			Object[] rowData = { laboratoire.getId_lab(), laboratoire.getNom_lab(), laboratoire.getEmail_lab() };
			model.addRow(rowData);
		}
	}

}
