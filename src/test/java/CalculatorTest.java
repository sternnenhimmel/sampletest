import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import javaslang.collection.*;
import javaslang.collection.HashMap;
import javaslang.collection.List;
import javaslang.collection.Map;
import javaslang.collection.Stream;
import javaslang.control.Option;
import javaslang.jackson.datatype.JavaslangModule;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
  private static final org.slf4j.Logger log = LoggerFactory.getLogger(CalculatorTest.class);
  Seq<Integer> items;
  Map<String, String> items2;

  @Before
  public void setUp() {
    //prepare testdata
    items = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    items2 = HashMap.of("a", "a", "b", "b", "c", "c");
  }


  @After
  public void tearDown() {
    //clean up
  }

  @Test
  public void evaluatesExpression() {
    Calculator calculator = new Calculator();
    int sum = calculator.evaluate("1+2+3");
    assertEquals(6, sum);
  }

  @Test
  public void ceilTest() {
    System.out.println(((int) Math.ceil(1.52D)));
  }

  @Test
  public void testItems() {
    System.out.println(items.count(t -> t > 5));
  }

  @Test
  public void javaslangMapTest() {
    System.out.println(items2.remove("a").toJavaMap());
    System.out.println(items2.put("a", "a").toJavaMap());
  }


  @Test
  public void testSerialize() throws JsonProcessingException {
    String json = new ObjectMapper().registerModule(new JavaslangModule()).writer().writeValueAsString(List.of(List.of(1)));
    System.out.println(json);
  }

  @Test
  public void testNull() {
    System.out.println(new Integer(null));
  }

  @Test
  public void testLogNull() {
    findRvnCstForEachItem(1, 1, null, null, 1, 1);
  }


  private void findRvnCstForEachItem(int siteId, int hospitalId, LocalDate endDate, Integer dept, int month, int type) {
    log.error("siteId:{}, hospitalId:{}, endDate:{}, dept:{}, month:{}, type:{}", siteId, hospitalId, endDate, dept, month, type);
  }

  @Test
  public void testString() {
    String tmp = "54台设备";
    String tmp2 = "2个（54台设备）";
    System.out.println(Ints.tryParse(tmp.substring(0, tmp.indexOf("台设备"))));
    System.out.println(Ints.tryParse(tmp2.substring(tmp2.indexOf("（") + 1, tmp2.indexOf("台设备"))));
    System.out.println(tmp2.indexOf("你好"));
  }

  @Test
  public void testLongs() {
    System.out.println(Doubles.tryParse("2.141234E8"));
  }

  @Test
  public void testCount() {
    Double a = 1D;
    Double b = null;
    Double c = 2D;
    System.out.println(List.of(a, b, c).count(v -> Option.of(v).getOrElse(-1D) > 1D));
  }

  @Test
  public void testUni() {
    System.out.println("\uD801\uDC01");
    System.out.println("\u2122");
  }

  @Test
  public void testInfinit() {
    System.out.println(1D / 0D);
    System.out.println(1D / -0D);
    System.out.println(1D / 0D > 1D / -0D);
    System.out.println(1D / 0D > 10e4);
    System.out.println(1D / 0D - 10);
    System.out.println(ObjectSizeCalculator.getObjectSize(true));
  }

  @Test
  public void testConversion() {
    System.out.println(Integer.MAX_VALUE);
    long a = 3147483647L;
    System.out.println((int) a);
    float b = 3147483647.1F;
    System.out.println((int) b);
    System.out.println(Float.MAX_VALUE);
    double c = 3.4e-90D;
    System.out.println((float) c);
    double d = 3.4e39;
    System.out.println((float) d);
    System.out.println(3 > 'a');
    System.out.println((int) 'a');
  }

  @Test
  public void testTryParse() {
    System.out.println(Doubles.tryParse(""));
  }

  private void myPredicted(Predicate<Integer> predicate) {
    System.out.println(predicate.test(1) ? 1 : 2);
  }

  @Test
  public void testPredicted() {
    Integer a = null;
    Integer b = null;
    myPredicted(v -> Option.of(a).map(sub -> sub.equals(1)).getOrElse(() -> b.equals(1)));
  }

  @Test
  public void testStream() {
    Stream<Integer> tmp = Stream.of(1, 2, 3);
    tmp.forEach(System.out::println);
    tmp.forEach(System.out::println);
  }

  @Test
  public void testStreamReuse() {
    java.util.List<Integer> a = ImmutableList.of(1, 2, 3, 4, 5);
    java.util.stream.Stream<Integer> b = a.stream();
    b.forEach(System.out::println);
    b.forEach(System.out::println);
  }

  @Test
  public void testStreamReuse2() {
    java.util.List<Integer> a = ImmutableList.of(1, 2, 3, 4, 5);
    java.util.stream.Stream<Integer> b = a.stream();
    System.out.println(b.count());
    b.forEach(System.out::println);
  }

  @Test
  public void testNullStream() {
    java.util.List<String> a = ImmutableList.of("1", "2", "3", "4");
    java.util.List<String> b = ImmutableList.of("a");
    java.util.List<String> c = ImmutableList.of("5");
    ImmutableList.of(a, b, c).stream()
      .flatMap(v -> v.stream().map(Ints::tryParse).filter(sub -> Option.of(sub).isDefined()))
      .forEach(System.out::println);
  }

  @Test
  public void testPeek() {
    java.util.stream.Stream.of("one", "two", "three", "four")
      .filter(e -> e.length() > 3)
      .peek(e -> System.out.println("Filtered value: " + e))
      .map(String::toUpperCase)
      .peek(e -> System.out.println("Mapped value: " + e))
      .collect(Collectors.toList());
  }

  @Test
  public void testForEachOrdered() {
    java.util.stream.Stream.of("AAA", "BBB", "CCC").parallel().forEach(s -> System.out.println("Output:" + s));
    java.util.stream.Stream.of("AAA", "BBB", "CCC").parallel().forEachOrdered(s -> System.out.println("Output:" + s));
  }

  class Person {
    int gender;

    public int getGender() {
      return gender;
    }

    public Person(int gender) {
      this.gender = gender;
    }
  }

  @Test
  public void testJavaArray() {
    ArrayList<Person> people = new java.util.ArrayList<Person>();
    people.add(new Person(1));
    people.add(new Person(2));
    people.add(new Person(3));
    Person[] men = people.stream()
      .filter(p -> p.getGender() == 1)
      .toArray(Person[]::new);
    System.out.println(men[0].getGender());
  }
}