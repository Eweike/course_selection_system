/**
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月26日2018上午9:13:57
 */
package com.shf.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import cn.hibernatedemo.dao.CurriculumDao;
import cn.hibernatedemo.dao.ElectiveInfoDao;
import cn.hibernatedemo.dao.HibernateUtil;
import cn.hibernatedemo.dao.ScoreDao;
import cn.hibernatedemo.dao.StudentDao;
import cn.hibernatedemo.entity.CourseStudents;
import cn.hibernatedemo.entity.Curriculum;
import cn.hibernatedemo.entity.ElectiveInfo;
import cn.hibernatedemo.entity.Score;
import cn.hibernatedemo.entity.Student;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @classNamw:courseAction
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月26日2018上午9:13:57
 */
@SuppressWarnings("serial")
// ajax要使用json传递数据，必须继承这个包
@ParentPackage(value = "json-default")
@Action(value = "shf", results = {
		// ajax的方法，返回的Type必须是json
		@Result(name = "courses", type = "json"),
		@Result(name = "success", type = "json", location = "../../WebRoot/myCourse-teacher.jsp") })
public class courseAction extends ActionSupport {
	// 需转换输出的Json数据
	private List<Curriculum> curriculumList; // 课程列表
	private List<CourseStudents> courseStudentsList; // 学生名单
	private String CNameDetail; // 课程名称
	private List<CourseStudents> updataResult; // 更新返回信息
	private String subResult; // 提交成功后返回信息
	private Score addNewScore; // 添加成绩信息
	private List<Float> scoreList; // 成绩列表

	public String getSubResult() {
		return subResult;
	}

	@JSON
	public List<Float> getScoreList() {
		return scoreList;
	}
	@JSON
	public List<CourseStudents> getUpdataResult() {
		return updataResult;
	}

	@JSON
	public Score getAddNewScore() {
		return addNewScore;
	}

	@JSON
	// 要转换输出数据对应的get方法
	public List<Curriculum> getCurriculumList() {
		return curriculumList;
	}

	@JSON
	public List<CourseStudents> getCourseStudentsList() {
		return courseStudentsList;
	}

	@JSON
	public String getCNameDetail() {
		return CNameDetail;
	}

	private CurriculumDao curriculumDao = new CurriculumDao();

	private ElectiveInfoDao electiveInfoDao = new ElectiveInfoDao();
	private StudentDao studentDao = new StudentDao();

	private ScoreDao scoreDao = new ScoreDao();

	public CurriculumDao getCurriculumDao() {
		return curriculumDao;
	}

	public void setCurriculumDao(CurriculumDao curriculumDao) {
		this.curriculumDao = curriculumDao;
	}

	public ElectiveInfoDao getElectiveInfoDao() {
		return electiveInfoDao;
	}

