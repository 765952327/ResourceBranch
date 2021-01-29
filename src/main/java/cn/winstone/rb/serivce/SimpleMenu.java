package cn.winstone.rb.serivce;


import cn.winstone.rb.entity.ConfigItem;

import java.util.List;

/**
 * @author Winstone
 * @date 2021/1/28 - 4:45 下午
 */
public class SimpleMenu {
	public void start(String version){
		System.out.println("---------------------------------");
		System.out.println("-        前端资源切换 "+version+"       -");
		System.out.println("---------------------------------");
	}

	public void choose(List<ConfigItem> items){
		int i = 0;
		for (ConfigItem item:items){
			System.out.println("+ " + i + "、 " + item.getTag());
			i++;
		}
		System.out.println("---------------------------------");
	}
}
