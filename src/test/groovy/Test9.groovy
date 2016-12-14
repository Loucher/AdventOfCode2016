import org.junit.Test

class Test9 {

    @Test
    void testDecomrpess() {
        assert test("(3x3)XYZ", "XYZXYZXYZ")
        assert test("X(8x2)(3x3)ABCY", "XABCABCABCABCABCABCY")
        assert test("(27x12)(20x12)(13x14)(7x10)(1x12)A", 241920)
        assert test("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN", 445)
    }

    private boolean test(String input, String output) {
        return Day9.decompress(input) == output.length()
    }

    private boolean test(String input, int outputLength) {
        return Day9.decompress(input) == outputLength
    }

}
