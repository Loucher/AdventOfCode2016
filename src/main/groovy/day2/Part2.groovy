package day2

class Part2 {

    private static char[][] numpad = [
            ['x','x','1','x','x'],
            ['x','2','3','4','x'],
            ['5','6','7','8','9'],
            ['x','A','B','C','x'],
            ['x','x','D','x','x']
    ]

    static void main(String... args) {
        int x = 0, y = 0
        this.getClass().getResource('/day2/input.txt').eachLine { line ->
            for (int i = 0; i < line.length(); i++) {
                switch (line.charAt(i)) {
                    case 'R' as char:
                        x = Math.min(x+1,2-Math.abs(y))
                        break
                    case 'L' as char:
                        x = Math.max(x-1,-2+Math.abs(y))
                        break
                    case 'D' as char:
                        y = Math.min(y+1,2-Math.abs(x))
                        break
                    case 'U' as char:
                        y = Math.max(y-1 ,-2+Math.abs(x))
                        break
                }
//                println numpad[y+2][x+2]
            }
            print numpad[y+2][x+2]
        }
    }
}
