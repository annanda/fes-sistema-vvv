package controller;

public class Controller {
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}
}
