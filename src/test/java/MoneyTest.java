import junitparams.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


@RunWith(JUnitParamsRunner.class)
public class MoneyTest {

    private Money money;
    public static final int VALID_AMOUNT = 10;
    public static final String VALID_CURRENCY = "USD";

    private static final Object[] getInvalidAmount(){
      return new Integer[][]{{-12345},{-5},{-7}};
    }

    private static final Object[] getMoney() {
      return new Object[] {
        new Object[] {10, "USD"},
        new Object[] {20, "EUR"}
      };
    }

    private static final Object[] getValidAmount(){
      return new Integer[][]{{10},{20},{30},{40},{50}};
    }

    @Before
    public void setUp(){
      money = new Money(VALID_AMOUNT, VALID_CURRENCY);
    }


    @Test
    @Parameters(method = "getMoney")
    public void constructorShouldSetAmountAndCurrency(int amount,
    String currency){
      Money money = new Money(amount, currency);

      assertEquals(amount, money.getAmount());
      assertEquals(currency, money.getCurrency());
    }

    @Test(expected = IllegalArgumentException.class)
    @Parameters(method = "getInvalidAmount")
    public void constructorShouldThrowIAEForInvalidAmount(int
    invalidAmount){
      new Money(invalidAmount, VALID_CURRENCY);
    }

    @Test
    @Parameters(method = "getValidAmount")
    public void shouldAllowToAddAmount(int validAmount){
      // Arrange on setUp()

      // Act
      money.addAmount(validAmount);

      //Assert
      assertEquals(validAmount + VALID_AMOUNT, money.getAmount());
    }
}
