package com.shf.service;

import static org.junit.Assert.*;

import org.junit.Test;

public class curriculumBizTest {
	curriculumBiz curriculumBiz = new curriculumBiz();
	@Test
	public void testFindByTid() {
		curriculumBiz.findByTid(1);
	}

}
