package net.tfedu.zhl.cloud.resource.sysRes.typeAndFormats;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.SysFrom;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.helper.tests.BaseServiceTestCase;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 资源类型、格式的service单元测试
 * @author WeiCuicui
 *
 */
public class TypeAndFormatServiceTest extends BaseServiceTestCase{

	@Resource
    ResTypeService resTypeService;
	
	@Resource
    ResFormatService resFormatService;
	
	/**
     * 系统资源，获取当前结点下的所有资源id
     * @throws IOException
     */
    @Test
    public void testGetAllSysIds()throws IOException{
    	 HashMap<String, Object> map = new HashMap<String, Object>();
         map.put("sys_from", SysFrom.sys_from);
         map.put("pTfcode", "RJGZ040101");
         List<Long> ids = resTypeService.getAllSysResIds(map);
         
         Assert.isTrue(ids.size() > 0);
         for (int i = 0; i < ids.size(); i++) {
			System.out.print(ids.get(i) + " ");
			if(i % 10 == 0)
				System.out.println();
		 }
    }
    
    /**
     * 区本、校本资源，获取当前结点下的所有资源id
     * @throws IOException
     */
    @Test
    public void testGetAllDisIds()throws IOException{
    	HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", "RJGZ040101");
        
        List<Long> ids = resTypeService.getAllDisResIds(map);
        
        System.out.println(ids.size());
        
        /*Assert.isTrue(ids.size() > 0);
        for (int i = 0; i < ids.size(); i++) {
			System.out.print(ids.get(i) + " ");
			if(i % 10 == 0)
				System.out.println();
		}*/
        
    }
	
