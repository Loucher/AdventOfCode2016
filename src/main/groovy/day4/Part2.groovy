package day4

import java.util.stream.Collectors

class Part2 {

    static void main(String... args) {
        int count = 0
        this.getClass().getResource('/day4/input.txt').eachLine { line ->
            if (validate(line)) {
                String decryptedName= decrypt(line)
                if(decryptedName.contains("north")){
                    println decryptedName
                }
            }
        }
        println count
    }


    private static String decrypt(String line) {
        int shift = line.find(/\d+/).toInteger()
        String name = line.substring(0, line.lastIndexOf("-")).replace("-", " ")
        StringBuilder builder = new StringBuilder(name.length())
        for (int i = 0; i < name.length(); i++) {
            char character = name.charAt(i)
            if (character == (' ' as char)) {
                builder.append(" ")
            } else {
                builder.append((char) ((character + shift -('a' as char) ) % 26) + ('a' as char))
            }
        }
        String value = builder.toString()
        return  "$value : $shift"
    }

    private static boolean validate(String line) {
        String[] tokens = line.split("\\[")
        String name = tokens[0].substring(0, tokens[0].lastIndexOf("-")).replace("-", "")
        def calculatedChecksum = name.chars.iterator()
                .countBy { it }
                .sort { a, b ->
            int compare = Integer.compare(b.value, a.value)
            if (compare == 0) {
                compare = Character.compare(a.key, b.key)
            }
            return compare
        }
        .take(5)
                .keySet()
                .stream()
                .map { it.toString() }
                .collect(Collectors.joining())
        String checksum = tokens[1].replace("]", "")
        return checksum == calculatedChecksum
    }

}
