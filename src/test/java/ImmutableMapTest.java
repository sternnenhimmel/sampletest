import com.google.common.collect.ImmutableList;

import javafx.util.Pair;
import javaslang.Tuple;
import org.junit.Test;

/**
 * Created by leon on 3/20/17.
 */
public class ImmutableMapTest {
  @Test
  public void testImmutableMap(){
    System.out.println(ImmutableList.of(Tuple.of("1","me"),Tuple.of("1","me")));
  }
}
