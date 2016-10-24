package bao.distance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import bao.BerkeleyDB.BerkeleyDB;

/**
 * �������͵㵽���������㡢O2O�̻����ľ���
 * */
public class SpotToOther {

	/**
	 * ��������������͵㵽���������O2O�̻��ľ��벢����Berkeley DB���ݿ�
	 * 
	 * @param site_Lng_Lat ����id���侭γ��
	 * @param spot_Lng_Lat ���͵�id���侭γ��
	 * @param shop_Lng_Lat O2O�̻�id���侭γ��
	 * 
	 * @throws IOException 
	 * */
	public void spot_Dist_SiteAndShop(Map<String, double[]> site_Lng_Lat,Map<String, double[]> spot_Lng_Lat,Map<String, double[]> shop_Lng_Lat,String spot_savePath) throws IOException{
		
		//�����ݴ������ݿ�
//		BerkeleyDB save_Spot_Spot = new BerkeleyDB(spot_savePath+"spot_spot_distance");//��ʽ��Map<Spot_id,Map<Spot_id,distance>>
//		BerkeleyDB save_Spot_Site = new BerkeleyDB(spot_savePath+"spot_site_distance");//��ʽ��Map<Spot_id,Map<Site_id,distance>>
//		BerkeleyDB save_Spot_Shop = new BerkeleyDB(spot_savePath+"spot_shop_distance");//��ʽ��Map<Spot_id,Map<Shop_id,distance>>
		BerkeleyDB saveSpot_SpotAndSiteAndShop = new BerkeleyDB(spot_savePath+"spot_spotandsiteandshop_distance");//��ʽ��Map<Spot_id,Map<Spot_id OR Site_id OR Shop_id,distance>>
		
		for(Entry<String, double[]> spotEntry:spot_Lng_Lat.entrySet()){
			String Spot_id=spotEntry.getKey();//���͵�id ��e.g. B0001��
			double[] Lng_Lat_Spot=spotEntry.getValue();
			double lng1=Lng_Lat_Spot[0];//���͵㾭��
			double lat1=Lng_Lat_Spot[1];//���͵�γ��
			
			HashMap<String, Double> cacheAll=new HashMap<>();
			
			//�������͵㵽���͵�ľ��룬���洢
			HashMap<String, Double> cacheSpot_Spot=new HashMap<>();
			for(Entry<String, double[]> spotEntry2:spot_Lng_Lat.entrySet()){
				String Spot_id2=spotEntry2.getKey();//���͵�id ��e.g. B0001��
				double[] Lng_Lat_Spot2=spotEntry2.getValue();
				double lng2=Lng_Lat_Spot2[0];//���͵㾭��
				double lat2=Lng_Lat_Spot2[1];//���͵�γ��
				
				double distance=computeDistance(lng1, lat1, lng2, lat2);
				cacheSpot_Spot.put(Spot_id2,distance);
				cacheAll.put(Spot_id2,distance);
			}
			//�洢���͵㵽���͵�ľ���
//			save_Spot_Spot.putData(Spot_id, MapSortByValue.sortByValue(cacheSpot_Spot));//�����뽵������
			
			
			//�������͵㵽��������ľ��룬���洢
			HashMap<String, Double> cacheSpot_Site=new HashMap<>();
			for(Entry<String, double[]> siteEntry:site_Lng_Lat.entrySet()){
				String Site_id = siteEntry.getKey();//����id��e.g. A001�� 
				double[] Lng_Lat_Site=siteEntry.getValue();
				double lng2 = Lng_Lat_Site[0];//���㾭��
				double lat2 = Lng_Lat_Site[1];//����γ��
				
				double distance=computeDistance(lng1, lat1, lng2, lat2);
				cacheSpot_Site.put(Site_id,distance);
				cacheAll.put(Site_id,distance);
			}
			//�洢���͵㵽����ľ���
//			save_Spot_Site.putData(Spot_id, MapSortByValue.sortByValue(cacheSpot_Site));//�����뽵������
			
			// �������͵㵽����O2O�̻��ľ��룬���洢
			HashMap<String, Double> cacheSpot_Shop=new HashMap<>();
			for (Entry<String, double[]> shopEntry : shop_Lng_Lat.entrySet()) {
				String Shop_id = shopEntry.getKey();//  �̻�id ��e.g. S001�� 
				double[] Lng_Lat_Shop = shopEntry.getValue();
				double lng2 = Lng_Lat_Shop[0];//�̻�����
				double lat2 = Lng_Lat_Shop[1];//�̻�γ��
				
				double distance=computeDistance(lng1, lat1, lng2, lat2);
				cacheSpot_Shop.put(Shop_id, distance);
				cacheAll.put(Shop_id, distance);
			}
			// �洢���͵㵽O2O�̻��ľ���
//			save_Spot_Shop.putData(Spot_id, MapSortByValue.sortByValue(cacheSpot_Shop));//�����뽵������
			
			System.out.println("Spot_id:"+Spot_id);
			//�洢���㵽���͵��O2O�̻��ľ���
			saveSpot_SpotAndSiteAndShop.putData(Spot_id, MapSortByValue.sortByValue(cacheAll));//�����뽵������
		}	
//		save_Spot_Spot.close();
//		save_Spot_Site.close();
//		save_Spot_Shop.close();
		saveSpot_SpotAndSiteAndShop.close();
	}
	
	
	/**
	 * ���ݾ�γ�ȼ������
	 * 
	 */
	 private double computeDistance(double lng1, double lat1, double lng2, double lat2) {

		double lat = (lat1 - lat2) / 2.0;
		double lng = (lng1 - lng2) / 2.0;
		double cache = Math.sin(Math.PI / 180 * lat) * Math.sin(Math.PI / 180 * lat) + Math.cos(Math.PI / 180 * lat1)
				* Math.cos(Math.PI / 180 * lat2) * Math.sin(Math.PI / 180 * lng) * Math.sin(Math.PI / 180 * lng);

		double R = 6378137;
		double S = 2 * R * Math.asin(Math.sqrt(cache));
		
		return S;
	}
}
