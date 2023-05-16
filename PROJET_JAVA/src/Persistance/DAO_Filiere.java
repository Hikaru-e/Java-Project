package Persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Metier.Filiere;

public class DAO_Filiere {
	private Connection con;

	public DAO_Filiere() {
		super();
	}

	public ArrayList<Filiere> getAll() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Filiere> filiere = new ArrayList<Filiere>();

		try {
			ps = con.prepareStatement("SELECT * FROM filiere;");
			rs = ps.executeQuery();
			while (rs.next()) {
				filiere.add(new Filiere(rs.getNString("nom_filiere"), rs.getNString("fk_id_dprt")));
			}
			return filiere;
		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			close(con, ps, rs);
		}
		return filiere;
	}

	public Filiere findByNom(String nom) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Filiere filiere = null;
			ps = con.prepareStatement("SELECT * FROM filiere WHERE nom_filiere=?;");
			ps.setNString(1, nom);
			rs = ps.executeQuery();
			if (rs.next()) {
			filiere = new Filiere(rs.getNString("nom_filiere"), rs.getNString("fk_id_dprt"));
			}
			return filiere;
		} catch (SQLException se) {

			se.printStackTrace();
		}
		finally {
			close(con, ps, rs);
		}
		return null;
	}

	public boolean addFiliere(Filiere filiere) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO filiere (nom_filiere ,fk_id_dprt) values (?,?);");
			ps.setNString(1, filiere.getNom_filiere());
			ps.setNString(2, filiere.getFk_id_dprt());
			ps.execute();
			return true;
		} catch (SQLException se) {

			se.printStackTrace();
		}
		finally {
			close(con, ps);
		}
		return false;
	}

	public boolean deleteFiliere(String nom_fil) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM filiere WHERE nom_filiere =?;");
			ps.setNString(1, nom_fil);
			ps.execute();
			return true;
		} catch (SQLException se) {

			se.printStackTrace();
		}
		finally {
			close(con, ps);
		}
		return false;
	}

	public boolean updateFiliere(String nom_fil, Filiere filiere) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE filiere SET nom_filiere = ?, fk_id_dprt=? WHERE nom_filiere =? ;");
			ps.setNString(1, filiere.getNom_filiere());
			ps.setNString(2, filiere.getFk_id_dprt());
			ps.setNString(3, nom_fil);
			ps.execute();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		finally {
			close(con, ps);
		}
		return false;
	}

	public int countFiliere() {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		ResultSet res = null;
		int Count = 0;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM filiere;");
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
