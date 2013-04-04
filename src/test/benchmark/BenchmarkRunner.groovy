package benchmark

import com.google.caliper.Runner

class BenchmarkRunner {

    public static void main(String[] args) {
        Runner.main(SortingAlgorithmsBenchmark, args)
    }
}
