package Presentation.Menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Metier.Etudiant;
import Metier.Filiere;
import Metier.Gestionnaires.Gestionnaire_Etudiant;
import Metier.Gestionnaires.Gestionnaire_Filiere;
import Persistance.Connexion;

public class EtudiantPanel extends JPanel {

	private DefaultTableModel model;

	private JPanel buttonsPanel, upperbuttonsPanel;
	private ArrayList<Etudiant> etudiants;
	private Gestionnaire_Etudiant gestionnaire_etd = new Gestionnaire_Etudiant();
	private Gestionnaire_Filiere gestionnaire_fil = new Gestionnaire_Filiere();
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private JButton ajouterButton, supprimerButton, modifierButton, searchButton, importExcelButton;
	private TableRowSorter<DefaultTableModel> sorter;

	private JTextField searchField;

	public EtudiantPanel() {
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

		importExcelButton = new JButton(new ImageIcon(EtudiantPanel.class.getResource("/images/excel.png")));
		importExcelButton.setText("Import Excel");

		searchField = new JTextField(26);
	}

	private void dessiner() {
		model.addColumn("CNE");
		model.addColumn("Nom");
		model.addColumn("Prénom");
		model.addColumn("Niveau");
		model.addColumn("Email");
		model.addColumn("Nom filiere");
		model.addColumn("Mot de passe");

		table.setRowSorter(sorter);

		etudiants = gestionnaire_etd.getAllEtudiants();

		// Ajouter les données dans le modèle de table
		for (Etudiant etudiant : etudiants) {
			Object[] rowData = { etudiant.getCNE_etd(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getNiveau(),
					etudiant.getEmail(), etudiant.getFk_nom_filiere(), etudiant.getPassword_etd() };
			model.addRow(rowData);
		}

		// Créer un JPanel pour les boutons
		// ***********************************************************
		// hna fin tzid buttons dial lta7t
		buttonsPanel.add(importExcelButton);
		buttonsPanel.add(ajouterButton);
		buttonsPanel.add(supprimerButton);
		buttonsPanel.add(modifierButton);

		// ***********************************************************
//		outerbuttonsPanel.add(buttonsPanel, new FlowLayout(FlowLayout.CENTER));

		searchField.setPreferredSize(new Dimension(20, 20));

		// ***********************************************************
		// hna fin tzid buttons dial lfo9
		upperbuttonsPanel.add(searchField);
		upperbuttonsPanel.add(searchButton);

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
					JOptionPane.showMessageDialog(EtudiantPanel.this,
							"Veuillez sélectionner une ligne pour supprimer.");
					return;
				}

				// Obtenir le CNE de l'étudiant à supprimer à partir de la ligne sélectionnée
				String cne = (String) table.getValueAt(row, 0);

				// Demander à l'utilisateur de confirmer la suppression
				int response = JOptionPane.showConfirmDialog(EtudiantPanel.this,
						"Êtes-vous sûr de vouloir supprimer " + "l'étudiant avec le CNE " + cne + " ?",
						"Confirmation de la suppression", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					// Supprimer l'étudiant de la base de données
					boolean success = gestionnaire_etd.supprimerEtudiant(cne);
					if (success) {
						JOptionPane.showMessageDialog(EtudiantPanel.this, "L'étudiant a été supprimé avec succès.");
						// Rafraîchir la JTable pour afficher les nouveaux résultats
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(EtudiantPanel.this,
								"Une erreur s'est produite lors de la suppression" + " de l'étudiant.");
					}
				}

			}
		});

		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Créer une boîte de dialogue pour saisir les informations de l'étudiant
				JDialog dialog = new JDialog();
				dialog.setTitle("Ajouter un etudiant");
				JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
				panel.add(new JLabel("CNE:"));
				JTextField cneField = new JTextField();
				panel.add(cneField);
				panel.add(new JLabel("Nom:"));
				JTextField nomField = new JTextField();
				panel.add(nomField);
				panel.add(new JLabel("Prénom:"));
				JTextField prenomField = new JTextField();
				panel.add(prenomField);
				panel.add(new JLabel("Niveau:"));
				JTextField niveauField = new JTextField();
				panel.add(niveauField);
				panel.add(new JLabel("Email:"));
				JTextField emailField = new JTextField();
				panel.add(emailField);
				panel.add(new JLabel("Nom filiere:"));

				JTextField nomFiliereField = new JTextField();
				panel.add(nomFiliereField);
				panel.add(new JLabel("Mot de passe:"));
				JTextField passwordField = new JTextField();
				panel.add(passwordField);
				panel.setBorder(new EmptyBorder(10, 30, 10, 30));
