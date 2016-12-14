

import org.junit.Test

class Test7 {

    @Test
    void testValidation() {
        assert Day7.supportTLS("abba[mnop]qrst")
        assert !Day7.supportTLS("abcd[bddb]xyyx")
        assert !Day7.supportTLS("aaaa[qwer]tyui")
        assert Day7.supportTLS("ioxxoj[asdfgh]zxcvbn")
    }

}
