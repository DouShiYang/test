//package jucTest;
//
//import org.apache.zookeeper.ZooKeeper;
//
//public class TestZk {
//
//    private static String connectString = "bigdata111:2181,bigdata112:2181,bigdata113:2181";
//    private static int sessionTimeout = 2000;
//    private ZooKeeper zkClient = null;
//
//    @Before
//    public void init() throws Exception {
//
//        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
//            @Override
//            public void process(WatchedEvent event) {
//                // 收到事件通知后的回调函数（用户的业务逻辑）
//                System.out.println(event.getType() + "--" + event.getPath());
//
//                // 再次启动监听
//                try {
//                    zkClient.getChildren("/", true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//    // 获取子节点
//     public void getChildren() throws Exception {
//        List<String> children = zkClient.getChildren("/", true);
//
//        for (String child : children) {
//            System.out.println(child);
//        }
//
//        // 延时阻塞
//        Thread.sleep(Long.MAX_VALUE);
//    }
//
//     public void create() throws Exception {
//        // 数据的增删改查
//        // 参数1：要创建的节点的路径； 参数2：节点数据 ； 参数3：节点权限 ；参数4：节点的类型
//        String nodeCreated = zkClient.create("/eclipse", "hello zk".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//    }
//}
