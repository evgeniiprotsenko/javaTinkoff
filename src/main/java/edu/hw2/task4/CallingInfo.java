package edu.hw2.task4;

public record CallingInfo(String className, String methodName) {
    public static CallingInfo getCallingInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement method = stackTrace[2];
        String fullClassName = method.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        String methodName = method.getMethodName();
        return new CallingInfo(className, methodName);
    }
}
