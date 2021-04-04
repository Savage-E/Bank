package generator;

import entity.Bank;
import entity.Client;
import entity.Operator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.LongFunction;

/**
 *
 */
public class ClientGenerator extends Thread {
  private final static Logger LOGGER = LogManager.getLogger(ClientGenerator.class);
  private final int CLIENTS_PER_MINUTE = 8;


  private final Bank bank;
  private Client client;

  public ClientGenerator(Bank bank) {
    this.bank = bank;
  }

  @Override
  public void run() {
    while (true) {
      try {
        Thread.sleep((60 / CLIENTS_PER_MINUTE) * 1000);
      } catch (InterruptedException e) {
        LOGGER.error(e);
      }
      client = new Client();
      bank.chooseQueue(client);
    }

  }
}
