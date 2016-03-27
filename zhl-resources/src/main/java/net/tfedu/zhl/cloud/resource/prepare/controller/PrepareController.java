package net.tfedu.zhl.cloud.resource.prepare.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.downloadrescord.service.ResZipDownloadService;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepare;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContent;
import net.tfedu.zhl.cloud.resource.prepare.entity.ResourceSimpleInfo;
import net.tfedu.zhl.cloud.resource.prepare.service.JPrepareService;
import net.tfedu.zhl.cloud.resource.prepare.util.JPrepareConstant;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



/**
 * 备课夹相关接口
 * 
 * @author wangwr
 *
 */


@Controller
@RequestMapping("/resRestAPI")
public class PrepareController {
	
	@Resource
	JPrepareService jPrepareService;
	
	@Resource
	ResZipDownloadService resZipDownloadService;
	
	/**
	 * 返回json的结果对象
	 */
	private ResultJSON result = new ResultJSON();
	
	/**
	 * 异常
	 */
	private CustomException exception ;
	
	
	
	/**
	 * 返回的有效信息
	 */
	private Object data;
	
	
	
	
	/**
	 * 新增编辑    备课夹 v1.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepare" , method = RequestMethod.POST)
	public  ResultJSON  addPrepare(HttpServletRequest request, HttpServletResponse response){
		
		try{
			long userId = 699230739;
			String title = request.getParameter("title");
			String tfcode = request.getParameter("tfcode");
			String  _method = request.getParameter("_method");
			String _id = request.getParameter("id");
			long prepareId = 0 ;
			if(StringUtils.isNotEmpty(_id)){
				prepareId = Long.parseLong(_id);
			}
			
			//是否为编辑备课夹
			if(StringUtils.isNotEmpty(_method) && RequestMethod.PATCH.name().equalsIgnoreCase(_method) ){
				JPrepare prepare = new JPrepare();
				prepare.setId(prepareId);
				prepare.setTitle(title.trim());				
				jPrepareService.editPrepare(prepare);			
			//否则为delete备课夹	
			}else if(StringUtils.isNotEmpty(_method) && RequestMethod.DELETE.name().equalsIgnoreCase(_method) ){
				
				jPrepareService.deletePrepareById(prepareId);
	
			//否则为新建备课夹	
			}else{
				//新建备课夹 
				Date currentDate = Calendar.getInstance().getTime();
				JPrepare prepare = new JPrepare();
				prepare.setUserid(userId);
				prepare.setCreatetime(currentDate);
				prepare.setTfcode(tfcode);
				prepare.setTitle(title.trim());
				jPrepareService.addPrepare(prepare);
				data = prepare;
			}
			
			exception = CustomException.SUCCESS;
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;
	}
	
	
	
	
	
	/**
	 * 获取节点下的备课夹
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepare" , method = RequestMethod.GET)
	public  ResultJSON  getPrepare(HttpServletRequest request, HttpServletResponse response){
		try{
			String tfcode = request.getParameter("tfcode");
			long userId = 699230739;
			if(StringUtils.isNotEmpty(tfcode)){
				data =  jPrepareService.queryPrepareList(tfcode, userId);
				exception = CustomException.SUCCESS;
			}else{
				exception = CustomException.PARAMSERROR;
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;
	}
	
	
	
	/**
	 * 获取整本书下的备课夹列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepare4book" , method = RequestMethod.GET)
	public  ResultJSON  getPrepare4book(HttpServletRequest request, HttpServletResponse response){
		try{
			String tfcode = request.getParameter("tfcode");
			long userId = 699230739;
			if(StringUtils.isNotEmpty(tfcode)){
				data =  jPrepareService.queryPrepareAndTimeScopeList(tfcode, userId);
				exception = CustomException.SUCCESS;
			}else{
				exception = CustomException.PARAMSERROR;
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;
	}
	
	
	

	/**
	 * 获取备课夹内容列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepareContent/{prepareId}" , method = RequestMethod.GET)
	public  ResultJSON  querPrepareContent(@PathVariable Long prepareId,HttpServletRequest request, HttpServletResponse response){
		try{

			if(prepareId>0){
				data  = jPrepareService.queryPrepareContentList(prepareId);
				exception = CustomException.SUCCESS;
			}else{
				exception = CustomException.PARAMSERROR;
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;
	}
	
	

	/**
	 * 插入备课夹
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepareContent/{prepareId}" , method = RequestMethod.POST)
	public  ResultJSON  addPrepareContent(@PathVariable Long prepareId, HttpServletRequest request, HttpServletResponse response){
		try{
			String resIds   =  request.getParameter("resIds");
			String fromFlags = request.getParameter("fromFlags");
			
			
			//参数传递有问题
			if(prepareId==0 || StringUtils.isEmpty(fromFlags)||StringUtils.isEmpty(resIds)){
				exception = CustomException.PARAMSERROR;
			}else{
				String ids[] = resIds.split(",");
				String fromFlag[] =fromFlags.split(",");
				if(ids.length==0 || fromFlag.length!=ids.length ){
					exception = CustomException.PARAMSERROR;
				}else{
					for (int i = 0; i < fromFlag.length; i++) {
						String _fromFlag = fromFlag[i];
						String _resId = ids[i];
						Date currentDate = Calendar.getInstance().getTime();
						//将资源加入备课夹
						JPrepareContent cont = new JPrepareContent();
						cont.setPreid(prepareId);
						cont.setContid(Long.parseLong(_resId));
						cont.setConttype(JPrepareConstant.getContTypeByFromFlag(Integer.parseInt(_fromFlag)));
						cont.setCreatetime(currentDate);
						jPrepareService.addPrepareContent(cont);
					}
					exception = CustomException.SUCCESS;
				}
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;
	}
	
	/**
	 * 清空备课夹
	 * 移出备课夹
	 * 调整顺序
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepareContent" , method = RequestMethod.POST)
	public  ResultJSON  delPrepareContent(HttpServletRequest request, HttpServletResponse response){
		try{
			String ids   =  request.getParameter("ids");
			String _method = request.getParameter("_method");
			
			//清空备课夹
			if(StringUtils.isNotEmpty(_method)&& "CLEAR".equalsIgnoreCase(_method)){
				 String _id = request.getParameter("id");
				 long id = 0 ;
				 if(StringUtils.isEmpty(_id)){
					 exception = CustomException.PARAMSERROR;
				 }else{
					 id = Long.parseLong(_id);
					 jPrepareService.clearPrepareContentByPrepareId(id);
					 exception = CustomException.SUCCESS;
				 }
				
				 
			//调整顺序	 
			}else if(StringUtils.isNotEmpty(_method)&& RequestMethod.PATCH.name().equalsIgnoreCase(_method)){
				 String _prevId = request.getParameter("prevId");
				 String _nextId = request.getParameter("nextId");
				 long nextId = 0 ;
				 long prevId = 0 ;
				 if(StringUtils.isNotEmpty(_prevId)&& StringUtils.isNotEmpty(_nextId)){
					 nextId = Long.parseLong(_nextId);
					 prevId = Long.parseLong(_prevId);
					 
				 }else{
					 exception = CustomException.PARAMSERROR;					 
				 }
			}else{
				
				// 移出备课夹参数传递有问题
				if(StringUtils.isEmpty(ids)){
					exception = CustomException.PARAMSERROR;
				}else{
					String _ids[] = ids.split(",");
					if(_ids.length==0){
						exception = CustomException.PARAMSERROR;
					}else{
						for (int i = 0; i < _ids.length; i++) {						
							//将资源移出备课夹
							jPrepareService.deletePrepareContentById(Long.parseLong(_ids[i]));
						}
						exception = CustomException.SUCCESS;
					}
				}
				
			}
			
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;
	}
	
	
	/**无节点选择时系统资源加入备课夹接口
	 * 
	 * @return
	 */
	  @RequestMapping(value="/v1.0/prepareContentFill" ,method=RequestMethod.POST)
	  public ResultJSON fillPrepareContent(HttpServletRequest request, HttpServletResponse response){
		   String rescode = request.getParameter("rescode");
		   String _method = request.getParameter("_method");
		   
		   try {
			   long userId = 699230739;
			   
			   
			 //无节点选择时系统资源加入备课夹接口
				if(StringUtils.isNotEmpty(_method)&& "RESOURCEFILL".equalsIgnoreCase(_method)){
					jPrepareService.addToPrepareWithOnlySysResource(rescode, userId);
					exception = CustomException.SUCCESS;
				}else{
					exception = CustomException.PARAMSERROR;
				}
			
		   }catch(Exception e){
				exception = CustomException.getCustomExceptionByCode(e.getMessage());
				//如果是普通的异常
				if(exception.getStatus()==500){
					e.printStackTrace();
				}
			}finally{
				result.setCode(exception.getCode());
				result.setMessage(exception.getMessage());
				result.setData(data==null?"":data);
				result.setSign("");			
			}
			   
		   
		   return result ;
	   }
	
	
	  /**
	   * 打包下载请求
	   * 返回 一个打包下载记录的id
	   * @param request
	   * @param response
	   * @return
	   */
	  @RequestMapping(value="/v1.0/prepareZip" ,method=RequestMethod.GET) 
	  public ResultJSON prepareZip(HttpServletRequest request, HttpServletResponse response){
		  
		  
		  
		  
		  
		  return null ;
	  }
	  
	  
	  /**
	   * 更新历史的打包下载的状态
	   * @param request
	   * @param response
	   * @return
	   */
	  @RequestMapping(value="/v1.0/zipDownload_status" ,method=RequestMethod.GET) 
	  public void updateZipDownloadStatus(HttpServletRequest request, HttpServletResponse response){
		  
		  

		  
		  
		  
	  
	  }
	  
	  
	  
