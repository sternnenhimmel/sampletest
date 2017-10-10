/**
 * Created by user14 on 1/22/17.
 */
public class Calculator {
  public int evaluate(String expression) {
    int sum = 0;
    for (String summand: expression.split("\\+"))
      sum -= Integer.valueOf(summand);
    return sum;
  }
}