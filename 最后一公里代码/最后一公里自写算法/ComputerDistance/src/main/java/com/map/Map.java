package com.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;



public class Map {
	// ���ж�ȡ ����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�������map����
	public static void wordcount(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String tempString = null;
		int line = 1;
		// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
		while ((tempString = reader.readLine()) != null) {
			// ��ʾ�к�
			StringTokenizer stringTokenizer = new StringTokenizer(tempString);
			// ����
			while (stringTokenizer.hasMoreElements()) {
				// ��ȡÿ��ֵ
				String wordValue = stringTokenizer.nextToken();
				// ����map�����keyֵ
				WriteFile.writeFileByLines("D:/b.txt", wordValue + "," + "1");
			}

			// System.out.println("line " + line + ": " + tempString);
			line++;
		}
		reader.close();

	}

	public static void main(String[] args) throws IOException {
		wordcount("D:/a.txt");
	}

}
