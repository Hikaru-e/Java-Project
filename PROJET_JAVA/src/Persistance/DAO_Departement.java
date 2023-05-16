package Persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Metier.Departement;

public class DAO_Departement {
	private Connection con;

	public DAO_Departement() {
		super();
	}

	public ArrayList<Departement> getAll() {
		con = Connexion.ConnectDB();
		ResultSet resultSet = null;
		PreparedStatement ps = null;
		ArrayList<Departement> departements = new ArrayList<Departement>();

		try {
			ps = con.prepareStatement("SELECT * FROM Departement ;");
			resultSet = ps.executeQuery();
			while (resultSet.next()) {

				Departement departement = new Departement(resultSet.getNString("id_dprt"),
						resultSet.getNString("nom_dprt"), resultSet.getNString("email_dprt"));
				departements.add(departement);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps, resultSet);
		}
		return departements;
	}

	public Departement findByID(String ID) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			ps = con.prepareStatement("SELECT * FROM departement WHERE id_dprt=?;");
			ps.setNString(1, ID);
			resultSet = ps.executeQuery();
			if (resultSet.next()) {
				Departement departement = new Departement(resultSet.getNString("id_dprt"),
						resultSet.getNString("nom_dprt"), resultSet.getNString("email_dprt"));
				return departement;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps, resultSet);
		}

		return null;
	}

	public boolean addDepartement(Departement departement) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("INSERT INTO departement (id_dprt,nom_dprt,email_dprt) VALUES(?,?,?);");
			ps.setNString(1, departement.getID());
			ps.setNString(2, departement.getNom());
			ps.setNString(3, departement.getEmail());
			ps.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public boolean UpdateDepartement(Departement departement) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("UPDATE departement SET nom_dprt=?, email_dprt=? WHERE id_dprt=?;");
			ps.setNString(1, departement.getNom());
			ps.setNString(2, departement.getEmail());
			ps.setNString(3, departement.getID());
			ps.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public boolean DeleteDepartement(String ID) {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("DELETE FROM departement WHERE id_dprt=?;");
			ps.setNString(1, ID);
			ps.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			close(con, ps);
		}
		return false;
	}

	public int countDepartement() {
		con = Connexion.ConnectDB();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int Count = 0;
		try {
			ps = con.prepareStatement("SELECT COUNT(*) FROM departement;");
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
