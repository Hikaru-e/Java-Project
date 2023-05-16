package Presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

import Metier.Etudiant;
import Metier.Professeur;
import Metier.Gestionnaires.Gestionnaire_Etudiant;
import Metier.Gestionnaires.Gestionnaire_Professeur;
import Persistance.Connexion;

public class Home extends JFrame {
	private Connection con;
	private JPanel rightPane;
	private JPanel innerRightPane;
	private JPanel leftPane;
	private JPanel contentPane;

	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel imgLabel;
	private JLabel emptySpacing;

	private JTextField emailField;
	private JPasswordField passwordField;

	private JButton signIn;
	private JButton forgetPwd;
	private JComboBox<String> comboBox;
	private String selectedChoice = "";
	private Controlleur controlleur;
	private Gestionnaire_Etudiant gestion_etd = new Gestionnaire_Etudiant();
	private Gestionnaire_Professeur gestion_prf = new Gestionnaire_Professeur();

	public Home(Controlleur c) {
		this.initialiser();
		this.dessiner();
		this.action();
		this.action2();
		this.controlleur = c;
	}

	public void initialiser() {
		contentPane = new JPanel();
		leftPane = new JPanel();
		rightPane = new JPanel();
		innerRightPane = new JPanel();

		lblEmail = new JLabel("EMAIL");
		lblPassword = new JLabel("PASSWORD");
		imgLabel = new JLabel("");
		emptySpacing = new JLabel("");
		emailField = new JTextField(26);
		passwordField = new JPasswordField(26);

		signIn = new JButton("Sign In");
		forgetPwd = new JButton("Forgot your password ?");

		String[] choices = { "Professeur", "Etudiant" };
		comboBox = new JComboBox<>(choices);
	}

	private void dessiner() {

		this.setContentPane(contentPane);

		// dividing the main panel to a two column table
		contentPane.setLayout(new GridLayout(1, 2));
		contentPane.setBackground(Color.WHITE);

		leftPane.setBackground(Color.DARK_GRAY);
		leftPane.setLayout(new GridBagLayout());

		// adding the image to the JLabel and then adding this label to the leftPane
		// using constraints
		imgLabel.setIcon(new ImageIcon(Home.class.getResource("/images/logo.png")));
		leftPane.add(imgLabel);

		emptySpacing.setPreferredSize(new Dimension(180, 30));
		signIn.setForeground(Color.WHITE);
		signIn.setBackground(new Color(52, 73, 94));

		innerRightPane.setLayout(new GridLayout(9, 1, 10, 10));
		innerRightPane.setBackground(Color.WHITE);
		innerRightPane.add(comboBox);
		innerRightPane.add(lblEmail);
		innerRightPane.add(emailField);
		innerRightPane.add(lblPassword);
		innerRightPane.add(passwordField);
		innerRightPane.add(emptySpacing);
		innerRightPane.add(signIn);
		innerRightPane.add(forgetPwd);
		// adding the innerRightPane and centering it
		rightPane.setLayout(new GridBagLayout());
		rightPane.setBackground(Color.WHITE);
		rightPane.add(innerRightPane);

		// Add a listener to detect when the user selects a choice
		comboBox.addActionListener(e -> {
			selectedChoice = (String) comboBox.getSelectedItem();
		});

		// finally adding the two panels in the form of two columns
		contentPane.add(leftPane);
		contentPane.add(rightPane);

	}