	public void setElectiveInfoDao(ElectiveInfoDao electiveInfoDao) {
		this.electiveInfoDao = electiveInfoDao;
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public ScoreDao getScoreDao() {
		return scoreDao;
	}

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	/**
	 * 
	 * @descript: 根据教师id查询已教授课程信息
	 * @author 20155790 孙鸿飞
	 * @date: 2018年4月28日 下午6:29:26
	 */
	public String curriculumList() {
		curriculumList = findCurriculumListFromDB();
		return SUCCESS;
	}

	private List<Curriculum> findCurriculumListFromDB() {
		List<Curriculum> newCurriculumList = new ArrayList<Curriculum>();
		Transaction tx = null;
		try {
			tx = curriculumDao.currentSession().beginTransaction();
			newCurriculumList = curriculumDao.findByTid(1);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return newCurriculumList;
	}

	/**
	 * 
	 * @descript: 获取课程名
	 * @author 20155790 孙鸿飞
	 * @date: 2018年4月28日 下午9:08:34
	 */
	public String CNameDetail() {
		int cid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("cid"));
		CNameDetail = findCNameFromDB(cid);
		return SUCCESS;
	}

	private String findCNameFromDB(Integer cid) {
		Transaction tx = null;
		String cName = new String();
		try {
			tx = curriculumDao.currentSession().beginTransaction();
			Curriculum newCurriculum = curriculumDao.get(cid);
			cName = newCurriculum.getCName();
			System.out.println("cName================"+cName);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return cName;
	}

	/**
	 *
	 * @descript: 在成绩管理详情页，根据课程id查询学生选课信息
	 * @author 20155790 孙鸿飞
	 * @date: 2018年4月28日 下午6:28:30
	 */
	public String courseStudentsList() {
		System.out.println("进入courseStudentsList");
		int cid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("cid"));
		courseStudentsList = findCourseStudentsListFromDB(cid);
		return SUCCESS;
	}

	private List<CourseStudents> findCourseStudentsListFromDB(Integer cid) {
		List<ElectiveInfo> newElectiveInfoList = new ArrayList<ElectiveInfo>();
		List<CourseStudents> newCourseStudentsList = new ArrayList<CourseStudents>();
		Transaction tx = null;
		try {
			tx = electiveInfoDao.currentSession().beginTransaction();
			Curriculum curriculum = new CurriculumDao().get(cid);
			System.out.println("课程名："+curriculum.getCName());
			newElectiveInfoList = electiveInfoDao.findByCid(curriculum);
			// 遍历并输出结果
			for (ElectiveInfo electiveInfo : newElectiveInfoList) {
				int eiId = electiveInfo.getEiId();
				int SId = electiveInfo.getStudent().getSId();
				int eiSemester = electiveInfo.getEiSemester();
				int eiCprogress = electiveInfo.getEiCprogress();
				Student student = (Student) studentDao.get(SId);
				CourseStudents courseStudents = new CourseStudents();
				courseStudents.setEiId(eiId);
				courseStudents.setSId(SId);
				courseStudents.setSName(student.getSName());
				courseStudents.setEiSemester(eiSemester);
				;
				if (eiCprogress == 1) {
					courseStudents.setEiCprogress("授课中");
				} else {
					courseStudents.setEiCprogress("已结课");
				}
				int newScore = 0;
				List<Score> scoreList = scoreDao.findByCidSid(curriculum, student);
				for (Score score : scoreList) {
					courseStudents.setScId(score.getScId());
					if (score.getScScore() != null) {
						newScore = score.getScScore();
						float gpa = gpa(newScore);
						courseStudents.setGpa(gpa);
						if (eiCprogress == 1 && score.getScScore() < 60) {
							courseStudents.setEiCprogress("重修中");
						} else if (eiCprogress == 0 && score.getScScore() < 60) {
							courseStudents.setEiCprogress("已重修");
						} else {
							courseStudents.setEiCprogress("已结课");
						}
					}
				}
				courseStudents.setScore(newScore);
				newCourseStudentsList.add(courseStudents);
			}
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return newCourseStudentsList;
	}

	public Float gpa(Integer score) {
		float gpa = 0;
		if (score >= 60 && score < 65) {
			gpa = 1.0f;
		} else if (score >= 65 && score < 70) {
			gpa = 1.5f;
		} else if (score >= 70 && score < 75) {
			gpa = 2.0f;
		} else if (score >= 75 && score < 80) {
			gpa = 2.5f;
		} else if (score >= 80 && score < 85) {
			gpa = 3.0f;
		} else if (score >= 85 && score < 90) {
			gpa = 3.5f;
		} else if (score >= 90 && score < 95) {
			gpa = 4.0f;
		} else if (score >= 95 && score < 100) {
			gpa = 4.5f;
		} else if (score == 100) {
			gpa = 5.0f;
		}
		return gpa;
	}

	/**
	 * 
	 * @descript: 修改学生成绩
	 * @author 20155790 孙鸿飞
	 * @date: 2018年4月29日 下午11:33:02
	 */
	public String upDataScore() {
		int scId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("scId"));
		int newScore = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("score"));
		updataResult = updataScoreFromDB(scId, newScore);
		return SUCCESS;
	}

