package com.example.touristapp.Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class UniqueCodes {
    public String randomNumber(){
        int numberOfRandomNumbers = 7;
        int range = 10;
        Random random = new Random();
        java.util.List<Integer> uniqueNumbers = random.ints(0, range)
                .distinct()
                .limit(numberOfRandomNumbers)
                .boxed()
                .collect(Collectors.toList());
        String code="";
        for (int number : uniqueNumbers) {
            code=code+String.valueOf(number);
        }
        return code;
    }
    public String dateUnique(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String currentDateTime = dateFormat.format(calendar.getTime());
        return currentDateTime;
    }
    public static String getFormattedTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault());
        return sdf.format(new Date());
    }
}
