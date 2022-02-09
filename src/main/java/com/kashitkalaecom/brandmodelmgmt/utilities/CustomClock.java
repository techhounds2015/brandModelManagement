package com.kashitkalaecom.brandmodelmgmt.utilities;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomClock {


    private static final Logger logger = LoggerFactory.getLogger(CustomClock.class);
    private static Clock clock = null;

    public static void main(String[] args) throws InterruptedException
    {
	for (int i = 0; i < 10000; i++)
	{
	    System.out.println(millis());
	    Thread.sleep(100);
	}

    }

    private CustomClock()
    {

    }

    /*
     * static { long secondsAhead = secondsAhead("20-FEB-2022"); Clock constantClock
     * = Clock.systemUTC(); clock = Clock.offset(constantClock,
     * Duration.ofSeconds(secondsAhead)); }
     */

    static
    {

	Clock constantClock = Clock.systemUTC();
	clock = Clock.offset(constantClock, Duration.ofSeconds(0));

    }

    public static long millis()
    {
	return clock.millis();
    }

    public static Timestamp timestamp()
    {
	return new Timestamp(clock.millis());
    }

    public static Calendar calendar()
    {
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(clock.millis());
	return calendar;
    }



}
