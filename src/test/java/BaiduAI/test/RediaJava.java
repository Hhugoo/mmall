package BaiduAI.test;

import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RediaJava {

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("localhost");
//        System.out.println("连接成功");
//        System.out.println("服务正在运行: "+jedis.ping());
//        jedis.set("runoobkey", "www.runoob.com");
//
//        jedis.lpush("site-list", "Runoob");
//        jedis.lpush("site-list", "Google");
//        jedis.lpush("site-list", "Taobao");
//
//        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
//        List<String> list = jedis.lrange("site-list", 0 ,2);
//        for(int i=0; i<list.size(); i++) {
//            System.out.println("列表项为: "+list.get(i));
//        }
//
//        Set<String> keys = jedis.keys("*");
//        Iterator<String> it=keys.iterator() ;
//        while(it.hasNext()){
//            String key = it.next();
//            System.out.println(key);
//        }

        String a = "a";
        String b = "a";
        System.out.println("==:" + (a==b));
        String c = new String("a");
        String d = c;

        System.out.println("==:" + (a==c));
    }

}
