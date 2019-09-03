package neo.thrift.example;

import neo.thrift.generated.DataException;
import neo.thrift.generated.Person;
import neo.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @Classname: PsersonServiceImpl
 * @Description: TODO
 * @Author: Neo
 * @Date: 2019/9/3 10:40
 * @Version: 1.0
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByName(String username) throws DataException, TException {
        System.out.println("neo.thrift.example.PsersonServiceImpl.getPersonByName：" + username);

        Person person = new Person();
        person.setUsername(username);
        person.setAge(25);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("neo.thrift.example.PsersonServiceImpl.savePerson:");
        
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
