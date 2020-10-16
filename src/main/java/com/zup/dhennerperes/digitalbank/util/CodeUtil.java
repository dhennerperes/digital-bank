package com.zup.dhennerperes.digitalbank.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class CodeUtil {

    public static String getShortUUID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

}
