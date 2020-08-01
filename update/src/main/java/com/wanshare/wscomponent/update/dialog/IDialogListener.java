package com.wanshare.wscomponent.update.dialog;


public interface IDialogListener {

    void confirm();

    void cancel();


    class SubDialogListener implements IDialogListener {

        @Override
        public void confirm() {

        }

        @Override
        public void cancel() {

        }
    }


}
