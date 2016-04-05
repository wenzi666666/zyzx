package net.tfedu.zhl.cloud.resource.bookself.bean;

import java.util.List;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.fileservice.ZhlResourceCenterWrap;

public class BookSelfImgPathConvert {
	
	/**
	 * 获取文件服务中的路径
	 * @param list
	 * @param resServiceLocal
	 * @param currentResPath
	 */
	public static void convert(List<BookSelfView> list,String resServiceLocal,String currentResPath){
		if(list!=null && list.size()>0){
			for (BookSelfView bookSelfView : list) {
				String imgPath = bookSelfView.getImgpath();
				if(StringUtils.isNotEmpty(imgPath)){
					System.out.println("-----"+imgPath);
					imgPath = ZhlResourceCenterWrap.getDownUrl(resServiceLocal, imgPath);
					imgPath = imgPath.replace(resServiceLocal, currentResPath);
					bookSelfView.setImgpath(imgPath);
				}
				
			}			
		}
	}

}
