package bao.BerkeleyDB;

import java.io.FileNotFoundException;
import java.util.Map.Entry;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReadWriteLock;
//import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Set;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;

/**
 * �����ݿ���б��桢���ҡ�ɾ���Ȳ��� 
 * 
 * ���ݴ������ݿ�ĸ�ʽΪ��Map<IP��Map<�������ڣ�Map<ʱ�̣������>>>
 * 
 * */
public class BerkeleyDB extends AbstractDBD{
	
	private StoredMap<String,Object> pendingUrisDB = null;
	
	public BerkeleyDB(String homeDirectory) throws DatabaseException,FileNotFoundException {
		super(homeDirectory);
		// TODO Auto-generated constructor stub
		
		EntryBinding<String> keyBinding = new SerialBinding<String>(javaCatalog, String.class);
		EntryBinding<Object> valueBinding = new SerialBinding<Object>(javaCatalog,Object.class);
		pendingUrisDB = new StoredMap<String, Object>(database, keyBinding, valueBinding, true);
	}

	/**
	 * �������ݿ�
	 * 
	 * */
	public  Set<Entry<String, Object>> iteration(){
		return pendingUrisDB.entrySet();
	}
	
	/**
	 * �������ݿ�
	 * 
	 * */
	public void putData(String key, Object value){
		put(key, value);
	}
	
	/**
	 * ����keyֵȡvalueֵ
	 * */
	public  Object getData(String key){
		return  get(key);
	}
	
	/**
	 * ����key�ж��Ƿ�����ü�¼
	 * 
	 * */
	public  boolean containsKey(Object key){
		return pendingUrisDB.containsKey(key);
	}
	
	/**
	 * �ر����ݿ�
	 * */
	public void closeBDB(){
		close();
	}
	
	@Override
	protected void put(Object key, Object value) {
		// TODO Auto-generated method stub
		pendingUrisDB.put((String)key, value);
	}

	@Override
	protected Object get(Object key) {
		// TODO Auto-generated method stub
		return pendingUrisDB.get(key);
	}

	@Override
	protected Object delete(Object key) {
		// TODO Auto-generated method stub
		return pendingUrisDB.remove(key);
	}
}
