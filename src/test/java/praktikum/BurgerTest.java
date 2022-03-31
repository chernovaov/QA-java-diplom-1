package praktikum;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;
    Database db = new Database();
    final int allIngredientsCount = db.availableIngredients().size();

    @Before
    public void startUp() {
        burger = new Burger();
    }

    @Mock
    private Bun mockBun;

    @Test
    public void addIngredientTest(){
        for(int i = 0; i < allIngredientsCount; i++) {
            burger.addIngredient(db.availableIngredients().get(i));
        }
        int actualCount = burger.ingredients.size();
        assertEquals("Количество ингредиентов не совпадает",allIngredientsCount, actualCount);

    }

    @Test
    public void removeIngredientTest(){
        for(int i = 0; i < allIngredientsCount; i++) {
            burger.addIngredient(db.availableIngredients().get(i));
        }
        burger.removeIngredient(allIngredientsCount-1);
        int actualCount = burger.ingredients.size();
        assertEquals("Ингредиент не удалился",allIngredientsCount-1, actualCount);
    }

    @Test
    public void moveIngredientTest(){
        for(int i = 0; i < allIngredientsCount; i++) {
            burger.addIngredient(db.availableIngredients().get(i));
        }
        Ingredient lastIngredient = burger.ingredients.get(allIngredientsCount-1);
        burger.moveIngredient(allIngredientsCount-1, 0);
        assertEquals("Ингредиент не перемещен", burger.ingredients.get(0), lastIngredient);
    }

    @Test
    public void getPriceTest(){
        burger.setBuns(mockBun);
        when(mockBun.getPrice()).thenReturn(200f);
        burger.addIngredient(db.availableIngredients().get(1));
        float expectedPrice = 2*200+db.availableIngredients().get(1).getPrice();
        float actualPrice = burger.getPrice();
        assertEquals("Цена бургера рассчитана неверно",expectedPrice, actualPrice, 0);
    }

    @Test
    public void getReceiptTest(){
        burger.setBuns(mockBun);
        burger.addIngredient(db.availableIngredients().get(0));
        String oneIngredient = db.availableIngredients().get(0).getName();
        when(mockBun.getName()).thenReturn("Булочка");
        assertTrue("Рецепт содержит неверные данные",burger.getReceipt().contains("Булочка")
                &&burger.getReceipt().contains(oneIngredient));

    }
}