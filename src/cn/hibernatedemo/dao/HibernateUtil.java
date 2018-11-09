/**
 * @Description:TODO
 * @author:susan
 * @date 2018年3月21日上午10:58:16
 */
package cn.hibernatedemo.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @ClassName:HibernateUtil
 * @Description:TODO
 * @author:susan
 * @date 2018年3月21日上午10:58:16
 */
public class HibernateUtil {
	private static Configuration configuration;
    private static final  SessionFactory sessionFactory;
    //初始化Configuration和SessionFactory
    static {
        try {
            configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (HibernateException ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    /**
	 * 
	 */
	public HibernateUtil() {
		// TODO Auto-generated constructor stub
	}
	//获取 Session对象
	public static Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

}
