package pack.utils;

import org.mindrot.jbcrypt.BCrypt;

public final class SecurityUtility {
	public static String encryptBcrypt(String content) {
		try {
			String data = BCrypt.hashpw(content, BCrypt.gensalt(12));
			return data;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
	
	public static boolean compareBcrypt(String contentEncrypted, String content) {
		try {
			return BCrypt.checkpw(content, contentEncrypted);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
