package com.mmall.task;

import com.mmall.common.Const;
//import com.mmall.common.RedissonManager;
import com.mmall.service.IOrderService;
import com.mmall.util.PropertiesUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
//import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CloseOrderTask {

    @Autowired
    private IOrderService iOrderService;

//    @Autowired
//    private RedissonManager redissonManager;

    //tomcat shutdown的时候会执行
    @PreDestroy
    public void delLock() {
        RedisShardedPoolUtil.del(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
    }


//    @Scheduled(cron = "0 */1 * * * ?") // 每一分钟
    public void closeOrderTaskV1() {
        log.info("关闭订单定时任务开始");
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
        iOrderService.closeOrder(hour);
        log.info("关闭订单定时任务结束");
    }

//    @Scheduled(cron = "0 */1 * * * ?") // 每一分钟
    public void closeOrderTaskV2() {
        log.info("关闭订单定时任务开始");
        Long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout", "5000"));
        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1) {
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        } else {
            log.info("没有获得分布式锁:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        }
        log.info("关闭订单定时任务结束～xixixi嘻嘻");
    }

    @Scheduled(cron = "0 */1 * * * ?") // 每一分钟
    public void closeOrderTaskV3() {
        log.info("start close order ");
        Long lockTimeout = Long.parseLong(PropertiesUtil.getProperty("lock.timeout", "5000"));
        Long setnxResult = RedisShardedPoolUtil.setnx(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1) {
            closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
        } else {
            //未获取到锁，继续判断，判断时间戳，看是否可以重置并获取到锁
            String localValueStr = RedisShardedPoolUtil.get(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            //看锁有没有超时
            if (localValueStr != null && System.currentTimeMillis() > Long.valueOf(localValueStr)) {
                String getSetResult = RedisShardedPoolUtil.getSet(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));

                if (getSetResult == null || (getSetResult != null && StringUtils.equals(localValueStr, getSetResult))) {
                    closeOrder(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                } else {
                    log.info("NO get sharedLock:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
                }
            } else {
                log.info("NO get sharedLock:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
            }
        }
        log.info("end close order");
    }

    private void closeOrder(String lockName) {
        RedisShardedPoolUtil.expire(lockName, 50);
        log.info("get lock:{}, ThreadName:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread());
        int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
//        iOrderService.closeOrder(hour);
        RedisShardedPoolUtil.del(lockName);
        log.info("release lock:{}, ThreadName:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread());
        log.info("============================");
    }


//    @Scheduled(cron = "0 */1 * * * ?") // 每一分钟
//    public void closeOrderTaskV4() {
//        RLock lock = redissonManager.getRedisson().getLock(Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK);
//        boolean getLock = false;
//        try {
//            if (getLock = lock.tryLock(0,5, TimeUnit.SECONDS)) {
//                log.info("Redisson获取到分布式锁:{}, ThreadName:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread());
//                int hour = Integer.parseInt(PropertiesUtil.getProperty("close.order.task.time.hour", "2"));
////                iOrderService.closeOrder(hour);
//            } else {
//                log.info("Redisson没有获取到分布式锁:{}, ThreadName:{}", Const.REDIS_LOCK.CLOSE_ORDER_TASK_LOCK, Thread.currentThread());
//            }
//        } catch (InterruptedException e) {
//            log.info("Redisson分布式锁获取异常", e);
//        } finally {
//            if (!getLock) {
//                return;
//            }
//            lock.unlock();
//            log.info("Redisson分布式锁释放锁");
//        }
//
//    }



    public static void main(String[] args) {
        System.out.println("呜呜呜");
    }


}
