package br.com.as.cinema;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.ByteBuffer;
import java.util.UUID;

public class ShortUuidUtils {
    public static String generateKey(){
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return Base64.encodeBase64URLSafeString(bb.array());
    }
    public static String uuidFromBase64(String str) {
        byte[] bytes = Base64.decodeBase64URLSafe(str);
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        UUID uuid = new UUID(bb.getLong(), bb.getLong());
        return uuid.toString();
    }

}
