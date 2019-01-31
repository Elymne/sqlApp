package modele.entities;

public class Emprunt_Export {

	private String nom;
	private String prenom;
	private int numAdh;
	private int isbn;
	private int numInv;

	public Emprunt_Export(String nom, String prenom, int numAdh, int isbn, int numInv) {
		this.nom = nom;
		this.prenom = prenom;
		this.numAdh = numAdh;
		this.isbn = isbn;
		this.numInv = numInv;
	}

	public int getNumInv() {
		return numInv;
	}

	public void setNumInv(int numInv) {
		this.numInv = numInv;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	public int getNumAdh() {
		return numAdh;
	}

	public void setNumAdh(int numAdh) {
		this.numAdh = numAdh;
	}

	@Override
	public String toString() {
		return "Emprunt_Export [nom=" + nom + ", prenom=" + prenom + ", numAdh=" + numAdh + ", isbn=" + isbn
				+ ", numInv=" + numInv + "]";
	}

}
