package booktracker.menus;

import booktracker.utils.MenuCommand;
import static booktracker.utils.KeyboardInput.getValidatedIntegerInput;

public class MainMenu {

    public static int showMainMenu() {
        MenuCommand.menu("""
                BOOK TRACKER
                1 - Add book (insert)
                2 - Book menu (update, delete, find)
                3 - Author menu (insert, update, find)
                4 - Check book stats
                5 - Check author stats
                0 - Quit
                """);
        return getValidatedIntegerInput();
    }

}