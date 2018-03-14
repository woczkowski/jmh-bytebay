package pl.itkontekst.jmh.bytebay;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 10)
@Measurement(iterations = 10)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@State(Scope.Benchmark)
public class MyBenchmark {

    private int val1 = 1;
    private int val2 = 1;

    @Benchmark
    public int baseline() {
        return 2;
    }

    @Benchmark
    public void addWrong(){
        int i = 1+1;
    }

    @Benchmark
    public int add(){
        return 1+1;
    }

    @Benchmark
    public int addState(MyBenchmark state){
        return state.val1+state.val2;
    }
}
