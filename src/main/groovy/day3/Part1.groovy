package day3

class Part1 {

    static void main(String... args){
        int count=0
        this.getClass().getResource('/day3/input.txt').eachLine{line->
            if(validTriangle(line.findAll( /\d+/ )*.toInteger())){
                count++
            }
        }
        println count
    }

    static boolean validTriangle(List<Integer> sides){
        return (sides[0] + sides[1]) > sides[2] && (sides[0] + sides[2]) > sides[1] && (sides[1] + sides[2]) > sides[0]
    }
}
