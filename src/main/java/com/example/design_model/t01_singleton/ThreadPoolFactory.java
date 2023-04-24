package com.example.design_model.t01_singleton;

import java.util.concurrent.*;

/**
 * 线程池工厂类
 * ThreadPoolFactory
 *
 * @author LuoXianchao
 * @date 2022/7/19 10:26
 */
public class ThreadPoolFactory {

   private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(
            10,
            20,
            3000,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
    );

    public static ThreadFactory getThreadFactory(){
         return THREAD_POOL.getThreadFactory();
        // threadFactory.newThread()
        // Thread thread = threadPool.getThreadFactory()
        //         .newThread(new Runnable() {
        //             @Override
        //             public void run() {
        //
        //             }
        //         }); // Replace "runnable" with the actual Runnable you want to execute
        // threadPool.execute(runnable);
    }





    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // threadPool.
        Future<String> submit1 = THREAD_POOL.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName() + "线程池返回值！！！";
            }
        });
        // Thread.sleep(1000);
        ///等待线程执行完毕
        while (!submit1.isDone()){}
        //执行完毕拿他的返回值
        System.out.println("main = " + submit1.get());
        //

        Future<String> submit2 = THREAD_POOL.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName() + "线程池返回值！！！";
            }
        });

        ///等待线程执行完毕
        while (!submit2.isDone()){}
        //执行完毕拿他的返回值
        System.out.println("main = " + submit2.get());

    }
}
