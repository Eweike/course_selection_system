/**
 * @Description:TODO
 * @author:susan
 * @date 2018年3月27日上午9:33:10
 */
package cn.hibernatedemo.dao;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;


/**
 * @ClassName:BaseDaoImpl
 * @Description:TODO
 * @author:susan
 * @date 2018年3月27日上午9:33:10
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {
	/* (non-Javadoc)
	 * @see cn.hibernatedemo.dao.IBaseDao#get(int)
	 */
	private Class<T> clazz;
	public Session currentSession() {
		System.out.println("执行currentSession()");
		return HibernateUtil.currentSession();
		
	}
	/**
	 * 
	 */
	public BaseDaoImpl() {
		// TODO Auto-generated constructor stub
		//使用反射技术得到T的真实类型
		ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
		this.clazz=(Class<T>)pt.getActualTypeArguments()[0];
		System.out.println("clazz--"+clazz);
	}
	@Override
	public T get(int id) {
		// TODO Auto-generated method stub
		return (T)HibernateUtil.currentSession().get(clazz, id);
	}
	/* (non-Javadoc)
	 * @see cn.hibernatedemo.dao.IBaseDao#load(int)
	 */
	@Override
	public T load(int id) {
		// TODO Auto-generated method stub
		return (T)HibernateUtil.currentSession().load(clazz, id);
	}
	/* (non-Javadoc)
	 * @see cn.hibernatedemo.dao.IBaseDao#save(java.lang.Object)
	 */
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		currentSession().save(entity);
		
	}
	/* (non-Javadoc)
	 * @see cn.hibernatedemo.dao.IBaseDao#update(java.lang.Object)
	 */
	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		currentSession().update(entity);
		
	}
	/* (non-Javadoc)
	 * @see cn.hibernatedemo.dao.IBaseDao#delete(java.lang.Object)
	 */
	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		currentSession().delete(entity);
		
	}

}
