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

import Metier.Departement;
import Metier.Gestionnaires.Gestionnaire_Departement;

public class DepartementPanel extends JPanel {

	private DefaultTableModel model;
	private JPanel buttonsPanel, upperbuttonsPanel;
	private ArrayList<Departement> departements;
	private Gestionnaire_Departement gestionnaire_depart = new Gestionnaire_Departement();
	private JTable table;
	private JScrollPane scrollPane;
	private JButton ajouterButton, supprimerButton, modifierButton, searchButton;
	private TableRowSorter<DefaultTableModel> sorter;

	private JTextField searchField;

	public DepartementPanel() {
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

		table.setRowSorter(sorter);

		departements = gestionnaire_depart.getAllDepartements();

		// Ajouter les données dans le modèle de table
		for (Departement departement : departements) {
			Object[] rowData = { departement.getID(), departement.getNom(), departement.getEmail() };
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
					JOptionPane.showMessageDialog(DepartementPanel.this,
							"Veuillez sélectionner une ligne pour supprimer.");
					return;
				}

				// Obtenir le CNE de l'departement à supprimer à partir de la ligne sélectionnée
				String id = (String) table.getValueAt(row, 0);

				// Demander à l'utilisateur de confirmer la suppression
				int response = JOptionPane.showConfirmDialog(DepartementPanel.this,
						"Êtes-vous sûr de vouloir supprimer " + "departement avec le ID " + id + " ?",
						"Confirmation de la suppression", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					// Supprimer l'departement de la base de données
					boolean success = gestionnaire_depart.supprimerDepartement(id);
					if (success) {
						JOptionPane.showMessageDialog(DepartementPanel.this, "departement a été supprimé avec succès.");
						// Rafraîchir la JTable pour afficher les nouveaux résultats
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(DepartementPanel.this,
								"Une erreur s'est produite lors de la suppression" + " du departement.");
					}
				}

			}
		});

		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Créer une boîte de dialogue pour saisir les informations de l'departement
				JDialog dialog = new JDialog();
				dialog.setTitle("Ajouter un departement");
				JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
				panel.add(new JLabel("ID:"));
				JTextField idField = new JTextField();
				panel.add(idField);
				panel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField();
				panel.add(nomField);
				panel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField();
				panel.add(emailField);
				panel.setBorder(new EmptyBorder(10, 30, 10, 30));
				dialog.add(panel, BorderLayout.CENTER);

				// Ajouter un bouton dans le dialog pour ajouter l'departement
				JButton ajouterButton = new JButton("Ajouter");
				ajouterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Departement departement = new Departement(idField.getText(), nomField.getText(),
								emailField.getText());
						boolean success = gestionnaire_depart.ajouterDepartement(departement);
						if (success) {
							JOptionPane.showMessageDialog(DepartementPanel.this, "departement ajouté avec succès");
							dialog.dispose();
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(DepartementPanel.this,
									"Une erreur s'est produite lors de l'ajout du departement");
						}
					}
				});
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(ajouterButton);
				dialog.add(buttonPanel, BorderLayout.SOUTH);

				dialog.pack();
				dialog.setLocationRelativeTo(DepartementPanel.this);
				dialog.setVisible(true);
			}
		});

		modifierButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Récupérer l'departement sélectionné dans la JTable
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex < 0) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un departement à modifier.");
					return;
				}
				String id = (String) table.getValueAt(selectedRowIndex, 0);
				Departement departement = gestionnaire_depart.getDepartementByID(id);
				if (departement == null) {
					JOptionPane.showMessageDialog(null, "L'departement sélectionné n'existe pas.");
					return;
				}

				// Afficher une boîte de dialogue pour modifier les informations de
				// l'departement
				JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

				dialogPanel.add(new JLabel("ID:"));
				JTextField idField = new JTextField(departement.getID());
				dialogPanel.add(idField);
				dialogPanel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField(departement.getNom());
				dialogPanel.add(nomField);
				dialogPanel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField(departement.getEmail());
				dialogPanel.add(emailField);
				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Modifier un departement",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					// Mettre à jour les informations de l'departement dans la base de données
					departement.setID(idField.getText());
					departement.setEmail(emailField.getText());
					departement.setNom(nomField.getText());

					boolean success = gestionnaire_depart.modifierDepartement(departement);
					if (success) {
						JOptionPane.showMessageDialog(null, "L'departement a été modifié avec succès.");
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(null,
								"Une erreur s'est produite lors de la modification de l'departement.");
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
		ArrayList<Departement> departements = gestionnaire_depart.getAllDepartements();
		for (Departement departement : departements) {
			Object[] rowData = { departement.getID(), departement.getNom(), departement.getEmail() };
			model.addRow(rowData);

		}
	}

}
