package cn.winstone.rb.entity;

import java.util.List;

/**
 * @author Winstone
 * @date 2021/1/28 - 4:30 下午
 */
public class Config {
	private List<ConfigItem> source;
	private List<ConfigItem> target;

	public List<ConfigItem> getSource() {
		return source;
	}

	public void setSource(List<ConfigItem> source) {
		this.source = source;
	}

	public List<ConfigItem> getTarget() {
		return target;
	}

	public void setTarget(List<ConfigItem> target) {
		this.target = target;
	}
}
