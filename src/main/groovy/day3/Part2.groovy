package day3

class Part2 {

    static void main(String... args) {
        int count = 0
        int side = 0
        int[][] sides = new int[3][3]
        this.getClass().getResource('/day3/input.txt').eachLine { line ->
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

    static boolean validTriangle(int[] sides) {
        return (sides[0] + sides[1]) > sides[2] && (sides[0] + sides[2]) > sides[1] && (sides[1] + sides[2]) > sides[0]
    }

}
