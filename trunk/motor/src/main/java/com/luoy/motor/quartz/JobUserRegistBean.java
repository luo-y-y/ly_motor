package com.luoy.motor.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;



@PersistJobDataAfterExecution
@DisallowConcurrentExecution
//1.修改数据，防止并发，2不允许并发执行  
public class JobUserRegistBean
      extends QuartzJobBean{

   @Override
   protected void executeInternal(JobExecutionContext jobContext)
         throws JobExecutionException{
      /*try{
//         ApplicationContext applicationContext = (ApplicationContext) jobContext.getScheduler().getContext().get("applicationContextKey");
         //OrderPayService orderPayService = (OrderPayService) applicationContext.getBean("orderPayService");
         String date = RDate.getCurrentTimeSssStr();
         for(int i = 0; i < 120; i++){
            try{
               System.out.println(date+"任务进行中，倒计时："+(120-i));
               Thread.sleep(1000);
            }catch(InterruptedException e){
               e.printStackTrace();
            }
         }
         System.out.println(date+"任务结束");
      }catch(SchedulerException e){
         e.printStackTrace();
      }*/

   }

}