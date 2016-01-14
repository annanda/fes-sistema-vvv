package model;

import java.text.SimpleDateFormat;

/*
 * This class shall only hold all constants from your project. To use them in another class, just
 * write at the beginning of your class 'import static Constants.name_your_constant' to import an
 * specific constant made here or 'import static Constants.*' to import all constants made here.
 */
public final class Constants {
    /*
     * Constant made to define the format of dates for this application. It's defined as a 'short'
     * mode and getting the default location from JVM.
     */
    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /*
     * Constant made to define the format of dates for this application.
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final String COMMA = ", ";

    public static final String SEMICOLON = ";";

    public static final String SINGLE_QUOTE = "'";

    public static final String ASTERISK = "*";
}
