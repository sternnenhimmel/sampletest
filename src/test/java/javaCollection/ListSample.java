package javaCollection;


import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.*;

public class ListSample {
  @Test
  public void testContais() {
    //1
    System.out.println(new ArrayList<>().contains(null));
    //2
    List<String> a = new ArrayList<>();
    a.add(null);
    System.out.println(a.contains(null));
    //3
    a = new ArrayList<>();
    a.add("abc");
    a.add("def");
    a.add("ghi");
    System.out.println(a.contains("a"));
  }

  @Test
  public void testIterator() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    Iterator<Integer> b = a.iterator();
    System.out.println(b.next());
    b.remove();
    b.forEachRemaining(System.out::println);
    a.forEach(System.out::println);
  }

  @Test
  public void testToArray() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    Integer[] b = a.toArray(new Integer[10]);
    for (Integer aB : b) {
      System.out.println(aB);
    }

    System.out.println(Arrays.toString(a.toArray()));
  }

  @Test
  public void testRemoveAdd() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    a.add(1);
    a.add(2);
    a.add(3);
    a.remove(((Integer) 1).intValue());
    System.out.println(a);

    List<Integer> b = ImmutableList.of(1, 2, 3, 4, 5, 1);
    System.out.println(b.containsAll(a));
    b.remove((Integer) 1);
    System.out.println(b);
  }

  @Test
  public void testAddAllRemoveAll() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    List<Integer> b = ImmutableList.of(1, 2, 3, 4, 5, 1);
    a.addAll(1, b);
    System.out.println(a);
    a.removeAll(b);
    System.out.println(a);
  }

  @Test
  public void testRetainAll() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    List<Integer> b = ImmutableList.of(1, 2, 3, 4, 5, 1);
    a.addAll(1, b);
    System.out.println(a);
    a.retainAll(ImmutableList.of(1, 2));
    System.out.println(a);
  }

  @Test
  public void testReplaceAll() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    a.replaceAll(v -> v + 1);
    System.out.println(a);
  }

  @Test
  public void testEquals() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    System.out.println(a.equals(ImmutableList.of(1, 2, 3)));
  }

  @Test
  public void testIndexOf() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    a.add(null);
    a.add(4);
    System.out.println(a.indexOf(null));
    System.out.println(a.lastIndexOf(null));
  }

  @Test
  public void testListIterator() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    a.add(null);
    a.add(4);
    ListIterator<Integer> b = a.listIterator(1);
    System.out.println(b.hasPrevious());
    System.out.println(b.previous());
    System.out.println(b.hasPrevious());
    System.out.println(b.hasNext());
    System.out.println(b.nextIndex());
    System.out.println(b.next());
    b.add(10);
    System.out.println(b.previous());
    b.remove();
    b.forEachRemaining(System.out::println);

    System.out.println(b.hasNext());
    b.previous();
    b.previous();
    System.out.println(b.hasNext());
    b.remove();
    System.out.println(b.hasNext());
    System.out.println(a);
    b.add(10);
    b.add(9);
    System.out.println(a);
  }

  @Test
  public void testSubList() {
    List<Integer> a = new ArrayList<>();
    a.add(1);
    a.add(2);
    a.add(3);
    a.add(null);
    a.add(4);
    List<Integer> b = a.subList(1, 2);
    System.out.println(b);
    b.clear();
    System.out.println(a);
    b = a.subList(1, 2);
    System.out.println(b);
    a.clear();
    System.out.println(b.isEmpty());
  }
}
