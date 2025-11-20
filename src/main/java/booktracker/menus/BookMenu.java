package booktracker.menus;

import booktracker.utils.MenuCommand;
import static booktracker.utils.KeyboardInput.getValidatedIntegerInput;

public class BookMenu {

    public static int showBookMenu() {
        MenuCommand.menu("""
                BOOK MENU
                1 - Update book
                2 - Delete book
                3 - Find book
                0 - Return
                """);
        return getValidatedIntegerInput();
    }

}