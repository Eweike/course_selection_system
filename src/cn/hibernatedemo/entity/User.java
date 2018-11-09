package cn.hibernatedemo.entity;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private Dept dept;
	private String password;
	private String telephone;
	private String username;
	private String isadmin;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** full constructor */
	public User(Dept dept, String password, String telephone, String username,
			String isadmin) {
		this.dept = dept;
		this.password = password;
		this.telephone = telephone;
		this.username = username;
		this.isadmin = isadmin;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Dept getDept() {
		return this.dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIsadmin() {
		return this.isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}

}