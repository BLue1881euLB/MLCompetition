package com.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;



public class SplitMap {
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
				
				String[] array = wordValue.toString().split("\\+");
				if (array.length<=2) {
					System.out.println("xxxxxxx"+wordValue.toString());
				}else {
				for (String str : array) {
//					String[] ar = str.split("\\+");
					WriteFile.writeFileByLines("E:/�ʼ�ѧϰ/��ر���/�����ư�ȫ�㷨/��һ��������/25w_url2.txt", str);
				}
				}
				
				
				
				// ����map�����keyֵ
				//WriteFile.writeFileByLines("D:/b.txt", wordValue + "," + "1");
			}

			// System.out.println("line " + line + ": " + tempString);
			line++;
		}
		reader.close();

	}

	public static void main(String[] args) throws IOException {
		wordcount("E:/�ʼ�ѧϰ/��ر���/�����ư�ȫ�㷨/��һ��������/25Wurl2.txt");
	}

}
