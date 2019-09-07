package neo.decorator.example;

/**
 * @Classname: CondreteCompent
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/7 21:32
 * @Version: 1.0
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("功能A");
    }
}
