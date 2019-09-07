package neo.decorator.example;

/**
 * @Classname: Decorator
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/7 21:29
 * @Version: 1.0
 */
public class Decorator implements Component{

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        component.doSomething();
    }
}
