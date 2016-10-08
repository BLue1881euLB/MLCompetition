package bao.distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class ReadCSV extends BasicClass{

	/**
	 * ��ȡ����id���侭γ��
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * */
	 Map<String, double[]> readSite_Lng_Lat() throws NumberFormatException, IOException{

		//��ȡ����id��γ��
		BufferedReader brSite=read("read//new_1.csv");
		
		//ʹ��Map<Site_id,double[2]>�������ȡ������
		Map<String, double[]> site_Lng_Lat=new TreeMap<>();
		
		String lineSite=null;
		while((lineSite=brSite.readLine())!=null){
			String[] res=lineSite.split(",");
			String Site_id=res[0];//����id��e.g. A001�� 
			double Lng=Double.parseDouble(res[1]);//���㾭��
			double Lat=Double.parseDouble(res[2]);//����γ��
			
			double[] Lng_Lat=new double[2];
			Lng_Lat[0]=Lng;
			Lng_Lat[1]=Lat;
			
			site_Lng_Lat.put(Site_id, Lng_Lat);
		}
		return site_Lng_Lat;
	}
	
	/**
	 * ��ȡ�������͵�id���侭γ��
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	 Map<String, double[]> readSpot_Lng_Lat() throws NumberFormatException, IOException {
		
		//��ȡ�������͵�id���侭γ��
		BufferedReader brSpot = read("read//new_2.csv");

		// ʹ��Map<Spot_id,double[2]>�������ȡ������
		
		Map<String, double[]> spot_Lng_Lat = new TreeMap<>();

		String lineSpot = null;
		while ((lineSpot = brSpot.readLine()) != null) {
			String[] res = lineSpot.split(",");
			String Spot_id = res[0];// ���͵�id ��e.g. B0001�� 
			double Lng = Double.parseDouble(res[1]);//���͵㾭��
			double Lat = Double.parseDouble(res[2]);// ���͵�γ��
			
			double[] Lng_Lat = new double[2];
			Lng_Lat[0] = Lng;
			Lng_Lat[1] = Lat;
			
			spot_Lng_Lat.put(Spot_id, Lng_Lat);
		}
		return spot_Lng_Lat;
	}
	
	/**
	 * ��ȡO2O�̻�id���侭γ��
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * */
	 Map<String, double[]> readShop_Lng_Lat() throws NumberFormatException, IOException {
		
        // ��ȡO2O�̻�id���侭γ��
		BufferedReader brShop = read("read//new_3.csv");

		// ʹ��Map<Shop_id,double[2]>�������ȡ������
		Map<String, double[]> shop_Lng_Lat = new TreeMap<>();

		String lineShop = null;
		while ((lineShop = brShop.readLine()) != null) {
			String[] res = lineShop.split(",");
			String Shop_id = res[0];//  �̻�id ��e.g. S001�� 
			double Lng = Double.parseDouble(res[1]);// �̻�����
			double Lat = Double.parseDouble(res[2]);// �̻�γ��
			
			double[] Lng_Lat = new double[2];
			Lng_Lat[0] = Lng;
			Lng_Lat[1] = Lat;
			
			shop_Lng_Lat.put(Shop_id, Lng_Lat);
		}
		return shop_Lng_Lat;
	}
}
