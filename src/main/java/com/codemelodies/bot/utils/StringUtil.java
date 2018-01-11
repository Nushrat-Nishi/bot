package com.codemelodies.bot.utils;

public class StringUtil {

	public static String banglaToEnglish(String banglaNumber) {

		banglaNumber = banglaNumber.replace("০", "0");
		banglaNumber = banglaNumber.replace("১", "1");
		banglaNumber = banglaNumber.replace("২", "2");
		banglaNumber = banglaNumber.replace("৩", "3");
		banglaNumber = banglaNumber.replace("৪", "4");
		banglaNumber = banglaNumber.replace("৫", "5");
		banglaNumber = banglaNumber.replace("৬", "6");
		banglaNumber = banglaNumber.replace("৭", "7");
		banglaNumber = banglaNumber.replace("৮", "8");
		banglaNumber = banglaNumber.replace("৯", "9");

		for (int i = 0; i < banglaNumber.length(); i++) {
			int asciiValue = (int) banglaNumber.charAt(i);
			if (!(asciiValue >= 48) || !(asciiValue <= 57)) {
				throw new IllegalArgumentException("Invalid Input:  "
						+ banglaNumber);
			}
		}
		return banglaNumber;
	}
}
