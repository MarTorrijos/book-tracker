package booktracker.menus;

import booktracker.utils.MenuCommand;
import static booktracker.utils.KeyboardInput.getValidatedIntegerInput;

public class BookStatsMenu {

    public static int showBookStatsMenu() {
        MenuCommand.menu("""
                BOOK STATS MENU
                1 - Books read this year
                2 - Books read in a given year
                3 - Total books read
                0 - Return
                """);
        return getValidatedIntegerInput();
    }

}