	private List<CourseStudents> updataScoreFromDB(Integer scId, int newScore) {
		Transaction tx = null;
		List<CourseStudents> result = new ArrayList<CourseStudents>();
		try {
			tx = curriculumDao.currentSession().beginTransaction();
			Score scoreToUpdate = scoreDao.load(scId);
			scoreToUpdate.setScScore(newScore);
			CourseStudents temp= new CourseStudents();
			temp.setScore(newScore);
			temp.setGpa(gpa(newScore));
			result.add(temp);
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	/**
	 * 
	 * @descript: 添加学生成绩（已废弃）
	 * @author 20155790 孙鸿飞
	 * @date: 2018年4月29日 下午11:33:02
	 */
	public String addScore() {
		int eiId = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("eiId"));
		int newScore = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("score"));
		addNewScore = addScoreFromDB(eiId, newScore);
		return SUCCESS;
	}

	private Score addScoreFromDB(int eiId, int newScore) {
		Transaction tx = null;
		Score scoreToAdd = new Score();
		Score scoreToAdd2 = new Score();
		try {
			tx = curriculumDao.currentSession().beginTransaction();
			ElectiveInfo electiveInfo = electiveInfoDao.get(eiId);
			int cId = electiveInfo.getCurriculum().getCId();
			int Sid = electiveInfo.getStudent().getSId();
			Curriculum curriculum = curriculumDao.get(cId);
			String cName = curriculum.getCName();

			scoreToAdd.setScScore(newScore);
			scoreToAdd.setCurriculum(curriculumDao.get(cId));
			scoreToAdd.setCName(cName);
			scoreToAdd.setStudent(studentDao.get(Sid));;
			scoreToAdd.setScStatus(0);
			scoreDao.save(scoreToAdd);
			List<Score> newScoreList = scoreDao.findByCidSid(curriculum,electiveInfo.getStudent() );
			for (Score score : newScoreList) {
				scoreToAdd2 = score;
			}
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return scoreToAdd2;
	}

	/**
	 * 
	 * @descript: 查询当前课程学生成绩统计信息
	 * @author 20155790 孙鸿飞
	 * @date: 2018年5月3日 上午11:30:51
	 */
	public String statisticsScore() {
		int cid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("cid"));
		scoreList = statisticsScoreFromDB(cid);
		return SUCCESS;
	}

	private List<Float> statisticsScoreFromDB(int cid) {
		List<ElectiveInfo> newElectiveInfoList = new ArrayList<ElectiveInfo>();
		List<Float> newScoreList = new ArrayList<Float>();
		Transaction tx = null;
		try {
			tx = electiveInfoDao.currentSession().beginTransaction();
			Curriculum curriculum = new CurriculumDao().get(cid);
			newElectiveInfoList = electiveInfoDao.findByCid(curriculum);
			// 遍历并输出结果
			for (ElectiveInfo electiveInfo : newElectiveInfoList) {
				int SId = electiveInfo.getStudent().getSId();
				List<Score> scoreList = scoreDao.findByCidSid(curriculum, electiveInfo.getStudent());
				newScoreList = scoreDao.findKindScore(curriculum);
				for (Score score : scoreList) {
					int scId = score.getScId();
				}
				float numTotal = scoreDao.findScoreByCondition(0, 101, curriculum);
				float num0to59 = scoreDao.findScoreByCondition(0, 59, curriculum);
				float num60To69 = scoreDao.findScoreByCondition(60, 69, curriculum);
				float num70To79 = scoreDao.findScoreByCondition(70, 79, curriculum);
				float num80To89 = scoreDao.findScoreByCondition(80, 89, curriculum);
				float num90To99 = scoreDao.findScoreByCondition(90, 99, curriculum);
				float num100 = scoreDao.findScoreByCondition(100, 100, curriculum);

				newScoreList.add(num0to59 / numTotal * 100);
				newScoreList.add(num60To69 / numTotal * 100);
				newScoreList.add(num70To79 / numTotal * 100);
				newScoreList.add(num80To89 / numTotal * 100);
				newScoreList.add(num90To99 / numTotal * 100);
				newScoreList.add(num100 / numTotal * 100);

				newScoreList.add(num0to59);
				newScoreList.add((1 - num0to59 / numTotal) * 100);
			}
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return newScoreList;
	}

	/**
	 * 
	 * @descript: 成绩批量录入
	 * @author 20155790 孙鸿飞
	 * @date: 2018年5月3日 上午11:30:51
	 */
	public String subScore() {
		int cid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("cid"));
		subResult = subScoreToDB(cid);
		return SUCCESS;
	}

