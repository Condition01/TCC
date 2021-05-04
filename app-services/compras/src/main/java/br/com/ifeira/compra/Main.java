package br.com.ifeira.compra;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        Date now = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE , 5+7);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
        System.out.println(calendar.getTime());

        int item = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(item);
    }
}
