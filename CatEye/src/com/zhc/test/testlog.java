package com.zhc.test;

import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class testlog {
	
	@Test
	public void testByteBuf() {
		
		/*ByteBuffer buff = ByteBuffer.allocate(1024);
		String str = "helloWorld--------------adadaa";
		buff.put(str.getBytes());
		byte[] array = buff.array();
		
		byte[] arrar=new byte[buff.position()];
		System.out.println(new String(arrar));
		
		 	buff.flip();
	        System.out.println("flip()...");
	        System.out.println("position:" + buff.position() + "\t limit:"
	                + buff.limit() + "\t capacity:" + buff.capacity());

	        byte[] tbyte = new byte[buff.limit()];
	        buff.get(tbyte);
	        System.out.println("get one byte to string:" + new String(tbyte));
	        System.out.println("position:" + buff.position() + "\t limit:"
	                + buff.limit());
	        if (buff.hasRemaining()) {
	            buff.compact();
	        } else {
	            buff.clear();
	        }*/
		/*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		try {
			Date begin = sdf.parse("12:45");
			System.out.println(begin.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date = new Date();
		
		System.out.println(date.getTime());*/
		
		Date newDate=new Date();
        String weekOfDate = getWeekOfDate(newDate);


        String weekOfDateAfter = getWeekOfDateAfter(newDate);
		
        System.out.println(weekOfDate+"--"+weekOfDateAfter);
	}
	public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    public static String getWeekOfDateAfter(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        cal.add(Calendar.DATE,-1);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w<0){
            w = 0;
        }
        return weekDays[w];
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
