package booktracker.menus;

import booktracker.utils.MenuCommand;
import static booktracker.utils.KeyboardInput.getValidatedIntegerInput;

public class AuthorStatsMenu {

    public static int showAuthorStatsMenu() {
        MenuCommand.menu("""
                AUTHOR STATS MENU
                1 - Most read author
                2 - Author with best rated books
                0 - Return
                """);
        return getValidatedIntegerInput();
    }

}