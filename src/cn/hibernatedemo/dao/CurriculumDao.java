/**
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月26日2018下午8:49:30
 */
package cn.hibernatedemo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Transaction;

import cn.hibernatedemo.entity.Curriculum;

/**
 * @classNamw:CurriculumDao
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月26日2018下午8:49:30
 */
public class CurriculumDao extends BaseDaoImpl<Curriculum> {
	/**
	 * 
	 * @descript: TODO
	 * @author 20155790 孙鸿飞
	 * @date: 2018年4月27日 上午10:16:04
	 */
	public List<Curriculum> findByTid(int tid) {
		// 定义hql语句
		String hql = "from Curriculum as curriculum where curriculum.TId=?";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		query.setInteger(0, tid);
		// 执行查询
		return query.list();
	}
	public static void main(String[] args) {
		CurriculumDao temp = new CurriculumDao();
		Transaction tx = temp.currentSession().beginTransaction();
		Curriculum curriculum = temp.get(1);
		System.out.println("课程名称==="+curriculum.getCName());
	}
}
