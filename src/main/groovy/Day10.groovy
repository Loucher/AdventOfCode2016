class Day10 {

    static Map<Integer, Set<Integer>> outputs = new HashMap<>()
    static Map<Integer, Bot> bots = new HashMap<>()

    static void main(String... args) {
        this.getClass().getResource('/input10.txt').eachLine { line ->
            String[] tokens = line.split(" ")
            if (tokens[0] == "value") {
                int value = tokens[1].toInteger()
                int bot = tokens[5].toInteger()
                getBot(bot).bin.add(value)
            } else {
                int botValue = tokens[1].toInteger()
                boolean lowIsBot = tokens[5] == "bot"
                int lowValue = tokens[6].toInteger()
                boolean highIsBot = tokens[10] == "bot"
                int highValue = tokens[11].toInteger()
                Bot bot = getBot(botValue)
                bot.lowValueGoesTo = lowValue
                bot.lowIsBot = lowIsBot
                bot.highValueGoesTo = highValue
                bot.highIsBot = highIsBot
            }
        }
        trigger()
        part2()
    }


    static void part2() {
        int sum = 1
        for (int i = 0; i < 3; i++) {
            sum *= outputs.get(i).first()
        }
        println "Part2 $sum"
    }

    static void trigger() {
        for (Integer value : bots.keySet()) {
            Bot bot = bots.get(value)
            if (bot.hasTwo()) {
                bot.give()
                return
            }
        }
    }


    static Bot getBot(int value) {
        if (!bots.containsKey(value)) {
            bots.put(value, new Bot())
        }
        return bots.get(value)
    }

    static Set<Integer> getOutput(int value) {
        if (!outputs.containsKey(value)) {
            outputs.put(value, new HashSet<Integer>())
        }
        return outputs.get(value)
    }


    private static class Bot {
        int lowValueGoesTo
        boolean lowIsBot
        int highValueGoesTo
        boolean highIsBot

        SortedSet<Integer> bin = new TreeSet<>()

        void addToBin(int value) {
            bin.add(value)
            if (bin.contains(61) && bin.contains(17)) {
                bots.each { key, bot ->
                    if (bot == this) {
                        println "Part1 $key"
                    }
                }
            }

            if (hasTwo()) {
                give()
            }
        }

        void give() {
            int low = bin.first()
            int high = bin.last()
            if (lowIsBot) {
                getBot(lowValueGoesTo).addToBin(low)
            } else {
                getOutput(lowValueGoesTo).add(low)
            }
            if (highIsBot) {
                getBot(highValueGoesTo).addToBin(high)
            } else {
                getOutput(highValueGoesTo).add(high)
            }
        }

        boolean hasTwo() {
            return bin.size() == 2
        }
    }
}
