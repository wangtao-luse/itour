package com.itour.quartz.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class SchedulerTask2 {
	private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("HH:mm:ss");
	@Scheduled(fixedRate = 6000)
    private void process(){
		System.out.println("now time is " + dateFormat.format(new Date())+"----"+Thread.currentThread().getName());
    }
}
