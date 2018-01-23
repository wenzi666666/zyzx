$(function(){

	
	
	
	 var format = function (domId,data) {
        var options = {
            dom: domId,
            isCollapsible: false,
            quoteKeys: true,
            tabSize:2
        };
        window.jf = new JsonFormater(options);
        jf.doFormat(data);
    };
    
	
	
	
	
	/**
	 * 点击提交
	 */
	$(".goBtn").click(function(){
		
		//是否是登录接口
		var isLoginAPI =false ;
		
		var api_div = $(this).parent().parent();

		
		//是否是资源的上传接口
		var isResUpload = false ;
		var isZipInterface = false;//是否是打包下载接口
		

		
		var checkFlag = true;
		//检查是否设置参数
		api_div.find("table").find("input").each(function(){
			var val = $.trim($(this).val());
			var key = $(this).attr("name").replace("request_","").replace("header_","");
			if(val.length==0){
				alert("请设置参数："+key);
				$(this).focus();
				checkFlag = false;
				return false ;
			}			
		});
		
		if(!checkFlag){
			return false ;
		}
		
		var api_name = api_div.find("span[name=api_name]").html();
		if(api_name.indexOf("login")>-1){
			isLoginAPI = true;
		}
		
		
		var api_url = api_div.find("span[name=api_url]").html();
		var api_method = api_div.find("span[name=api_method]").html();
		var header_key = new Array();
		var header_value = new Array();
		var request_key = new Array();
		var request_value = new Array();
		
		
		
		//判断路径中是否含有参数
		var regx = /\{{1}.*\}{1}/;
		var path_param = api_url.match(regx);
		var path_param_array = new Array();
		if(path_param!=null){
			path_param_array.push(path_param[0].replace(/\{/,"").replace(/\}/,""));
		}
		
		

		if("/resRestAPI/v1.0/resource/upload"==api_url){
			isResUpload = true;
		}else if("/resRestAPI/v1.0/prepareZip"==api_url){
			isZipInterface = true ;
		}
		
		//整理参数设置
		api_div.find("table").find("input").each(function(){
			var name =  $(this).attr("name");
			var val = $.trim($(this).val());
			
			//逗号要转义, &#44
			val = val.replace(/,/ig,"&#44");
			
			var key =name.replace("request_","").replace("header_","");
			if(name.indexOf("request_")==0){
				
				//是否时路径中的参数
				if(path_param_array.indexOf(key)>-1){
					var str = "\{"+key+"\}";
					api_url = api_url.replace( new RegExp(str,"ig") ,val);
				}else{
					request_key.push(key);
					request_value.push(val)				
				}
			}else if(name.indexOf("header_")==0){
				header_key.push(key);
				header_value.push(val)
			}			
		});
		
		var server = $.trim($("#server").val());
		
		
		//提交
//		$("#targetForm").find("input[name=url]").val(server+"/"+api_url);
//		$("#targetForm").find("input[name=http_method]").val(api_method);
//		$("#targetForm").find("input[name=header_key]").val(header_key.toString());
//		$("#targetForm").find("input[name=header_value]").val(header_value.toString());
//		$("#targetForm").find("input[name=request_key]").val(request_key.toString());
//		$("#targetForm").find("input[name=request_value]").val(request_value.toString());
//		$("#targetForm").attr("method","POST").attr("target","_blank").submit();

		
		//data_sign 用户获取获取sign
		var data_sign = "";
		for (var int = 0; int < request_key.length; int++) {
			if(int >0 ){
				data_sign+="&";
			}
			data_sign += (request_key[int]+'='+request_value[int]);
		}
		
		
		//非登錄接口需要校驗sign
		if(!isLoginAPI){
			
			//增加token
			data_sign += "&token="+$.trim($("#token").val());

			
			var url =$("#basePath").val()+"/baseData/resource/v1.0/createSign";
			$.ajax({
				url:url,
				data:data_sign,
				async:false,
				success:function(data){
					if("OK" == data.code){
						data_sign+=("&sign="+data.data);
					}else{
						alert("不能正常获取校验字符串SIGN");
					}
				}
			});
			
			
		}
		
		
		
		
		
		
		
		
		if("GET"==api_method){
			$.get(server+api_url,data_sign,function(data){
				
				detailWithData(data,api_div,isLoginAPI);
			});
			
			
		}else{
			
			$.post(server+api_url,data_sign,function(data){
				
				detailWithData(data,api_div,isLoginAPI);
			});
			
			
		}
		
		
		
	});
	
	
	

	/**
	 * 处理ajax请求
	 * @param data
	 */
	function detailWithData(data,api_div,isLoginAPI){
		var cid ;
		var canvas = api_div.find(".Canvas");
		if(canvas.length==0){
			cid = "Canvas_"+new Date().getTime().toString();				
			api_div.append("<div id="+cid+" class=Canvas></div>");
		}else{
			cid = canvas.attr("id");
		}
		
		
		//如果是登录接口
		if(isLoginAPI){
			var token = data.data.token;
			$("input[name=token]").val(token);
		}

		var json_string = JSON.stringify(data);
		format("#"+cid,json_string);
		
		
	}

	
});






var num = 0;
var total=0; 