	  /**
	   * 获取指定的下载的zip打包 是否完成
	   * @param request
	   * @param response
	   * @return
	   */
	  @RequestMapping(value="/v1.0/prepareZip_staus" ,method=RequestMethod.GET) 
	  public ResultJSON getZipDownloadStatus(HttpServletRequest request, HttpServletResponse response){
		  
		  
		  return null ;
	  }
	  
	  
	  
	  
	  
	  
	  /**
	   * 
	   * 获取资源 的播放地址 
	   * @param request
	   * @param response
	   * @return
	   */
	  @RequestMapping(value="/v1.0/resViewUrl" ,method=RequestMethod.GET) 
	  public ResultJSON getResViewUrl(HttpServletRequest request, HttpServletResponse response){
			String resIds   =  request.getParameter("resIds");
			String fromFlags = request.getParameter("fromFlags");

		try{
			//参数传递有问题
			if(StringUtils.isEmpty(fromFlags)||StringUtils.isEmpty(resIds)){
				exception = CustomException.PARAMSERROR;
			}else{
				String ids[] = resIds.split(",");
				String fromFlag[] =fromFlags.split(",");
				if(ids.length==0 || fromFlag.length!=ids.length ){
					exception = CustomException.PARAMSERROR;
				}else{
					List<ResourceSimpleInfo> list = jPrepareService.getResourceSimpleInfo(ids, fromFlag);
					
					
					
					exception = CustomException.SUCCESS;
				}
			}
		}catch(Exception e){
			exception = CustomException.getCustomExceptionByCode(e.getMessage());
			//如果是普通的异常
			if(exception.getStatus()==500){
				e.printStackTrace();
			}
		}finally{
			result.setCode(exception.getCode());
			result.setMessage(exception.getMessage());
			result.setData(data==null?"":data);
			result.setSign("");			
		}
		return  result;
		  
		  
	  }

}
