class Day2 {

    private static int[][] numpad1 = [
            [7, 8, 9],
            [4, 5, 6],
            [1, 2, 3]
    ]

    private static char[][] numpad2 = [
            ['x', 'x', '1', 'x', 'x'],
            ['x', '2', '3', '4', 'x'],
            ['5', '6', '7', '8', '9'],
            ['x', 'A', 'B', 'C', 'x'],
            ['x', 'x', 'D', 'x', 'x']
    ]

    static void main(String... args) {
        part1()
        part2()
    }

    private static void part1() {
        int x = 0, y = 0
        this.getClass().getResource('/input2.txt').eachLine { line ->
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case 'R' as char:
                        x = Math.min(x + 1, 1)
                        break
                    case 'L' as char:
                        x = Math.max(x - 1, -1)
                        break
                    case 'U' as char:
                        y = Math.min(y + 1, 1)
                        break
                    case 'D' as char:
                        y = Math.max(y - 1, -1)
                        break
                }
            }
            print numpad1[y + 1][x + 1]
        }
    }

    static void part2() {
        int x = 0, y = 0
        this.getClass().getResource('/input2.txt').eachLine { line ->
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case 'R' as char:
                        x = Math.min(x + 1, 2 - Math.abs(y))
                        break
                    case 'L' as char:
                        x = Math.max(x - 1, -2 + Math.abs(y))
                        break
                    case 'D' as char:
                        y = Math.min(y + 1, 2 - Math.abs(x))
                        break
                    case 'U' as char:
                        y = Math.max(y - 1, -2 + Math.abs(x))
                        break
                }
            }
            print numpad2[y + 2][x + 2]
        }
    }

}
