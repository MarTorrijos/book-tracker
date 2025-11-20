package booktracker.menus;

import booktracker.utils.MenuCommand;
import static booktracker.utils.KeyboardInput.getValidatedIntegerInput;

public class AuthorMenu {

    public static int showAuthorMenu() {
        MenuCommand.menu("""
                AUTHOR MENU
                1 - Update author
                2 - Save author
                3 - Find author
                0 - Return
                """);
        return getValidatedIntegerInput();
    }

}