package Metier;

public class Professeur {
	private String id_prf;
	private String nom_prf;
	private String prenom_prf;
	private String email_prf;
	private String grade;
	private String role_prf;
	private String password_prf;
	private String fk_id_lab;
	private String fk_id_dprt;
	

	
	
	public Professeur(String id_prf, String nom_prf, String prenom_prf, String email_prf, String grade,
			String role_prf, String password_prf) {
		this.id_prf = id_prf;
		this.nom_prf = nom_prf;
		this.prenom_prf = prenom_prf;
		this.email_prf = email_prf;
		this.grade = grade;
		this.role_prf = role_prf;
		this.password_prf = password_prf;
	}

	public Professeur(String id_prf, String nom_prf, String prenom_prf, String email_prf, String grade, String role_prf, String password_prf,
			String fk_id_lab, String fk_id_dprt) {
		super();
		this.id_prf = id_prf;
		this.nom_prf = nom_prf;
		this.prenom_prf = prenom_prf;
		this.email_prf = email_prf;
		this.grade = grade;
		this.role_prf = role_prf;
		this.password_prf = password_prf;
		this.fk_id_lab = fk_id_lab;
		this.fk_id_dprt = fk_id_dprt;
	}
	
	public String getId_prf() {
		return id_prf;
	}

	public void setId_prf(String id_prf) {
		this.id_prf = id_prf;
	}

	public String getNom_prf() {
		return nom_prf;
	}

	public void setNom_prf(String nom_prf) {
		this.nom_prf = nom_prf;
	}

	public String getPrenom_prf() {
		return prenom_prf;
	}

	public void setPrenom_prf(String prenom_prf) {
		this.prenom_prf = prenom_prf;
	}

	public String getEmail_prf() {
		return email_prf;
	}

	public void setEmail_prf(String email_prf) {
		this.email_prf = email_prf;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRole_prf() {
		return role_prf;
	}

	public void setRole_prf(String role_prf) {
		this.role_prf = role_prf;
	}

	public String getFk_id_lab() {
		return fk_id_lab;
	}

	public void setFk_id_lab(String fk_id_lab) {
		this.fk_id_lab = fk_id_lab;
	}

	public String getFk_id_dprt() {
		return fk_id_dprt;
	}

	public void setFk_id_dprt(String fk_id_dprt) {
		this.fk_id_dprt = fk_id_dprt;
	}
	

	public String getPassword_prf() {
		return password_prf;
	}

	public void setPassword_prf(String password_prf) {
		this.password_prf = password_prf;
	}

	@Override
	public String toString() {
		return "Professeur [id_prf=" + id_prf + ", nom_prf=" + nom_prf + ", prenom_prf=" + prenom_prf + ", email_prf="
				+ email_prf + ", grade=" + grade + ", role_prf=" + role_prf + ", password_prf=" + password_prf
				+ ", fk_id_lab=" + fk_id_lab + ", fk_id_dprt=" + fk_id_dprt + "]";
	}
	
	

}
