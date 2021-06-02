import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantTest {
    Restaurant restaurant;
    //REFACTORED CODE
    //@BeforeEach
    public Restaurant createRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        // Arrange
        restaurant = createRestaurant();
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("13:00:00"));
        // Act
        boolean isRestaurantOpen = spiedRestaurant.isRestaurantOpen();
        // Assert
        assertEquals(true, isRestaurantOpen);

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE
        // Arrange
        restaurant = createRestaurant();
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("09:00:00"));
        // Act
        boolean isRestaurantOpen = spiedRestaurant.isRestaurantOpen();
        // Assert
        assertEquals(false, isRestaurantOpen);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {
        restaurant = createRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant = createRestaurant();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant = createRestaurant();
        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void get_total_cost_should_return_total_price_of_all_the_items_added_in_the_list(){
        // Arrange
        restaurant = createRestaurant();
        restaurant.addToMenu("Tomato soup",119);
        restaurant.addToMenu("Pizza", 97);
        restaurant.addToMenu("Tofu", 302);
        restaurant.addToMenu("Pancakes", 500);
        restaurant.addToMenu("Vada Pav", 101);
        int expectedTotal = 119+97+302+500+101;
        List<String> menuItemsToBeTotaled = new ArrayList<String>();
        menuItemsToBeTotaled.add("Tomato soup");
        menuItemsToBeTotaled.add("Pizza");
        menuItemsToBeTotaled.add("Tofu");
        menuItemsToBeTotaled.add("Pancakes");
        menuItemsToBeTotaled.add("Vada Pav");
        // Act
        int totalCost = restaurant.calculateTotalCostOfOrder(menuItemsToBeTotaled);
        // Assert
        assertEquals(expectedTotal, totalCost);
    }

}