	private String subScoreToDB(int cid) {
		String result = "成功";
		Transaction tx = null;
		List<ElectiveInfo> newElectiveInfoList = new ArrayList<ElectiveInfo>();
		Score scoreToAdd = new Score();
		try {
			tx = electiveInfoDao.currentSession().beginTransaction();
			System.out.println("开始tx");
			Curriculum thisCurriculum = new CurriculumDao().get(cid);
			newElectiveInfoList = electiveInfoDao.findByCid(thisCurriculum);
			// 遍历并输出结果
			for (int i = 0; i < newElectiveInfoList.size(); i++) {
				int SId = newElectiveInfoList.get(i).getStudent().getSId();
				Curriculum curriculum = curriculumDao.get(cid);
				String cName = curriculum.getCName();
				String inputScore = ServletActionContext.getRequest()
						.getParameter("inputScore" + i);
				if (inputScore == null || inputScore == "") {
					inputScore = "0";
				}
				int newscore = Integer.parseInt(inputScore);
				scoreToAdd.setScScore(newscore);
				scoreToAdd.setCurriculum(curriculumDao.get(cid));
				scoreToAdd.setCName(cName);
				scoreToAdd.setStudent(studentDao.get(SId));;
				scoreToAdd.setScStatus(0);
				scoreDao.save(scoreToAdd);
				HibernateUtil.currentSession().flush();
				HibernateUtil.currentSession().clear();
			}
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return result;
	}

	/**
	 * 
	 * @descript: 按条件查询学生成绩
	 * @author 20155790 孙鸿飞
	 * @date: 2018年5月10日 上午9:47:37
	 */
	public String searchByCondition() {
		System.out.println("进入searchByCondition");
		int cid = Integer.parseInt(ServletActionContext.getRequest()
				.getParameter("cid"));
		String searchName = ServletActionContext.getRequest().getParameter(
				"searchName");
		String searchCondition = ServletActionContext.getRequest()
				.getParameter("searchCondition");
		if (searchName.length() != 0) { // 有名称
			if (searchCondition.equals("单科成绩")) {
				System.out.println("有名称有条件-单科成绩");
				courseStudentsList = searchByNameConditionFromDB(cid,
						searchName);
			} else {
				System.out.println("有名称有条件-选课学号");
				courseStudentsList = searchByNameFromDB(cid, searchName);
			}
		} else { // 无名称
			if (searchCondition.equals("单科成绩")) {
				System.out.println("无名称有条件-单科成绩");
				courseStudentsList = searchByConditionFromDB(cid);
			} else {
				System.out.println("无名称有条件-选课学号");
				courseStudentsList = findCourseStudentsListFromDB(cid);
			}
		}
		return SUCCESS;
	}

	private List<CourseStudents> searchByConditionFromDB(Integer cid) { // 根据条件排序查询
		System.out.println("进入根据条件排序查询");
		List<ElectiveInfo> newElectiveInfoList = new ArrayList<ElectiveInfo>();
		List<Score> scoreList = new ArrayList<Score>();
		List<CourseStudents> newCourseStudentsList = new ArrayList<CourseStudents>();
		Transaction tx = null;
		try {
			tx = electiveInfoDao.currentSession().beginTransaction();
			Curriculum curriculum = curriculumDao.get(cid);
			System.out.println("开始tx，根据条件排序查询");
			scoreList = scoreDao.findByCid(curriculum);
			for (Score score : scoreList) {
				CourseStudents courseStudents = new CourseStudents();
				int sid = score.getStudent().getSId();
				int newScore = score.getScScore();
				float gpa = gpa(newScore);
				newElectiveInfoList = electiveInfoDao.findByCidSid(curriculum, score.getStudent());
				for (ElectiveInfo electiveInfo : newElectiveInfoList) {
					int eiCprogress = electiveInfo.getEiCprogress();
					if (eiCprogress == 1 && score.getScScore() < 60) {
						courseStudents.setEiCprogress("重修中");
					} else if (eiCprogress == 0 && score.getScScore() < 60) {
						courseStudents.setEiCprogress("已重修");
					} else {
						courseStudents.setEiCprogress("已结课");
					}
					Student student = studentDao.get(score.getStudent().getSId());
					courseStudents.setScId(score.getScId());
					courseStudents.setSId(sid);
					courseStudents.setScore(score.getScScore());
					courseStudents.setGpa(gpa);
					courseStudents.setSName(student.getSName());
					courseStudents.setEiId(electiveInfo.getEiId());
					courseStudents.setEiSemester(electiveInfo.getEiSemester());
					newCourseStudentsList.add(courseStudents);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return newCourseStudentsList;
	}

	private List<CourseStudents> searchByNameFromDB(Integer cid, String sName) { // 根据学生姓名查询
		List<ElectiveInfo> newElectiveInfoList = new ArrayList<ElectiveInfo>();
		List<CourseStudents> newCourseStudentsList = new ArrayList<CourseStudents>();
		Transaction tx = null;
		try {
			tx = electiveInfoDao.currentSession().beginTransaction();
			Curriculum curriculum = curriculumDao.get(cid);
			System.out.println("开始tx，根据学生姓名查询");
			List<Student> studentList = studentDao.findBySName(sName);
			for (Student student : studentList) {
				int SId = student.getSId();
				newElectiveInfoList = (List<ElectiveInfo>) electiveInfoDao
						.findByCidSid(curriculum, student);
				// 遍历并输出结果
				for (ElectiveInfo electiveInfo : newElectiveInfoList) {
					int eiId = electiveInfo.getEiId();
					int eiSemester = electiveInfo.getEiSemester();
					int eiCprogress = electiveInfo.getEiCprogress();
					CourseStudents courseStudents = new CourseStudents();
					courseStudents.setEiId(eiId);
					courseStudents.setSId(SId);
					courseStudents.setSName(student.getSName());
					courseStudents.setEiSemester(eiSemester);
					int newScore = 0;
					List<Score> scoreList = scoreDao.findByCidSid(curriculum, student);
					for (Score score : scoreList) {
						courseStudents.setScId(score.getScId());
						if (score.getScScore() != null) {
							newScore = score.getScScore();
							float gpa = gpa(newScore);
							courseStudents.setGpa(gpa);
							if (eiCprogress == 1 && score.getScScore() < 60) {
								courseStudents.setEiCprogress("重修中");
							} else if (eiCprogress == 0
									&& score.getScScore() < 60) {
								courseStudents.setEiCprogress("已重修");
							} else {
								courseStudents.setEiCprogress("已结课");
							}
						}
					}
					courseStudents.setScore(newScore);
					newCourseStudentsList.add(courseStudents);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return newCourseStudentsList;
	}

	private List<CourseStudents> searchByNameConditionFromDB(Integer cid,
			String sName) { // 根据学生姓名和排序条件查询
		List<ElectiveInfo> newElectiveInfoList = new ArrayList<ElectiveInfo>();
		List<Score> scoreList = new ArrayList<Score>();
		List<CourseStudents> newCourseStudentsList = new ArrayList<CourseStudents>();
		Transaction tx = null;
		try {
			tx = electiveInfoDao.currentSession().beginTransaction();
			Curriculum curriculum = curriculumDao.get(cid);
			System.out.println("开始tx，根据学生姓名和排序条件查询");
			List<Student> thisStudent = studentDao.findBySName(sName);
			for (Student student : thisStudent) {
				Score tempScore = scoreDao.findByCidSName(curriculum, student);
				scoreList.add(tempScore);
			}
			Collections.sort(scoreList);
			for (Score score : scoreList) {
				System.out.println("---------"+score.getScScore());
				CourseStudents courseStudents = new CourseStudents();
				int newScore = score.getScScore();
				float gpa = gpa(newScore);
				newElectiveInfoList = electiveInfoDao.findByCidSid(curriculum,
						score.getStudent());
				for (ElectiveInfo electiveInfo : newElectiveInfoList) {
					int eiCprogress = electiveInfo.getEiCprogress();
					if (eiCprogress == 1 && score.getScScore() < 60) {
						courseStudents.setEiCprogress("重修中");
					} else if (eiCprogress == 0 && score.getScScore() < 60) {
						courseStudents.setEiCprogress("已重修");
					} else {
						courseStudents.setEiCprogress("已结课");
					}
					Student student = studentDao.get(score.getStudent().getSId());
					courseStudents.setScId(score.getScId());
					courseStudents.setSId(score.getStudent().getSId());
					courseStudents.setScore(score.getScScore());
					courseStudents.setGpa(gpa);
					courseStudents.setSName(student.getSName());
					courseStudents.setEiId(electiveInfo.getEiId());
					courseStudents.setEiSemester(electiveInfo.getEiSemester());
					newCourseStudentsList.add(courseStudents);
				}
			}
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		}
		return newCourseStudentsList;
	}
	
	/**
	 * 
	 * @descript: 从本地Excel文件导入成绩
	 * @author 20155790 孙鸿飞
	 * @date: 2018年6月1日 下午5:31:27
	 */
	public String loadScoreInfo() throws IOException {
		String urlPath = ServletActionContext.getRequest().getParameter("urlPath");
		updataResult = loadScoreInfoFromExcel(urlPath);
		return SUCCESS;
	}

	public List<CourseStudents> loadScoreInfoFromExcel(String xlsPath)
			throws IOException {
		List<CourseStudents> temp = new ArrayList<CourseStudents>();
		FileInputStream fileIn = new FileInputStream(xlsPath);
		// 根据指定的文件输入流导入Excel从而产生Workbook对象
		Workbook wb0 = new HSSFWorkbook(fileIn);
		// 获取Excel文档中的第一个表单
		Sheet sht0 = wb0.getSheetAt(0);
		// 对Sheet中的每一行进行迭代
		for (Row r : sht0) {
			// 如果当前行的行号（从0开始）未达到2（第三行）则从新循环
			if (r.getRowNum() < 2) {
				continue;
			}
			// 创建实体类
			CourseStudents info = new CourseStudents();
			// 取出当前行第1个单元格数据，并封装在info实体stuName属性上
			int newSid = (int)r.getCell(0).getNumericCellValue();
			String newSname = r.getCell(1).getStringCellValue();
			int newScore = (int)r.getCell(2).getNumericCellValue();
			int newCid = (int)r.getCell(3).getNumericCellValue();
			info.setSId(newSid);
			info.setScore(newScore);
			info.setSName(newSname);
			info.setCid(newCid);
			temp.add(info);
		}
		fileIn.close();
		return temp;
	}
}