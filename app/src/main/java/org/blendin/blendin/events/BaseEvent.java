package org.blendin.blendin.events;

public class BaseEvent<T> {
    protected T result;
    protected boolean success;
    protected String error;

    public BaseEvent(T result, boolean success, String error) {
        this.result = result;
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public T getResult() {
        return result;
    }
}
