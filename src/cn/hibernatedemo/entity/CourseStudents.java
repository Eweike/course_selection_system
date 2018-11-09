/**
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月28日2018下午8:02:24
 */
package cn.hibernatedemo.entity;

/**
 * @classNamw:CourseStudents
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月28日2018下午8:02:24
 */
public class CourseStudents implements java.io.Serializable {
	private Integer eiId;
	private Integer SId;
	private String SName;
	private Integer eiSemester;
	private String eiCprogress;
	private Integer score;
	private Integer scId;
	private Float gpa;
	private Integer cid;
	
	public CourseStudents() {
		super();
	}
	public Integer getEiId() {
		return eiId;
	}
	public void setEiId(Integer eiId) {
		this.eiId = eiId;
	}
	public Integer getSId() {
		return SId;
	}
	public void setSId(Integer sId) {
		SId = sId;
	}
	public String getSName() {
		return SName;
	}
	public void setSName(String sName) {
		SName = sName;
	}
	public Integer getEiSemester() {
		return eiSemester;
	}
	public void setEiSemester(Integer eiSemester) {
		this.eiSemester = eiSemester;
	}
	public String getEiCprogress() {
		return eiCprogress;
	}
	public void setEiCprogress(String eiCprogress) {
		this.eiCprogress = eiCprogress;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getScId() {
		return scId;
	}
	public void setScId(Integer scId) {
		this.scId = scId;
	}
	public Float getGpa() {
		return gpa;
	}
	public void setGpa(Float gpa) {
		this.gpa = gpa;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
}
