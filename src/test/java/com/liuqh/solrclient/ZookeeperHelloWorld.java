package com.liuqh.solrclient;
import java.util.List;

/**
* @author :liuqinghua
* @version 创建时间：2017年8月8日 下午4:56:14
* 
*/
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
  
public class ZookeeperHelloWorld{
  
    private static final int TIME_OUT = 3000;
    private static final String HOST = "192.168.188.128:2181,192.168.188.128:2182,192.168.188.128:2183";
    public static void main(String[] args) throws Exception{
 
 
        ZooKeeper zookeeper = new ZooKeeper(HOST, TIME_OUT, null);
        System.out.println("=========创建节点===========");
        if(zookeeper.exists("/test", false) == null)
        {
            zookeeper.create("/test", "znode1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println("=============查看节点是否安装成功===============");
        System.out.println(new String(zookeeper.getData("/test", false, null)));
         
        System.out.println("=========修改节点的数据==========");
        String data = "zNode2";
        zookeeper.setData("/test", data.getBytes(), -1);
         
        System.out.println("========查看修改的节点是否成功=========");
        System.out.println(new String(zookeeper.getData("/test", false, null)));
        
        //System.out.println(new String(zookeeper.getData("/configs/core1conf/data-config.xml", false, null)));
        
        
        System.out.println("=======删除节点==========");
        rmr("/overseer",zookeeper);
        //rmr("/configs/core1conf",zookeeper);
         
        System.out.println("==========查看节点是否被删除============");
        //System.out.println("节点状态：" + zookeeper.exists("/test", false));
         
        zookeeper.close();
    } 
    
    public static void rmr(String path,ZooKeeper zk) throws Exception {
       
        //获取路径下的节点
        List<String> children = zk.getChildren(path, false);
        for (String pathCd : children) {
            //获取父节点下面的子节点路径
            String newPath = "";
            //递归调用,判断是否是根节点
            if (path.equals("/")) {
                newPath = "/" + pathCd;
            } else {
                newPath = path + "/" + pathCd;
            }
            rmr(newPath,zk);
        }
        //删除节点,并过滤zookeeper节点和 /节点
        if (path != null && !path.trim().startsWith("/zookeeper") && !path.trim().equals("/")) {
            zk.delete(path, -1);
            //打印删除的节点路径
            System.out.println("被删除的节点为：" + path);
        }
    }
}
