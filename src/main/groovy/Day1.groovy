class Day1 {
    static void main(String... args) {
        part1()
        part2()
    }

    private static void part1() {
        int x = 0, y = 0, direction = 0
        this.getClass().getResource('/input1.txt').splitEachLine(", ", { tokens ->
            tokens.each { it ->
                if ('R' as char == it.charAt(0)) {
                    direction++
                } else {
                    direction--
                    if (direction < 0) {
                        direction = 3
                    }
                }
                int distance = Integer.parseInt(it.substring(1))
                switch (direction % 4) {
                    case 0:
                        y += distance
                        break
                    case 1:
                        x += distance
                        break
                    case 2:
                        y -= distance
                        break
                    case 3:
                        x -= distance
                        break

                }
            }
        })
        println Math.abs(x) + Math.abs(y)
    }

    static void part2() {
        Map<Integer, Set<Integer>> visited = new HashMap<>();
        int x = 0, y = 0, direction = 0
        this.getClass().getResource('/input1.txt').splitEachLine(", ", { tokens ->
            tokens.each { it ->
                if ('R' as char == it.charAt(0)) {
                    direction++
                } else {
                    direction--
                    if (direction < 0) {
                        direction = 3
                    }
                }
                int distance = Integer.parseInt(it.substring(1))
                for (int i = 0; i < distance; i++) {
                    switch (direction % 4) {
                        case 0:
                            y++
                            break
                        case 1:
                            x++
                            break
                        case 2:
                            y--
                            break
                        case 3:
                            x--
                            break
                    }
                    if (!visited.containsKey(x)) {
                        visited.put(x, new HashSet<Integer>())
                    }
                    Set<Integer> visitedY = visited.get(x)
                    if (visitedY.contains(y)) {
                        println Math.abs(x) + Math.abs(y)
                        System.exit(0)
                    } else {
                        visitedY.add(y)
                    }
                }
            }
        })
    }
}
