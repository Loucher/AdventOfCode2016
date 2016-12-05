package day5

import java.security.MessageDigest

class Part1 {

    private static final String INPUT = "cxdnnyjw"

    static void main(String... args) {
        long index = 0
        StringBuilder password = new StringBuilder(8)
        while (password.length() < 8) {
            String temp = (INPUT + Long.toString(index))
            def var = temp.bytes
            String hash = MessageDigest.getInstance("MD5").digest(var).encodeHex().toString()
            if(hash.startsWith("00000")){
                password.append(hash.charAt(5))
            }
            index++
        }
        println password.toString()
    }

}
