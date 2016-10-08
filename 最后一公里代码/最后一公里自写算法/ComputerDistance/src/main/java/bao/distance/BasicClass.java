package bao.distance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * ���洦���õ��Ļ�������
 * */
public abstract class BasicClass {

	static InputStreamReader read = null;
	
	/**
	 * ��ȡ�ļ��������
	 * 
	 * @param path
	 *            �ļ�·��+�ļ���
	 * 
	 * @return BufferedReader
	 * 
	 * */
	static BufferedReader read(String pathName) {
		File file = new File(pathName);
		if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
			
			try {
				read = new InputStreamReader(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new BufferedReader(read);
		}
		return null;
	}

	/**
	 * �ر���
	 * @throws IOException 
	 * */
	static void close() throws IOException{
		read.close();
	}
	
	/**
	 * ��ȡ��ǰĿ¼�µ��ļ���
	 * 
	 * @param path
	 *            ��ǰĿ¼·��
	 * */
	static String[] getFileName(String path) {
		File file = new File(path);
		String[] fileName = file.list();
		return fileName;
	}

	/**
	 * ������
	 * 
	 * @param path
	 *            ����·��
	 * @param name
	 *            ������ļ���
	 * @param record
	 *            һ����¼
	 * */
	static void save(String path, String name, String record) {

		String md = path + name + ".txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(md, true));
			bw.write(record+ "\r\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/**
	 * ������
	 * 
	 * @param path
	 *            ����·��
	 * @param res
	 *            ����IP����������(��ʽ��Map<IP,��������>)
	 * @param name
	 *            ������ļ���
	 * 
	 * */
	static void save(String path, int name, Map<String, Integer> res) {

		String md = path + name + ".txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(md, true));

			for (Map.Entry<String, Integer> entry : res.entrySet()) {
				bw.write(entry.getKey() + " " + entry.getValue() + "\r\n");
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * �����ļ�
	 * 
	 * @param ��Ҫ�������ļ���·�����ļ���
	 * */
	static void createFile(String path, String name) {
		String file = path + name + ".txt";
		File zb = new File(file);
		if (!zb.exists()) {
			try {
				zb.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * �����ļ���
	 * 
	 * @param path
	 *            �������ļ��е�·��
	 * 
	 * */
	static void createFolder(String path) {

		File wjj = new File(path);
		if (!wjj.exists()) {
			wjj.mkdirs();
		}

	}
}
