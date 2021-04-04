package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents bank model.
 */
public class Bank {
  //Amount of operators.
  private final int N = 5;
  private final List<Operator> operators;
  private final CashBox cashBox;


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



  /**
   * Chooses queue with min size.
   *
   * @param client the client to set to queue
   */
  public void chooseQueue(Client client) {

    int minSize = Integer.MAX_VALUE;
    int tempSize;
    for (Operator operator : operators) {
      if (operator.getQueueSize() == 0) {
        operator.addToQueue(client);
        return;
      } else {
        tempSize = operator.getQueueSize();
        minSize = Math.min(tempSize, minSize);
      }
    }
    int finalMinSize = minSize;
    operators.stream().filter(x -> x.getQueueSize() == finalMinSize).findFirst().get().addToQueue(client);
  }
}
