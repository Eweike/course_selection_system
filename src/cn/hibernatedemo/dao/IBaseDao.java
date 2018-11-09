/**
 * @Description:TODO
 * @author:susan
 * @date 2018年3月27日上午9:27:05
 */
package cn.hibernatedemo.dao;


/**
 * @ClassName:IBaseDao
 * @Description:TODO
 * @author:susan
 * @date 2018年3月27日上午9:27:05
 */
public interface IBaseDao<T> {
	    //通过Session的get()方法根据OID 加载指定对象
		public T get(int id) ;
		//通过Session的load()方法根据OID 加载指定对象
		public T load(int id) ;
		//保存指定的T对象
		public void save(T entity);
		//修改指定的T对象
		public void update(T entity);
		//删除指定的T对象
		public void delete(T entity) ;

}
