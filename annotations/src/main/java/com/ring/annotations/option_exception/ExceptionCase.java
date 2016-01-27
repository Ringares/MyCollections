package com.ring.annotations.option_exception;

/**
 * Created by ring
 * on 15/12/21.
 *
 * 调用方并不知道方法内部做了判空处理，很有可能又做了一次判空操作。明明只需要一次判空就OK了，由于信息不对称，导致调用方和提供方各自进行了一次判空操作，造成性能开销。
 */
public class ExceptionCase {

    public void addControllerListener(ControllerListener<? super Object> controllerListener) {
        Preconditions.checkNotNull(controllerListener);

    }


    static class Preconditions {
        public static void checkNotNull(Object object) {
            if (null == object) {
                throw new RuntimeException();
            }
        }
    }

    private class ControllerListener<T> {
    }
}
