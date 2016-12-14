class Day7 {

    static void main(String... args) {
        int count = 0
        int count2 = 0
        this.getClass().getResource('/input7.txt').eachLine { line ->
            if (supportTLS(line)) {
                count++
            }
            if(supportSSL(line)){
                count2++
            }
        }
        println "TLS: $count"
        println "SSL: $count2"
    }
    static boolean supportTLS(String string) {
        return string =~ /^(?=.*(\w)(?!\1)(\w)\2\1(?![^\[]*]))(?!.*(\w)(?!\3)(\w)\4\3(?=[^\[]*]))/
    }

    static boolean supportSSL(String string) {
        return string =~ /(.)(?!\1)(.)\1\w*(([]\[]\w*){2})*[]\[]\w*\2\1\2/
    }
}