//增加上传按钮
function addUploadBtn(uploadUrl,uploadPath,api_div){
	var prev_id = api_div.attr("id").replace("testDiv_","");
	var next_id = 1+ new Number(prev_id);
	var next_div = $("#testDiv_"+next_id);

	var basePath = $("#basePath").val();
	var html = "<div class='upload_div'> <input id='uploadBtn' type='button'/> </div>";
	var btn = $("#uploadBtn");
	if(btn.length == 0){
		api_div.append(html);
	}
	
	
	$('#uploadBtn').uploadify({
		'swf'      : basePath+'/upload/uploadify.swf',  //上传flash组件
		'buttonText':"UP",			//上传按钮文字
		'progressData' : 'percentage',		//进度条
		'queueSizeLimit':10,			//上传队列长度
		'fileTypeDesc' : 'Image Files', //文件选择窗口描述
		'fileTypeExts' : '*.swf;*.jpg;*.gif;*.jpeg;*.png;*.bmp;*.mp4;*.wmv;*.avi;*.flv;*.mp3;*.wma;*.wav;*.m4a;*.doc;*.docx;*.ppt;*.xls;*.pdf;*.txt;*.pptx;*.xlsx;*.rar;*.zip;',//上传文件类型限制
		'method'   : 'post',					//提交方式
		'multi'    :  true,						//是否支持多文件上传 
		'fileObjName':'file',
		'fileSizeLimit' : '150MB',					//上传文件在服务器端的name；struts2必须
		'uploader':uploadUrl+'&aa='+new Date(),
		'formData'      : {},
		 width:52,
		 height:28,
		 successTimeout:600,
		'cancelImg':"<img src='/resource_center/img/closes.jpg' />",
		'onDialogClose':function(queueData){
		 total+=queueData.filesQueued;
		},
		'onUploadError' : function(file, errorCode, errorMsg, errorString) {
			if(!("Cancelled"==errorString))
			{
				alertError("服务器错误:"+errorString);
			}
		},
		'onCancel': function(file){
		},
		'onDialogOpen':function(){
//			$(".w_zybox").hide();
		},
		'onUploadSuccess' : function(file, data, response) {
			if(data=="error"){
				alertError("对不起,上传文件名中不能包含非法字符");
			}else{
				var append_path = uploadPath+data;
				num++;
				abc(file,data,response,append_path);
				if(total!=0&&num==total){
					
					next_div.find("input[name=request_paths]").val(append_path);
					next_div.find("input[name=request_sizes]").val(file.size);
					next_div.find("input[name=request_names]").val(file.name);
					//names
					next_div.find("input[name=request_islocals]").val("0");
					next_div.find("input[name=request_iscoursewares]").val("0");
					next_div.find("input[name=request_unifTypeIds]").val("1");
					//scopes
					next_div.find("input[name=request_scopes]").val("0");
					next_div.find("input[name=request_tfcodes]").val("RJXX01010101");

				}
			}
		},
		'onSelect':function(file) {
					var alt = checkSizeForType(file.type,file.size,file.name);	
					if(alt!= "" && alt.length > 0 ){
						alert(alt);
						$('#file_upload').uploadify("cancel",file.id+"");//取消过大的文件上传
						total--;
						return false;
					}
				}
			});
	
	
	
}



	
function abc(file,data,response,append_path){
	var obj=document.getElementById("file_span");
	var rsize = document.getElementById("rsize");
	rsize.value = rsize.value+file.size+",";
	var rpath = document.getElementById("rpath");
	rpath.value = rpath.value+append_path+"|";
	if(navigator.userAgent.indexOf("MSIE")>0) { 
	}
	if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ 
	}
	obj.innerHTML=obj.innerHTML+num+","+encodeURIComponent(file.name)+data+";"+"<BR/><BR/>";
}
      
    //根据文件类型校验资源
function checkSizeForType(fileType,fileSize,filename){
      	fileType=fileType.toLowerCase();
      	 var imgType   = ".jpg;.gif;.jpeg;.png;.bmp;";
      	 var redioType = ".mp3;.wma;.wav;";
      	 var docType   = ".doc;.docx;.ppt;.xls;.pdf;.txt;.xlsx;.pptx;";
      	 var flashType = ".swf;";
      	 var vedioType = ".mp4;";
      	 var zipType   = ".rar;.zip;";
      	 
      	 
      	 var sizeLimit1 = 5;//单位为M
      	 var sizeLimit2 = 30;//单位为M
      	 var sizeLimit3 = 100;//单位为M	
      	 
      	 var _filesize =  fileSize/(1024*1024) ; 
      	 var size_able = 0 ;
      	 if(imgType.indexOf(fileType)>=0){
      		 size_able = sizeLimit1;
      		 return size_able> _filesize ?"":(filename+"图片文件过大");
      	 }else  if(redioType.indexOf(fileType)>=0 || redioType.indexOf(docType)>=0 || redioType.indexOf(flashType)>=0) {
      		 size_able = sizeLimit2;
      		 return size_able> _filesize ?"":(filename+"文件过大");
      	 } else if(redioType.indexOf(vedioType)>=0|| redioType.indexOf(zipType)>=0  ){
      		 size_able = sizeLimit3;
      		 return size_able> _filesize ?"":(filename+"视频文件过大");
       	 }
      	 if(filename!=null&&filename.length>30){
      		 return filename+"文件名称太长";
      	 }
      	 return "";
      }
      	



function setZipTaskId(taskId,api_div){
	var prev_id = api_div.attr("id").replace("testDiv_","");
	var next_id = 1+ new Number(prev_id);
	var next_div = $("#testDiv_"+next_id);
	next_div.find("input[name=request_id]").val(taskId);


}