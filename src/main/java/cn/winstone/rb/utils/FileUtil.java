package cn.winstone.rb.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 文件处理工具
 */
public class FileUtil {
	private static String[] ignores = {".DS_Store"};

	/**
	 * 复制文件
	 *
	 * @param source
	 * @param target
	 * @return boolean
	 */
	public static boolean copy(String source, String target) throws IOException {
		File targetFile = new File(target);
		if (!targetFile.isDirectory()) {
			throw new IOException("目标地址不是文件夹");
		}
		File[] files = getAllFiles(source);
		if (files == null) {
			return false;
		}
		if (files.length == 0) {
			return false;
		}
		//遍历所有文件
		for (File file : files) {
			// macos支持
			if (isIgnore(file)){
				continue;
			}
			try {
				String targetUrl = getTargetPath(file, source, target);
				//创建目录或文件
				if (file.isDirectory()) {
					createDir(targetUrl);
					copy(file.getAbsolutePath(), targetUrl);
				} else {
					write(file, targetUrl);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private static boolean isIgnore(File file){
		for (String i:ignores){
			if (i.equals(file.getName())){
				return true;
			}
		}
		return false;
	}

	private static String getTargetPath(File file, String source, String target) {
		String fileAbsolutePath = file.getAbsolutePath();
		String filePath = fileAbsolutePath.substring(source.length());
		String targetPath = target + filePath;
		return targetPath;
	}

	public static void write(File file, String path) {
		File target = new File(path);
		write(file, target);
	}

	public static void write(File s, File t) {
		try {
			FileInputStream fin = new FileInputStream(s);
			FileOutputStream fout = new FileOutputStream(t);

			byte[] a = new byte[1024 * 1024 * 4];
			int b = -1;

			//边读边写
			while ((b = fin.read(a)) != -1) {
				fout.write(a, 0, b);
			}
			fout.close();
			fin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取路径下所有文件
	 *
	 * @param path
	 * @return files
	 */
	public static File[] getAllFiles(String path) {
		File dir = new File(path);
		if (dir.isDirectory()) {
			return dir.listFiles();
		}
		if (dir.isFile()) {
			File[] files = new File[1];
			files[0] = dir;
			return files;
		}
		return null;
	}

	/**
	 * 创建文件夹
	 *
	 * @param path
	 * @return boolean
	 */
	public static boolean createDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return file.mkdir();
		}
		return false;
	}

	/**
	 * 读取JSON文件
	 *
	 * @param path
	 * @return
	 */
	public static String readJsonFile(String path) {
		String jsonStr = "";
		try {
			File jsonFile = new File(path);
			String name = jsonFile.getName();
			if (!".json".equals(name.substring(name.lastIndexOf(".")))) {
				return null;
			}
			FileReader fileReader = new FileReader(jsonFile);
			Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
			int ch = 0;
			StringBuffer sb = new StringBuffer();
			while ((ch = reader.read()) != -1) {
				sb.append((char) ch);
			}
			fileReader.close();
			reader.close();
			jsonStr = sb.toString();
			return jsonStr;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
