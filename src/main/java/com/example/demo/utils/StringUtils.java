package com.example.demo.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static boolean isEmpty(String str){
		if(null == str || "".equals(str) || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isEmail(String email){
		if(isEmpty(email))return false;
		boolean bool = true;
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()){
			bool = false;
		}
		return bool;
	}
	
	public static boolean isNumeric(String number){
		if(number.matches("//d*")){
			return true;
		}else{
			return false;
		}
	}

	public static String randomNum(int length) {
		Random ram = new Random();
		int inum = Math.abs(ram.nextInt());
		String numt = String.valueOf(inum);
		StringBuffer sbbase = new StringBuffer("0");

		for(int i = 0; i < length; ++i) {
			sbbase.append("0");
		}

		String sbase = sbbase.toString();
		String snum = null;
		if(numt.length() < length) {
			snum = sbase.substring(0, length - numt.length()) + numt;
		} else {
			snum = numt.substring(0, length);
		}

		return snum;
	}

	public static String md5(String text) {
		String result = "";

		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] buf = text.getBytes();
			byte[] dig = md5.digest(buf);
			String hex = null;

			for(int i = 0; i < dig.length; ++i) {
				int n = dig[i] < 0?256 + dig[i]:dig[i];
				hex = Integer.toHexString(n);
				if(hex.length() < 2) {
					hex = "0" + hex;
				}

				result = result + hex;
			}
		} catch (NoSuchAlgorithmException var8) {
			result = null;
		}

		return result;
	}

	public static final String generateNickName(int length) {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		char[] re = new char[length];
		for (int i=0;i<length;i++){
			re[i] = chars.charAt((int)(Math.random() * 62));
		}
		return String.valueOf(re);
	}

	public static final String generateInviteCode(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		char[] re = new char[length];
		for (int i=0;i<length;i++){
			re[i] = chars.charAt((int)(Math.random() * 36));
		}
		return String.valueOf(re);
	}
	
}
