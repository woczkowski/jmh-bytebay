package pl.itkontekst.jmh.bytebay;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Fork(1)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
@Threads(10)
public class MyBenchmark {

    private int[] integers;
    
    @Setup(Level.Iteration)
    public void init(){
        integers = new Random().ints(10000000).toArray();
    }

    
    @Benchmark
    @Group("sum")
    public int sumUp() {
        int sum = 0;
        for (int integer : integers) {
            sum+=integer;
        }
        return sum;
    }

    @Benchmark
    @Group("sum")
    public int sumDown() {
        int sum = 0;
        for (int i = integers.length -1; i >= 0; i--) {
            sum += integers[i];
        }
        return sum;
    }
    
    
    public MyBenchmark() {
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .threads(Runtime.getRuntime().availableProcessors() * 16)
                .syncIterations(false)
                .build();
        new Runner(opt).run();
    }
}
