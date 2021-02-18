package com.alcalzans.tribo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Auxiliar {

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("1234"));
		System.out.println(converterTextoParaUnicode("áéçó!"));
	}


	public static String converterTextoParaUnicode(String text) {
		StringBuffer stringBuffer = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if ((ch >= 0x0020) && (ch <= 0x007e)) {
				stringBuffer.append(ch);
			} else {
				stringBuffer.append("\\u");
				String hexString = Integer.toHexString(text.charAt(i) & 0xFFFF);
				for (int j = 0; j < 4 - hexString.length(); j++) {
					stringBuffer.append("0");
				}
				stringBuffer.append(hexString.toLowerCase());
			}
		}
		return (new String(stringBuffer));
	}
}
