package edu.hw2.task4;

public class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        Throwable throwable = new Throwable();
        return new CallingInfo(throwable.getStackTrace()[1].getClassName(),
            throwable.getStackTrace()[1].getMethodName()
        );
    }
}

