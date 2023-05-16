package Presentation.Menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Metier.Projet;
import Metier.Gestionnaires.Gestionnaire_Projet;
import Presentation.Controlleur;

public class ProjetPanel extends JPanel {

	private DefaultTableModel model;
	private JPanel buttonsPanel, upperbuttonsPanel;
	private ArrayList<Projet> Projets;
	private Gestionnaire_Projet gestionnaire_projet;
	private JTable table = new JTable();
	private JScrollPane scrollPane;
	private JButton ajouterButton, supprimerButton, searchButton;

	/// to imnplement
	private JTextField searchField;
	private TableRowSorter<DefaultTableModel> sorter;
	private Controlleur controlleur;


	public ProjetPanel() {
		super();
		controlleur = new Controlleur();
		initialiser();
		dessiner();
		action();
	}

	public void initialiser() {

		gestionnaire_projet = new Gestionnaire_Projet();


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

		searchButton = new JButton("Rechercher");
		searchField = new JTextField(26);
	}

	private void dessiner() {
		model.addColumn("ID");
		model.addColumn("Type");
		model.addColumn("Lieu");
		model.addColumn("Titre");
		model.addColumn("Date depart");
		model.addColumn("Duree");
		model.addColumn("Id prf");
		model.addColumn("Id lab");
		model.addColumn("Id ent");
		model.addColumn("cne_etd");

		Projets = gestionnaire_projet.getAllProjects();

		// Ajouter les données dans le modèle de table
		for (Projet Projet : Projets) {
			Object[] rowData = { Projet.getId_prj(), Projet.getType_prj(), Projet.getLieu_prj(), Projet.getTitre_prj(),
					Projet.getDate_depart(), Projet.getDuree_prj(), Projet.getFk_id_prf(), Projet.getFk_id_lab(),
					Projet.getFk_id_ent(), Projet.getFk_cne_etd() };
			model.addRow(rowData);
		}

		// Ajouter le JScrollPane contenant la JTable au JPanel

		// Créer un JPanel pour les boutons
		// ***********************************************************
		// hna fin tzid buttons dial lta7t

		buttonsPanel.add(ajouterButton);
		buttonsPanel.add(supprimerButton);

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
					JOptionPane.showMessageDialog(ProjetPanel.this, "Veuillez sélectionner une ligne pour supprimer.");
					return;
				}

				// Obtenir le CNE de l'étudiant à supprimer à partir de la ligne sélectionnée
				String id = (String) table.getValueAt(row, 0);

				// Demander à l'utilisateur de confirmer la suppression
				int response = JOptionPane.showConfirmDialog(ProjetPanel.this,
						"Êtes-vous sûr de vouloir supprimer " + "le Projet avec ID " + id + " ?",
						"Confirmation de la suppression", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					// Supprimer l'étudiant de la base de données
					boolean success = gestionnaire_projet.supprimerProject(id);
					if (success) {
						JOptionPane.showMessageDialog(ProjetPanel.this, "Le Projet a été supprimé avec succès.");
						// Rafraîchir la JTable pour afficher les nouveaux résultats
						refreshTable();
					} else {
						JOptionPane.showMessageDialog(ProjetPanel.this,
								"Une erreur s'est produite lors de la suppression" + " du Projet.");
					}
				}

			}
		});

		ajouterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlleur.afficheProjetPanel();
			}
		});
		
		
