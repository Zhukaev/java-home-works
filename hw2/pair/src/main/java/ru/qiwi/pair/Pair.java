package ru.qiwi.pair;

public class Pair<One, Two> {
    private final One first;
    private final Two second;

    private Pair(One first, Two second) {
        this.first = first;
        this.second = second;
    }

    public static <T, V> Pair<T, V> of(T first, V second) {
        return new Pair<>(first, second);
    }

    public One getFirst() {
        return first;
    }

    public Two getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Pair<?,?> other = (Pair<?,?>) obj;
        return this.first == other.first && this.second == other.second;
    }
}
