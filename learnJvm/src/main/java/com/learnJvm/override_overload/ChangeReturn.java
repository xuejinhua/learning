package com.learnJvm.override_overload;

/**
 * ChangeReturn
 *
 * @author xuejinhua
 * @date 2019/1/11 15:25
 */
public class ChangeReturn {

    private void sayHi(Java java) {
        System.out.println("Hi Java");
    }

    /**
     * 方法的重载可以改变返回值
     *
     * @param language
     * @return
     */
    private int sayHi(Language language) {
        System.out.println("Im Language");
        return 0;
    }

    public class Java extends Language {
    }

    public abstract class Language {
    }
}
