package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class Operator extends Thread {
  private final static Logger LOGGER = LogManager.getLogger(Operator.class);

  private final Queue<Client> queue;

  private CashBox cashBox;

  public Operator(CashBox cashBox) {
    queue = new LinkedList<>();
    this.cashBox = cashBox;
  }

  public void addToQueue(Client client) {
    synchronized (queue) {
      queue.add(client);
      queue.notify();
    }
  }

  public int getQueueSize() {
    return queue.size();
  }

  @Override
  public void run() {
    Client client;
    while (true) {
      synchronized (queue) {
        try {
          queue.wait();
        } catch (InterruptedException e) {
          //ignored
        }
        client = queue.remove();
      }
      LOGGER.info("Starting service the client");
      try {
        Thread.sleep(client.getSERVICE_TIME());
      } catch (InterruptedException e) {
        //ignored
      }
      if (client.operation == Client.action.WITHDRAW) {
        if (cashBox.getMoney() < client.getTransactionAmount()) {
          LOGGER.info("Denied operation due to insufficient amount of required amount of money");
        } else {
          cashBox.getMoney(client.getTransactionAmount());
          LOGGER.info("Client withdrew " + client.getTransactionAmount() + " from CashBox");
        }
      } else {

        cashBox.putMoney(client.getTransactionAmount());
        LOGGER.info("Client deposit " + client.getTransactionAmount() + " from CashBox");
      }
      LOGGER.info("Service time " + client.getSERVICE_TIME());
      LOGGER.info("Remaining money in CashBox " + cashBox.getMoney());
    }
  }


}
