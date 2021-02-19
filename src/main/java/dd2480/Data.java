package dd2480;

public class Data {
    public boolean hasRun = false;
    public int falseRun;
    public int trueRun;

    public Data(int num, boolean run) {
        if (run) {
            this.falseRun = 0;
            this.trueRun = num;
        } else {
            this.falseRun = num;
            this.trueRun = 0;
        }
    }

    public Data() {
        this.trueRun = 0;
        this.falseRun = 0;
    }

}