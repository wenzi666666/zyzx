package net.tfedu.zhl.cloud.resource.prepare.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.downloadrescord.entity.ResZipDownRecord;
import net.tfedu.zhl.cloud.resource.downloadrescord.service.ResZipDownloadService;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepare;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContent;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentView;
import net.tfedu.zhl.cloud.resource.prepare.entity.ResourceSimpleInfo;
import net.tfedu.zhl.cloud.resource.prepare.service.JPrepareService;
import net.tfedu.zhl.cloud.resource.prepare.util.JPrepareConstant;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;
import net.tfedu.zhl.fileservice.ZipTaskContent;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



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
	 * 新增编辑    备课夹 v1.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepare" , method = RequestMethod.POST)
	@ResponseBody
	public  ResultJSON  addPrepare(HttpServletRequest request, HttpServletResponse response){
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;

		try{
			if(currentUserId!=null && exception==null){			
				long userId = currentUserId;
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
					
					HashMap<String,Long> map = new HashMap<String,Long>();
					map.put("id", prepare.getId());
					data = map;
				}				
				exception = CustomException.SUCCESS;				
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
	 * 获取节点下的备课夹
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepare" , method = RequestMethod.GET)
	@ResponseBody
	public  ResultJSON  getPrepare(HttpServletRequest request, HttpServletResponse response){
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;
		
		try{
			if(currentUserId!=null && exception==null){	
				long userId = currentUserId;
				String tfcode = request.getParameter("tfcode");
				if(StringUtils.isNotEmpty(tfcode)){
					data =  jPrepareService.queryPrepareList(tfcode, userId);
					exception = CustomException.SUCCESS;
				}else{
					exception = CustomException.PARAMSERROR;
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
	 * 获取整本书下的备课夹列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepare4book" , method = RequestMethod.GET)
	@ResponseBody
	public  ResultJSON  getPrepare4book(HttpServletRequest request, HttpServletResponse response){
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;
		
		try{
			if(currentUserId!=null && exception==null){	
				long userId = currentUserId;
				String tfcode = request.getParameter("tfcode");
				if(StringUtils.isNotEmpty(tfcode)){
					data =  jPrepareService.queryPrepareAndTimeScopeList(tfcode, userId);
					exception = CustomException.SUCCESS;
				}else{
					exception = CustomException.PARAMSERROR;
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
	 * 获取备课夹内容列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value ="/v1.0/prepareContent/{prepareId}" , method = RequestMethod.GET)
	@ResponseBody
	public  ResultJSON  querPrepareContent(@PathVariable Long prepareId,HttpServletRequest request, HttpServletResponse response){
		
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;
		
		try{
			
			String resServiceLocal = (String)request.getAttribute("resServiceLocal");
			String currentResPath = (String)request.getAttribute("currentResPath");
			
			
			if(currentUserId!=null && exception==null){	
				if(prepareId>0){
					//获取备课夹内容
					List<JPrepareContentView> list   = jPrepareService.queryPrepareContentList(prepareId);
					//将缩略图的path换成url
					JPrepareConstant.resetPrepareContImageView(list, resServiceLocal, currentResPath);
					
					data = list ; 
					
					exception = CustomException.SUCCESS;
				}else{
					exception = CustomException.PARAMSERROR;
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
	 * 插入备课夹
	 * @param request
	 * @param response
	 * @return
	 */
		@RequestMapping(value ="/v1.0/prepareContent/{prepareId}" , method = RequestMethod.POST)
		@ResponseBody
	  public  ResultJSON  addPrepareContent(@PathVariable Long prepareId, HttpServletRequest request, HttpServletResponse response){
			//返回json的结果对象
			ResultJSON result = new ResultJSON();
			//异常
			CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
			//当前登录用户id 
			Long currentUserId  =  (Long)request.getAttribute("currentUserId");
			//返回
			Object data = null;
			
			try{
			String resIds   =  request.getParameter("resIds");
			String fromFlags = request.getParameter("fromFlags");
			if(currentUserId!=null && exception==null){	
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
	@ResponseBody
	public  ResultJSON  delPrepareContent(HttpServletRequest request, HttpServletResponse response){
		
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		//返回
		Object data = null;
		
		try{
			String ids   =  request.getParameter("ids");
			String _method = request.getParameter("_method");

			if(currentUserId!=null && exception==null){	
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
							 jPrepareService.exchangeContOrderIdx(prevId, nextId);
							 exception = CustomException.SUCCESS;
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
	  @ResponseBody
	  public ResultJSON fillPrepareContent(HttpServletRequest request, HttpServletResponse response){
		//返回json的结果对象
			ResultJSON result = new ResultJSON();
			//异常
			CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
			//当前登录用户id 
			Long currentUserId  =  (Long)request.getAttribute("currentUserId");
			//返回
			Object data = null;
			
		
			
			String rescode = request.getParameter("rescode");
		    String _method = request.getParameter("_method");

		   try {
			   
				if(currentUserId!=null && exception==null){	
								long userId = currentUserId;
								 //无节点选择时系统资源加入备课夹接口
								if(StringUtils.isNotEmpty(_method)&& "RESOURCEFILL".equalsIgnoreCase(_method)){
									jPrepareService.addToPrepareWithOnlySysResource(rescode, userId);
									exception = CustomException.SUCCESS;
								}else{
									exception = CustomException.PARAMSERROR;
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
	  @ResponseBody
	  public ResultJSON prepareZip(HttpServletRequest request, HttpServletResponse response){
		  	//返回json的结果对象
			ResultJSON result = new ResultJSON();
			//异常
			CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
			//当前登录用户id 
			Long currentUserId  =  (Long)request.getAttribute("currentUserId");
			//返回
			Object data = null;		  
		  	
			String resIds   =  request.getParameter("resIds");
			String fromFlags = request.getParameter("fromFlags");
			String hostLocal = (String)request.getAttribute("hostLocal");
			String resServiceLocal = (String)request.getAttribute("resServiceLocal");

		try{
			if(currentUserId!=null && exception==null){	
						long  userId = currentUserId;
					  	String zippath = ZhlResourceCenterWrap.getUserZipPath(userId);
					  	String rollBackUrl = "";//回调函数

					  	
					  	
					  	
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
									//将原始的path重置为可用对应的下载文件的路径
									JPrepareConstant.resetResourceDownLoadUrl(list, resServiceLocal);
									
									ResZipDownRecord  record = new ResZipDownRecord();
									record.setUserid(userId);
									record.setFromflags(fromFlags);
									record.setIds(resIds);
									record.setStatus(false);
									record.setZippath(zippath);
									record.setTime(Calendar.getInstance().getTime());
									//增加下载记录
									resZipDownloadService.addZipDownRecord(record);
									Long recordId = record.getId();
									rollBackUrl = hostLocal+"/resRestAPI/zipDownload_status?id="+recordId;//回调函数
									//创建打包任务
									ZipTaskContent content = new ZipTaskContent();
									//指定打包文件路径
									content.setZipFileName(zippath);
									String[] src = new String[list.size()];					
									String[] end = new String[list.size()];					
									for (int i = 0; i < list.size(); i++) {
										ResourceSimpleInfo info = list.get(i);
										String title = info.getTitle();
										String path = info.getPath().replaceAll("\\\\", "/");
										String name = path.substring(path.lastIndexOf("/")+1,path.length());
										name = name.replace(name.substring(0, name.lastIndexOf(".")), title);
										src[i] = path;
										end[i] = name;
									}
									
									content.setSourceFile(src);
									content.setEntryName(end);
									//增加回调
									content.setNotifyUrl(rollBackUrl);
									//打包
									ZhlResourceCenterWrap.sendZipTask(content, resServiceLocal);
									exception = CustomException.SUCCESS;
									data = record.getId();
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
	  
	  
	  /**
	   * 更新历史的打包下载的状态
	   * @param request
	   * @param response
	   * @return
	   */
	  @RequestMapping(value="/v1.0/zipDownload_status" ,method=RequestMethod.GET) 
	  @ResponseBody
	  public void updateZipDownloadStatus(HttpServletRequest request, HttpServletResponse response){
		  String _id = request.getParameter("id");
		  long id = 0 ;
		  if(StringUtils.isNotEmpty(_id)){
			  id = Long.parseLong(_id);
		  }		  
		  
		  ResZipDownRecord  record = new ResZipDownRecord();		  
		  record.setId(id);
		  record.setStatus(true);
		  resZipDownloadService.updateZipDownRecord(record);
	  
	  }
	  
	  
	  
	  /**
	   * 获取指定的下载的zip打包 是否完成
	   * @param request
	   * @param response
	   * @return
	   */
	  @RequestMapping(value="/v1.0/prepareZip_staus" ,method=RequestMethod.GET) 
	  @ResponseBody
	  public ResultJSON getZipDownloadStatus(HttpServletRequest request, HttpServletResponse response){

		  	//返回json的结果对象
			ResultJSON result = new ResultJSON();
			//异常
			CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
			//当前登录用户id 
			Long currentUserId  =  (Long)request.getAttribute("currentUserId");
			//返回
			Object data = null;	
			
			try{
				
				if(currentUserId!=null && exception==null){	
					  String _id = request.getParameter("id");
					  long id = 0 ;
					  if(StringUtils.isNotEmpty(_id)){
						  id = Long.parseLong(_id);
					  }
					  
					  ResZipDownRecord  record = resZipDownloadService.getZipDownRecord(id);
					  HashMap<String, Object> map = new HashMap<String, Object>();
					  map.put("id", record.getId());
					  map.put("status", record.getStatus());
					  map.put("zippath", record.getZippath());
					  data = map;
					  exception = CustomException.SUCCESS;
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
	   * 
	   * 获取资源 的播放地址 
	   * @param request
	   * @param response
	   * @return
	   */
	  @RequestMapping(value="/v1.0/resViewUrl" ,method=RequestMethod.GET) 
	  @ResponseBody
	  public ResultJSON getResViewUrl(HttpServletRequest request, HttpServletResponse response){
			String resIds   =  request.getParameter("resIds");
			String fromFlags = request.getParameter("fromFlags");
			
			String resServiceLocal = (String)request.getAttribute("resServiceLocal");
			String currentResService = (String)request.getAttribute("currentResPath");
			
			//返回json的结果对象
			ResultJSON result = new ResultJSON();
			//异常
			CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
			//当前登录用户id 
			Long currentUserId  =  (Long)request.getAttribute("currentUserId");
			//返回
			Object data = null;	

		try{
			
			if(currentUserId!=null && exception==null){	
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
									//将原始的path重置为可用的web链接
									JPrepareConstant.resetResourceViewUrl(list, resServiceLocal, currentResService);
									data = list; 
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
	  
	  
	  public static void main(String[] args) {
		String s = "20150416160806813.swf";
		
		System.out.println("1212112");
		
	}

}