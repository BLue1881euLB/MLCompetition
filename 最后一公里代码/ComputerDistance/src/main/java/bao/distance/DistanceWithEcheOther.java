package bao.distance;

import java.io.IOException;
import java.util.Map;

/**
 * �������㡢���͵㡢O2O�̵�����֮��ľ���
 * */
public class DistanceWithEcheOther {

	public static void main(String[] args) throws IOException {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");//������Ƚ���������������
		DistanceWithEcheOther.saveData();
	}
	
	/**
	 * �������㡢���͵㡢O2O�̵�����֮��ľ��벢����Berkeley DB���ݿ�
	 * @throws IOException 
	 * @throws NumberFormatException 
	 * */
	public static void saveData() throws NumberFormatException, IOException{
		
		//��ȡԴ����
		ReadCSV rc=new ReadCSV();
		Map<String, double[]> site_Lng_Lat=rc.readSite_Lng_Lat();
		Map<String, double[]> spot_Lng_Lat=rc.readSpot_Lng_Lat();
		Map<String, double[]> shop_Lng_Lat=rc.readShop_Lng_Lat();
		
//		//����������㵽�����������͵��O2O�̻��ľ��벢����Berkeley DB���ݿ�
		String site_savePath="save//site//";
		new SiteToOtner().site_Dist_SpotAndShop(site_Lng_Lat, spot_Lng_Lat, shop_Lng_Lat, site_savePath);
		
		//��������������͵㵽���������O2O�̻��ľ��벢����Berkeley DB���ݿ�
//		String spot_savePath="save//spot//";
//		new SpotToOther().spot_Dist_SiteAndShop(site_Lng_Lat, spot_Lng_Lat, shop_Lng_Lat, spot_savePath);
		
//		//�������O2O�̻��������������͵������ľ��벢����Berkeley DB���ݿ�
//		String shop_savePath="save//shop//";
//		new ShopToOther().shop_Dist_SiteAndSpot(site_Lng_Lat, spot_Lng_Lat, shop_Lng_Lat, shop_savePath);
	}
}
