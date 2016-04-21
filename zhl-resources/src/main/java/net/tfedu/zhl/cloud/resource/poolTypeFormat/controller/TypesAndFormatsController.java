package net.tfedu.zhl.cloud.resource.poolTypeFormat.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.config.ResourceWebConfig;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResPool;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.entity.ResType;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResFormatService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResPoolService;
import net.tfedu.zhl.cloud.resource.poolTypeFormat.service.ResTypeService;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.core.exception.ParamsException;
import net.tfedu.zhl.helper.ControllerHelper;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统、区本、校本资源的类型、格式查询
 * 
 * @author WeiCuicui
 *
 */
@Controller
@RequestMapping("/resRestAPI")
public class TypesAndFormatsController {

	@Resource
    ResTypeService resTypeService;
	
	@Resource
    ResFormatService resFormatService;
	
	@Resource
    ResPoolService resPoolService;
	
	@Resource
    private ResourceWebConfig resourceWebConfig;
	
	/**
     * 查询所有的资源库
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/v1.0/pools", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getAllPools(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
        List<ResPool> pools = resPoolService.getAllPools();
      
        return ResultJSON.getSuccess(pools);
    }
	
	
	/**
	 * 查询系统资源的类型
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/sysResource/types", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getSysResTypesByPool(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		
        //查询结果
        List<ResType> types = new ArrayList<ResType>();
        
        //资源来源
        List<Integer> sys_from = resourceWebConfig.getSys_from(request);

        //父结点tfcode
    	String pTfcode = "";
    	
    	//e备课需要排除的类型
    	List<Integer> removeTypeIds = resourceWebConfig.getRemoveTypes(request);
    	
    	//资源库id
    	long poolId = 0;
    	
    	if(StringUtils.isNotEmpty(request.getParameter("pTfcode"))){
    		pTfcode = request.getParameter("pTfcode").toString().trim();
    	} else {
    		throw new ParamsException();
    	}
       
        if(StringUtils.isNotEmpty(request.getParameter("poolId"))){
        	poolId = Long.parseLong(request.getParameter("poolId").toString().trim());
        } else {
			throw new ParamsException();
		}
    	
        if(request.getParameter("isEPrepare") != null){//e备课
        	//新的类型查询方法（去除一些类型）
        	types = resTypeService.getSysResTypes_EPrepare(poolId, pTfcode,removeTypeIds,sys_from);
        } else {
        	types = resTypeService.getSysResTypes(poolId, pTfcode,sys_from);
		}
        
        return ResultJSON.getSuccess(types);
    }
	
	/**
	 * 查询系统资源的格式
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/sysResource/formats", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getSysResFormatsByMtype(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
      
        List<String> formats = new ArrayList<String>();
        
        //系统资源来源
        List<Integer> sys_from = resourceWebConfig.getSys_from(request);
        
    	String pTfcode = "";
    	int typeId = 0;
    	long poolId = 0;
    	if(StringUtils.isNotEmpty(request.getParameter("pTfcode"))){
    		pTfcode = request.getParameter("pTfcode").toString().trim();
    	} else {
			throw new ParamsException();
		}
    	
    	
        if(StringUtils.isNotEmpty(request.getParameter("typeId"))){
        	typeId = Integer.parseInt(request.getParameter("typeId").toString().trim());
        } else {
			throw new ParamsException();
		}
        
        
        if(StringUtils.isNotEmpty(request.getParameter("poolId"))){
        	poolId = Long.parseLong(request.getParameter("poolId").toString().trim());
        } else {
			throw new ParamsException();
		}

        // 根据 resourceIds和typeIds，查询资源格式
        formats = resFormatService.getSysResFormats(poolId, pTfcode, typeId,sys_from);
        
        return ResultJSON.getSuccess(formats);
    }
	
	/**
	 * 查询区本、校本资源的类型
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/districtResource/types", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getDisResTypesByPool(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		
		List<ResType> types = null;
       
        //e备课需要排除的资源类型
        List<Integer> removeTypeIds = resourceWebConfig.getRemoveTypes(request);

    	String tfcode = "";
    	
    	//资源来源 3 校本；4 区本
    	int fromFlag = 3;
    	
    	if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
    		tfcode = request.getParameter("tfcode").toString().trim();
    	} else {
			throw new ParamsException();
		}
    	
        if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
        	fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
        } else {
			throw new ParamsException();
		}
        
        if(request.getParameter("isEPrepare") != null){//而备课
        	//新的类型查询方法（去除一些类型）
        	types = resTypeService.getDisResType_EPrepare(tfcode, fromFlag, removeTypeIds);
        	
        } else {
        	 types = resTypeService.getDisResTypes(tfcode, fromFlag);
		}
        
        return ResultJSON.getSuccess(types);
    }
	
	/**
	 * 查询区本、校本资源的格式
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/v1.0/districtResource/formats", method = RequestMethod.GET)
    @ResponseBody
    public ResultJSON getDisFormats(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
        // 格式
        List<String> formats = new ArrayList<String>();
        String tfcode = ControllerHelper.getParameter(request, "tfcode");
    	int fromFlag = 3;
    	
//    	if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
//    		tfcode = request.getParameter("tfcode").toString().trim();
//    	} else {
//			throw new ParamsException();
//		}
    	
        if(StringUtils.isNotEmpty(request.getParameter("fromFlag"))){
        	fromFlag = Integer.parseInt(request.getParameter("fromFlag").toString().trim());
        } else {
			throw new ParamsException();
		}

        formats = resFormatService.getDisResFormats(tfcode, fromFlag);
        
        return ResultJSON.getSuccess(formats);
    }
}
