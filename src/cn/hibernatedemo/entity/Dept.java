package cn.hibernatedemo.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Dept entity. @author MyEclipse Persistence Tools
 */

public class Dept implements java.io.Serializable {

	// Fields

	private Integer id;
	private String deptName;
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Dept() {
	}

	/** full constructor */
	public Dept(String deptName, Set users) {
		this.deptName = deptName;
		this.users = users;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}