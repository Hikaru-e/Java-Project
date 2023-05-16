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

import Metier.Professeur;

public class DAO_Professeur {

	private Connection con;

	public DAO_Professeur() {
		super();
	}

	public ArrayList<Professeur> getAll() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Professeur> professeur = new ArrayList<Professeur>();
		try {
			ps = con.prepareStatement("SELECT * FROM professeur;");
			rs = ps.executeQuery();
			while (rs.next()) {
				professeur
						.add(new Professeur(rs.getNString("id_prf"), rs.getNString("nom_prf"), rs.getNString("prenom_prf"),
								rs.getNString("email_prf"), rs.getNString("grade"), rs.getNString("role_prf"),
								rs.getNString("password_prf"), rs.getNString("fk_id_lab"), rs.getNString("fk_id_dprt")));
			}
			return professeur;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return professeur;
	}

	public Professeur findById(String id) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Professeur professeur = null;
			ps = con.prepareStatement("SELECT * FROM professeur WHERE id_prf=?;");
			ps.setNString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
			professeur = new Professeur(rs.getNString("id_prf"), rs.getNString("nom_prf"),
					rs.getNString("prenom_prf"), rs.getNString("email_prf"), rs.getNString("grade"),
					rs.getNString("role_prf"), rs.getNString("password_prf"), rs.getNString("fk_id_lab"),
					rs.getNString("fk_id_dprt"));
			}
			return professeur;
		} catch (SQLException se) {

			se.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return null;
	}

	public boolean addProfesseur(Professeur professeur) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO professeur"
					+ " (id_prf,nom_prf,prenom_prf,email_prf,grade,role_prf,password_prf, fk_id_lab,fk_id_dprt) "
					+ "values (?,?,?,?,?,?,?,?,?);");
			ps.setNString(1, professeur.getId_prf());
			ps.setNString(2, professeur.getNom_prf());
			ps.setNString(3, professeur.getPrenom_prf());
			ps.setNString(4, professeur.getEmail_prf());
			ps.setNString(5, professeur.getGrade());
			ps.setNString(6, professeur.getRole_prf());
			ps.setNString(7, professeur.getPassword_prf());
			ps.setNString(8, professeur.getFk_id_lab());
			ps.setNString(9, professeur.getFk_id_dprt());

			ps.execute();
			return true;
		} catch (SQLException se) {

			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public boolean deleteProfesseur(String id_prof) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM professeur WHERE id_prf=?;");
			ps.setNString(1, id_prof);
			ps.execute();
			return true;
		} catch (SQLException se) {

			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public boolean updateProfesseur(Professeur professeur) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"UPDATE professeur SET nom_prf=?, prenom_prf=?, email_prf=?, grade=?, role_prf=?,password_prf=?, fk_id_lab=?, fk_id_dprt=?  WHERE id_prf=? ;");
			ps.setNString(1, professeur.getNom_prf());
			ps.setNString(2, professeur.getPrenom_prf());
			ps.setNString(3, professeur.getEmail_prf());
			ps.setNString(4, professeur.getGrade());
			ps.setNString(5, professeur.getRole_prf());
			ps.setNString(6, professeur.getPassword_prf());
			ps.setNString(7, professeur.getFk_id_lab());
			ps.setNString(8, professeur.getFk_id_dprt());
			ps.setNString(9, professeur.getId_prf());
			ps.execute();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public int countProfesseur() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int Count = 0;
		try {
			ps = con.prepareStatement("SELECT COUNT(*) FROM professeur;");
			rs = ps.executeQuery();
			if (rs.next()) {
				Count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}

		return Count;
	}
	public void modifierPassword(Professeur prof) {
	    JDialog dialog = new JDialog();
	    dialog.setTitle("Enter your new Password");
	    
	    JPanel panel = new JPanel(new GridLayout(0, 1));
	    
	    panel.add(new JLabel("Mot de passe :"));
	    
	    JTextField pwdField = new JTextField();
	    panel.add(pwdField);
	    
	    JButton ModifierButton = new JButton("Modifier");
	    ModifierButton.setForeground(Color.WHITE);
	    ModifierButton.setBackground(new Color(52, 73, 94));
	    panel.setBorder(new EmptyBorder(10, 0, 10, 0));
	    ModifierButton.setBorder(new EmptyBorder(5, 30, 5, 30));
	    
	    dialog.add(panel);
	    dialog.add(ModifierButton, BorderLayout.SOUTH);
	    
	    dialog.pack();
	    dialog.setLocationRelativeTo(null);
	    dialog.setVisible(true);
	    dialog.setResizable(false);
	    dialog.setSize(new Dimension(128,128));
	    
	    ModifierButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            try {
	            	if (con.isClosed()) {
	                    // si la connexion est fermée, la rétablir
	            		con = Connexion.ConnectDB();
	                }
	            	if (!pwdField.getText().isEmpty())
	            	{
	            		PreparedStatement ps = con.prepareStatement("UPDATE professeur SET password_prf=? WHERE id_prf=?;");
	            		ps.setString(1, pwdField.getText());
	            		ps.setString(2, prof.getId_prf());
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
	
	public boolean addProfFiliere(String id_prof, String id_filiere) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO prof_filiere (fk_id_prof, fk_id_filiere) VALUES (?,?) ;");
			stmt.setNString(1, id_prof);
			stmt.setNString(2, id_filiere);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt);
		}
		return false;
	}

	public boolean deleteProfFiliere(String id_prof, String id_filiere) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("DELETE FROM prof_filiere WHERE fk_id_prof = ? and fk_id_filiere= ?;");
			stmt.setNString(1, id_prof);
			stmt.setNString(2, id_filiere);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt);
		}
		return false;
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