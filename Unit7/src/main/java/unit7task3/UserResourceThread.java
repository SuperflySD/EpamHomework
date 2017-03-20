package unit7task3;

import java.util.ArrayList;
import java.util.List;

public class UserResourceThread {
    private volatile int threadsNum = 0;
    private List<Integer> list;

    public UserResourceThread() {
        list = new ArrayList<Integer>();
    }

    public void setElement(Integer element) {
        list.add(element);
    }

    public Integer getELement() {
        if (list.size() > 0) {
            return list.remove(0);
        }
        return null;
    }


    public int getThreadsNum() {
        return threadsNum;
    }

    public void incrementThreadsNum() {
        this.threadsNum++;
    }
    public void decrementThreadsNum() {
        this.threadsNum--;
    }


}



