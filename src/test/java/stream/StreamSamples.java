package stream;

import com.google.common.collect.ImmutableList;
import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.collection.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class StreamSamples {

  //reduce
  @Test
  public void testReduce() {
    int result = ImmutableList.of(1, 2, 3).stream().reduce((a, b) -> a + b).orElseGet(() -> 0);
    Assertions.assertThat(result).isEqualTo(6);
    int result2 = ImmutableList.of(1, 2, 3).stream().reduce(0, (init, v) -> init + v);
    Assertions.assertThat(result2).isEqualTo(6);


    //这个combiner似乎只要格式满足要求，怎么写都可以。。。(因为当前是串行的，不会用到第三个function)
    Tuple2<Integer, Integer> result4 = ImmutableList.of(1, 2, 3).stream()
      .reduce(new Tuple2<Integer, Integer>(0, 0), (init, v) ->
          Tuple.of(init._1 + 1, init._2 + v),
        (a, b) -> Tuple.of(a._1 + b._1 + 1, a._2 + b._2));
    Assertions.assertThat(result4._2 / (double) result4._1).isEqualTo(2D);

    //combiner用于并行的情况，所以以下测试combiner就必须严格与accumulator是compatible的了
    //reduce实现average
    double result5 = ImmutableList.of(1, 2, 3).parallelStream()
      .reduce(new Tuple2<>(0, 0),
        (init, v) -> Tuple.of(init._1 + 1, init._2 + v),
        (a, b) -> Tuple.of(a._1 + b._1, a._2 + b._2))
      .transform((v1, v2) -> v2 / (double) v1);
    Assertions.assertThat(result5).isEqualTo(2D);
  }

  @Test
  public void testCollect() {
    //collect is terminal operation
    //the result is mutable
    java.util.List<Integer> result = ImmutableList.of(1, 2, 3).parallelStream()
      .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    Assertions.assertThat(result).contains(1);
    Assertions.assertThat(result.size()).isEqualTo(3);

    java.util.List<Integer> result2 = ImmutableList.of(1, 2, 3).parallelStream()
      .collect(Collector.of(ArrayList::new, ArrayList::add, (left, right) -> {
        left.addAll(right);
        return left;
      }, Collector.Characteristics.UNORDERED));
    result2.forEach(System.out::println);
  }

  @Test
  public void testMinMax() {
    int min = ImmutableList.of(1, 2, 3).parallelStream().min((a, b) -> a - b).orElseGet(() -> 0);
    Assertions.assertThat(min).isEqualTo(1);
    int max = ImmutableList.of(1, 2, 3).parallelStream().max((a, b) -> a - b).orElseGet(() -> 0);
    Assertions.assertThat(max).isEqualTo(3);
  }

  @Test
  public void testMatch() {
    Assertions.assertThat(ImmutableList.of("abc", "def", "ghi").stream().anyMatch(a -> a.contains("d"))).isTrue();
    Assertions.assertThat(ImmutableList.of("abc", "def", "ghi").stream().allMatch(a -> a.contains("d"))).isFalse();
    Assertions.assertThat(ImmutableList.of("abc", "def", "ghi").stream().allMatch(a -> a.length() == 3)).isTrue();
    Assertions.assertThat(new ArrayList<String>().stream().allMatch(a -> a.contains("d"))).isTrue();
    Assertions.assertThat(ImmutableList.of("abc", "def", "ghi").stream().noneMatch(a -> a.contains("d"))).isFalse();
    Assertions.assertThat(ImmutableList.of("abc", "def", "ghi").stream().noneMatch(a -> a.contains("k"))).isTrue();
    Assertions.assertThat(ImmutableList.of("abc", "def", "ghi").stream().noneMatch(a -> a.length() == 3)).isFalse();
    Assertions.assertThat(new ArrayList<String>().stream().noneMatch(a -> a.contains("d"))).isTrue();
  }

  @Test
  public void testFind() {
    java.util.List<Integer> a = ImmutableList.of(1, 2, 3, 4);
    System.out.println(a.parallelStream().findFirst().orElse(0));
    System.out.println(a.parallelStream().findFirst().orElse(0));
    System.out.println(a.parallelStream().findFirst().orElse(0));
    System.out.println(a.parallelStream().findFirst().orElse(0));
    System.out.println(a.parallelStream().findAny().orElse(0));
    System.out.println(a.parallelStream().findAny().orElse(0));
    System.out.println(a.parallelStream().findAny().orElse(0));
    System.out.println(a.parallelStream().findAny().orElse(0));
  }

  private final Supplier<Integer> supplier1 = new Supplier<Integer>() {
    private final int seed = 1;
    private int count = 0;
    private int t;

    @Override
    public Integer get() {
      if (count == 0) {
        t = seed;
        count++;
        return t;
      } else {
        count++;
        return ++t;
      }
    }
  };

  @Test
  public void testGenerate() {
    Stream.generate(supplier1).forEach(System.out::println);
  }

  @Test
  public void testConstructor() {
    Stream.of(1, 2, 3).forEach(System.out::println);
    Stream.builder().add(1).add(2).add(3).build().forEach(System.out::println);
    Stream.Builder<Integer> builder = Stream.builder();
    builder.accept(1);
    builder.accept(2);
    builder.accept(3);
    builder.add(4).build().forEach(System.out::println);
  }

  @Test
  public void testIterate() {
    Stream.iterate(0, v -> v + 1).forEach(System.out::println);
  }

  @Test
  public void testConcat() {
    Stream.concat(ImmutableList.of(1, 2, 3).stream(), ImmutableList.of(1D, 2D, 3D).parallelStream())
      .forEach(System.out::println);
  }

  @Test
  public void testInfiniteConcat() {
    Stream.concat(Stream.generate(supplier1).parallel(), Stream.iterate(0, v -> v + 1)).forEach(System.out::println);
  }

}
