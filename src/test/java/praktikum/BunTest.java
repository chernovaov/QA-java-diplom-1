package praktikum;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BunTest {
    private Bun bun;

    @Before
    public void startUp() {
       bun = new Bun("Флюоресцентная булка R2-D3", 988);
    }

    @Test
    public void getNameTest() {
        String expectedName = "Флюоресцентная булка R2-D3";
        String actualName = bun.getName();
        assertEquals("Неверное название булочки",expectedName, actualName);
    }

    @Test
    public void getPriceTest() {
        int expectedPrice = 988;
        float actualPrice = bun.getPrice();
        assertEquals("Неверная цена булочки",expectedPrice, actualPrice, 0);
    }

}