	public void action() {
		signIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String password = new String(passwordField.getPassword());
				if (email.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(Home.this, "Please enter both email and password", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					con = Connexion.ConnectDB();
					PreparedStatement ps = null;
					ResultSet resultSet = null;
					if (selectedChoice.equals("Etudiant")) {

						try {
							String query = "SELECT * FROM Etudiant WHERE email_etd=? and password_etd=? ;";
							ps = con.prepareStatement(query);
							ps.setString(1, email);
							ps.setString(2, password);
							resultSet = ps.executeQuery();
							if (resultSet.next()) {
								controlleur.afficheEtudiantPanel();
							} else {
								JOptionPane.showMessageDialog(Home.this, "Invalid email or password", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						} catch (SQLException ex) {
							ex.printStackTrace();
						} finally {
							close(con, ps, resultSet);
						}
					} else {

						try {
							String query = "SELECT * FROM Professeur WHERE email_prf=? and password_prf=? ;";
							ps = con.prepareStatement(query);
							ps.setString(1, email);
							ps.setString(2, password);
							resultSet = ps.executeQuery();
							if (resultSet.next()) {
								switch (resultSet.getString("role_prf")) {

								// find the suitable name
								case "admin": {
									controlleur.afficheAdminPanel();
								}
									break;
								case "professeur": {
									controlleur.afficheProfPanel();
								}
									break;
								case "chef depart": {
									controlleur.afficheChefDepartPanel();
								}
									break;
								case "coord filiere": {
									controlleur.afficheCoordFilierPanel();
								}
									break;
								}

							} else {
								JOptionPane.showMessageDialog(Home.this, "Invalid email or password", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						} catch (SQLException ex) {
							ex.printStackTrace();
						} finally {
							close(con, ps, resultSet);
						}

					}

				}
			}
		});

		// code to change theme
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

	public void action2() {
		forgetPwd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (selectedChoice.equals("Etudiant")) {
					JDialog dialog = new JDialog();
					dialog.setTitle("Forgot My Password");
					JPanel panel = new JPanel(new GridLayout(0, 1));

					panel.add(new JLabel("CNE:"));
					
					JTextField cneField = new JTextField();
					panel.add(cneField);
					
					panel.add(new JLabel("Email:"));
					
					JTextField emailField = new JTextField();
					panel.add(emailField);
					
					panel.setBorder(new EmptyBorder(10, 30, 10, 30));
					JButton ModifierButton = new JButton("Modifier");
					JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					buttonsPanel.add(ModifierButton);
					buttonsPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

					dialog.add(panel, BorderLayout.CENTER);
					dialog.add(buttonsPanel, BorderLayout.SOUTH);
					
					dialog.setSize(new Dimension(280,200));
					dialog.setLocationRelativeTo(Home.this);
					dialog.setVisible(true);
					dialog.setResizable(false);

					ModifierButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							con = Connexion.ConnectDB();
							PreparedStatement ps = null;
							ResultSet resultSet = null;
							try {
								String query = "SELECT * FROM Etudiant WHERE email_etd=? and cne_etd=? ;";
								ps = con.prepareStatement(query);
								ps.setString(1, emailField.getText());
								ps.setString(2, cneField.getText());
								resultSet = ps.executeQuery();
								if (resultSet.next()) {

									dialog.dispose();
									Etudiant etd = gestion_etd.getEtudiantCNE(cneField.getText());
									gestion_etd.modifierPassword(etd);

								} else {
									JOptionPane.showMessageDialog(dialog, "CNE ou Email est errone", "Erreur",
											JOptionPane.ERROR_MESSAGE);
								}

							} catch (SQLException ex) {
								ex.printStackTrace();
							} finally {
								close(con, ps, resultSet);
							}

						}
					});

				}
				else
				{
					JDialog dialog = new JDialog();
					dialog.setTitle("Forgot My Password");
					JPanel panel = new JPanel(new GridLayout(0, 1));

					panel.add(new JLabel("ID:"));
					JTextField idField = new JTextField();
					panel.add(idField);
					
					panel.add(new JLabel("Email:"));
					
					JTextField emailField = new JTextField();
					panel.add(emailField);
					
					panel.setBorder(new EmptyBorder(10, 30, 10, 30));
					JButton ModifierButton = new JButton("Modifier");
					JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
					buttonsPanel.add(ModifierButton);
					buttonsPanel.setBorder(new EmptyBorder(10, 30, 10, 30));

					dialog.add(panel, BorderLayout.CENTER);
					dialog.add(buttonsPanel, BorderLayout.SOUTH);
					
					dialog.setSize(new Dimension(280,200));
					dialog.setLocationRelativeTo(Home.this);
					dialog.setVisible(true);
					dialog.setResizable(false);

					ModifierButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							con = Connexion.ConnectDB();
							PreparedStatement ps = null;
							ResultSet resultSet = null;
							try {
								String query = "SELECT * FROM professeur WHERE id_prf=? and email_prf=? ;";
								ps = con.prepareStatement(query);
								ps.setString(1, emailField.getText());
								ps.setString(2, idField.getText());
								resultSet = ps.executeQuery();
								if (resultSet.next()) {

									dialog.dispose();
									Professeur prf = gestion_prf.getProfesseurById(idField.getText());
									gestion_prf.modifierPassword(prf);

								} else {
									JOptionPane.showMessageDialog(dialog, "ID ou Email est errone", "Erreur",
											JOptionPane.ERROR_MESSAGE);
								}

							} catch (SQLException ex) {
								ex.printStackTrace();
							} finally {
								close(con, ps, resultSet);
							}

						}
					});

				}
					
				

			}

		});
	}

	// code to make it easier to close connection
	private void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
