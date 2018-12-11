package w50;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class MyRecursiveTask extends RecursiveTask<Long> {

    private static final int WORKLOAD_THRESHOLD = 16;
    private long workLoad;

    MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }

    protected Long compute() {
        //if work is above threshold, break tasks up into smaller tasks
        if(shouldSplitWorkload()) 
            return waitForSubTaskResults();
        else return workLoad;
    }

    private Long waitForSubTaskResults() {
        System.out.println("Splitting workLoad : " + this.workLoad);
        List<MyRecursiveTask> subTasks = new ArrayList<>(createSubTasks());
        subTasks.forEach(MyRecursiveTask::fork);
        return subTasks.stream().mapToLong(MyRecursiveTask::join).sum();
    }

    private boolean shouldSplitWorkload() {
        return this.workLoad > WORKLOAD_THRESHOLD;
    }

    private List<MyRecursiveTask> createSubTasks() {
        List<MyRecursiveTask> subTasks = new ArrayList<>();
        subTasks.add(new MyRecursiveTask((long) Math.floor(this.workLoad / 2.)));
        subTasks.add(new MyRecursiveTask((long) Math.ceil(this.workLoad / 2.)));
        return subTasks;
    }
}
