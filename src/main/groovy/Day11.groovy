import groovyx.gpars.GParsPool

import java.util.stream.Collectors

class Day11 {

    static State initialState = new State(
            previousState: null,
            elevator: 0,
            floors: [
                    ["pog", "thg", "thm", "prg", "rug", "rum", "cog", "com"],
                    ["pom", "prm"],
                    [],
                    []
            ])

    static int moves = 0

    static void main(String... args) {
        Set<State> currentStates = new ArrayList<>()
        Set<State> finished = new HashSet<>()
        currentStates.add(initialState)
        moves = 0
        initialState.print()
        boolean completed = false
        int best = Integer.MAX_VALUE
        while (!completed) {
            Set<State> newStates = new ArrayList<>()
            GParsPool.withPool {
                currentStates.eachParallel { State state ->
                    newStates.addAll(generateNewStates(state))
                }
            }

            newStates.removeAll(currentStates)
            currentStates.addAll(newStates)
            println currentStates.size()
            currentStates.each {
                if (it.isCompleted()) {
                    int steps = printProgress(it)
                    if (steps < best) {
                        best = steps
                    }
                    println best
                }
            }

        }
    }


    static int printProgress(State state) {
        int depth = 1
        if (state.previousState != null) {
            depth += printProgress(state.previousState)
        }
        state.print()
        return depth
    }

    static List<State> generateNewStates(State currentState) {
        List<State> newStates = new ArrayList<>()
        Set<String> currentFloor = currentState.floors[currentState.elevator]
        new ArrayList<>(currentFloor.toList()).subsequences().each { List<String> subsequence ->
            newStates.addAll(currentState.move(subsequence.toSet()).findAll { it.isValid() })
        }
        return newStates
    }


    private static class State {
        State previousState
        int elevator
        Set<String>[] floors

        private State moveUp(Set<String> payload) {
            executeMove payload, true
        }

        private State moveDown(Set<String> payload) {
            executeMove payload, false
        }

        List<State> move(Set<String> payload) {

            List<State> newStates = new ArrayList<>()
            if (moveIsValid(payload)) {
                if (elevator > 0) {
                    newStates.add moveDown(payload)
                }
                if (elevator < 3) {
                    newStates.add(moveUp(payload))
                }
            }
            return newStates
        }


        static boolean moveIsValid(Set<String> payload) {
            if (payload.size() == 1) {
                return true
            }
            if (payload.size() == 2) {
                return validate(payload)
            }
            return false
        }

        private executeMove(Set<String> payload, boolean up) {
            State newState = new State()
            newState.elevator = elevator + (up ? 1 : -1)
            newState.floors = new HashSet<String>[4]
            for (int i = 0; i < 4; i++) {
                newState.floors[i] = new HashSet(floors[i])
                if (i == elevator) {
                    newState.floors[i].removeAll(payload)
                }
                if (i == newState.elevator) {
                    newState.floors[i].addAll(payload)
                }
            }
            newState.previousState = this
            return newState
        }

        boolean isValid() {
            for (int i = 0; i < 4; i++) {
                if (!validate(floors[i])) {
                    return false
                }
            }
            return true
        }

        boolean isCompleted() {
            for (int i = 0; i < 3; i++) {
                if (!floors[i].isEmpty()) {
                    return false
                }
            }
            return true
        }

        private static boolean validate(Set<String> list) {
            Set<String> generators = list.stream().filter { it.endsWith("g") }.map {
                it.substring(0, 2)
            }.collect(Collectors.toList())
            Set<String> microchips = list.stream().filter { it.endsWith("m") }.map {
                it.substring(0, 2)
            }.collect(Collectors.toList())
            Set<String> pairs = new ArrayList<>(generators)
            pairs.retainAll(microchips)
            generators.removeAll(pairs)
            microchips.removeAll(pairs)
            return generators.isEmpty() || microchips.isEmpty()
        }

        void print() {
            for (int i = 0; i < 4; i++) {
                println "Floor$i ${i == elevator ? 'E' : ''} ${floors[i]}"
            }
            println '####'
        }

        int score() {
            for (int i = 0; i < 4; i++) {
                if (!floors[i].isEmpty()) {
                    return i
                }
            }
            return 4
        }

        boolean equals(o) {
            if (this.is(o)) return true
            if (getClass() != o.class) return false

            State state = (State) o

            if (elevator != state.elevator) return false
            if (!Arrays.equals(floors, state.floors)) return false

            return true
        }

        int hashCode() {
            int result
            result = elevator
            result = 31 * result + Arrays.hashCode(floors)
            return result
        }
    }


}
