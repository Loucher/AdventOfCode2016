class Day16 {

    static String INPUT = "00111101111101000"

    static void main(String... args) {
        solve(272)
        solve(35651584)
    }

    static void solve(int diskSize) {
        List<Boolean> data = new ArrayList<>()
        data.addAll(INPUT.findAll(/./)*.toBoolean())
        while (data.size() < diskSize) {
            modifiedDragonCurve(data)
        }
        while (data.size() > diskSize) {
            data.remove(data.size() - 1)
        }
        List<Boolean> cs = checksum(data)
        while (cs.size() % 2 == 0) {
            cs = checksum(cs)
        }
        cs.each { print it ? '1' : 0 }
        println ""
    }


    static void modifiedDragonCurve(List<Boolean> data) {
        List<Boolean> b = data.reverse()
        data.add(false)
        b.each { data.add(!it) }
    }

    static List<Boolean> checksum(List<Boolean> data) {
        List<Boolean> checksum = new ArrayList<>()
        for (int i = 0; i < data.size(); i += 2) {
            if (i == data.size() - 1) {
                checksum.add(data.get(i))
            } else {
                checksum.add(data.get(i) == data.get(i + 1))
            }
        }
        return checksum
    }

}
