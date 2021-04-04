package entity;

/**
 * Bank Cashbox.
 */
public class CashBox {

  private int money;

  public CashBox(int money) {
    this.money = money;
  }

  public int getMoney() {
    return money;
  }

  public synchronized void getMoney(int money) {
    this.money -= money;
  }

  public synchronized void putMoney(int money) {
    this.money += money;
  }

}
