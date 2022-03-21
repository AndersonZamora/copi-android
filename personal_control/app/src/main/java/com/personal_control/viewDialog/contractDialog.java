package com.personal_control.viewDialog;

public class contractDialog {

    public interface successfulDialog {
        void attachView(attachViewDialog attachViewDialog);
    }

    public interface attachViewDialog {
        void viewMessageAcceptLocation();

        void viewMessageCancelLocation();

        void viewMessageAcceptCamera();

        void viewMessageCancelCamera();
    }
}
