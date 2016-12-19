import java.util.concurrent.ConcurrentLinkedDeque

class Day13 {
    static int NOT_GENERATED = Integer.MIN_VALUE
    static int WALL = -1
    static int INPUT = 1352
    static int[][] map = new int[500][500]
    static Coordinates start = new Coordinates(x: 1, y: 1)
    static Coordinates end = new Coordinates(x: 31, y: 39)
    static Coordinates boundaries = new Coordinates(x: 0, y: 0)

    static Queue<Coordinates> next = new ConcurrentLinkedDeque<>()

    static void main(String... args) {
        for (int[] row : map) {
            Arrays.fill(row, NOT_GENERATED)
        }

        next.add(start)
        setPointValue(start, 0)

        while (!next.isEmpty()) {
            Coordinates point = next.poll()
            int distance = getPointDistance(point)
            assert distance != WALL
            getNeighbors(point).each { Coordinates neighbor ->
                int neighborDistance = getPointDistance(neighbor)
                if (neighbor.x == end.x && neighbor.y == end.y) {
                    println distance + 1
                    next.clear()
                } else if (neighborDistance == Integer.MAX_VALUE) {
                    setPointValue(neighbor, distance + 1)
                    next.add(neighbor)
                }
            }
//            printMap()
        }
        printMap()
        int count=0
        for (int[] row : map) {
            for (int val : row) {
                if (val >= 0 && val <= 50) {
                    count++
                }
            }
        }
        println count
    }

    static printMap() {
        for (int i = 0; i < Math.max(end.x, boundaries.x) + 5; i++) {
            for (int j = 0; j < Math.max(end.y, boundaries.y) + 5; j++) {
                if (i == end.x && j == end.y) {
                    print "!! "
                } else {
                    int value = map[i][j]
                    switch (value) {
                        case WALL:
                            print "## "
                            break
                        case NOT_GENERATED:
                            print "   "
                            break
                        default:
                            print String.format("%02d ", value)

                    }
                }
            }
            println ""
        }
        println "----------------------------------------"
    }

    static List<Coordinates> getNeighbors(Coordinates coordinates) {
        int x = coordinates.x
        int y = coordinates.y
        List<Coordinates> neighbors = new ArrayList<>()
        neighbors.add(new Coordinates(x: x + 1, y: y))
        neighbors.add(new Coordinates(x: x, y: y + 1))
        if (x > 0) {
            neighbors.add(new Coordinates(x: x - 1, y: y))
        }
        if (y > 0) {
            neighbors.add(new Coordinates(x: x, y: y - 1))
        }
        return neighbors
    }

    static int getPointDistance(Coordinates coordinates) {
        if (boundaries.x < coordinates.x) {
            boundaries.x = coordinates.x
        }
        if (boundaries.y < coordinates.y) {
            boundaries.y = coordinates.y
        }
        int value = map[coordinates.x][coordinates.y]
        if (value == NOT_GENERATED) {
            value = generatePoint(coordinates)
            map[coordinates.x][coordinates.y] = value
        }
        return value
    }

    static void setPointValue(Coordinates coordinates, int value) {
        map[coordinates.x][coordinates.y] = value
    }

    static int generatePoint(Coordinates coordinates) {
        int x = coordinates.x
        int y = coordinates.y
        boolean isWall = Integer.toBinaryString(x * x + 3 * x + 2 * x * y + y + y * y + INPUT).count("1") % 2
        return isWall ? WALL : Integer.MAX_VALUE
    }


    static class Coordinates {
        int x
        int y
    }
}
