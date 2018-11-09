/**
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月28日2018下午8:10:23
 */
package cn.hibernatedemo.dao;

import java.util.List;

import org.hibernate.Query;
import cn.hibernatedemo.entity.Student;

/**
 * @classNamw:StudentDao
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月28日2018下午8:10:23
 */
public class StudentDao  extends BaseDaoImpl<Student> {
	@Override
	public Student get(int id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}
	public List<Student> findBySName(String sName) {
		// 定义hql语句
		String hql = "from Student where SName like ?";
		// 构建Query对象
		Query query = currentSession().createQuery(hql);
		query.setString(0, "%" + sName + "%");
		// 执行查询
		return query.list();
	}
}
