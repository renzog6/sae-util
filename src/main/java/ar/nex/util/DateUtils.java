package ar.nex.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author
 * https://www.mkyong.com/regular-expressions/how-to-validate-date-with-regular-expression/
 */
public class DateUtils {

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat DATE_FORMAT_Y = new SimpleDateFormat("yyyy-mm-dd");

    public static String formatDateTimeString(Date date) {
        return DATE_TIME_FORMAT.format(date);
    }

    public static String formatDateTimeString(Long time) {
        return DATE_TIME_FORMAT.format(new Date(time));
    }

    public static String getDateString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String getDateYString(Date date) {
        return DATE_FORMAT_Y.format(date);
    }

    private final Pattern pattern;
    private Matcher matcher;

    //Date Format (dd/mm/yyyy) Regular Expression Pattern
    private static final String DATE_PATTERN
            = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";

    private final DateFormat fdy = new SimpleDateFormat("yyyy-mm-dd");
    private final DateFormat fdd = new SimpleDateFormat("dd/mm/yyyy");

    public DateUtils() {
        pattern = Pattern.compile(DATE_PATTERN);
    }

    /**
     * Validate date format with regular expression
     *
     * @param date date address for validation
     * @return true valid date fromat, false invalid date format
     */
    public boolean validate(String date) {

        System.out.println("ar.nex.util.DateValidator.validate(): " + date);
        try {
            Date dt = fdy.parse(date);
            date = fdd.format(dt);
            System.out.println("ar.nex.util.DateValidator.validate(): " + date);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        matcher = pattern.matcher(date);

        if (matcher.matches()) {

            matcher.reset();

            if (matcher.find()) {

                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (year <= 2017) {
                    return false;
                }
                if (day.equals("31")
                        && (month.equals("4") || month.equals("6") || month.equals("9")
                        || month.equals("11") || month.equals("04") || month.equals("06")
                        || month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if (year % 4 == 0) {
                        if (day.equals("30") || day.equals("31")) {
                            return false;
                        } else {
                            return true;
                        }
                    } else {
                        if (day.equals("29") || day.equals("30") || day.equals("31")) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public boolean compareDateToLocalDate(Date date, LocalDate localDate) {
        String dt = convertToLocalDateViaSqlDate(date).toString();
        String ld = convertToDateViaSqlDate(localDate).toString();
        System.out.println("ar.nex.util.DateUtils.compareDateToLocalDate() dt: " + dt + " ld: " + ld);
        return dt.contentEquals(ld);
    }

}
