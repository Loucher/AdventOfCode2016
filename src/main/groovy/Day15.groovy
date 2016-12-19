class Day15 {
    static private List<Disk> disks

    static void main(String... args) {
        disks = new ArrayList<>()
        this.getClass().getResource('/input15.txt').eachLine { String line ->
            List<Integer> integers = line.findAll(/\d+/)*.toInteger()
            Day15.disks.add(new Disk(integers[1], integers[3]))
        }
        int time = 0
        while (!passes(time)) {
            time++
        }
        println "Part1 $time"

        time = 0
        disks.add(new Disk(11,0))
        while (!passes(time)) {
            time++
        }
        println "Part2 $time"

    }

    static boolean passes(int time) {
        for (int i = 0; i < disks.size(); i++) {
            if (disks.get(i).getDiskPosition(time + i + 1) != 0) {
                return false
            }
        }
        return true
    }

    static class Disk {
        int positions
        int initialPosition

        Disk(int positions, int initialPosition) {
            this.positions = positions
            this.initialPosition = initialPosition
        }

        int getDiskPosition(int time) {
            return (time + initialPosition) % positions
        }
    }

}
