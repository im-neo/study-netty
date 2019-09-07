package neo.decorator.example;

/**
 * @Classname: ConcreteDecortor2
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/7 21:37
 * @Version: 1.0
 */
public class ConcreteDecortor2 extends Decorator {

    public ConcreteDecortor2(Component component) {
        super(component);
    }


    @Override
    public void doSomething() {
        super.doSomething();
        this.doAnotherThing();
    }
    
    
    
    private void doAnotherThing(){
        System.out.println("功能C");
    }
}
