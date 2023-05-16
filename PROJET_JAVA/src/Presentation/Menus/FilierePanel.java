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

import Metier.Filiere;
import Metier.Gestionnaires.Gestionnaire_Filiere;

public class FilierePanel extends JPanel {

	private DefaultTableModel model;
	private JPanel buttonsPanel, upperbuttonsPanel;
	private ArrayList<Filiere> filieres;
	private Gestionnaire_Filiere gestionnaire_filiere = new Gestionnaire_Filiere();
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private JButton ajouterButton, supprimerButton, modifierButton, searchButton;

	/// to imnplement
	private JTextField searchField;
	private TableRowSorter<DefaultTableModel> sorter;

	public FilierePanel() {
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

		model.addColumn("Nom");
		model.addColumn("Id dprt:");

		filieres = gestionnaire_filiere.getAllFilieres();

		// Ajouter les données dans le modèle de table
		for (Filiere filiere : filieres) {
			Object[] rowData = { filiere.getNom_filiere(), filiere.getFk_id_dprt() };
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
					JOptionPane.showMessageDialog(FilierePanel.this, "Veuillez sélectionner une ligne pour supprimer.");
					return;
				}

				// Obtenir le CNE de la filiere à supprimer à partir de la ligne sélectionnée
				String nom = (String) table.getValueAt(row, 0);

				// Demander à l'utilisateur de confirmer la suppression
				int response = JOptionPane.showConfirmDialog(FilierePanel.this,
						"Êtes-vous sûr de vouloir supprimer " + "la filiere avec le nom: " + nom + " ?",
						"Confirmation de la suppression", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					// Supprimer la filiere de la base de données
					boolean success = gestionnaire_filiere.supprimerFiliere(nom);
					if (success) {
						JOptionPane.showMessageDialog(FilierePanel.this, "la filiere a été supprimé avec succès.");
						// Rafraîchir la JTable pour afficher les nouveaux résultats
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(FilierePanel.this,
								"Une erreur s'est produite lors de la suppression" + " de la filiere.");
					}
				}

			}
		});

		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Créer une boîte de dialogue pour saisir les informations de la filiere
				JDialog dialog = new JDialog();
				dialog.setTitle("Ajouter une filiere");
				JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

				panel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField();
				panel.add(nomField);
				panel.add(new JLabel("Id dprt:"));
				JTextField idDprtField = new JTextField();
				panel.add(idDprtField);
				panel.setBorder(new EmptyBorder(10, 30, 10, 30));
				dialog.add(panel, BorderLayout.CENTER);

				// Ajouter un bouton dans le dialog pour ajouter la filiere
				JButton ajouterButton = new JButton("Ajouter");
				ajouterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// to check if the fields of foreign keys are empty, if so; make them null in the database
						String fk_id_dprt = idDprtField.getText();
						if (idDprtField.getText().isEmpty()) {
							fk_id_dprt = null;
						}
						// ****************************************
						Filiere Filiere = new Filiere(nomField.getText(), fk_id_dprt);
						boolean success = gestionnaire_filiere.ajouterFiliere(Filiere);
						if (success) {
							JOptionPane.showMessageDialog(FilierePanel.this, "Filiere ajouté avec succès");
							dialog.dispose();
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(FilierePanel.this,
									"Une erreur s'est produite lors de l'ajout de la filiere");
						}
					}
				});
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(ajouterButton);
				dialog.add(buttonPanel, BorderLayout.SOUTH);

				dialog.pack();
				dialog.setLocationRelativeTo(FilierePanel.this);
				dialog.setVisible(true);
			}
		});

		modifierButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Récupérer l'filiere sélectionné dans la JTable
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex < 0) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un filiere à modifier.");
					return;
				}
				String nom = (String) table.getValueAt(selectedRowIndex, 0);
				Filiere filiere = gestionnaire_filiere.getFiliereByNom(nom);
				if (filiere == null) {
					JOptionPane.showMessageDialog(null, "L'filiere sélectionné n'existe pas.");
					return;
				}

				// Afficher une boîte de dialogue pour modifier les informations de l'filiere
				JPanel dialogPanel = new JPanel(new GridLayout(0, 1));

				dialogPanel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField(filiere.getNom_filiere());
				dialogPanel.add(nomField);
				dialogPanel.add(new JLabel("Id dprt:"));
				JTextField idDprtField = new JTextField(filiere.getFk_id_dprt());
				dialogPanel.add(idDprtField);

				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Modifier un filiere",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					
					// Mettre à jour les informations de l'filiere dans la base de données
					// to check if the fields of foreign keys are empty, if so; make them null in the database
					String fk_id_dprt = idDprtField.getText();
					if (idDprtField.getText().isEmpty())
						fk_id_dprt = null;
					// ****************************************
					Filiere filiere2 = new Filiere(nomField.getText(), fk_id_dprt);
					
					boolean success = gestionnaire_filiere.modifierFiliere(filiere.getNom_filiere(), filiere2);
					if (success) {
						JOptionPane.showMessageDialog(null, "L'filiere a été modifié avec succès.");
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(null,
								"Une erreur s'est produite lors de la modification de l'filiere.");
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
		ArrayList<Filiere> filieres = gestionnaire_filiere.getAllFilieres();
		for (Filiere Filiere : filieres) {
			Object[] rowData = { Filiere.getNom_filiere(), Filiere.getFk_id_dprt() };
			model.addRow(rowData);
		}
	}

}
