package cn.hibernatedemo.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer SId;
	private Integer SPassword;
	private String SName;
	private String SSex;
	private Integer SAge;
	private String SNative;
	private String SFamilyname;
	private Integer SSemeter;
	private String SMajor;
	private String SCollege;
	private Integer SClass;
	private String SPhone;
	private String SEmail;
	private String SCet4;
	private Set scores = new HashSet(0);
	private Set electiveInfos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(Integer SPassword, String SName, String SSex, Integer SAge,
			String SNative, String SFamilyname, Integer SSemeter,
			String SMajor, String SCollege, Integer SClass, String SPhone,
			String SEmail, String SCet4, Set scores, Set electiveInfos) {
		this.SPassword = SPassword;
		this.SName = SName;
		this.SSex = SSex;
		this.SAge = SAge;
		this.SNative = SNative;
		this.SFamilyname = SFamilyname;
		this.SSemeter = SSemeter;
		this.SMajor = SMajor;
		this.SCollege = SCollege;
		this.SClass = SClass;
		this.SPhone = SPhone;
		this.SEmail = SEmail;
		this.SCet4 = SCet4;
		this.scores = scores;
		this.electiveInfos = electiveInfos;
	}

	// Property accessors

	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	public Integer getSPassword() {
		return this.SPassword;
	}

	public void setSPassword(Integer SPassword) {
		this.SPassword = SPassword;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getSSex() {
		return this.SSex;
	}

	public void setSSex(String SSex) {
		this.SSex = SSex;
	}

	public Integer getSAge() {
		return this.SAge;
	}

	public void setSAge(Integer SAge) {
		this.SAge = SAge;
	}

	public String getSNative() {
		return this.SNative;
	}

	public void setSNative(String SNative) {
		this.SNative = SNative;
	}

	public String getSFamilyname() {
		return this.SFamilyname;
	}

	public void setSFamilyname(String SFamilyname) {
		this.SFamilyname = SFamilyname;
	}

	public Integer getSSemeter() {
		return this.SSemeter;
	}

	public void setSSemeter(Integer SSemeter) {
		this.SSemeter = SSemeter;
	}

	public String getSMajor() {
		return this.SMajor;
	}

	public void setSMajor(String SMajor) {
		this.SMajor = SMajor;
	}

	public String getSCollege() {
		return this.SCollege;
	}

	public void setSCollege(String SCollege) {
		this.SCollege = SCollege;
	}

	public Integer getSClass() {
		return this.SClass;
	}

	public void setSClass(Integer SClass) {
		this.SClass = SClass;
	}

	public String getSPhone() {
		return this.SPhone;
	}

	public void setSPhone(String SPhone) {
		this.SPhone = SPhone;
	}

	public String getSEmail() {
		return this.SEmail;
	}

	public void setSEmail(String SEmail) {
		this.SEmail = SEmail;
	}

	public String getSCet4() {
		return this.SCet4;
	}

	public void setSCet4(String SCet4) {
		this.SCet4 = SCet4;
	}

	public Set getScores() {
		return this.scores;
	}

	public void setScores(Set scores) {
		this.scores = scores;
	}

	public Set getElectiveInfos() {
		return this.electiveInfos;
	}

	public void setElectiveInfos(Set electiveInfos) {
		this.electiveInfos = electiveInfos;
	}

}