package day2

class Part1 {

    private static int[][] numpad = [
            [7,8,9],
            [4,5,6],
            [1,2,3]
    ]

    static void main(String... args) {
        int x = 0, y = 0
        this.getClass().getResource('/day2/input.txt').eachLine { line ->
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case 'R' as char:
                        x = Math.min(x+1,1)
                        break
                    case 'L' as char:
                        x = Math.max(x-1,-1)
                        break
                    case 'U' as char:
                        y = Math.min(y+1,1)
                        break
                    case 'D' as char:
                        y = Math.max(y-1 , -1)
                        break
                }
            }
            print numpad[y+1][x+1]
        }
    }
}
