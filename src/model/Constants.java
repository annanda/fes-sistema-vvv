package model;

import java.text.DateFormat;
import java.util.Locale;

/*
 * This class shall only hold all constants from your project. To use them
 * in another class, just write at the beginning of your class 'import
 * static Constants.name_your_constant' to import an specific constant made
 * here or 'import static Constants.*' to import all constants made here.
 */
public final class Constants {
	/*
	 * Constant made to define the format of dates for this application. It's
	 * defined as a 'short' mode and getting the default location from JVM.
	 */
	public static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(
			DateFormat.SHORT, Locale.getDefault());

	public static final String COMMA = ", ";

	public static final String SEMICOLON = ";";

	public static final String SINGLE_QUOTE = "'";
}
