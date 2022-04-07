package by.arabienko.onlineSchool.generatepassword;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class GeneratePassword {
    public static void main(String[] args) {
        //Исследования, проведенные на сегодняшний день, показывают,
        // что лучшим и наиболее безопасным алгоритмом хеширования является SHA-512,
        // в котором используются 64-битные слова
        // (Secure Hash Algorithms, 2017, пункт 2).
        String username = "admin";
        String password = "admin";
        Sha sha = new Sha();

        String asHex2 = "";
        try {
            asHex2 = sha.hashToHex(password, Optional.empty());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("sha.hashToHex: " + asHex2);

       // sha256Hex(byte[] data) Вычисляет дайджест SHA-256 и
        // возвращает значение в виде шестнадцатеричной строки.
        //String hash2 = DigestUtils.sha256Hex("student15");
        //System.out.println("DigestUtils.sha256Hex: "+hash2);
    }
}
