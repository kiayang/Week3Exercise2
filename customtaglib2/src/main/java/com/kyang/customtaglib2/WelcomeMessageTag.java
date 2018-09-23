package com.kyang.customtaglib2;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import java.util.Date;

import static jdk.nashorn.internal.objects.NativeDate.getTime;

/**
 * EXERCISE: Second Custom Tag Lib
 * Using the hello custom tag created above as an example, along with the tag lib resources in the syllabus,
 * create a custom tag that displays a different welcome message based on the time of day: "Good morning!",
 * "Good afternoon!", etc. Then, get a little fancier: if the date is 2/2, for example, include a "Groundhog Day"
 * message.
 */
public class WelcomeMessageTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        JspWriter out = getJspContext().getOut();
        Date date = new Date();

        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int currentMonth = localDate.getMonthValue();
        int currentDay = localDate.getDayOfMonth();

        LocalTime currentTime = LocalTime.now(ZoneId.of("America/Chicago"));

        LocalTime time12 = LocalTime.parse("12:00:00");
        LocalTime time17 = LocalTime.parse("17:00:00");

        /*
        * Compare the time to the parsed time, (1)if the current time is less then the compared to time, it will
        * return a -1 (2) if it is the same it will return 0 and (3) if current time is greater, it will return a 1
         */

        if (currentTime.compareTo(time12) < 0) {
            out.println("Good Morning! ");
        } else if (currentTime.compareTo(time12) >= 0 && currentTime.compareTo(time17) < 0){
            out.println("Good Afternoon! ");
        } else if (currentTime.compareTo(time17) >= 0 )
            out.println("Good Evening! ");

        if (currentMonth == 2 && currentDay == 2){
            out.println("It's Groundhog Day!");
        }

        out.println();
        out.println("The current month and day is " + currentMonth + "/" + currentDay + ".");
        out.println();
        out.println("The current time is " + currentTime + ".");

    }
}
