package hr.fer.zemris.diplproj.heuristic.simanneal.temp;

public class LinearTempSchedule implements ITempSchedule {
    private final double tInitial;
    private final double tFinal;
    private final int innerLimit;
    private final int outerLimit;

    private double tCurrent;


    public LinearTempSchedule(int innerLimit, int outerLimit, double tInitial, double tFinal) {
        this.innerLimit = innerLimit;
        this.outerLimit = outerLimit;
        this.tInitial = tInitial;
        this.tFinal = tFinal;

        this.tCurrent = tInitial;
    }

    @Override
    public double getNextTemperature() {
        tCurrent -= ((tFinal - tInitial) / outerLimit);

        return tCurrent;
    }

    @Override
    public int getInnerLoopCounter() {
        return innerLimit;
    }

    @Override
    public int getOuterLoopCounter() {
        return outerLimit;
    }
}
