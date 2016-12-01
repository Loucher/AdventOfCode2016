package day1

class Part1 {
    static void main(String... args) {
        int x = 0, y = 0, direction = 0
        this.getClass().getResource('/day1/input.txt').splitEachLine(", ", { tokens ->
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
//                println "$it : $x x $y : ${direction % 4}"
            }
        })
        println Math.abs(x) + Math.abs(y)
    }
}
