package com.liuqh.solrclient;

import java.io.Serializable;
import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class Zookeeper {
	public static String ZKServers="localhost:2181,localhost:2182,localhost:2183";  

	public static void main(String[] args) throws InterruptedException {
		//createSession();
		//createNode();
		getData();
		//delNode();
		//checkNodeIsExist();
		//writeData();
		//subscribeDataChanges();
		//subscribeChildChanges();
	}
	public static void createSession (){
        /** 
         * 创建会话 
         * new SerializableSerializer() 创建序列化器接口，用来序列化和反序列化 
         */  
        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());  
        System.out.println("conneted ok!");  
	}
	public static void createNode(){  
	        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());  
	        System.out.println("conneted ok!");  
	          
	        User user = new User();  
	        user.setId(1);  
	        user.setName("testUser");  
	          
	        /** 
	         * "/testUserNode" :节点的地址 
	         * user：数据的对象 
	         * CreateMode.PERSISTENT：创建的节点类型 
	         */  
	        String path = zkClient.create("/testUserNode", user, CreateMode.PERSISTENT);  
	        //输出创建节点的路径  
	        System.out.println("created path:"+path);  
	    } 
	
	public static void getData(){
        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());  
        System.out.println("conneted ok!");  
        Stat stat = new Stat();  
        //获取 节点中的对象  
        User  user = zkClient.readData("/testUserNode",stat);  
        System.out.println(user.getName());  
        System.out.println(stat.getMtime());  
	}
	
	public static void checkNodeIsExist(){
        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());  
        System.out.println("conneted ok!");  
          
        boolean e = zkClient.exists("/testUserNode");  
        //返回 true表示节点存在 ，false表示不存在  
        System.out.println(e);  
	}
	
	public static void delNode(){
		  //zk集群的地址  
        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());  
        System.out.println("conneted ok!");  
          
        //删除单独一个节点，返回true表示成功  
        boolean e1 = zkClient.delete("/testUserNode");  
        //删除含有子节点的节点  
        boolean e2 = zkClient.deleteRecursive("/test");  
          
        //返回 true表示节点成功 ，false表示删除失败  
        System.out.println(e1);  
	}
	public static void writeData(User u){
        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());  
        System.out.println("conneted ok!");  
        /** 
         * testUserNode 节点的路径 
         * user 传入的数据对象 
         */  
        zkClient.writeData("/testUserNode", u);  
	}
	public static void subscribeChildChanges() throws InterruptedException{
        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());  
        System.out.println("conneted ok!");  
        /** 
         * "/testUserNode" 监听的节点，可以是现在存在的也可以是不存在的 
         */  
        zkClient.subscribeChildChanges("/testUserNode", new ZKChildListener());  
        Thread.sleep(Integer.MAX_VALUE);  
	}
	public static void subscribeDataChanges() throws InterruptedException{
        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());  
        System.out.println("conneted ok!");  
  
        zkClient.subscribeDataChanges("/testUserNode", new ZKDataListener());  
        Thread.sleep(Integer.MAX_VALUE);  
	}
	 private static class ZKChildListener implements IZkChildListener{  
	        /** 
	         * handleChildChange： 用来处理服务器端发送过来的通知 
	         * parentPath：对应的父节点的路径 
	         * currentChilds：子节点的相对路径 
	         */  
	        public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {  
	              
	            System.out.println(parentPath);  
	            System.out.println(currentChilds.toString());  
	              
	        }  
	          
	    } 
	 private static class ZKDataListener implements IZkDataListener{  
		  
	        public void handleDataChange(String dataPath, Object data) throws Exception {  
	              
	            System.out.println(dataPath+":"+data.toString());  
	        }  
	  
	        public void handleDataDeleted(String dataPath) throws Exception {  
	              
	            System.out.println(dataPath);  
	              
	        }  
	         
	          
	    }  
}
class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
