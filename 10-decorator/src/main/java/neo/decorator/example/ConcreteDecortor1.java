package neo.decorator.example;

/**
 * @Classname: ConcreteDecortor1
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/7 21:35
 * @Version: 1.0
 */
public class ConcreteDecortor1 extends Decorator {

    public ConcreteDecortor1(Component component) {
        super(component);
    }


    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }


    private void doAnotherThing() {
        System.out.println("功能B");
    }
}

