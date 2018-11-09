package cn.hibernatedemo.entity;

/**
 * ElectiveInfo entity. @author MyEclipse Persistence Tools
 */

public class ElectiveInfo implements java.io.Serializable {

	// Fields

	private Integer eiId;
	private Curriculum curriculum;
	private Student student;
	private Integer eiStatus;
	private Integer eiSemester;
	private Integer eiCprogress;

	// Constructors

	/** default constructor */
	public ElectiveInfo() {
	}

	/** full constructor */
	public ElectiveInfo(Curriculum curriculum, Student student,
			Integer eiStatus, Integer eiSemester, Integer eiCprogress) {
		this.curriculum = curriculum;
		this.student = student;
		this.eiStatus = eiStatus;
		this.eiSemester = eiSemester;
		this.eiCprogress = eiCprogress;
	}

	// Property accessors

	public Integer getEiId() {
		return this.eiId;
	}

	public void setEiId(Integer eiId) {
		this.eiId = eiId;
	}

	public Curriculum getCurriculum() {
		return this.curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getEiStatus() {
		return this.eiStatus;
	}

	public void setEiStatus(Integer eiStatus) {
		this.eiStatus = eiStatus;
	}

	public Integer getEiSemester() {
		return this.eiSemester;
	}

	public void setEiSemester(Integer eiSemester) {
		this.eiSemester = eiSemester;
	}

	public Integer getEiCprogress() {
		return this.eiCprogress;
	}

	public void setEiCprogress(Integer eiCprogress) {
		this.eiCprogress = eiCprogress;
	}

}