package javaCollection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by leon on 6/2/17.
 */
public class SetSample {
  @Test
  public void testAdd() {
    Set<Integer> a = new HashSet<>();
    System.out.println(a.add(1));
    System.out.println(a.add(2));
    System.out.println(a.add(3));
    System.out.println(a.add(1));
    System.out.println(a);
  }

  @Test
  public void testRemove() {
    Set<Integer> a = new HashSet<>();
    System.out.println(a.add(1));
    System.out.println(a.add(2));
    System.out.println(a.add(3));
    System.out.println(a.remove((Long) 1L));
    System.out.println(a.remove((Integer) 1));
    System.out.println(a.remove(2));
    System.out.println(a);
  }

  @Test
  public void testContainsAll() {
    Set<Integer> a = new HashSet<>();
    System.out.println(a.add(1));
    System.out.println(a.add(2));
    System.out.println(a.add(3));
    System.out.println(a.add(null));
    System.out.println(a.add(4));
    System.out.println(a.contains(null));
    System.out.println(a.containsAll(ImmutableList.of(1, 2, 3, 1, 2, 3)));
    List<Integer> b = new ArrayList<>();
    b.add(1);
    b.add(2);
    b.add(3);
    b.add(null);
    b.add(1);
    b.add(null);
    System.out.println(a.containsAll(b));
    System.out.println(ImmutableSet.of(1, 2, 3).contains(null));
    System.out.println(ImmutableSet.of(1, 2, 3).containsAll(b));
    System.out.println(a.addAll(b));
    System.out.println(a);
    System.out.println(a.retainAll(b));
    System.out.println(a);
  }

  @Test
  public void testEquals(){
    Set<Integer>a=new HashSet<>();
    System.out.println(a.add(1));
    System.out.println(a.add(2));
    System.out.println(a.add(3));
    System.out.println(a.equals(ImmutableSet.of(2, 1, 3)));
  }
}
