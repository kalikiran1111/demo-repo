import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.DoubleStream;

public class ParallelSum {
    public static void main(String[] args) {
        final int N = 9000000;
        double[] list = new double[N];
        for (int i = 0; i < list.length; i++) {
            list[i] = 1;
        }

        long startTime = System.currentTimeMillis();
        double resultWhenRunInParallel = parallelSum(list);
        System.out.println("Parallel Sum when run in parallel using Fork/Join : " + resultWhenRunInParallel);
        long endTime = System.currentTimeMillis();
        System.out.println("Parallel Sum took  " + (endTime - startTime) + " milliseconds\n");

        long startTimeForSequential = System.currentTimeMillis();
        double resultWhenRunInSequence = sequentialSum(list);
        System.out.println("Sequential Sum when values added sequentially took : " + resultWhenRunInSequence);
        long endTimeForSequential = System.currentTimeMillis();
        System.out.println("Sequential Sum took  " + (endTimeForSequential - startTimeForSequential) + " milliseconds\n");
    }

    public static double parallelSum(double[] list) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelSumRecursiveTask task = new ParallelSumRecursiveTask(list);
        return forkJoinPool.invoke(task);
    }

    public static double sequentialSum(double[] list) {
        double sum = 0;
        for ( double value : list) {
            sum+=value;
        }
        return sum;
    }

    public static class ParallelSumRecursiveTask extends RecursiveTask<Double> {
        private double[] list;
        private static int THRESHOLD = 1000;


        public ParallelSumRecursiveTask(double[] list) {
            this.list = list;
        }

        @Override
        protected Double compute() {
            if (list.length > THRESHOLD) {
                return ForkJoinTask.invokeAll(subtasks())
                        .stream()
                        .mapToDouble(ForkJoinTask::join)
                        .sum();
            } else {
                return sum(list);
            }
        }

        private Collection<ParallelSumRecursiveTask> subtasks() {
            List<ParallelSumRecursiveTask> dividedTasks = new ArrayList<>();
            dividedTasks.add(new ParallelSumRecursiveTask(Arrays.copyOfRange(list,0, list.length/2)));
            dividedTasks.add(new ParallelSumRecursiveTask(Arrays.copyOfRange(list, list.length/2, list.length)));
            return dividedTasks;
        }

        private Double sum(double[] list) {
            return DoubleStream.of(list).sum();
        }
    }
}
