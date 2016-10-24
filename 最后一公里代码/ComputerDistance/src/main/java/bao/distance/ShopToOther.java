package bao.distance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import bao.BerkeleyDB.BerkeleyDB;

/**
 * ����O2O�̻������������㡢���͵㣩�ľ���
 * */
public class ShopToOther {


	/**
	 * �������O2O�̻��������������͵������ľ��벢����Berkeley DB���ݿ�
	 * 
	 * @param site_Lng_Lat ����id���侭γ��
	 * @param spot_Lng_Lat ���͵�id���侭γ��
	 * @param shop_Lng_Lat id���侭γ��
	 * 
	 * @throws IOException 
	 * */
	public void shop_Dist_SiteAndSpot(Map<String, double[]> site_Lng_Lat,Map<String, double[]> spot_Lng_Lat,Map<String, double[]> shop_Lng_Lat,String shop_savePath) throws IOException{
		
		//�����ݴ������ݿ�
		BerkeleyDB save_Shop_Shop = new BerkeleyDB(shop_savePath+"shop_shop_distance");//��ʽ��Map<Shop_id,Map<Shop_id,distance>>
		BerkeleyDB save_Shop_Site = new BerkeleyDB(shop_savePath+"shop_site_distance");//��ʽ��Map<Shop_id,Map<Site_id,distance>>
		BerkeleyDB save_Shop_Spot = new BerkeleyDB(shop_savePath+"shop_spot_distance");//��ʽ��Map<Shop_id,Map<Spot_id,distance>>
//		BerkeleyDB saveShop_ShopAndSiteAndSpot = new BerkeleyDB(shop_savePath+"shop_shopandsiteandspot_distance");//��ʽ��Map<Shop_id,Map<Shop_id OR Site_id OR Spot_id,distance>>
		
		for(Entry<String, double[]> shopEntry:shop_Lng_Lat.entrySet()){
			String Shop_id=shopEntry.getKey();// �̻�id ��e.g. S001�� 
			double[] Lng_Lat_Shop=shopEntry.getValue();
			double lng1=Lng_Lat_Shop[0];//�̻�����
			double lat1=Lng_Lat_Shop[1];//�̻�γ��
			
			HashMap<String, Double> cacheAll=new HashMap<>();
			
			//����O2O�̻���O2O�̻��ľ��룬���洢
			HashMap<String, Double> cacheShop_Shop=new HashMap<>();
			for(Entry<String, double[]> shopEntry2:shop_Lng_Lat.entrySet()){
				String Shop_id2 = shopEntry2.getKey();//�̻�id ��e.g. S001�� 
				double[] Lng_Lat_Shop2=shopEntry.getValue();
				double lng2=Lng_Lat_Shop2[0];//�̻�����
				double lat2=Lng_Lat_Shop2[1];//�̻�γ��
				
				double distance=computeDistance(lng1, lat1, lng2, lat2);
				cacheShop_Shop.put(Shop_id2,distance);
				cacheAll.put(Shop_id2,distance);
			}
			//�洢O2O�̻�������ľ���
			save_Shop_Shop.putData(Shop_id, MapSortByValue.sortByValue(cacheShop_Shop));//�����뽵������
			
			//����O2O�̻�����������ľ��룬���洢
			HashMap<String, Double> cacheShop_Site=new HashMap<>();
			for(Entry<String, double[]> siteEntry:site_Lng_Lat.entrySet()){
				String Site_id = siteEntry.getKey();//����id��e.g. A001�� 
				double[] Lng_Lat_Site=siteEntry.getValue();
				double lng2 = Lng_Lat_Site[0];//���㾭��
				double lat2 = Lng_Lat_Site[1];//����γ��
				
				double distance=computeDistance(lng1, lat1, lng2, lat2);
				cacheShop_Site.put(Site_id,distance);
				cacheAll.put(Site_id,distance);
			}
			//�洢O2O�̻�������ľ���
			save_Shop_Site.putData(Shop_id, MapSortByValue.sortByValue(cacheShop_Site));//�����뽵������
			
			// ����O2O�̻����������͵�ľ��룬���洢
			HashMap<String, Double> cacheShop_Spot=new HashMap<>();
			for (Entry<String, double[]> spotEntry : spot_Lng_Lat.entrySet()) {
				String Spot_id = spotEntry.getKey();// ���͵�id ��e.g. B0001��
				double[] Lng_Lat_Spot = spotEntry.getValue();
				double lng2 = Lng_Lat_Spot[0];//���͵㾭��
				double lat2 = Lng_Lat_Spot[1];//���͵�γ��
				
				double distance=computeDistance(lng1, lat1, lng2, lat2);
				cacheShop_Spot.put(Spot_id, distance);
				cacheAll.put(Spot_id, distance);
			}
			// �洢���͵㵽O2O�̻��ľ���
			save_Shop_Spot.putData(Shop_id, MapSortByValue.sortByValue(cacheShop_Spot));//�����뽵������
			
			//�洢���㵽���͵��O2O�̻��ľ���
//			saveShop_ShopAndSiteAndSpot.putData(Shop_id, MapSortByValue.sortByValue(cacheAll));//�����뽵������
			System.out.println("Shop_id:"+Shop_id);
		}	
		save_Shop_Shop.close();
		save_Shop_Site.close();
		save_Shop_Spot.close();
//		saveShop_ShopAndSiteAndSpot.close();
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
