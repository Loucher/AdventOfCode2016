/**
 * Created by matus on 15.12.2016.
 */
class Day12 {
    static index = 0
    static List<Command> commands = new ArrayList<>()
    static Register[] registers = [new Register(), new Register(), new Register(), new Register()]
    static Closure cpy = { Object[] params ->
        assert params.length == 2
        Object from = params[0]
        Register to = params[1] as Register
        if (from instanceof Integer) {
            to.value = from
        } else if (from instanceof Register) {
            to.value = from.value
        }
        index++
    }

    static Closure inc = { Object[] params ->
        assert params.length == 1
        assert params[0] instanceof Register
        ((Register) params[0]).value++
        index++
    }


    static Closure dec = { Object[] params ->
        assert params.length == 1
        assert params[0] instanceof Register
        ((Register) params[0]).value--
        index++
    }

    static Closure jnz = { Object[] params ->
        assert params.length == 2
        Object valueObject = params[0]
        Object distanceObject = params[1]
        boolean jump
        if (valueObject instanceof Integer) {
            jump = valueObject != 0
        } else if (valueObject instanceof Register) {
            jump = valueObject.value != 0
        }
        if (jump) {
            if (distanceObject instanceof Integer) {
                index += distanceObject
            } else if (distanceObject instanceof Register) {
                index += distanceObject.value
            }
        } else {
            index++
        }
    }


    static void main(String... args) {
        this.getClass().getResource('/input12.txt').eachLine { line ->
            String[] tokens = line.split(" ")
            switch (tokens[0]) {
                case "cpy":
                    commands.add(new Command(cpy, parse(tokens[1]), parse(tokens[2])))
                    break
                case "inc":
                    commands.add(new Command(inc, parse(tokens[1])))
                    break
                case "dec":
                    commands.add(new Command(dec, parse(tokens[1])))
                    break
                case "jnz":
                    commands.add(new Command(jnz, parse(tokens[1]), parse(tokens[2])))
            }
        }
        registers[2].value = 1
        while (index < commands.size()) {
            commands.get(index).execute()
//            println "$index $registers"
        }
        println registers[0].value
    }

    static Object parse(String value) {
        if (value.isInteger()) {
            return value.toInteger()
        } else {
            int i = value.charAt(0) - ('a' as char)
            return registers[i]
        }
    }

    static class Register {
        int value

        @Override
        String toString() {
            Integer.toString(value)
        }
    }

    static class Command {
        Closure closure
        Object[] params

        Command(Closure closure, Object... params) {
            this.closure = closure
            this.params = params
        }

        void execute() {
            closure.call(params)
        }
    }


}
