package com.learnJvm.override_overload;

/**
 * MethodMain
 *
 * @author xuejinhua
 * @date 2019/1/11 15:01
 */
public class MethodMain {

    /**
     * A a = new B();
     * 对于A和B来说，他们有不同的学术名词。A称之为静态类型，B称之为实际类型
     * 对于Language language = new MethodMain().new Java();
     * 也是如此：Language是静态类型，Java是实际类型
     * 重载方法的调用是根据静态类型去匹配
     * @param args
     */
    public static void main(String[] args) {
        MethodMain main = new MethodMain();
        Language language = new MethodMain().new Java();
        Language java = new MethodMain().new Java();

        main.sayHi(language);
        main.sayHi(java);
    }

    private void sayHi(Java java) {
        System.out.println("Hi Java");
    }

    private void sayHi(Language language) {
        System.out.println("Im Language");
    }

    public class Java extends Language {}
    public abstract class Language {}
}
