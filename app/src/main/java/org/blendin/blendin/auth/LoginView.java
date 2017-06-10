package org.blendin.blendin.auth;

public interface LoginView {

    LoginPresenter getPresenter();

    void showError(String msg);
}
