import entity.Bank;
import generator.ClientGenerator;

/**
 *
 */
public class BankApp {

  public static void main(String[] args) {
    Bank bank = new Bank();
    ClientGenerator clientGenerator = new ClientGenerator(bank);
    clientGenerator.start();
  }
}
