/**
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月27日2018上午10:36:45
 */
package com.shf.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import cn.hibernatedemo.dao.CurriculumDao;
import cn.hibernatedemo.dao.ScoreDao;
import cn.hibernatedemo.entity.Curriculum;

/**
 * @classNamw:curriculumBiz
 * @descript: TODO
 * @author 20155790 孙鸿飞
 * @date: 2018年4月27日2018上午10:36:45
 */
public class curriculumBiz {
	private CurriculumDao curriculumDao = new CurriculumDao();

	public CurriculumDao getCurriculumDao() {
		return curriculumDao;
	}

	public void setCurriculumDao(ScoreDao scoreDao) {
		this.curriculumDao = curriculumDao;
	}
	
	public List<Curriculum> findByTid(int tid) {
		List<Curriculum> newCurriculumList = new ArrayList<Curriculum>();
		Transaction tx = null;
		try {
			tx = curriculumDao.currentSession().beginTransaction();
			newCurriculumList = curriculumDao.findByTid(1);
			// 遍历并输出结果
			for (Curriculum curriculum : newCurriculumList) {
				System.out.println("课程编号：" + curriculum.getCId() + "，课程名称："
						+ curriculum.getCName());
			}
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
}
