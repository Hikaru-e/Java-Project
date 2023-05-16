package Persistance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Metier.Projet;

public class DAO_Projet {

	private Connection con;

	public DAO_Projet() {
		super();
	}

	public ArrayList<Projet> getAllProjects() {
		con = Connexion.ConnectDB();
		ResultSet res = null;
		PreparedStatement stmt = null;

		ArrayList<Projet> projects = new ArrayList<Projet>();
		try {
			stmt = con.prepareStatement("SELECT * FROM projet;");
			res = stmt.executeQuery();
			while (res.next()) {
				projects.add(new Projet(res.getNString("id_prj"), res.getNString("type_prj"),
						res.getNString("lieu_prj"), res.getNString("titre_prj"), res.getDate("date_depart"),
						res.getFloat("duree_prj"), res.getNString("fk_cne_etd"), res.getNString("fk_id_prf"),
						res.getNString("fk_id_lab"), res.getNString("fk_id_ent")));
			}

			return projects;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, res);
		}
		return projects;
	}

	public Projet getProjectById(String id) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			Projet project = null;
			stmt = con.prepareStatement("SELECT * FROM projet WHERE id_prj = ?;");
			stmt.setNString(1, id);

			res = stmt.executeQuery();
			if (res.next()) {
				project = new Projet();
				project.setId_prj(id);
				project.setType_prj(res.getNString("type_prj"));
				project.setLieu_prj(res.getNString("lieu_prj"));
				project.setTitre_prj(res.getNString("titre_prj"));
				project.setDate_depart(res.getDate("date_depart"));
				project.setDuree_prj(res.getFloat("duree_prj"));
				project.setFk_cne_etd(res.getNString("fk_cne_etd"));
				project.setFk_id_prf(res.getNString("fk_id_prf"));
				project.setFk_id_lab(res.getNString("fk_id_lab"));
				project.setFk_id_ent(res.getNString("fk_id_ent"));
			}

			return project;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, res);
		}
		return null;
	}

	public boolean addProject(Projet project) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"INSERT INTO projet (id_prj, type_prj, lieu_prj, titre_prj, date_depart, duree_prj, fk_cne_etd, fk_id_prf , fk_id_lab, fk_id_ent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			stmt.setNString(1, project.getId_prj());
			stmt.setNString(2, project.getType_prj());
			stmt.setNString(3, project.getLieu_prj());
			stmt.setNString(4, project.getTitre_prj());

			Date temp = project.getDate_depart();
			temp.setYear(project.getDate_depart().getYear() - 1900);
			temp.setMonth(project.getDate_depart().getMonth() - 1);

			if (project.getType_prj().equals("PFE")) {
				if (temp.getMonth() >= 1 && temp.getMonth() <= 3)
					stmt.setDate(5, temp);
				else
					JOptionPane.showMessageDialog(null, "Les projets PFE démarrent en février au plus tôt", "Error",
							JOptionPane.ERROR_MESSAGE);

			} else if (project.getType_prj().equals("PFA")) {
				if (temp.getMonth() == 5)
					stmt.setDate(5, temp);
				else
					JOptionPane.showMessageDialog(null, "Les projets PFA démarrent en Juin et se terminent en Aout",
							"Error", JOptionPane.ERROR_MESSAGE);
			} else if (project.getType_prj().equals("Doctorat")) {
				if (temp.getMonth() == 9)
					stmt.setDate(5, temp);
				else
					JOptionPane.showMessageDialog(null, "Les doctorats démarrent en octobre", "Error",
							JOptionPane.ERROR_MESSAGE);
			}

			stmt.setFloat(6, project.getDuree_prj());
			stmt.setNString(7, project.getFk_cne_etd());
			stmt.setNString(8, project.getFk_id_prf());
			stmt.setNString(9, project.getFk_id_lab());
			stmt.setNString(10, project.getFk_id_ent());

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt);
		}
		return false;
	}

	public boolean updateProject(Projet project) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"UPDATE projet SET type_prj = ?, lieu_prj = ?, titre_prj = ?, date_depart = ?, duree_prj = ?,fk_cne_etd = ?, fk_id_prf = ?, fk_id_lab = ?, fk_id_ent = ? WHERE id_prj = ?;");
			stmt.setNString(1, project.getType_prj());
			stmt.setNString(2, project.getLieu_prj());
			stmt.setNString(3, project.getTitre_prj());

			Date temp = project.getDate_depart();
			temp.setYear(project.getDate_depart().getYear() - 1900);
			temp.setMonth(project.getDate_depart().getMonth() - 1);

			if (project.getType_prj().equals("PFE")) {
				if (temp.getMonth() >= 1 && temp.getMonth() <= 3)
					stmt.setDate(4, temp);
				else
					JOptionPane.showMessageDialog(null, "Les projets PFE démarrent en février au plus tôt", "Error",
							JOptionPane.ERROR_MESSAGE);

			} else if (project.getType_prj().equals("PFA")) {
				if (temp.getMonth() == 5)
					stmt.setDate(4, temp);
				else
					JOptionPane.showMessageDialog(null, "Les projets PFA démarrent en Juin et se terminent en Aout",
							"Error", JOptionPane.ERROR_MESSAGE);
			} else if (project.getType_prj().equals("Doctorat")) {
				if (temp.getMonth() == 9)
					stmt.setDate(4, temp);
				else
					JOptionPane.showMessageDialog(null, "Les doctorats démarrent en octobre", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
			stmt.setFloat(5, project.getDuree_prj());
			stmt.setNString(6, project.getFk_cne_etd());
			stmt.setNString(7, project.getFk_id_prf());
			stmt.setNString(8, project.getFk_id_lab());
			stmt.setNString(9, project.getFk_id_ent());
			stmt.setNString(10, project.getId_prj());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt);
		}
		return false;
	}

	public boolean deleteProject(String id) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM projet WHERE id_prj = ?;");
			stmt.setNString(1, id);

			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt);
		}
		return false;
	}

	public Integer countProjets() {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		ResultSet res = null;
		int Count = 0;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM projet;");
			res = stmt.executeQuery();
			if (res.next()) {
				Count = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, res);
		}

		return Count;
	}

	public ArrayList<Projet> getProjetByProf(String id_prof) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<Projet> liste = new ArrayList<Projet>();
		try {
			stmt = con.prepareStatement("SELECT * FROM projet WHERE fk_id_prf = ?;");
			stmt.setNString(1, id_prof);
			res = stmt.executeQuery();
			while (res.next()) {
				liste.add(new Projet(res.getNString("id_prj"), res.getNString("type_prj"), res.getNString("lieu_prj"),
						res.getNString("titre_prj"), res.getDate("date_depart"), res.getFloat("duree_prj"),
						res.getNString("fk_cne_etd"), res.getNString("fk_id_prf"), res.getNString("fk_id_lab"),
						res.getNString("fk_id_ent")));
			}
			return liste;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, res);
		}
		return null;
	}

	public boolean addProjetEtape(String id_projet, String id_etape) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO projet_etape (fk_id_prj, fk_id_etape) VALUES (?,?) ;");
			stmt.setNString(1, id_projet);
			stmt.setNString(2, id_etape);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt);
		}
		return false;
	}

	public boolean deleteProjetEtape(String id_projet, String id_etape) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("DELETE FROM projet_etape WHERE fk_id_prj = ? and fk_id_etape= ?;");
			stmt.setNString(1, id_projet);
			stmt.setNString(2, id_etape);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt);
		}
		return false;
	}
	
//	public boolean ifProfHasProjet(String id_prf) {
//		con = Connexion.ConnectDB();
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		int Count = 0;
//		try {
//			ps = con.prepareStatement("SELECT COUNT(fk_id_prf) FROM projet WHERE fk_id_prf=?;");
//			ps.setNString(1, id_prf);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				Count = rs.getInt(1);
//			}
//			if (Count>0)
//				return false;
//			else
//				return true;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(con, ps, rs);
//		}
//		return false;
//	}
	
	

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