//				panel.set
				dialog.add(panel, BorderLayout.CENTER);

				// Ajouter un bouton dans le dialog pour ajouter l'étudiant
				JButton addButton = new JButton("Ajouter");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// To check if the nomFiliere field is empty or not, if it was then "null" will
						// be its value in the database
						String nomFiliereFinalField = nomFiliereField.getText();
						if (nomFiliereField.getText().isEmpty()) {
							nomFiliereFinalField = null;
						}
						// ****************************************
						Etudiant etudiant = new Etudiant(cneField.getText(), nomField.getText(), prenomField.getText(),
								niveauField.getText(), emailField.getText(), passwordField.getText(),
								nomFiliereFinalField);
						boolean success = gestionnaire_etd.ajouterEtudiant(etudiant);
						if (success) {
							JOptionPane.showMessageDialog(EtudiantPanel.this, "Étudiant ajouté avec succès");
							dialog.dispose();
							refreshTable();
						} else {
							JOptionPane.showMessageDialog(EtudiantPanel.this,
									"Une erreur s'est produite lors de l'ajout de l'étudiant");
						}
					}
				});
				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				buttonPanel.add(addButton);
				dialog.add(buttonPanel, BorderLayout.SOUTH);

				dialog.pack();
				dialog.setLocationRelativeTo(EtudiantPanel.this);
				dialog.setVisible(true);
			}
		});

		modifierButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// Récupérer l'étudiant sélectionné dans la JTable
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex < 0) {
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner un étudiant à modifier.");
					return;
				}
				String cne = (String) table.getValueAt(selectedRowIndex, 0);
				Etudiant etudiant = gestionnaire_etd.getEtudiantCNE(cne);
				if (etudiant == null) {
					JOptionPane.showMessageDialog(null, "L'étudiant sélectionné n'existe pas.");
					return;
				}

				// Afficher une boîte de dialogue pour modifier les informations de l'étudiant
				JPanel dialogPanel = new JPanel(new GridLayout(0, 1));
				JTextField nomField = new JTextField(etudiant.getNom());
				JTextField prenomField = new JTextField(etudiant.getPrenom());
				JTextField niveauField = new JTextField(etudiant.getNiveau());
				JTextField emailField = new JTextField(etudiant.getEmail());
				JTextField nomFiliereField = new JTextField(etudiant.getFk_nom_filiere());
				JTextField passwordField = new JTextField(etudiant.getPassword_etd());
				dialogPanel.add(new JLabel("Nom:"));
				dialogPanel.add(nomField);
				dialogPanel.add(new JLabel("Prénom:"));
				dialogPanel.add(prenomField);
				dialogPanel.add(new JLabel("Niveau:"));
				dialogPanel.add(niveauField);
				dialogPanel.add(new JLabel("Email:"));
				dialogPanel.add(emailField);
				dialogPanel.add(new JLabel("Nom Filiere:"));
				dialogPanel.add(nomFiliereField);
				dialogPanel.add(new JLabel("Mot de passe:"));
				dialogPanel.add(passwordField);
				int result = JOptionPane.showConfirmDialog(null, dialogPanel, "Modifier un étudiant",
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					// Mettre à jour les informations de l'étudiant dans la base de données
					etudiant.setNom(nomField.getText());
					etudiant.setPrenom(prenomField.getText());
					etudiant.setNiveau(niveauField.getText());
					etudiant.setEmail(emailField.getText());
					// to check if the modified value of the foreign key(s) is null and update it
					// accordingly in the database
					if (nomFiliereField.getText().isEmpty()) {
						etudiant.setFk_nom_filiere(null);
					} else
						etudiant.setFk_nom_filiere(nomFiliereField.getText());
					// ****************************************
					etudiant.setPassword_etd(passwordField.getText());
					boolean success = gestionnaire_etd.modifierEtudiant(etudiant);
					if (success) {
						JOptionPane.showMessageDialog(null, "L'étudiant a été modifié avec succès.");
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(null,
								"Une erreur s'est produite lors de la modification de l'étudiant.");
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
				refreshTable();
			}
		});
		importExcelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int option = fileChooser.showOpenDialog(EtudiantPanel.this);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					importExcel(file);
				}
			}
		});

	}

	private void importExcel(File file) {
		try {

			// Connect to the database
			Connection connection = Connexion.ConnectDB();
			// Create a prepared statement for inserting data
			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO etudiant (cne_etd, nom_etd, prenom_etd,email_etd,niveau_etd,password_etd, fk_nom_filiere) VALUES (?, ?, ?, ?, ?, ?, ?)");

			// Read the Excel file
			FileInputStream fis = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			// Clear the existing table data
//			tableModel.setRowCount(0);

			// Iterate through each row in the Excel file
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Object[] rowData = new Object[7];
				int columnIndex = 0;

				// Read each cell in the row and add it to the database
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String cellValue = "";

					switch (cell.getCellType()) {
					case STRING:
						cellValue = cell.getStringCellValue();
						break;
					case NUMERIC:
						cellValue = String.valueOf((int) cell.getNumericCellValue());
						break;
					case BLANK:
						cellValue = null;
						break;
					default:
						break;

					// Add more cases as needed for other cell types
					}

					// Add the cell value to the prepared statement
					preparedStatement.setString(columnIndex + 1, cellValue);
//                    columnIndex++; // Increment the column index

					// Add the cell value to the JTable
					rowData[columnIndex] = cellValue;
					columnIndex++; // Increment the column index
				}

				// Execute the prepared statement to insert the row into the database
				preparedStatement.executeUpdate();

				// Add the row to the JTable
				model.addRow(rowData);
			}

			// Close the workbook and database connection
			workbook.close();
			connection.close();

			JOptionPane.showMessageDialog(EtudiantPanel.this, "Import successful!");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(EtudiantPanel.this, "Import failed: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void refreshTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0); // Effacer les lignes existantes
		ArrayList<Etudiant> etudiants = gestionnaire_etd.getAllEtudiants();
		for (Etudiant etudiant : etudiants) {
			Object[] rowData = { etudiant.getCNE_etd(), etudiant.getNom(), etudiant.getPrenom(), etudiant.getNiveau(),
					etudiant.getEmail(), etudiant.getFk_nom_filiere(), etudiant.getPassword_etd() };
			model.addRow(rowData);
		}
	}

}
