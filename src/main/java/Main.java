import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Main {
    public static void main(String[] args) {
//        test1();
        test2();
    }


    /**
     * 未使用Jedis连接池的用法
     */
    private static void test1(){
        String host = "127.0.0.1";
        int port = 6379;
        Jedis jedis = new Jedis(host, port); // 连接redis
        jedis.set("name", "garry");   // 设置数据
        System.out.println(jedis.get("name")); // 获取数据
        jedis.close(); // 释放资源
    }

    private static void test2(){
        Jedis jedis = RedisUtil.getJedis();
        jedis.set("name", "garry");
        System.out.println(jedis.get("name"));
        jedis.close();
    }
}
