package org.blendin.blendin;

public interface Callback<T> {
    void onSuccess(T result);

    void onError(String message);
}
