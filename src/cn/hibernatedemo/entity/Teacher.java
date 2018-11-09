package cn.hibernatedemo.entity;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private Integer TId;
	private String TName;
	private String TSex;
	private String TPassword;
	private String TPhone;
	private String TEmaill;
	private String TOffice;
	private String TMajor;
	private String TCollege;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** full constructor */
	public Teacher(String TName, String TSex, String TPassword, String TPhone,
			String TEmaill, String TOffice, String TMajor, String TCollege) {
		this.TName = TName;
		this.TSex = TSex;
		this.TPassword = TPassword;
		this.TPhone = TPhone;
		this.TEmaill = TEmaill;
		this.TOffice = TOffice;
		this.TMajor = TMajor;
		this.TCollege = TCollege;
	}

	// Property accessors

	public Integer getTId() {
		return this.TId;
	}

	public void setTId(Integer TId) {
		this.TId = TId;
	}

	public String getTName() {
		return this.TName;
	}

	public void setTName(String TName) {
		this.TName = TName;
	}

	public String getTSex() {
		return this.TSex;
	}

	public void setTSex(String TSex) {
		this.TSex = TSex;
	}

	public String getTPassword() {
		return this.TPassword;
	}

	public void setTPassword(String TPassword) {
		this.TPassword = TPassword;
	}

	public String getTPhone() {
		return this.TPhone;
	}

	public void setTPhone(String TPhone) {
		this.TPhone = TPhone;
	}

	public String getTEmaill() {
		return this.TEmaill;
	}

	public void setTEmaill(String TEmaill) {
		this.TEmaill = TEmaill;
	}

	public String getTOffice() {
		return this.TOffice;
	}

	public void setTOffice(String TOffice) {
		this.TOffice = TOffice;
	}

	public String getTMajor() {
		return this.TMajor;
	}

	public void setTMajor(String TMajor) {
		this.TMajor = TMajor;
	}

	public String getTCollege() {
		return this.TCollege;
	}

	public void setTCollege(String TCollege) {
		this.TCollege = TCollege;
	}

}