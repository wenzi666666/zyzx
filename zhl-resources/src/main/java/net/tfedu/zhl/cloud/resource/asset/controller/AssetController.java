package net.tfedu.zhl.cloud.resource.asset.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.tfedu.zhl.cloud.resource.asset.entity.ZAsset;
import net.tfedu.zhl.cloud.resource.asset.service.ZAssetService;
import net.tfedu.zhl.cloud.resource.prepare.entity.JPrepareContentViewUtil;
import net.tfedu.zhl.cloud.resource.resourceList.entity.Pagination;
import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.config.CommonWebConfig;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;
import net.tfedu.zhl.fileservice.zhldowncenter;
import net.tfedu.zhl.helper.CustomException;
import net.tfedu.zhl.helper.ResultJSON;
import net.tfedu.zhl.sso.user.entity.JUser;
import net.tfedu.zhl.sso.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/resRestAPI")
public class AssetController {
	
	
	
	
	/**
	 * 用户service
	 */
	@Resource
	private UserService userService ;
	
	/**
	 * 自建资源service
	 */
	@Resource
	ZAssetService  assetService ;
	
	
	
    @Resource
    private CommonWebConfig commonWebConfig;
    
    
    
    
    Logger logger = LoggerFactory.getLogger(AssetController.class);

	
	/**
	 * 前端web端获取上传文件路径（支持格式转换）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/resource/upload",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON  getUploadUrl(HttpServletRequest request, HttpServletResponse response){
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
				//获取文件服务器的访问url 
				String resServiceLocal = commonWebConfig.getResServiceLocal();
				String currentResPath = commonWebConfig.getCurrentResPath(request);
				String hostLocal = commonWebConfig.getHostLocalOne();
				
				
				
				long userId = currentUserId;
				JUser  user =  userService.getUserById(userId);
				long schoolId = user.getSchoolid();

				logger.debug("获取用户的上传路径,userId="+userId);

				//组装上传路径
				String uploadPath = ZhlResourceCenterWrap.getUserUploadPath(userId, schoolId);
				//获取上传文件路径
				String uploadUrl=  ZhlResourceCenterWrap.getUploadUrlConvert(uploadPath, resServiceLocal, currentResPath, hostLocal, userId);
				
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("uploadUrl", uploadUrl);
				map.put("uploadPath", uploadPath);
				data = map ; 
				exception = CustomException.SUCCESS;
			}else{
            	exception = CustomException.INVALIDACCESSTOKEN;
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
	 * 文件服务格式转换后的回调
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/resource/uploadConvertCallBack",method=RequestMethod.GET)
	@ResponseBody
	public String  uploadConvertCallBack(HttpServletRequest request, HttpServletResponse response){
		try {
			//获得文件服务器返回的extend参数
			String ext = zhldowncenter.GetOriginQueryString(request.getParameter("ext")); 
			//从extend参数中获得userId
			int index = ext.indexOf("=");
			long userId = Long.parseLong(ext.substring(index + 1,ext.length())); 
			//获得文件服务器返回的file参数
			String resPath = request.getParameter("file");

			assetService.setTypeConvertSucceed(userId, resPath);
	
			logger.debug("文件服务格式转换后的回调,userId="+userId+",resPath="+resPath);

			
			return "SUCCESS";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
	}
	
	
	
	
	
	/**
	 * 新建资源
	 * 删除资源
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/resource/asset",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON  deleteAsset(HttpServletRequest request, HttpServletResponse response){
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
				String _method = request.getParameter("_method");
				//删除资源
				if(StringUtils.isNotEmpty(_method)&&RequestMethod.DELETE.name().equalsIgnoreCase(_method)){
					String resIds = request.getParameter("resIds");
					if(StringUtils.isNotEmpty(resIds)){
						assetService.delAsset(resIds);
						exception = CustomException.SUCCESS;
						logger.debug("删除资源,resIds="+resIds);

					}else{
						exception = CustomException.PARAMSERROR;
					}
					
				}else{
					//新建资源

					//获取文件服务器的访问url 
					String resServiceLocal = commonWebConfig.getResServiceLocal();
					String currentResPath = commonWebConfig.getCurrentResPath(request);
					String hostLocal = commonWebConfig.getHostLocalOne();
					
					

					String _names = request.getParameter("names");
					String _unifTypeIds = request.getParameter("unifTypeIds");
					String _tfcodes = request.getParameter("tfcodes");
					String _scopes = request.getParameter("scopes");
					String _keywords = request.getParameter("keywords");
					String _descs = request.getParameter("descs");
					String _paths = request.getParameter("paths");
					String _sizes = request.getParameter("sizes");
					String _iscoursewares = request.getParameter("iscoursewares");
					String _islocals = request.getParameter("islocals");
					//非空判断并长度检测
					if(StringUtils.isNotEmpty(_names)&&StringUtils.isNotEmpty(_unifTypeIds)&&StringUtils.isNotEmpty(_tfcodes)&&
							StringUtils.isNotEmpty(_scopes)&&StringUtils.isNotEmpty(_keywords)&&StringUtils.isNotEmpty(_descs)&&
							StringUtils.isNotEmpty(_paths)&&StringUtils.isNotEmpty(_sizes)&&StringUtils.isNotEmpty(_iscoursewares)
							&&StringUtils.isNotEmpty(_islocals)
							){						
						logger.debug("新建资源,names="+_names);
						
						
						String names[] = _names.split(",");
						String unifTypeIds[] = _unifTypeIds.split(",");
						String tfcodes[] = _tfcodes.split(",");
						String scopes[] = _scopes.split(",");
						String keywords[] = _keywords.split(",");
						String descs[] = _descs.split(",");
						String paths[] = _paths.split(",");
						String sizes[] = _sizes.split(",");
						String iscoursewares[] = _iscoursewares.split(",");
						String islocals[] = _islocals.split(",");
						if(names.length!=unifTypeIds.length ||names.length!=tfcodes.length ||names.length!=scopes.length ||names.length!=keywords.length
								|| names.length!=descs.length ||names.length!=paths.length ||names.length!=sizes.length||names.length!=iscoursewares.length
								||names.length!=islocals.length
								){
							exception = CustomException.PARAMSERROR;
						}else{
							//资源标示
							String fileType= "A";
							//0：转码完成，1：未完成
							int isfinished = 1 ;
							//是否发布 （默认未发布）
							Boolean isIssue = false ;
							//是否为网络资源

							
							Date createtime = Calendar.getInstance().getTime();
							SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhMMssSSS");
							String resourceId = userId+format.format(createtime) ;
							
	
							
							
							ArrayList<ZAsset> list = new ArrayList<ZAsset>();
							ArrayList<String> tfcode_list = new ArrayList<String>();
							ArrayList<Integer> scope_list = new ArrayList<Integer>();
							
							for (int i = 0; i < names.length; i++) {
								//系统目录
								tfcode_list.add(tfcodes[i]);
								//共享范围   0 个人可见 1 校本共享 2 区本共享
								scope_list.add(Integer.parseInt(scopes[i]));

								int islocal = Integer.parseInt(islocals[i]) ;


								ZAsset a  = new ZAsset();
								a.setUserid(userId);
								a.setFiletype(fileType);
								a.setCreatetime(createtime);
								a.setAssetpath(paths[i]);
								a.setAssetsize(sizes[i]);
								a.setAssetdesc(descs[i]);
								a.setIsfinished(isfinished);
								//新版资源类型
								a.setUnifytypeid(Integer.parseInt(unifTypeIds[i]));
								a.setName(names[i]);
								//旧版资源类型字段也设置为新版的类型
								a.setTypeid(Long.parseLong(unifTypeIds[i]));
								a.setIscourseware(iscoursewares[i].equals("1")?true:false);
								a.setIslocal(islocal);
								a.setResourceid(resourceId);
								a.setIsissue(isIssue);
								a.setKeyword(keywords[i]);
								a.setFilepath(paths[i]);
								list.add(a);
								
							}
							assetService.addAssetBatch(list, tfcode_list,scope_list,resServiceLocal,currentResPath,hostLocal);
							exception = CustomException.SUCCESS;
						}
					}else{
						exception = CustomException.PARAMSERROR;
					}
				}				
			}else{
            	exception = CustomException.INVALIDACCESSTOKEN;
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
	 * 分页获取资源
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/resource/asset",method=RequestMethod.GET)
	@ResponseBody
	public ResultJSON  queryAsset(HttpServletRequest request, HttpServletResponse response){
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
				//获取文件服务器的访问url 
				String resServiceLocal = commonWebConfig.getResServiceLocal();
				String currentResPath = commonWebConfig.getCurrentResPath(request);

				long userId = 390440126;
				long unifyTypeId = 0 ;
				int page = 1; 
				int prePage = 10;
				
				String _unifyTypeId = request.getParameter("unifyTypeId");
				String _page = request.getParameter("page");
				String _prePage = request.getParameter("perPage");
				String fileFormat = request.getParameter("fileFormat");
				if(StringUtils.isNotEmpty(_unifyTypeId)){
					unifyTypeId  = Long.parseLong(_unifyTypeId.toString().trim());
				}
				if(StringUtils.isNotEmpty(_page)){
					page  = Integer.parseInt(_page.toString().trim());
				}
				if(StringUtils.isNotEmpty(_prePage)){
					prePage  = Integer.parseInt(_prePage.toString().trim()); 
				}
				
				if(StringUtils.isNotEmpty(fileFormat)){
					fileFormat  = fileFormat.toString().trim(); 
				}
				
				logger.debug("分页获取资源,userId="+userId+",unifyTypeId="+unifyTypeId+",fileFormat="+fileFormat+",page="+page+",prePage="+prePage);

				Pagination page_result = assetService.queryMyAssets(userId, unifyTypeId, fileFormat, page, prePage);
				
				JPrepareContentViewUtil.convertToPurpose_Asset(page_result.getList(), resServiceLocal, currentResPath);
				
				data = page_result;
				
				exception = CustomException.SUCCESS;
			}else{
            	exception = CustomException.INVALIDACCESSTOKEN;
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
	 * 编辑资源
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/resource/asset/{id}",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON  updateAsset(@PathVariable Long id,HttpServletRequest request, HttpServletResponse response){
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
				String _method = request.getParameter("_method");
				if(StringUtils.isNotEmpty(_method)&&RequestMethod.PUT.name().equalsIgnoreCase(_method)){
					//获取文件服务器的访问url 
					String resServiceLocal = commonWebConfig.getResServiceLocal();
					String currentResPath = commonWebConfig.getCurrentResPath(request);
					String hostLocal = commonWebConfig.getHostLocalOne();
					
					
					String name = request.getParameter("name");
					String unifTypeId = request.getParameter("unifTypeId");
					String scope = request.getParameter("scope");
					String keyword = request.getParameter("keyword");
					String desc = request.getParameter("desc");
					String path = request.getParameter("path");
	  				String size = request.getParameter("size");

				
					ZAsset a  = new ZAsset();
					a.setId(id);
					
					if(StringUtils.isEmpty(path)){
						a.setAssetpath(path);
						
					}
					if(StringUtils.isEmpty(size)){
						a.setAssetsize(size);
						
						
					}
					if(StringUtils.isEmpty(desc)){
						a.setAssetdesc(desc);
						
					}
					if(StringUtils.isEmpty(unifTypeId)){

						//新版资源类型
						a.setUnifytypeid(Integer.parseInt(unifTypeId));
						//旧版资源类型字段也设置为新版的类型
						a.setTypeid(Long.parseLong(unifTypeId));

					}
					if(StringUtils.isEmpty(name)){
						a.setName(name);
						
					}
					if(StringUtils.isEmpty(keyword)){
						a.setKeyword(keyword);
						
					}
					//新版资源类型
					assetService.updateAsset(a, resServiceLocal, currentResPath, hostLocal);
				
					logger.debug("编辑资源,"+a.toString());

				}
				exception = CustomException.SUCCESS;
			}else{
            	exception = CustomException.INVALIDACCESSTOKEN;
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
	 * 批量复制、批量剪切，我的资源
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/v1.0/resource/asset/patchCopyCut",method=RequestMethod.POST)
	@ResponseBody
	public ResultJSON  patchCopyAsset(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//返回json的结果对象
		ResultJSON result = new ResultJSON();
		//异常
		CustomException exception = (CustomException)request.getAttribute(CustomException.request_key);
		//当前登录用户id 
		Long currentUserId  =  (Long)request.getAttribute("currentUserId");
		
		Object data = null;
		
		try{
			if(currentUserId != null && exception == null){	
				
				String _method = request.getParameter("_method");
				
				//目录结点的tfcode
				String tfcode = "";
				if(StringUtils.isNotEmpty(request.getParameter("tfcode"))){
					tfcode = request.getParameter("tfcode").toString().trim();
				}
				
				//资源id
				List<Long> resIds = new ArrayList<Long>();
				String resIdString = "";
				if(StringUtils.isNotEmpty(request.getParameter("resIds"))){
					resIdString = request.getParameter("resIds").toString().trim();
					String[] ids = resIdString.split(",");
					for(int i = 0; i < ids.length;i++){
						resIds.add(Long.parseLong(ids[i].toString().trim()));
					}
				}
				
				//批量剪切       自建资源
				if(StringUtils.isNotEmpty(_method) && RequestMethod.DELETE.name().equalsIgnoreCase(_method)){
					logger.debug("批量剪切,resIdString="+resIdString);
					//要剪切到的目标课程结点tfcode
					String des_tfcode = "";
					if(StringUtils.isNotEmpty(request.getParameter("des_tfcode"))){
						des_tfcode = request.getParameter("des_tfcode").toString().trim();
					}
					assetService.patchCutAsset(resIds, tfcode, des_tfcode);
					
				} else { //批量复制     自建资源
					logger.debug("批量复制     自建资源,resIds="+resIds.toString());
					assetService.patchCopyAsset(resIds, tfcode);
				}
				exception = CustomException.SUCCESS;
			}else{
            	exception = CustomException.INVALIDACCESSTOKEN;
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

