package neo.decorator.example;

/**
 * @Classname: Client
 * @Description: 装饰模式：java io 体系的应用
 * 
 * @Author: Neo
 * @Date: 2019/9/7 21:38
 * @Version: 1.0
 */
public class Client {

    public static void main(String[] args) throws Exception{
        Component component = new ConcreteDecortor2(new ConcreteDecortor1(new ConcreteComponent()));
        component.doSomething();
    }
}
