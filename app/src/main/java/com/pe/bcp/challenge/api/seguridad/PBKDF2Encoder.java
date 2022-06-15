package com.pe.bcp.challenge.api.seguridad;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

@Component
public class PBKDF2Encoder implements PasswordEncoder {
    private String secret = "mysecret";
    private int iteration = 33;
    private int keylength = 256;

    @Override
    public String encode(CharSequence rawPassword) {

        try{
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(new PBEKeySpec(rawPassword.toString().toCharArray(),
                            secret.getBytes(),iteration,keylength))
                    .getEncoded();
            return Base64.getEncoder().encodeToString(result);

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
