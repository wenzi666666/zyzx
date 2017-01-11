package net.tfedu.zhl.cloud.back.controller;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;
import net.tfedu.zhl.sso.users.entity.SCard;

/**
 
  
    @author wangwr
    @date 2017年1月9日
    @desc 
  
    copyRight@ 同方知好乐教育科技(北京)有限公司 
*/
@Transactional
public class AccountCardControllerTest extends BaseControllerTestCase{

	@Resource
	AccountCardController controller;
	
	ResultJSON  result ; 
	
	
	@Test
	public void testAddSCard() throws Exception {
		
		

		SCard card = new SCard();
		

		card.setRegistkeytype("P");
        card.setCount(10);
        card.setRoleid(2l);
        card.setExpNum(10);
        card.setCreatetime(Calendar.getInstance().getTime());
        card.setCreateman("1");
        
        
		result =  controller.addSCard(card, new BeanPropertyBindingResult(card, "card"),request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
	}

	@Test
	public void testUpdateSCard() throws Exception {
		SCard card = new SCard();
		card.setId(577680126l);
		card.setFlag(true);
	    
		result =  controller.updateSCard(card, new BeanPropertyBindingResult(card, "card"));
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
	}

	@Test
	public void testDelSCard()  throws Exception {
		
		
		result =  controller.delSCard(577680126l);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
				
		
		
	}

	@Test
	public void testPageScard() throws Exception {
		result =  controller.pageScard(1, 10, request, response);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
				
	}

	@Test
	public void testDelayCard() throws Exception {
		
		result =  controller.delayCard(new String[]{"577680126"}, 12, request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		
		
	}

	@Test
	public void testActiveCard() throws Exception {

		result =  controller.activeCard(new String[]{"577680126"}, request);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
		
		log.info(JSONObject.toJSONString(result));
		}

	@Test
	public void testRegisterCardBatch() {
		
		
	}

	@Test
	public void testExportCard() {
		
		
		
	}

}
