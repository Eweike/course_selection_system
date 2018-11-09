/**
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月26日2018下午9:10:10
 */
package cn.hibernatedemo.dao;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;

import cn.hibernatedemo.entity.Curriculum;
import cn.hibernatedemo.entity.ElectiveInfo;
import cn.hibernatedemo.entity.Score;
import cn.hibernatedemo.entity.Student;

/**
 * @classNamw:ScoreDao
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月26日2018下午9:10:10
 */
public class ScoreDao extends BaseDaoImpl<Score> {
	@Override
	public void save(Score entity) {
		// TODO Auto-generated method stub
		super.save(entity);
	}
	public List<Score> findByCidSid(Curriculum cc,Student ss) {
		// 定义hql语句
		String hql = "from Score where curriculum=? and student=?";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, cc);
		query.setParameter(1, ss);
		// 执行查询
		return query.list();
	}
	public List<Float> findKindScore(Curriculum curriculum){
		// 定义hql语句
		String hql = "select max(scScore),min(scScore),avg(scScore) from Score where curriculum=?";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, curriculum);
		// 执行查询
		return query.list();
	}
	public Long findScoreByCondition(long min,long max,Curriculum curriculum){
		// 定义hql语句
		String hql = "select count(scScore) from Score where scScore>=? and scScore<=? and curriculum=?";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		query.setLong(0, min);
		query.setLong(1, max);
		query.setParameter(2,curriculum);
		// 执行查询
		return (Long) query.uniqueResult();
	}
	public List<Score> findByCid(Curriculum curriculum) {
		System.out.println("进入findByCid-score");
		// 定义hql语句
		String hql = "from Score where curriculum=? order by scScore desc";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, curriculum);
		// 执行查询
		return query.list();
	}
	/**
	 * 
	 * @descript: 根据学生姓名和排序条件查询
	 * @author 20155790 孙鸿飞
	 * @date: 2018年5月10日 上午10:48:32
	 */
	public Score findByCidSName(Curriculum curriculum,Student student) {
		// 定义hql语句
		System.out.println("进入findByCid,根据学生姓名和排序条件查询");
		String hql = "from Score where curriculum=? and student=? order by scScore";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, curriculum);
		query.setParameter(1, student);
		// 执行查询
		return (Score) query.uniqueResult();
	}
}
