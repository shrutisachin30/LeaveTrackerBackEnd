package org.generated.project.infrastructure;

public class StringEncryptionDecryptionExample {

	public static void main(String[] args) {
		 final String secretKey = "JHKLXABYZC!!!!";
	     
		    String originalString = "Java Web Developer";
		    String encryptedString = AESUtils.encrypt(originalString, secretKey) ;
		    String decryptedString = AESUtils.decrypt(encryptedString, secretKey) ;
		     
		    System.out.println("String Before Encryption is :");
		    System.out.println(originalString);
		    System.out.println("String After Encryption is :");
		    System.out.println(encryptedString);
		    System.out.println("String After Decryption is:");
		    System.out.println(decryptedString);

	}

}