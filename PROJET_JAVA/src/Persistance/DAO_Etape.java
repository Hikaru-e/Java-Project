package Persistance;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Metier.Etape;

public class DAO_Etape {

	private Connection con;

	public DAO_Etape() {
		super();
//		con = Connexion.ConnectDB();
	}


	public ArrayList<Etape> getEtapesByProjet(String id){
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<Etape> etapes = new ArrayList<Etape>();
		try {
			stmt = con.prepareStatement(
					"SELECT * FROM etape,projet_etape WHERE projet_etape.fk_id_etape = etape.id_etape AND projet_etape.fk_id_prj = ?");
			stmt.setNString(1, id);

			res = stmt.executeQuery();
			while (res.next()) {
//				InputStream inputStream = res.getBinaryStream("livraison");
//				byte[] fileData = inputStream.readAllBytes();
				
				etapes.add(new Etape(res.getNString("id_etape"), res.getNString("duree_etape"),
						res.getDate("etape_debut"), res.getNString("livraison"), res.getNString("documentation")));
			}

			return etapes;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(con, stmt, res);
		}
		return etapes;
	}
	
	public boolean addEtape(Etape etape) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO etape (id_etape, duree_etape, etape_debut, livraison, documentation) VALUES (?, ?, ?, ?, ?)");
			stmt.setNString(1, etape.getId_etape());
			stmt.setNString(2, etape.getDuree_etape());
			
			Date temp = etape.getEtape_debut();
			temp.setYear(etape.getEtape_debut().getYear()-1900);
			temp.setMonth(etape.getEtape_debut().getMonth()-1);
		
			stmt.setDate(3, temp);
			
			stmt.setNString(4, etape.getLivraison());
			stmt.setNString(5, etape.getDocumentation());
			
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(con, stmt);
		}
		return false;
	}
	public boolean updateEtape(Etape etape) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(
					"UPDATE etape SET duree_etape = ?, etape_debut = ?, livraison = ?, documentation = ? WHERE id_etape = ?");
			stmt.setNString(1, etape.getDuree_etape());
			
			Date temp = etape.getEtape_debut();
			temp.setYear(etape.getEtape_debut().getYear()-1900);
			temp.setMonth(etape.getEtape_debut().getMonth()-1);
			
			stmt.setDate(2, temp);
			stmt.setNString(3, etape.getLivraison());
			stmt.setNString(4, etape.getDocumentation());
			stmt.setNString(5, etape.getId_etape());
			
			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(con, stmt);
		}
		return false;
	}
	public boolean deleteEtape(String id) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("DELETE FROM etape WHERE id_etape = ?");
			stmt.setNString(1, id);

			stmt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(con, stmt);
		}
		return false;
	}
	
	public int countEtapeByProjet(String id) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		ResultSet res = null;
		int Count = 0;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM etape, projet_etape where fk_id_prj=? AND fk_id_etape=id_etape;");
			stmt.setNString(1, id);
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
	
	public Etape getEtapeById(String idEtape) {
		con = Connexion.ConnectDB();
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			Etape etape = null;
			stmt = con.prepareStatement("SELECT * FROM etape WHERE id_etape = ?;");
			stmt.setNString(1, idEtape);

			res = stmt.executeQuery();
			if (res.next()) {
				etape = new Etape();
				etape.setId_etape(idEtape);
				etape.setDocumentation(res.getNString("documentation"));
				etape.setDuree_etape(res.getString("duree_etape"));
				etape.setEtape_debut(res.getDate("etape_debut"));
				etape.setLivraison(res.getNString("livraison"));
				
			}

			return etape;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, stmt, res);
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