//		ajouterButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// Créer une boîte de dialogue pour saisir les informations de l'étudiant
//				JDialog dialog = new JDialog();
//				dialog.setTitle("Ajouter un projet");
//				JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
//				panel.add(new JLabel("ID:"));
//				JTextField idField = new JTextField();
//				panel.add(idField);
//				panel.add(new JLabel("Titre:"));
//				JTextField titreField = new JTextField();
//				panel.add(titreField);
//
//				panel.add(new JLabel("Lieu:"));
//				JTextField lieuField = new JTextField();
//				panel.add(lieuField);
//
//				panel.add(new JLabel("Date Depart:"));
//				JDateChooser date_deprt = new JDateChooser();
//				panel.add(date_deprt);
//
//				panel.add(new JLabel("Duree du projet:"));
//				JTextField dureePrjField = new JTextField();
//				panel.add(dureePrjField);
//
//				panel.add(new JLabel("Type de projet:"));
//				JComboBox<String> typePrjField = new JComboBox<String>();
//				typePrjField.addItem("PFA");
//				typePrjField.addItem("PFE");
//				typePrjField.addItem("Doctorat");
//				panel.add(typePrjField);
//
//				panel.add(new JLabel("Id prf:"));
//				JTextField idPrfField = new JTextField();
//				panel.add(idPrfField);
//
//				panel.add(new JLabel("Id lab:"));
//				JTextField idLabField = new JTextField();
//				panel.add(idLabField);
//
//				panel.add(new JLabel("Id ent:"));
//				JTextField idEntField = new JTextField();
//				panel.add(idEntField);
//
//				panel.add(new JLabel("cne etd:"));
//				JTextField cneEtdField = new JTextField();
//				panel.add(cneEtdField);
//				panel.setBorder(new EmptyBorder(10, 30, 10, 30));
//
//				dialog.add(panel, BorderLayout.CENTER);
//
//				// Ajouter un bouton dans le dialog pour ajouter l'étudiant
//				JButton ajouterButton = new JButton("Ajouter");
//
//				ajouterButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						// to check if the fields of foreign keys are empty, if so; make them null in the database
//						String fk_cne_etd = cneEtdField.getText();
//						if (cneEtdField.getText().isEmpty()) {
//							fk_cne_etd = null;
//						}
//						String fk_id_prf = idPrfField.getText();
//						if (idPrfField.getText().isEmpty()) {
//							fk_id_prf = null;
//						}
//						String fk_id_lab = idLabField.getText();
//						if (idLabField.getText().isEmpty()) {
//							fk_id_lab = null;
//						}
//						String fk_id_ent = idEntField.getText();
//						if (idEntField.getText().isEmpty()) {
//							fk_id_ent = null;
//						}
//						
//						// Parsing the duree field to float and doing checks if it's null
//						Float dureePrj = 0f;
//						if (!dureePrjField.getText().isEmpty())
//							dureePrj = Float.parseFloat(dureePrjField.getText());
//							
//						// Grabbing the text from the JDateChooser and formatting it to sql.Date
//						java.util.Date selectedDate =  date_deprt.getDate();
//						selectedDate.setMonth(selectedDate.getMonth()+1);
//						selectedDate.setYear(selectedDate.getYear()+1900);
//						java.sql.Date sqlDate = new java.sql.Date(selectedDate.getTime());
//
//						
//						Projet projet = new Projet(idField.getText(), (String)typePrjField.getSelectedItem(), lieuField.getText(),
//								titreField.getText(), sqlDate , dureePrj, fk_cne_etd,
//								fk_id_prf, fk_id_lab, fk_id_ent);
//						boolean success = gestionnaire_projet.ajouterProject(projet);
//						if (success) {
//							JOptionPane.showMessageDialog(ProjetPanel.this, "Projet ajouté avec succès");
//							dialog.dispose();
//							refreshTable();
//						} else {
//							JOptionPane.showMessageDialog(ProjetPanel.this,
//									"Une erreur s'est produite lors de l'ajout du Projet");
//						}
//					}
//				});
//				JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//				buttonPanel.add(ajouterButton);
//				dialog.add(buttonPanel, BorderLayout.SOUTH);
//
//				dialog.pack();
//				dialog.setLocationRelativeTo(ProjetPanel.this);
//				dialog.setVisible(true);
//			}
//		});

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
		ArrayList<Projet> Projets = gestionnaire_projet.getAllProjects();
		// Ajouter les données dans le modèle de table
		for (Projet Projet : Projets) {
			Object[] rowData = { Projet.getId_prj(), Projet.getType_prj(), Projet.getLieu_prj(), Projet.getTitre_prj(),
					Projet.getDate_depart(), Projet.getDuree_prj(), Projet.getFk_id_prf(), Projet.getFk_id_lab(),
					Projet.getFk_id_ent(), Projet.getFk_cne_etd() };
			model.addRow(rowData);
		}
	}

}
