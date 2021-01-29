package cn.winstone.rb.serivce;


import cn.winstone.rb.entity.Config;
import cn.winstone.rb.utils.FileUtil;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @author Winstone
 * @date 2021/1/28 - 4:33 下午
 */
public class ConfigReader {
	private String path;

	public ConfigReader(String path) {
		this.path = path;
	}

	public Config getConfig() throws IOException {
		String json = FileUtil.readJsonFile(path);
		if (json == null){
			throw new IOException("读取配置文件出错");
		}
		return JSONObject.parseObject(json,Config.class);
	}
}
