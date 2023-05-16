package Persistance;

import java.sql.*;
import java.util.ArrayList;

import Metier.Laboratoire;
import Metier.Etudiant;

public class DAO_Laboratoire {

	private Connection con;

	public DAO_Laboratoire() {
		super();
	}

	public ArrayList<Laboratoire> getAll() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Laboratoire> laboratoire = new ArrayList<Laboratoire>();
		try {
			ps = con.prepareStatement("SELECT * FROM laboratoire;");
			rs = ps.executeQuery();
			while (rs.next()) {
				laboratoire.add(
						new Laboratoire(rs.getNString("id_lab"), rs.getNString("nom_lab"), rs.getNString("email_lab")));
			}
			return laboratoire;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return laboratoire;
	}

	public Laboratoire findById(String id) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM laboratoire WHERE id_lab=?;");
			ps.setNString(1, id);
			rs = ps.executeQuery();
			Laboratoire laboratoire = null;
			if (rs.next()) {
			laboratoire = new Laboratoire(rs.getNString("id_lab"), rs.getNString("nom_lab"),
					rs.getNString("email_lab"));
			}
			return laboratoire;
		} catch (SQLException se) {

			se.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return null;
	}

	public boolean addLaboratoire(Laboratoire laboratoire) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("insert into laboratoire (id_lab,nom_lab,email_lab) values(? , ?, ?);");
			ps.setNString(1, laboratoire.getId_lab());
			ps.setNString(2, laboratoire.getNom_lab());
			ps.setNString(3, laboratoire.getEmail_lab());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public boolean deleteLaboratoire(String id) {

		con = Connexion.ConnectDB();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("Delete from laboratoire where id_lab = ?;");
			ps.setNString(1, id);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public boolean updateLaboratoire(Laboratoire laboratoire) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update laboratoire Set nom_lab = ? ,email_lab = ? where id_lab=?;");
			ps.setNString(1, laboratoire.getNom_lab());
			ps.setNString(2, laboratoire.getEmail_lab());
			ps.setNString(3, laboratoire.getId_lab());

			ps.executeUpdate();
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public int countLaboratoire() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int Count = 0;
		try {
			ps = con.prepareStatement("SELECT COUNT(*) FROM laboratoire;");
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

	public ArrayList<Etudiant> getEtudiantbyLab(String id) {
		ArrayList<Etudiant> etudiant = new ArrayList<Etudiant>();
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(
					"SELECT * FROM etudiant,projet  WHERE projet.fk_id_lab =? AND projet.fk_cne_etd = etudiant.cne_etd; ");
			ps.setNString(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				etudiant.add(new Etudiant(rs.getNString("cne_etd"), rs.getNString("nom_etd"), rs.getNString("prenom_etd"),
						rs.getNString("email_etd"),rs.getNString("niveau_etd"),rs.getNString("password_etd"), rs.getNString("fk_id_filiere")));

			}
			return etudiant;
		} catch (SQLException se) {

			se.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return null;
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
