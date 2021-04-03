package entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
public class Bank {
  private final int N = 5;
  private final List<Operator> operators;
  private CashBox cashBox;


  public Bank() {
    operators = new ArrayList<>(5);
    Operator operator;
    cashBox = new CashBox(40000);
    for (int i = 0; i < N; i++) {
      operator = new Operator(cashBox);
      operator.start();
      operators.add(operator);
    }

  }

  public boolean checkMoneyAvailability(int money) {
    return cashBox.getMoney() < money;
  }

  public void chooseQueue(Client client) {

    int minSize = Integer.MAX_VALUE;
    int tempSize;
    for (Operator operator : operators) {
      if (operator.getQueueSize() == 0) {
        operator.addToQueue(client);
        break;
      } else {
        tempSize = operator.getQueueSize();
        minSize = Math.min(tempSize, minSize);
      }
    }
   Arrays.stream(operators.toArray()).min(Comparator.comparing(Integer::valueOf))
  }
}
