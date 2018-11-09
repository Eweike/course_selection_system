/**
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月28日2018下午6:06:11
 */
package cn.hibernatedemo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import cn.hibernatedemo.entity.Curriculum;
import cn.hibernatedemo.entity.ElectiveInfo;
import cn.hibernatedemo.entity.Student;


/**
 * @classNamw:ElectiveInfoDao
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月28日2018下午6:06:11
 */
public class ElectiveInfoDao extends BaseDaoImpl<ElectiveInfo> {
	/**
	 * 
	 * @descript: 根据课程id查询选课信息
	 * @author 20155790 孙鸿飞
	 * @date: 2018年4月28日 下午6:18:57
	 */
	public List<ElectiveInfo> findByCid(Curriculum curriculum) {
		// 定义hql语句
		System.out.println("进入findByCid");
		String hql = "from ElectiveInfo where curriculum=?";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		System.out.println("定义query对象");
		query.setParameter(0, curriculum);
		System.out.println("执行hql语句");
		// 执行查询
		return query.list();
	}
	/**
	 * 
	 * @descript: TODO
	 * @author 20155790 孙鸿飞
	 * @date: 2018年5月10日 上午10:20:20
	 */
	public List<ElectiveInfo> findByCidSid(Curriculum cc,Student ss) {
		// 定义hql语句
		System.out.println("进入findByCidSid");
		String hql = "from ElectiveInfo where curriculum=? and student=?";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		query.setParameter(0, cc);
		query.setParameter(1, ss);
		// 执行查询
		return query.list();
	}
}
