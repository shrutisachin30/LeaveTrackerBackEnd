package org.generated.project.infrastructure;

public class StringEncryptionDecryptionExample {

	public static void main(String[] args) {
		final String secretKey = "JH4KL6XA@ByC!$";

		String originalString = "subasri@345";
		String encryptedString = AESUtils.encrypt(originalString, secretKey);
		String decryptedString = AESUtils.decrypt("Pi5lB99CMEHc7i3lyPpGmA==", secretKey);

		System.out.println("String Before Encryption is :");
		System.out.println(originalString);
		System.out.println("String After Encryption is :");
		System.out.println(encryptedString);
		System.out.println("String After Decryption is:");
		System.out.println(decryptedString);

	}

}