	/**
	 * 系统资源：根据资源库id，得到父类型的所有子类型及其自身
	 */
	@Test
	public void testGetAllTypeIdsByPool()throws IOException{
		long poolId = 0;
		List<Integer> types = resTypeService.getAllTypeIdsByPool(poolId);
		
		Assert.isTrue(types.size() > 0);

        for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }
	}
	
	/**
	 * 系统资源：根据资源库id和父类型id，得到父类型的所有子类型及其自身
	 */
	@Test
	public void testGetTypesByPMTypeAndPool()throws IOException{
		
		long poolId = 0;
		int MType = 1;
		List<Integer> types = resTypeService.getTypesByPMTypeAndPool(poolId, MType);
		
		Assert.isTrue(types.size() > 0);

        for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }
	}
	
	/**
	 * 系统资源：当资源库选择 “全部” 或 “教学素材” 时，显示所有一级类型
	 */
	@Test
	public void testGetSysFirstLevelType()throws IOException{
		
		List<ResType> types = new ArrayList<ResType>();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sys_from", SysFrom.sys_from);
        map.put("pTfcode", "RJGZ040101");
        List<Long> resourceIds = resTypeService.getAllSysResIds(map);
         
        long poolId = 0;
 		List<Integer> typeIds = resTypeService.getAllTypeIdsByPool(poolId);
	
 		types = resTypeService.getSysFirstLevelType(resourceIds, typeIds);
 		
 		Assert.isTrue(types.size() > 0);
 		for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }
	}
	
	/**
	 * 系统资源：当资源库选择 “动画焦教具”、“名师微课”、“教学案例” 时，显示所有二级类型。当资源库为“理化生实验”时，只显示“全部”
	 */
	@Test
	public void testGetSysSecondLevelType()throws IOException{
        List<ResType> types = new ArrayList<ResType>();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sys_from", SysFrom.sys_from);
        map.put("pTfcode", "RJGZ040101");
        List<Long> resourceIds = resTypeService.getAllSysResIds(map);
         
        long poolId = 1;
 		List<Integer> typeIds = resTypeService.getAllTypeIdsByPool(poolId);
	
 		types = resTypeService.getSysSecondLevelType(resourceIds, typeIds);
 		
 		Assert.isTrue(types.size() > 0);
 		for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }
	}
	
	
	/**
	 * 区本校本资源：根据资源ids和fromFlag（区本/校本），查询资源类型
	 */
	@Test
	public void testGetDisResType()throws IOException{
		List<ResType> types = new ArrayList<ResType>();
		int fromFlag = 3;
		HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pTfcode", "RJGZ040101");
        
        List<Long> resourceIds = resTypeService.getAllDisResIds(map);
        types = resTypeService.getDisResType(resourceIds, fromFlag);
        
        System.out.println(types.size());
        
        /*Assert.isTrue(types.size() > 0);
 		for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }*/
	}
	
	/**
	 * 区本校本资源：根据父类型Id，查询父类型及其所有子类型
	 */
	@Test
	public void testGetDisResTypesByPMType()throws IOException{
		int MType = 1;
		
		List<Integer> types = resTypeService.getDisResTypesByPMType(MType);
		
		System.out.println(types.size());
		
		/*Assert.isTrue(types.size() > 0);
		
 		for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }*/
	}

	/**
	 * 系统资源类型  单元测试
	 * @throws IOException
	 */
    @Test
    public void testResTypeService() throws IOException {

        List<ResType> types = new ArrayList<ResType>();

        long poolId = 4;
        String pTfcode = "RJCZ01010501";

        types = resTypeService.getSysResTypes(poolId, pTfcode);
        Assert.isTrue(types.size() > 0);

        for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }
    }
    
    /**
	 * 系统资源类型  单元测试  e备课
	 * @throws IOException
	 */
    @Test
    public void testResTypeService_ePrepare() throws IOException {

        List<ResType> types = new ArrayList<ResType>();

        long poolId = 4;
        String pTfcode = "RJCZ01010501";

        types = resTypeService.getSysResTypes_EPrepare(poolId, pTfcode, SysFrom.removeTypeIds);

        Assert.isTrue(types.size() > 0);

        for (int i = 0; i < types.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(types.get(i) + ",");
        }
    }
    
    /**
     * 系统资源格式  单元测试
     * @throws IOException
     */
    @Test
    public void testSysFormatService() throws IOException {

        List<String> formats = new ArrayList<String>();

        long poolId = 6;
        String pTfcode = "RJCZ01010501";
        int typeId = 1;

        // 根据 resourceIds和typeIds，查询资源格式
        formats = resFormatService.getSysResFormats(poolId, pTfcode, typeId);

        Assert.isTrue(formats.size() > 0);
        System.out.println(formats.size());
        for (int i = 0; i < formats.size(); i++) {
            if (i % 10 == 0)
                System.out.println();
            System.out.print(formats.get(i) + ",");
        }

    }
    
    /**
     * 校本、区本资源类型  单元测试
     * @throws IOException
     */
    @Test
    public void testDisResTypeService() throws IOException {

        // 传递参数
        String tfcode = "RJGZ040101";
        // 3校本 4区本
        int fromFlag = 3;

        // 定义类型结果集
        List<ResType> types = new ArrayList<ResType>();

        // 查询类型id
        types = resTypeService.getDisResTypes(tfcode, fromFlag);

        Assert.isTrue(types.size() > 0);
        System.out.println(types.size());
        for (int i = 0; i < types.size(); i++)
            System.out.print(types.get(i) + ",");
    }
    
    /**
     * 校本、区本资源类型  单元测试  e备课
     * @throws IOException
     */
    @Test
    public void testDisResTypeService_ePrepare() throws IOException {

        // 传递参数
        String tfcode = "RJGZ040101";
        // 3校本 4区本
        int fromFlag = 3;

        // 定义类型结果集
        List<ResType> types = new ArrayList<ResType>();

        // 查询类型id
        types = resTypeService.getDisResType_EPrepare(tfcode, fromFlag, SysFrom.removeTypeIds);

        Assert.isTrue(types.size() > 0);
        System.out.println(types.size());
        for (int i = 0; i < types.size(); i++)
            System.out.print(types.get(i) + ",");
    }
    
    /**
     * 区本校本资源格式  单元测试
     * @throws IOException
     */
    @Test
    public void testDisResFormatService() throws IOException {
        // 格式
        List<String> formats = new ArrayList<String>();

        // 资源类型id
        String tfcode = "RJGZ040101";
        int fromFlag = 3;

        formats = resFormatService.getDisResFormats(tfcode, fromFlag);

        Assert.isTrue(formats.size() > 0);
        System.out.println(formats.size());
        for (int i = 0; i < formats.size(); i++)
            System.out.print(formats.get(i) + ",");
    }
}
