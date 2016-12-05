package day4

import java.util.stream.Collectors

class Part1 {

    static void main(String... args) {
        int count = 0
        this.getClass().getResource('/day4/input.txt').eachLine { line ->
            if (validate(line)) {
                count += line.find(/\d+/).toInteger()
            }
        }
        println count
    }

    private static boolean validate(String line) {
        String[] tokens = line.split("\\[")
        String name = tokens[0].substring(0, tokens[0].lastIndexOf("-")).replace("-", "")
        def calculatedChecksum = name.chars.iterator()
                .countBy { it }
                .sort { a, b ->
                        int compare = Integer.compare(b.value, a.value)
                        if(compare==0){
                            compare = Character.compare(a.key,b.key)
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
