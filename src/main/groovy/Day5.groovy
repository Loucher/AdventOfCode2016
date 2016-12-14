import java.security.MessageDigest

class Day5 {

    private static final String INPUT = "cxdnnyjw"

    static void main(String... args) {
        part1()
        part2()
    }

    private static void part1() {
        long index = 0
        StringBuilder password = new StringBuilder(8)
        while (password.length() < 8) {
            String temp = (INPUT + Long.toString(index))
            def var = temp.bytes
            String hash = MessageDigest.getInstance("MD5").digest(var).encodeHex().toString()
            if (hash.startsWith("00000")) {
                password.append(hash.charAt(5))
            }
            index++
        }
        println password.toString()
    }

    private static void part2() {
        long index = 0
        int found = 0
        char[] password = new char[8]
        Arrays.fill(password, '.' as char)
        while (password.contains('.')) {
            String temp = (INPUT + Long.toString(index))
            def var = temp.bytes
            String hash = MessageDigest.getInstance("MD5").digest(var).encodeHex().toString()
            if (hash.startsWith("00000")) {
                try {
                    int position = Integer.parseInt(hash.charAt(5) as String)
                    char character = hash.charAt(6)
                    if (position < 8 && password[position] == '.' as char) {
                        password[position] = character
                        found++
                        println password.toString()
                    }
                } catch (NumberFormatException ignored) {
                }
            }
            index++
        }
    }

}
