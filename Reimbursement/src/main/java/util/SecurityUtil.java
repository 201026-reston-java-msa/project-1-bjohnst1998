package util;

import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;

public class SecurityUtil {
	private static Logger log = Logger.getLogger(SecurityUtil.class);
	private static final String UNICODE_FORMAT ="UTF8";
	public static final String DESDE_ENCRYPTION_SCHEME = "DESede";
	private KeySpec ks;
	private SecretKeyFactory skf;
	private Cipher cipher;
	byte[] arrayBytes;
	private String myEncryptionKey;
	private String myEncryptionScheme;
	SecretKey key;
	
	public SecurityUtil() throws Exception{
		myEncryptionKey = "ElsaCatWatcherBranchFrontierSolidBoxFrankenstein";
		myEncryptionScheme = DESDE_ENCRYPTION_SCHEME;
		arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
		ks = new DESedeKeySpec(arrayBytes);
		skf = SecretKeyFactory.getInstance(myEncryptionScheme);
		cipher = Cipher.getInstance(myEncryptionScheme);
		key = skf.generateSecret(ks);
		
	}
	
	
	public String encrypt(String s)
	{
		String encrypted = null;
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = s.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			encrypted = new String(Base64.encodeBase64(encryptedText));
		}catch (Exception e) {
			// TODO: handle exception
			log.warn("Failed to encrypt data!!!! ABORTING", e);
		}
		return encrypted;
	}
	
	public String decrypt(String s)
	{
		String decrypted = null;
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
		
			byte[] encryptedTxt = Base64.decodeBase64(s.getBytes(UNICODE_FORMAT));
			byte[] plainText = cipher.doFinal(encryptedTxt);
			decrypted = new String(plainText);
		}catch (Exception e) {
			log.warn("Decryption failed!! ABORTING",e);
		}
		return decrypted;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
























