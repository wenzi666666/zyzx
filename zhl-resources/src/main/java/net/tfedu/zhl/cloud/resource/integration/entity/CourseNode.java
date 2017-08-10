package net.tfedu.zhl.cloud.resource.integration.entity;

import java.util.Map;

/**
 * 目录tree节点实体（easyui）
 * 
 * @author wangwr
 *
 */
public class CourseNode {

	/**
	 * id
	 */
	private String id;
	/**
	 * 名称
	 */
	private String text;
	/**
	 * 状态 展开 收缩
	 */
	private String state;
	/**
	 * 额外的属性
	 */
	private Map<String, Object> attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}