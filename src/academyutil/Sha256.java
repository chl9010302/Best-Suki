package academyutil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {
	public String sha256(String user_pw) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		StringBuffer hexString = new StringBuffer();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(user_pw.getBytes("UTF-8"));
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
	}
}
