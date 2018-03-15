package pl.itkontekst.jmh.bytebay;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@Fork(1)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class MyBenchmark {

    @State(Scope.Group)
    public static class Shared {
        private int readField;
        private int writeField;
    }
    @State(Scope.Group)
    public static class Read {
        private int readField;
    }
    @State(Scope.Group)
    public static class Write {
        private int writeField;
    }

    @Benchmark
    @Group("shared")
    public int readerShared(Shared shared) {
        return shared.readField;
    }

    @Benchmark
    @Group("shared")
    public void writerShared(Shared shared) {
        shared.writeField++;
    }

    @Benchmark
    @Group("padded")
    public int readerPadded(Read read) {
        return read.readField;
    }

    @Benchmark
    @Group("padded")
    public void writerPadded(Write write) {
        write.writeField++;
    }


    public MyBenchmark() {
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .threads(Runtime.getRuntime().availableProcessors())
                .build();
        new Runner(opt).run();
    }
}
