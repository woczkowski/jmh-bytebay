package pl.itkontekst.jmh.bytebay;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@Fork(1)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@State(Scope.Benchmark)
public class MyBenchmark {

    @Param( {"http://www.wp.pl","fsdfs:dfs//"})
    private String url;

    private static Pattern pattern = Pattern.compile("[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)");

    
    @Benchmark
    public boolean testRegex() {
        return pattern.matcher(url).matches();
    }

    @Benchmark
    public boolean testURL() {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException ex) {
            return false;
        }
    }

}
