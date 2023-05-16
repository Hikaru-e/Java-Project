package Persistance;

import java.sql.*;
import java.util.ArrayList;
import Metier.Entreprise;

public class DAO_Entreprise {

	private Connection con;

	public DAO_Entreprise() {
		super();
	}

	public ArrayList<Entreprise> getAll() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Entreprise> entreprise = new ArrayList<Entreprise>();
		try {
			ps = con.prepareStatement("SELECT * FROM entreprise;");
			rs = ps.executeQuery();
			while (rs.next()) {
				entreprise.add(new Entreprise(rs.getNString("id_ent"), rs.getNString("raisone_sociale"),
						rs.getNString("adresse_ent"), rs.getNString("tele_ent"), rs.getNString("email_ent"),
						rs.getNString("responsable_ent")));
			}
			return entreprise;
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return entreprise;
	}

	public Entreprise findById(String id) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Entreprise entreprise = null;
			ps = con.prepareStatement("SELECT * FROM entreprise WHERE id_ent=?;");
			ps.setNString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
			entreprise = new Entreprise(rs.getNString("id_ent"), rs.getNString("raisone_sociale"),
					rs.getNString("adresse_ent"), rs.getNString("tele_ent"), rs.getNString("email_ent"),
					rs.getNString("responsable_ent"));
			}
			return entreprise;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return null;
	}

	public boolean addEntreprise(Entreprise entreprise) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"INSERT INTO entreprise (id_ent,raisone_sociale,adresse_ent,tele_ent,email_ent,responsable_ent) values (?,?,?,?,?,?);");
			ps.setNString(1, entreprise.getId_ent());
			ps.setNString(2, entreprise.getRaisone_sociale());
			ps.setNString(3, entreprise.getAdresse_ent());
			ps.setNString(4, entreprise.getTele_ent());
			ps.setNString(5, entreprise.getEmail_ent());
			ps.setNString(6, entreprise.getResponsable_ent());

			ps.execute();
			return true;
		} catch (SQLException se) {

			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public boolean deleteEntreprise(String id_entreprise) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM entreprise WHERE id_ent=?;");
			ps.setNString(1, id_entreprise);
			ps.execute();
			return true;
		} catch (SQLException se) {

			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public boolean updateEntreprise(Entreprise entreprise) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"UPDATE entreprise SET raisone_sociale=?, adresse_ent=?, tele_ent=?,email_ent=?,responsable_ent=? WHERE id_ent=?");
			ps.setNString(1, entreprise.getRaisone_sociale());
			ps.setNString(2, entreprise.getAdresse_ent());
			ps.setNString(3, entreprise.getTele_ent());
			ps.setNString(4, entreprise.getEmail_ent());
			ps.setNString(5, entreprise.getResponsable_ent());
			ps.setNString(6, entreprise.getId_ent());
			ps.execute();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public int countEntreprise() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int Count = 0;
		try {
			ps = con.prepareStatement("SELECT COUNT(*) FROM entreprise;");
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
