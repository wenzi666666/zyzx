package net.tfedu.zhl.cloud.resource.lessonrecord.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSONObject;

import net.tfedu.zhl.cloud.resource.lessonrecord.entity.ZLessonRecord;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.helper.tests.BaseControllerTestCase;

/**
 * @author wangwr
 * @date 2016年11月10日
 * @desc 单元测试类 copyRight@ 同方知好乐教育科技(北京)有限公司
 * 
 */
@Transactional
public class LessonRecordControllerTest extends BaseControllerTestCase {

	@Resource
	LessonRecordController controller;

	ResultJSON result;

	@Test
	public void testAddLessonRecord() throws ParamsException, ParseException {

		ZLessonRecord record = new ZLessonRecord();

		record.setLessonTitle("示例听课记录");
		record.setCreateTime(Calendar.getInstance().getTime());
		record.setTeacherTruename("张三老师");

		record.setLessonTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2016-11-10 14:30"));

		record.setLessonTfcode("RJCZ0101050101");
		record.setLessonContent("沁园春 雪---毛澤東");
		record.setLessonPlace("北京四中 1号教学楼  阶梯报告厅");

		result = controller.addLessonRecord(request, record);

		System.out.println(result.toString());
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testEditLessonRecord() throws Exception {

		ZLessonRecord record = new ZLessonRecord();
		record.setId(1l);
		record.setLessonTitle("示例听课记录123");
		record.setCreateTime(Calendar.getInstance().getTime());
		record.setTeacherTruename("张三老师");

		record.setLessonTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2016-11-10 14:30"));

		record.setLessonTfcode("RJCZ0101050101");
		record.setLessonContent("沁园春 雪---毛澤東ADD");
		record.setLessonPlace("北京四中 1号教学楼  阶梯报告厅");

		result = controller.editLessonRecord(request, record, 1l);

		System.out.println(result.toString());
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testQueryPage() throws Exception {

		result = controller.queryPage(request, 1, 10);

		System.out.println(JSONObject.toJSONString(result));
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));

	}

	@Test
	public void testGetLessonRecord() throws Exception {
		result =  controller.getLessonRecord(request, response, 1l);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

	@Test
	public void testDelLessonRecord() throws Exception {

		result = controller.delLessonRecord(request, response, 1l);
		
		Assert.isTrue("ok".equalsIgnoreCase(result.getCode()));
	}

}
