package com.example;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

class VerifySignature {
    public static boolean verify(String message, String signatureBase64, String publicKeyBase64) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(message.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signatureBase64);
        return signature.verify(signatureBytes);
    }

    public static void main(String[] args) throws Exception {
        String publicKeyBase64 = "MIIBIjANBgkqh..."; // Sostituisci con la tua chiave pubblica in Base64
        String signatureBase64 = "..."; // Sostituisci con la tua firma in Base64
        String message = "Hi I am here";

        boolean isCorrect = verify(message, signatureBase64, publicKeyBase64);
        System.out.println("Signature verification: " + isCorrect);
    }
}
