package com.akamon.api.client.net;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class Signer {
    private static final String SIGNATURE_ALGORITHM = "HmacSHA256";
    private static final int SIGNATURE_LENGTH = 64;
    private static final char TRAILING_SIGNATURE_CHARACTER = '0';

    private String key;

    Signer(String key) {
        this.key = key;
    }

    String generateSignature(String dataToSign) {
        StringBuilder signatureBuilder;
        
        try {
            Mac mac = Mac.getInstance(SIGNATURE_ALGORITHM);
            SecretKeySpec secret = new SecretKeySpec(key.getBytes(), SIGNATURE_ALGORITHM);
            mac.init(secret);

            byte[] digest = mac.doFinal(dataToSign.getBytes());
            BigInteger hash = new BigInteger(1, digest);
            String signature = hash.toString(16);

            String signaturePreffix = "";
            int numberOfZerosToPrepend = SIGNATURE_LENGTH - signature.length();            

            if (numberOfZerosToPrepend > 0) {                
                signaturePreffix = generateSignaturePreffix(numberOfZerosToPrepend);
            }
            
            signatureBuilder = new StringBuilder(signaturePreffix);
            signatureBuilder.append(signature);     
            
        } catch (NoSuchAlgorithmException nsae) {
            throw new RuntimeException("Problems calculating HMAC", nsae);
        } catch (InvalidKeyException ike) {
            throw new RuntimeException("Problems calculating HMAC", ike);
        }

        return signatureBuilder.toString();
    }
    
    private String generateSignaturePreffix(int preffixLength){
        char[] chars = new char[preffixLength];
        Arrays.fill(chars, TRAILING_SIGNATURE_CHARACTER);
        
        return new String(chars);
    }
}
