class Day8 {
    static private boolean[][] screen

    static void main(String... args) {
        screen = new boolean[6][50]
        this.getClass().getResource('/input8.txt').eachLine { line ->
            def values = line.findAll(/\d+/)*.toInteger()
            if (line.startsWith("rect")) {
                drawRect(values.get(0), values.get(1))
            } else {
                if (line.contains("row")) {
                    rotateRow(values.get(0), values.get(1))
                } else {
                    rotatCol(values.get(0), values.get(1))
                }
            }
            printScreen()
        }
        countScreen()
    }

    static void rotatCol(int col, int distance) {
        List<Boolean> list = new ArrayList<>()
        for (int i = 0; i < screen.length; i++) {
            list.add(screen[i][col])
        }
        Collections.rotate(list, distance)
        for (int i = 0; i < screen.length; i++) {
            screen[i][col] = list.get(i)
        }
    }

    static void rotateRow(int row, int distance) {
        List<Boolean> list = new ArrayList<>()
        for (int i = 0; i < screen[row].length; i++) {
            list.add(screen[row][i])
        }
        Collections.rotate(list, distance)
        for (int i = 0; i < list.size(); i++) {
            screen[row][i] = list.get(i)
        }
    }

    static void drawRect(int x, int y) {
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                screen[i][j] = true
            }
        }
    }

    static void printScreen() {
        for (int y = 0; y < screen.length; y++) {
            for (int x = 0; x < screen[y].length; x++) {
                print screen[y][x] ? "#" : "."
            }
            println ""
        }
        println "------------------------------------------------"
    }

    static void countScreen() {
        int count = 0
        for (int y = 0; y < screen.length; y++) {
            for (int x = 0; x < screen[y].length; x++) {
                if (screen[y][x]) {
                    count++
                }
            }
        }
        println count
    }
}
