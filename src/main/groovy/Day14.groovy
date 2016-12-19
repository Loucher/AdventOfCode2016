import org.apache.commons.lang3.StringUtils

import java.security.MessageDigest

class Day14 {
    private static String SALT = "cuanljph"
    private static Map<Integer, String> cache = new HashMap<>()
    static private MessageDigest instance

    static void main(String... args) {
        instance = MessageDigest.getInstance("MD5")
        ArrayList<Integer> keys = new ArrayList<>()
        int key = 0
        while (keys.size() < 64) {
            String hash = getHash(key)
            String triplet = hash.find(/(.)\1\1/)
            if (triplet) {
                if (isValid(key, triplet.charAt(0))) {
                    keys.add(key)
                    println "$keys.size $key "
                }
            }
            key++
        }
    }

    static boolean isValid(int index, char lookupChar) {
        String lookupString = StringUtils.repeat(lookupChar, 5)
        for (int i = index + 1; i <= index + 1000; i++) {
            if (getHash(i).contains(lookupString)) {
                return true
            }
        }
        return false
    }

    static String getHash(int index) {
        String value = cache.get(index)
        if (value == null) {
            value = generateMD5(SALT + Integer.toString(index))
            cache.put(index, value)
        }
        return value
    }

    static String generateMD5(String s) {
        String input = s
        for (int i = 0; i < 2017; i++) {
            input = instance.digest(input.bytes).encodeHex().toString()
        }
        return input
    }

}
