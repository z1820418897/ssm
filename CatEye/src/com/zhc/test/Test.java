package com.zhc.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {  
    public static void main(String[] args) {  
         int count = 2000;  
         CyclicBarrier cyclicBarrier = new CyclicBarrier(count);  
         ExecutorService executorService = Executors.newFixedThreadPool(count);  
         System.out.println("开始创建线程");
         for (int i = 0; i < count; i++)  
              executorService.execute(new Test().new Task(cyclicBarrier));  
         
         System.out.println("创建完毕 执行");
         executorService.shutdown();  
         /*while (!executorService.isTerminated()) {  
              try {  
                   Thread.sleep(1);  
              } catch (InterruptedException e) {  
                   e.printStackTrace();  
              }  
         }  */
    }  
 
    public class Task implements Runnable {  
         private CyclicBarrier cyclicBarrier;  
 
         public Task(CyclicBarrier cyclicBarrier) {  
              this.cyclicBarrier = cyclicBarrier;  
         }  
 
         @Override  
         public void run() {  
              try {  
                   // 等待所有任务准备就绪  
                   cyclicBarrier.await();  
                   // 测试内容  
                   
                   Map<String,Integer> map=new HashMap();
                   map.put("limit", 10);
                   map.put("page", 1);
                   long a= System.currentTimeMillis();
                   String s = SendFromMsg.sendHttpPostRequest("http://10.10.2.176/CatEye/cateye/data", new String[]{},map);
                   
                   System.out.println("---"+System.currentTimeMillis()+"***"+((Long)System.currentTimeMillis()-a)+s);
                   
              } catch (Exception e) {  
                   e.printStackTrace();  
              }  
         }  
    }  
}  

