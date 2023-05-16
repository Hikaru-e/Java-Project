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

import Metier.Professeur;
import Metier.Gestionnaires.Gestionnaire_Professeur;
import Metier.Gestionnaires.Gestionnaire_Projet;

public class ProfesseurPanel extends JPanel {

	private DefaultTableModel model;
	private JPanel buttonsPanel, upperbuttonsPanel;
	private ArrayList<Professeur> professeurs;
	private Gestionnaire_Professeur gestionnaire_prf = new Gestionnaire_Professeur();
	private Gestionnaire_Projet gestionnaire_projet = new Gestionnaire_Projet();
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private JButton ajouterButton, supprimerButton, modifierButton, searchButton;

	/// to imnplement
	private JTextField searchField;
	private TableRowSorter<DefaultTableModel> sorter;

	public ProfesseurPanel() {
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
		model.addColumn("Prénom");
		model.addColumn("Email");
		model.addColumn("Grade");
		model.addColumn("Role");
		model.addColumn("Id lab");
		model.addColumn("Id dprt");
		model.addColumn("Mot de passe");

		professeurs = gestionnaire_prf.getAllProfs();

		// Ajouter les données dans le modèle de table
		for (Professeur professeur : professeurs) {
			Object[] rowData = { professeur.getId_prf(), professeur.getNom_prf(), professeur.getPrenom_prf(),
					professeur.getEmail_prf(), professeur.getGrade(), professeur.getRole_prf(),
					professeur.getFk_id_lab(), professeur.getFk_id_dprt(), professeur.getPassword_prf() };
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
					JOptionPane.showMessageDialog(ProfesseurPanel.this,
							"Veuillez sélectionner une ligne pour supprimer.");
					return;
				}

				// Obtenir le id du prof à supprimer à partir de la ligne sélectionnée
				String id = (String) table.getValueAt(row, 0);

				// Demander à l'utilisateur de confirmer la suppression
				int response = JOptionPane.showConfirmDialog(ProfesseurPanel.this,
						"Êtes-vous sûr de vouloir supprimer " + "le professeur avec ID " + id + " ?",
						"Confirmation de la suppression", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					// Supprimer l'étudiant de la base de données
					if (gestionnaire_projet.getProjetByProf(id).size()==0)
					{
						boolean success = gestionnaire_prf.supprimerProfesseur(id);
						if (success) {
							JOptionPane.showMessageDialog(ProfesseurPanel.this,
									"Le professeur a été supprimé avec succès.");
							// Rafraîchir la JTable pour afficher les nouveaux résultats
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(ProfesseurPanel.this,
									"Une erreur s'est produite lors de la suppression" + " du professeur.");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Ce professeur ne peut pas être supprimé car il est responsable d'un projet ", "Error", JOptionPane.ERROR_MESSAGE);
					}
						
				}

			}
		});

		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Créer une boîte de dialogue pour saisir les informations de prof
				JDialog dialog = new JDialog();
				dialog.setTitle("Ajouter un professeur");
				JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
				panel.add(new JLabel("ID:"));
				JTextField idField = new JTextField();
				panel.add(idField);
				panel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField();
				panel.add(nomField);
				panel.add(new JLabel("Prénom:"));
				JTextField prenomField = new JTextField();
				panel.add(prenomField);
				panel.add(new JLabel("Grade:"));
				JTextField gradeField = new JTextField();
				panel.add(gradeField);
				panel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField();
				panel.add(emailField);
				panel.add(new JLabel("Role:"));
				JTextField roleField = new JTextField();
				panel.add(roleField);
				panel.add(new JLabel("Id lab:"));
				JTextField idLabField = new JTextField();
				panel.add(idLabField);
				panel.add(new JLabel("Id dprt:"));
				JTextField idDprtField = new JTextField();
				panel.add(idDprtField);
				panel.add(new JLabel("Mot de passe:"));
				JTextField passwordField = new JTextField();
				panel.add(passwordField);
				panel.setBorder(new EmptyBorder(10, 30, 10, 30));
				dialog.add(panel, BorderLayout.CENTER);

				// Ajouter un bouton dans le dialog pour ajouter prof

				JButton ajouterButton = new JButton("Ajouter");
				ajouterButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// To check if the foreign fields are empty or not, if they were then "null"
						// will
						// be their value in the database
						String fk_id_lab = idLabField.getText();
						if (idLabField.getText().isEmpty()) {
							fk_id_lab = null;
						}
						String fk_id_dprt = idDprtField.getText();
						if (idDprtField.getText().isEmpty()) {
							fk_id_dprt = null;
						}
						// ****************************************

						Professeur professeur = new Professeur(idField.getText(), nomField.getText(),
								prenomField.getText(), emailField.getText(), gradeField.getText(), roleField.getText(),
								new String(passwordField.getText()), fk_id_lab, fk_id_dprt);
						boolean success = gestionnaire_prf.ajouterProfesseur(professeur);
						if (success) {
							JOptionPane.showMessageDialog(ProfesseurPanel.this, "Professeur ajouté avec succès");
							dialog.dispose();
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(ProfesseurPanel.this,
									"Une erreur s'est produite lors de l'ajout du professeur");
						}
					}
				});
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(ajouterButton);
				dialog.add(buttonPanel, BorderLayout.SOUTH);

				dialog.pack();
				dialog.setLocationRelativeTo(ProfesseurPanel.this);
				dialog.setVisible(true);
			}
		});

		modifierButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Récupérer l'professeur sélectionné dans la JTable
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex < 0) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un professeur à modifier.");
					return;
				}
				String id = (String) table.getValueAt(selectedRowIndex, 0);
				Professeur professeur = gestionnaire_prf.getProfesseurById(id);
				if (professeur == null) {
					JOptionPane.showMessageDialog(null, "L'professeur sélectionné n'existe pas.");
					return;
				}

				// Afficher une boîte de dialogue pour modifier les informations de l'professeur
				JPanel dialogPanel = new JPanel(new GridLayout(0, 1));
				dialogPanel.add(new JLabel("ID:"));
				JTextField idField = new JTextField(professeur.getId_prf());
				dialogPanel.add(idField);
				dialogPanel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField(professeur.getNom_prf());
				dialogPanel.add(nomField);
				dialogPanel.add(new JLabel("Prénom:"));
				JTextField prenomField = new JTextField(professeur.getPrenom_prf());
				dialogPanel.add(prenomField);
				dialogPanel.add(new JLabel("Grade:"));
				JTextField gradeField = new JTextField(professeur.getGrade());
				dialogPanel.add(gradeField);
				dialogPanel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField(professeur.getEmail_prf());
				dialogPanel.add(emailField);
				dialogPanel.add(new JLabel("Role:"));
				JTextField roleField = new JTextField(professeur.getRole_prf());
				dialogPanel.add(roleField);
				dialogPanel.add(new JLabel("Id lab:"));
				JTextField idLabField = new JTextField(professeur.getFk_id_lab());
				dialogPanel.add(idLabField);
				dialogPanel.add(new JLabel("Id dprt:"));
				JTextField idDprtField = new JTextField(professeur.getFk_id_dprt());
				dialogPanel.add(idDprtField);
				dialogPanel.add(new JLabel("Mot de passe:"));
				JTextField passwordField = new JTextField(professeur.getPassword_prf());
				dialogPanel.add(passwordField);
				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Modifier un professeur",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					// Mettre à jour les informations de l'professeur dans la base de données
					professeur.setEmail_prf(emailField.getText());
					
					if (idDprtField.getText().isEmpty()) {
						professeur.setFk_id_dprt(null);
					} else
						professeur.setFk_id_dprt(idDprtField.getText());

					if (idLabField.getText().isEmpty()) {
						professeur.setFk_id_lab(null);
					} else
						professeur.setFk_id_lab(idLabField.getText());

					professeur.setGrade(gradeField.getText());
					professeur.setId_prf(idField.getText());
					professeur.setNom_prf(nomField.getText());
					professeur.setPassword_prf(passwordField.getText());
					professeur.setPrenom_prf(prenomField.getText());
					professeur.setRole_prf(roleField.getText());

					boolean success = gestionnaire_prf.modifierProfesseur(professeur);
					if (success) {
						JOptionPane.showMessageDialog(null, "L'professeur a été modifié avec succès.");
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(null,
								"Une erreur s'est produite lors de la modification de l'professeur.");
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
		ArrayList<Professeur> professeurs = gestionnaire_prf.getAllProfs();
		for (Professeur professeur : professeurs) {
			Object[] rowData = { professeur.getId_prf(), professeur.getNom_prf(), professeur.getPrenom_prf(),
					professeur.getEmail_prf(), professeur.getGrade(), professeur.getRole_prf(),
					professeur.getFk_id_lab(), professeur.getFk_id_dprt(), professeur.getPassword_prf() };
			model.addRow(rowData);
		}
	}

}
