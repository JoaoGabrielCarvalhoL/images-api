package br.com.joaogabriel.imagesrepo.utils;

import java.util.regex.Pattern;

public class StringUtils {
	
	public static String getExtension(String originalNameFile) {
		String[] result = originalNameFile.split(Pattern.quote("."));
		return result[1].toUpperCase();
	}

}
