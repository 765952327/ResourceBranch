package cn.winstone.rb;


import cn.winstone.rb.entity.Config;
import cn.winstone.rb.serivce.ConfigReader;
import cn.winstone.rb.serivce.SimpleMenu;
import cn.winstone.rb.utils.FileUtil;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Winstone
 * @date 2021/1/28 - 3:55 下午
 */
public class Main {
	private static String version = "v 1.0";
	public static void main(String[] args) {
		ConfigReader configReader = new ConfigReader("./config.json");
		Config config = null;
		try {
			config = configReader.getConfig();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return;
		}
		if (config == null){
			System.err.println("配置文件不存在");
			return;
		}

		Scanner scanner = new Scanner(System.in);
		SimpleMenu menu = new SimpleMenu();
		menu.start(version);
		menu.choose(config.getSource());
		System.out.println("选择前端资源:");
		int i = scanner.nextInt();
		menu.choose(config.getTarget());
		System.out.println("选择工作区:");
		int j = scanner.nextInt();

		try {
			FileUtil.copy(config.getSource().get(i).getPath(),config.getTarget().get(j).getPath());
			System.out.println("SUCCESS");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
