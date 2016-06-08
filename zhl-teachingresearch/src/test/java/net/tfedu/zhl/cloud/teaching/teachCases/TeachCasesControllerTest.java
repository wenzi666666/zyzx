package net.tfedu.zhl.cloud.teaching.teachCases;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.teaching.teachCases.controller.TeachCasesTermSubjectController;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

/**
 * 教学案例相关接口单元测试
 * @author WeiCuicui
 *
 */
public class TeachCasesControllerTest extends BaseControllerTestCase {

	
	@Resource TeachCasesTermSubjectController teachCasesTermSubjectController;
	ResultJSON result ;
	
	//获取所有年级
	@Test
	public void testAllGrades() throws Exception{
	
		result = teachCasesTermSubjectController.getAllGrades();
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
	//查询学段下的学科
	@Test
    public void testSubjectsByTerm() throws Exception{
    	int termId = 1;
    	result = teachCasesTermSubjectController.getSubjectsByTerm(termId);
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
    
	//查询所有学段
	@Test
    public void testAllTerms()throws Exception{
    	result = teachCasesTermSubjectController.getTerms();
		Assert.isTrue("OK".equalsIgnoreCase(result.getCode()));
		log.info(JSONObject.toJSONString(result));
	}
	
}
