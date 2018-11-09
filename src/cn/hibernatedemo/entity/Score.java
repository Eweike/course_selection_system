package cn.hibernatedemo.entity;

/**
 * Score entity. @author MyEclipse Persistence Tools
 */

public class Score implements java.io.Serializable,Comparable<Score> {

	// Fields

	private Integer scId;
	private Curriculum curriculum;
	private Student student;
	private String CName;
	private Integer scScore;
	private Integer scStatus;

	// Constructors

	/** default constructor */
	public Score() {
	}

	/** full constructor */
	public Score(Curriculum curriculum, Student student, String CName,
			Integer scScore, Integer scStatus) {
		this.curriculum = curriculum;
		this.student = student;
		this.CName = CName;
		this.scScore = scScore;
		this.scStatus = scStatus;
	}

	// Property accessors

	public Integer getScId() {
		return this.scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
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

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public Integer getScScore() {
		return this.scScore;
	}

	public void setScScore(Integer scScore) {
		this.scScore = scScore;
	}

	public Integer getScStatus() {
		return this.scStatus;
	}

	public void setScStatus(Integer scStatus) {
		this.scStatus = scStatus;
	}

	@Override
	public int compareTo(Score o) {
        return o.getScScore()-this.scScore;
	}

}