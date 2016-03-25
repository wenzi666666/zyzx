package net.tfedu.zhl.cloud.resources.poolTypeFormats.service;

import java.util.List;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.FileFormat;
import net.tfedu.zhl.cloud.resources.poolTypeFormats.entity.ResPoolType;


/**
 * 资源格式 service
 * @author WeiCuicui
 *
 */
public interface ResFormatService {

	//根据资源库id，得到父类型的所有子类型及其自身
	public List<ResPoolType> getTypesByPMTypeAndPool(long poolId, long MType);
	
	//根据资源ids和typeIds，查询得到资源格式
	public List<FileFormat> getFormatsByMType(List<Long> resourceIds, List<Integer> typeIds);
}