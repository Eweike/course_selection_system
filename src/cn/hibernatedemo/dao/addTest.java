/**
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月26日2018下午10:07:16
 */
package cn.hibernatedemo.dao;

import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.shf.util.Tools;

import cn.hibernatedemo.entity.Curriculum;
import cn.hibernatedemo.entity.ElectiveInfo;
import cn.hibernatedemo.entity.Student;
import cn.hibernatedemo.entity.Teacher;


/**
 * @classNamw:addTest
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月26日2018下午10:07:16
 */
public class addTest {
	public static void main(String[] args) {
		addTest temp = new addTest();
		temp.updateElectiveInfo();
	}
	public static void addManyTeacher(){
		Configuration conf=null;
		SessionFactory sf=null;
		Session session=null;
		Transaction tx=null;
		Tools tool = new Tools();
		try {
			//1、读取并解析配置文件及映射文件
			conf=new Configuration().configure();
			//2、依据配置文件和映射审计信息，创建SessionFactory对象
			sf=conf.buildSessionFactory();
			//3、打开Session
			session=sf.getCurrentSession();
			//4、开始一个事务
			tx=session.beginTransaction();
			//5、数据库操作
			for (int i = 0; i < 30; i++) {
				Teacher teacher = new Teacher();
				teacher.setTName(tool.getPeopleNameRandom());
				teacher.setTSex(tool.getPeopleSexRandom());
				teacher.setTPhone(tool.getTel());
				teacher.setTEmaill(tool.getEmail(6, 9));
				teacher.setTOffice(tool.getRoad());
				teacher.setTMajor("软件工程");
				teacher.setTCollege("华信软件学院");
		        session.save(teacher);
			}
			//6、提交事务
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			//6、回滚事务
			tx.rollback();
		}
	}
	public static void addManyStudent(){
		Configuration conf=null;
		SessionFactory sf=null;
		Session session=null;
		Transaction tx=null;
		Tools tool = new Tools();
		Random random = new Random();
		try {
			//1、读取并解析配置文件及映射文件
			conf=new Configuration().configure();
			//2、依据配置文件和映射审计信息，创建SessionFactory对象
			sf=conf.buildSessionFactory();
			//3、打开Session
			session=sf.getCurrentSession();
			//4、开始一个事务
			tx=session.beginTransaction();
			//5、数据库操作
			for (int i = 0; i < 30; i++) {
				Student student = new Student();
				student.setSName(tool.getPeopleNameRandom());
				student.setSSex(tool.getPeopleSexRandom());
				student.setSPhone(tool.getTel());
				student.setSFamilyname("汉族");
				student.setSPassword(random.nextInt(99999)+100000);
				student.setSEmail(tool.getEmail(6, 9));
				student.setSClass(random.nextInt(8)+1);
				student.setSAge(random.nextInt(15)+10);
				student.setSMajor("软件工程");
				student.setSCollege("华信软件学院");
				student.setSSemeter(4);
				student.setSCet4("是");
				student.setSNative("中国大陆");
				session.save(student);
			}
			//6、提交事务
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			//6、回滚事务
			tx.rollback();
		}
	}
	public static void addManyCurriculum(){
		Configuration conf=null;
		SessionFactory sf=null;
		Session session=null;
		Transaction tx=null;
		Tools tool = new Tools();
		Random random = new Random();
		try {
			//1、读取并解析配置文件及映射文件
			conf=new Configuration().configure();
			//2、依据配置文件和映射审计信息，创建SessionFactory对象
			sf=conf.buildSessionFactory();
			//3、打开Session
			session=sf.getCurrentSession();
			//4、开始一个事务
			tx=session.beginTransaction();
			//5、数据库操作
			for (int i = 0; i < 10; i++) {
				Curriculum curriculum = new Curriculum();
				curriculum.setCCapacity(120);
				curriculum.setCClassroom(random.nextInt(2)+3+"0"+random.nextInt(8));
				curriculum.setCCredit(random.nextInt(4)+1);
				curriculum.setCInstitute("华信软件学院");
				curriculum.setCName(tool.getLessonNameRandom());
				curriculum.setCNature("必修");
				curriculum.setCSelectedNumb(100);
				curriculum.setCSemester(random.nextInt(7)+1);
				curriculum.setCTime("1；0；0；0；0");
				curriculum.setCType("专业课");
				curriculum.setDtId(random.nextInt(9)+1);
				curriculum.setTId(1);
//				curriculum.setTId(random.nextInt(29)+1);
				session.save(curriculum);
			}
			//6、提交事务
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			//6、回滚事务
			tx.rollback();
		}
	}
	public static void addManyElectiveInfo(){
		Configuration conf=null;
		SessionFactory sf=null;
		Session session=null;
		Transaction tx=null;
		Tools tool = new Tools();
		Random random = new Random();
		try {
			//1、读取并解析配置文件及映射文件
			conf=new Configuration().configure();
			//2、依据配置文件和映射审计信息，创建SessionFactory对象
			sf=conf.buildSessionFactory();
			//3、打开Session
			session=sf.getCurrentSession();
			//4、开始一个事务
			tx=session.beginTransaction();
			//5、数据库操作
			for (int i = 1; i <= 30; i++) {
				Student student = new Student();
				student=(Student)session.get(Student.class, i);
				for (int j = 1; j <= 20; j++) {
					Curriculum curriculum = new Curriculum();
					curriculum=(Curriculum)session.get(Curriculum.class, j);
					ElectiveInfo electiveInfo = new ElectiveInfo();
					electiveInfo.setCurriculum(curriculum);
					electiveInfo.setStudent(student);;
					int eiCprogress = 0;
					if (random.nextBoolean()) {
						eiCprogress = 0;
					} else {
						eiCprogress = 1;
					}
					electiveInfo.setEiCprogress(eiCprogress);
					electiveInfo.setEiSemester(curriculum.getCSemester());
					electiveInfo.setEiStatus(random.nextInt(2));
					session.save(electiveInfo);					
				}
			}
			//6、提交事务
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			//6、回滚事务
			tx.rollback();
		}
	}
	public static void updateElectiveInfo(){
		Configuration conf=null;
		SessionFactory sf=null;
		Session session=null;
		Transaction tx=null;
		try {
			//1、读取并解析配置文件及映射文件
			conf=new Configuration().configure();
			//2、依据配置文件和映射审计信息，创建SessionFactory对象
			sf=conf.buildSessionFactory();
			//3、打开Session
			session=sf.getCurrentSession();
			//4、开始一个事务
			tx=session.beginTransaction();
			//5、数据库操作
			String hql = "from ElectiveInfo where CId=?";
			// 构建Query对象
			Query query = session.createQuery(hql);
			query.setInteger(0, 4);
			List<ElectiveInfo> electiveInfoList = query.list();
			for (ElectiveInfo electiveInfo : electiveInfoList) {
				electiveInfo.setEiCprogress(0);
			}
			//6、提交事务
			tx.commit();
		} catch (HibernateException e) {
			// TODO: handle exception
			e.printStackTrace();
			//6、回滚事务
			tx.rollback();
		}
	}
}
