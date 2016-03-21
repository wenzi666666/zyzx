package net.tfedu.zhl.sso.filter;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import net.tfedu.zhl.cloud.utils.datatype.StringUtils;
import net.tfedu.zhl.sso.dao.SysReourceMapper;
import net.tfedu.zhl.sso.entity.Role;
import net.tfedu.zhl.sso.entity.SysResource;

/**
 * 动态配置权限
 * @author Bruce.Liu
 *
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Section> {
	@Autowired
	private SysReourceMapper sysReourceMapper;

	private String filterChainDefinitions;

	/**
	 * 默认premission字符串
	 */
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";
	public static final String ROLE_STRING = "roles[\"{0}\"]";

	public Section getObject() throws BeansException {

		// 获取所有SysReource
		List<SysResource> list = sysReourceMapper.selectAllPermission();

		Ini ini = new Ini();
		// 加载默认的url
		ini.load(filterChainDefinitions);
		Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
		// 循环SysReource的url,逐个添加到section中。section就是filterChainDefinitionMap,
		// 里面的键就是链接URL,值就是存在什么条件才能访问该链接
		for (Iterator<SysResource> it = list.iterator(); it.hasNext();) {

			SysResource resource = it.next();
			// 如果不为空值添加到section中
			if (StringUtils.isNotEmpty(resource.getUrl())) {
				// /admin/** = authc, roles[admin]
				for (Role role : resource.getRoles()) {
					section.put(resource.getUrl(), MessageFormat.format(PREMISSION_STRING, role.getName()));
				}
			}

		}

		return section;
	}

	/**
	 * 通过filterChainDefinitions对默认的url过滤定义
	 * 
	 * @param filterChainDefinitions
	 *            默认的url过滤定义
	 */
	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}

	public Class<?> getObjectType() {
		return this.getClass();
	}

	public boolean isSingleton() {
		return false;
	}
}
