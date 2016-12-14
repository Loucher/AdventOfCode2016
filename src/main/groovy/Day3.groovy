class Day3 {

    static void main(String... args){
        part1()
        part2()
    }

    private static void part1() {
        int count = 0
        this.getClass().getResource('/input3.txt').eachLine { line ->
            if (validTriangle(line.findAll(/\d+/)*.toInteger())) {
                count++
            }
        }
        println count
    }
    private static void part2() {
        int count = 0
        int side = 0
        int[][] sides = new int[3][3]
        this.getClass().getResource('/input3.txt').eachLine { line ->
            List<Integer> l = line.findAll(/\d+/)*.toInteger()
            for (int i = 0; i < sides.length; i++) {
                sides[i][side] = l.get(i)
            }
            side++
            if (side == 3) {
                side = 0
                for (int i = 0; i < sides.length; i++) {
                    println sides[i]
                    if (validTriangle(sides[i])) {
                        count++
                    }
                }
            }
        }

        println count
    }

    static boolean validTriangle(List<Integer> sides){
        return (sides[0] + sides[1]) > sides[2] && (sides[0] + sides[2]) > sides[1] && (sides[1] + sides[2]) > sides[0]
    }
}
