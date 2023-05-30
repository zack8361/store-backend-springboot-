package UsedStore.Core;

import org.springframework.stereotype.Component;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;
import java.security.NoSuchAlgorithmException;

@Component
public class AES128 {
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";

    public String decrypt(String str) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        ;

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKeySpec = new SecretKeySpec(MessageUtils.getMessage("aes.key").getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(MessageUtils.getMessage("aes.iv").getBytes(StandardCharsets.UTF_8));

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        return new String(cipher.doFinal(Base64.getDecoder(). decode(str)), "UTF-8");
    }

    public String encrypt(String str) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {

        SecretKeySpec secretKeySpec = new SecretKeySpec(MessageUtils.getMessage("aes.key").getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(MessageUtils.getMessage("aes.iv").getBytes(StandardCharsets.UTF_8));

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encryptedBytes = cipher.doFinal(str.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}

