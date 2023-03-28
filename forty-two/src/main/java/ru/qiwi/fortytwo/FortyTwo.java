package ru.qiwi.fortytwo;

public class FortyTwo extends Number implements Comparable<FortyTwo>, TheUltimateQuestionOfLifeTheUniverseAndEverything {

    private final Number value;

    public FortyTwo() {
        this.value = 42;
    }

    @Override
    public int intValue() {
        return value.intValue();
    }

    @Override
    public long longValue() {
        return value.longValue();
    }

    @Override
    public float floatValue() {
        return value.floatValue();
    }

    @Override
    public double doubleValue() {
        return value.doubleValue();
    }

    @Override
    public byte byteValue() {
        return value.byteValue();
    }

    @Override
    public short shortValue() {
        return value.shortValue();
    }

    @Override
    public int compareTo(FortyTwo o) {
        if (this.value == o.value) {
            return 0;
        } else if (this.value.intValue() < o.value.intValue()) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public void getAnswer() {
        System.out.println(this.value);
    }
}

interface TheUltimateQuestionOfLifeTheUniverseAndEverything {
    void getAnswer();
}