class Day9 {

    static String buffer

    static void main(String... args) {
        part1()
        part2()
    }

    private static void part1() {
        println "Part1 ${decompress1(this.getClass().getResource('/input9.txt').text).size()}"
    }

    private static void part2() {
        println "Part2 ${decompress(this.getClass().getResource('/input9.txt').text)}"
    }


    static String decompress1(String file) {
        StringReader reader = new StringReader(file)
        StringBuilder builder = new StringBuilder()
        char character
        while (true) {
            character = reader.read()
            if (character == (char) -1) {
                break
            }
            if (character == '(' as char) {
                char endChar = ' ' as char
                StringBuilder buffer = new StringBuilder()
                while (endChar != ')' as char) {
                    endChar = reader.read()
                    buffer.append(endChar)
                }
                List<Integer> values = buffer.toString().findAll(/\d+/)*.toInteger()
                int length = values.get(0)
                int repeates = values.get(1)
                char[] charBuffer = new char[length]
                reader.read(charBuffer)
                for (int i = 0; i < repeates; i++) {
                    builder.append(charBuffer)
                }
            } else {
                builder.append(character)
            }
        }
        return builder.toString()
    }

    static long decompress(String string) {
        buffer = string
        return decompress(0, buffer.length())
    }

    static long decompress(int start, int end) {
        long decompressedLength = 0
        int index = start
        while (index < end) {
            char character = buffer.charAt(index)
            if (character == '(' as char) {
                int endBracket = index
                while (buffer.charAt(endBracket) != ')' as char) {
                    endBracket++
                }
                List<Integer> values = buffer.substring(index, endBracket).findAll(/\d+/)*.toInteger()
                int length = values.get(0)
                int repeates = values.get(1)
                index = endBracket + 1 + length
                decompressedLength +=decompress(endBracket + 1, index) * repeates
            } else {
                decompressedLength++
                index++
            }
        }
        return decompressedLength
    }


}