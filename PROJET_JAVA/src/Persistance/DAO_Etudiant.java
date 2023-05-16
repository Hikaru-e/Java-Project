package Persistance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Metier.Etudiant;

public class DAO_Etudiant {
	private Connection con;

	public DAO_Etudiant() {
		super();
	}

	public ArrayList<Etudiant> getAll() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		ArrayList<Etudiant> etudiants = new ArrayList<>();

		try  {
			ps = con.prepareStatement("SELECT * FROM Etudiant ;");
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Etudiant etudiant = new Etudiant(resultSet.getNString("cne_etd"), resultSet.getNString("nom_etd"),
						resultSet.getNString("prenom_etd"), resultSet.getNString("niveau_etd"),
						resultSet.getNString("email_etd"), resultSet.getNString("password_etd"),
						resultSet.getNString("fk_nom_filiere"));

				etudiants.add(etudiant);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		finally {
			close(con, ps, resultSet);
		}
		
		return etudiants;
	}

	public Etudiant findByCNE(String CNE) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT * FROM etudiant WHERE cne_etd=?;");
			ps.setNString(1, CNE);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				Etudiant etudiant = new Etudiant(resultSet.getNString("cne_etd"), resultSet.getNString("nom_etd"),
						resultSet.getNString("prenom_etd"), resultSet.getNString("niveau_etd"),
						resultSet.getNString("email_etd"), resultSet.getNString("password_etd"),
						resultSet.getNString("fk_nom_filiere"));
				return etudiant;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}

		finally {
			close(con, ps, resultSet);
		}

		return null;
	}

	public boolean updateEtudiant(Etudiant etudiant) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"UPDATE Etudiant SET nom_etd=?,prenom_etd=?,niveau_etd=?,email_etd=?,password_etd=?, fk_nom_filiere=? WHERE cne_etd=?;");
			ps.setNString(1, etudiant.getNom());
			ps.setNString(2, etudiant.getPrenom());
			ps.setNString(3, etudiant.getNiveau());
			ps.setNString(4, etudiant.getEmail());
			ps.setNString(5, etudiant.getPassword_etd());
			ps.setNString(6, etudiant.getFk_nom_filiere());
			ps.setNString(7, etudiant.getCNE_etd());
			ps.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps);
		}

		return false;
	}

	public boolean addEtudiant(Etudiant etudiant) {

		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"INSERT INTO Etudiant (cne_etd,nom_etd,prenom_etd,niveau_etd,email_etd,password_etd,fk_nom_filiere) VALUES(?,?,?,?,?,?,?);");
			ps.setNString(1, etudiant.getCNE_etd());
			ps.setNString(2, etudiant.getNom());
			ps.setNString(3, etudiant.getPrenom());
			ps.setNString(4, etudiant.getNiveau());
			ps.setNString(5, etudiant.getEmail());
			ps.setNString(6, etudiant.getPassword_etd());
			ps.setNString(7, etudiant.getFk_nom_filiere());

			ps.executeUpdate();
			return true;

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;

	}

	public boolean DeleteEtudiant(String CNE) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM Etudiant WHERE cne_etd=?;");

			ps.setNString(1, CNE);
			ps.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public Integer countEtudiant() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet res = null;
		int Count = 0;
		try {
			ps = con.prepareStatement("SELECT COUNT(*) FROM etudiant;");
			res = ps.executeQuery();
			if (res.next()) {
				Count = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, res);
		}

		return Count;
	}
	
	
	public void modifierPassword(Etudiant etd) {
	    JDialog dialog = new JDialog();
	    dialog.setTitle("Enter your new Password");
	    
	    JPanel panel = new JPanel(new GridLayout(0, 1));
	    
	    panel.add(new JLabel("Mot de passe :"));
	    
	    JTextField pwdField = new JTextField();
	    panel.add(pwdField);
	    
	    JButton ModifierButton = new JButton("Modifier");
	    ModifierButton.setForeground(Color.WHITE);
	    ModifierButton.setBackground(new Color(52, 73, 94));
	    panel.setBorder(new EmptyBorder(10, 30, 10, 30));
	    ModifierButton.setBorder(new EmptyBorder(5, 0, 5, 0));
	    
	    dialog.add(panel);
	    dialog.add(ModifierButton, BorderLayout.SOUTH);
	    
	    dialog.pack();
	    dialog.setLocationRelativeTo(null);
	    dialog.setVisible(true);
	    dialog.setResizable(false);
//	    dialog.setSize(new Dimension(128,128));
	    
	    ModifierButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            try {
	            	if (con.isClosed()) {
	                    // si la connexion est fermée, la rétablir
	            		con = Connexion.ConnectDB();
	                }
	            	if (!pwdField.getText().isEmpty())
	            	{
	            		PreparedStatement ps = con.prepareStatement("UPDATE etudiant SET password_etd=? WHERE cne_etd=?;");
	            		ps.setString(1, pwdField.getText());
	            		ps.setString(2, etd.getCNE_etd());
	            		ps.executeUpdate();
	            		JOptionPane.showMessageDialog(dialog, "Mot de passe modifié avec succès !");
	            	}
	            	else {
	            		JOptionPane.showMessageDialog(dialog, "Le mot de passe ne doit pas être vide !", "Erreur", JOptionPane.ERROR_MESSAGE);
	            	}
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                JOptionPane.showMessageDialog(dialog, "Erreur lors de la modification du mot de passe !", "Erreur", JOptionPane.ERROR_MESSAGE);
	            }
	            dialog.dispose();
	        }
	    });

	  
	}




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

	private void close(Connection con2, PreparedStatement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (con2 != null) {
				con2.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
