package javaCollection;


import org.junit.Test;

import java.util.*;

public class MapSample {
  @Test
  public void testMutableKey() {
    List<Integer> a = new ArrayList<>();
    List<Integer> b = new LinkedList<>();
    a.add(1);
    b.add(2);
    Map<List<Integer>, Integer> m = new HashMap<>();
    m.put(a, 5);
    m.put(b, 4);
    System.out.println(m.get(a));
    System.out.println(m.get(b));
    a.set(0, 3);
    System.out.println(m.get(a));
    System.out.println(m.get(b));


    Integer c = 1;
    Integer d = 2;
    Map<Integer, Integer> m2 = new TreeMap<>();
    m2.put(c, 5);
    m2.put(d, 4);
    System.out.println(m2.get(c));
    System.out.println(m2.get(d));
    c = 3;
    System.out.println(m2.get(c));
    System.out.println(m2.get(d));
  }

  @Test
  public void testMutableValue() {
    List<Integer> a = new ArrayList<>();
    List<Integer> b = new LinkedList<>();
    a.add(1);
    b.add(2);
    Map<Integer, List<Integer>> m = new HashMap<>();
    m.put(1, a);
    m.put(2, b);
    System.out.println(m.get(1));
    System.out.println(m.get(2));
    a.set(0, 3);
    System.out.println(m.get(1));
    System.out.println(m.get(2));
  }

  @Test
  public void testContainsValue() {
    Integer c = 1;
    Integer d = 2;
    Map<Integer, Integer> m2 = new TreeMap<>();
    m2.put(c, 5);
    m2.put(d, 4);
    System.out.println(m2.containsValue(1));
    System.out.println(m2.containsValue(5));
  }

  @Test
  public void testKeySet() {
    Map<Integer, Integer> a = new HashMap<>();
    a.put(1, 1);
    a.put(2, 2);
    a.put(3, 3);
    Set<Integer> b;
    System.out.println(b = a.keySet());
    System.out.println(b.remove(0));
    System.out.println(b.remove(1));
    System.out.println(b);
    System.out.println(a);
    System.out.println(a.put(4, 4));
    System.out.println(a.put(4, 5));
    System.out.println(b);
    System.out.println(a);
  }

  @Test
  public void testValues() {
    Map<Integer, Integer> a = new HashMap<>();
    a.put(1, 1);
    a.put(2, 2);
    a.put(3, 3);
    a.put(4, 3);
    Collection<Integer> values;
    System.out.println(values = a.values());
    System.out.println(values.remove(3));
    System.out.println(values);
    System.out.println(a);
    System.out.println(values.remove(3));
    System.out.println(values);
    System.out.println(a);
  }

  @Test
  public void testEntrySet() {
    Map<Integer, Integer> a = new HashMap<>();
    a.put(1, 1);
    a.put(2, 2);
    a.put(3, 3);
    Set<Map.Entry<Integer, Integer>> b = a.entrySet();
    Iterator<Map.Entry<Integer, Integer>> it = b.iterator();
    it.next();
    it.remove();
    System.out.println(a);
    System.out.println(b);
    Map.Entry<Integer, Integer> c = it.next();
    System.out.println(c.getKey());
    System.out.println(c.setValue(3));
    System.out.println(a);
    b.remove(c);
    System.out.println(a);
    System.out.println(b);
  }

  @Test
  public void testGetOrDefault() {
    Map<Integer, Integer> a = new HashMap<>();
    a.put(1, 1);
    a.put(2, 2);
    a.put(3, 3);
    System.out.println(a.getOrDefault(4, 5));
  }

  @Test
  public void testForEach() {
    Map<Integer, Integer> a = new HashMap<>();
    a.put(1, 1);
    a.put(2, 2);
    a.put(3, 3);
    a.forEach((k, v) -> System.out.println("" + k + "," + v));
  }

  @Test
  public void testReplaceAll() {
    Map<Integer, Integer> a = new HashMap<>();
    a.put(1, 1);
    a.put(2, 2);
    a.put(3, 3);
    a.replaceAll((k, v) -> v + 1);
    System.out.println(a);
  }

  @Test
  public void testComputeIfAbsent() {
    Map<Integer, Integer> a = new HashMap<>();
    a.put(1, 1);
    a.put(2, 2);
    a.put(3, 3);
    a.put(null, 4);
    a.computeIfAbsent(null, k -> 0);
    a.computeIfAbsent(4, k -> 0);
    System.out.println(a);
  }

  @Test
  public void testHashMapOrder() {
    Map<Integer, Integer> hash = new HashMap<>();
    hash.put(1, 1);
    hash.put(2, 2);
    hash.put(5, 5);
    hash.put(4, 4);
    hash.put(3, 3);
    System.out.println(hash);
    System.out.println(hash);
    System.out.println(hash);
    System.out.println(hash);
    System.out.println(hash);
  }
}
