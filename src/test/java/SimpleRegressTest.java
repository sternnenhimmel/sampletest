import org.apache.commons.math.stat.regression.SimpleRegression;
import org.junit.Test;

public class SimpleRegressTest {
  @Test
  public void testSimpleRegress(){
    SimpleRegression regression = new SimpleRegression();

    regression.addData(1d,2d);

    regression.addData(3d,3d);

    regression.addData(3d,3d);

    System.out.println(regression.getIntercept());

    System.out.println(regression.getSlope());

    System.out.println(regression.getSlopeStdErr());

    System.out.println(regression.predict(1.5d));
  }

}
