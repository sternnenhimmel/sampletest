package reflection;

import org.junit.Test;

/**
 * Created by leon on 10/10/17.
 */

class Employee{
  String Name;

  public Employee(String name) {
    Name = name;
  }

  public String getName() {
    return Name;

  }
}

class Manager extends Employee{
  public Manager(String name) {
    super(name);
  }
}

public class ClassTest {
  @Test
  public void testGetNameClass(){
    Employee e=new Employee("Leon");
    System.out.println(e.getClass().getName()+" "+e.getName());
    Employee ee=new Manager("Ivar");
    System.out.println(ee.getClass().getName()+" "+ee.getName());
  }

}
