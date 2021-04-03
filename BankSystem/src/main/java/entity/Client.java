package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 *
 */
public class Client {

  private final static Logger LOGGER = LogManager.getLogger(Client.class);
  private final int SERVICE_TIME;
  action operation;
  private int transactionAmount;

  public Client() {
    Random random = new Random();
    SERVICE_TIME = random.nextInt(20000);
    transactionAmount = random.nextInt(1000);
    operation = random.nextInt(2) == 1 ? action.WITHDRAW : action.DEPOSIT;
    LOGGER.info("Client appeared");
  }

  public int getTransactionAmount() {
    return transactionAmount;
  }


  public int getSERVICE_TIME() {
    return SERVICE_TIME;
  }

  enum action {
    WITHDRAW,
    DEPOSIT
  }
}
