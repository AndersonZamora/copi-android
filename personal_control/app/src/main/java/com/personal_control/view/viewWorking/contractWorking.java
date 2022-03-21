package com.personal_control.view.viewWorking;

public class contractWorking {

   public interface workingView {
        void set_working_view(viewWorking working_view);
    }

  public  interface viewWorking {
        void view_location(Double la, Double lo);
    }
}
