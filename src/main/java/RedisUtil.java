import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    private final static String REDIS_HOST = "127.0.0.1";
    private final static int REDIS_PORT = 6379;
    private static volatile JedisPool jedisPool = null;

    private RedisUtil(){ }

    public static JedisPool getJedisPool(){
        if (jedisPool == null){
            synchronized (RedisUtil.class){
                if (jedisPool == null){
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(30); // 最多可分配的Jedis实例个数
                    jedisPoolConfig.setMaxIdle(10); // 最大空闲数
                    jedisPoolConfig.setMaxWaitMillis(1000); //最大建立连接等待时间。如果超过此时间将接到异常
                    jedisPool = new JedisPool(jedisPoolConfig, REDIS_HOST, REDIS_PORT);
                }
            }
        }
        return jedisPool;
    }

    public static Jedis getJedis() {
        JedisPool jedisPool = getJedisPool();
        return jedisPool.getResource();
    }

    public static String get(String key){
        return getJedis().get(key);
    }

    public static String set(String key, String value){
        return getJedis().set(key, value);
    }

    public static Long del(String... keys){
        return getJedis().del(keys);
    }

    public static Long append(String key, String str){
        return getJedis().append(key, str);
    }

    public static boolean exists(String key){
        return getJedis().exists(key);
    }

    public static Long setnx(String key, String value){
        return getJedis().setnx(key, value);
    }

    public static String setex(String key, int seconds, String value){
        return getJedis().setex(key, seconds, value);
    }

}
