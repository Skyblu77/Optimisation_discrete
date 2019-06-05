import algo.HillClimbing;
import algo.RandomWalk;
import algo.SimulatedAnnealing;
import algo.TabuSearch;
import algo.mapping.IMapping;
import algo.mapping.MPermutation;
import benchmark.Benchmark;
import io.BenchResultWriter;
import io.Parser;
import utils.Landscape;
import utils.Order;

public class Main {

    private static final int THREAD_NB = 2;
    private static final int AVERAGE_ITERATION = 4;

    static Landscape tai12a;
    static Landscape tai15a;
    static Landscape tai17a;
    static Landscape tai20a;
    static Landscape tai25a;
    static Landscape tai30a;
    static Landscape tai35a;
    static Landscape tai40a;
    static Landscape tai50a;
    static Landscape tai60a;
    static Landscape tai80a;
    static Landscape tai100a;

    public static void main(String[] args) throws Exception {


        MPermutation mapping = new MPermutation();

        tai12a = Parser.createLandscape("data","tai12a.dat").setBestFitness(224416);
        tai15a = Parser.createLandscape("data","tai15a.dat").setBestFitness(388214);
        tai17a = Parser.createLandscape("data","tai17a.dat").setBestFitness(491812);
        tai20a = Parser.createLandscape("data","tai20a.dat").setBestFitness(703482);
        tai25a = Parser.createLandscape("data","tai25a.dat").setBestFitness(1167256);
        tai30a = Parser.createLandscape("data","tai30a.dat").setBestFitness(1818146);
        tai35a = Parser.createLandscape("data","tai35a.dat").setBestFitness(2422002);
        tai40a = Parser.createLandscape("data","tai40a.dat").setBestFitness(3139370);
        tai50a = Parser.createLandscape("data","tai50a.dat").setBestFitness(4938796);
        tai60a = Parser.createLandscape("data","tai60a.dat").setBestFitness(7205962);
        tai80a = Parser.createLandscape("data","tai80a.dat").setBestFitness(13499184);
        tai100a = Parser.createLandscape("data","tai100a.dat").setBestFitness(21044752);


//        benchmarkRandomLogOverAll(mapping)
//                .runBench()
//                .writeOut(new BenchResultWriter("out", "benchmarkRandomLogOverAll"));
//
//        benchmarkHillClimbingSmallOnes(mapping)
//                .runBench()
//                .writeOut(new BenchResultWriter("out", "benchmarkHillClimbingSmallOnes"));
//
//        benchmarkHillClimbingBigOnes(mapping)
//                .runBench()
//                .writeOut(new BenchResultWriter("out", "benchmarkHillClimbingBigOnes"));

//        benchmarkSimulatedAnnealingMuX(mapping, 0.5F)
//                .runBench()
//                .writeOut(new BenchResultWriter("out", "benchmarkSimulatedAnnealingMu050"));
//        benchmarkSimulatedAnnealingMuX(mapping, 0.8F)
//                .runBench()
//                .writeOut(new BenchResultWriter("out", "benchmarkSimulatedAnnealingMu080"));
//        benchmarkSimulatedAnnealingMuX(mapping, 0.9F)
//                .runBench()
//                .writeOut(new BenchResultWriter("out", "benchmarkSimulatedAnnealingMu090"));
//        benchmarkSimulatedAnnealingMuX(mapping, 0.95F)
//                .runBench()
//                .writeOut(new BenchResultWriter("out", "benchmarkSimulatedAnnealingMu095"));
//        benchmarkSimulatedAnnealingMuX(mapping, 0.99F)
//                .runBench()
//                .writeOut(new BenchResultWriter("out", "benchmarkSimulatedAnnealingMu099"));

        benchmarkTabou(mapping)
                .runBench()
                .writeOut(new BenchResultWriter("out", "benchmarkTabou"));


//        for (int i = 1; i < 10; i += 1) {
//            benchmark.registerAlgo(new HillClimbing(mapping, 0.05, i));
//        }



//        Benchmark benchmark2 = new Benchmark(2, 4);
//        benchmark2.registerLandscape(Parser.createLandscape("data","tai12a.dat").setBestFitness(224416));
//        BenchResultWriter benchResultWriter2 = new BenchResultWriter("out", "test10");
//        for (int i = 1; i < 100000; i *= 10) {
//            benchmark2.registerAlgo(new RandomWalk(mapping, i));
//        }
//        benchmark2.runBench();
//        benchmark2.writeOut(benchResultWriter2);


    }

    public static Benchmark benchmarkRandomLogOverAll(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        benchmark.registerLandscape(tai35a);
        benchmark.registerLandscape(tai40a);
        benchmark.registerLandscape(tai50a);
        benchmark.registerLandscape(tai60a);
        benchmark.registerLandscape(tai80a);
        benchmark.registerLandscape(tai100a);
        for (int i = 1; i < 100001; i *= 10) {
            benchmark.registerAlgo(new RandomWalk(mapping, i));
        }
        return benchmark;
    }

    public static Benchmark benchmarkHillClimbingSmallOnes(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        for (int i = 1; i < 30; i += 1) {
            benchmark.registerAlgo(new HillClimbing(mapping, 1, i));
        }
        return benchmark;
    }

    public static Benchmark benchmarkHillClimbingBigOnes(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        benchmark.registerLandscape(tai35a);
        benchmark.registerLandscape(tai40a);
        benchmark.registerLandscape(tai50a);
        benchmark.registerLandscape(tai60a);
        benchmark.registerLandscape(tai80a);
        benchmark.registerLandscape(tai100a);
        for (int i = 1; i < 33; i += 3) {
            benchmark.registerAlgo(new HillClimbing(mapping, 0.05, i));
        }
        return benchmark;
    }


    public static Benchmark benchmarkSimulatedAnnealingMuX(IMapping mapping, Float mu) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        benchmark.registerLandscape(tai35a);
        benchmark.registerLandscape(tai40a);
        benchmark.registerLandscape(tai50a);
        benchmark.registerLandscape(tai60a);
        benchmark.registerLandscape(tai80a);
        benchmark.registerLandscape(tai100a);
        for (int i = 1; i < 8192 + 1; i *= 2) {
            benchmark.registerAlgo(new SimulatedAnnealing(mapping, mu, i));
        }
        return benchmark;
    }

    public static Benchmark benchmarkTabou(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
//        benchmark.registerLandscape(tai12a);
//        benchmark.registerLandscape(tai15a);
//        benchmark.registerLandscape(tai17a);
//        benchmark.registerLandscape(tai20a);
//        benchmark.registerLandscape(tai25a);
//        benchmark.registerLandscape(tai30a);
//        benchmark.registerLandscape(tai35a);
//        benchmark.registerLandscape(tai40a);
//        benchmark.registerLandscape(tai50a);
//        benchmark.registerLandscape(tai60a);
//        benchmark.registerLandscape(tai80a);
        benchmark.registerLandscape(tai100a);
        for (int tabouSize = 1; tabouSize < 10; tabouSize += 1) {
            for (int maxSteps = 10; maxSteps < 100000 + 1; maxSteps *= 10) {
                benchmark.registerAlgo(new TabuSearch(mapping, maxSteps, tabouSize));
            }
        }
        for (int tabouSize = 10; tabouSize < 101; tabouSize += 10) {
            for (int maxSteps = 10; maxSteps < 100000 + 1; maxSteps *= 10) {
                benchmark.registerAlgo(new TabuSearch(mapping, maxSteps, tabouSize));
            }
        }
        return benchmark;
    }
}
