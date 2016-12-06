package day6

class Part1 {
    static void main(String... args) {
        List<Map<Character, Integer>> stats = null
        this.getClass().getResource('/day6/input.txt').eachLine { line ->
            if (stats == null) {
                stats = new ArrayList<>()
                for (int i = 0; i < line.length(); i++) {
                    stats.add(new LinkedHashMap<Character, Integer>())
                }
            }
            for (int i = 0; i < line.length(); i++) {
                char character = line.charAt(i)
                Map<Character, Integer> charStat = stats.get(i)
                if (!charStat.containsKey(character)) {
                    charStat.put(character, 1)
                } else {
                    charStat.put(character, charStat.get(character) + 1)
                }
            }
        }
        print "Part1: "
        for (int i = 0; i < stats.size(); i++) {
            print stats.get(i).max { a, b -> Integer.compare(a.value, b.value) }.key
        }
        print "\nPart2: "
        for (int i = 0; i < stats.size(); i++) {
            print stats.get(i).min { a, b -> Integer.compare(a.value, b.value) }.key
        }
    }
}
