package booktracker.validators.implementations.review;

import booktracker.exceptions.FieldValidationException;
import booktracker.validators.FieldValidator;

import java.io.IOException;
import java.io.InputStream;
import java.time.Year;
import java.util.Properties;

import static booktracker.validators.messages.ReviewErrorMessages.INCORRECT_YEAR_READ_IN;

public class ReadInValidator implements FieldValidator<Integer> {

    private final int CURRENT_YEAR = Year.now().getValue();
    private final int USER_BIRTH_YEAR;

    public ReadInValidator() {
        this.USER_BIRTH_YEAR = getUserBirthYearFromConfig();
    }

    @Override
    public void validate(Integer year) {
        if (year != null) {
            if (year < USER_BIRTH_YEAR || year > CURRENT_YEAR) {
                throw new FieldValidationException(INCORRECT_YEAR_READ_IN);
            }
        }
    }

    private static int getUserBirthYearFromConfig() {
        Properties properties = new Properties();

        try (InputStream input = ReadInValidator.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new FieldValidationException("Config file 'config.properties' not found in the classpath.");
            }

            properties.load(input);
            String userBirthYearStr = properties.getProperty("user.birth.year");

            if (userBirthYearStr == null || userBirthYearStr.trim().isEmpty()) {
                throw new FieldValidationException("user.birth.year property is missing or empty.");
            }

            try {
                return Integer.parseInt(userBirthYearStr);
            } catch (NumberFormatException e) {
                throw new FieldValidationException("Invalid number format for user.birth.year.");
            }

        } catch (IOException e) {
            throw new FieldValidationException("Error reading config file: " + e.getMessage());
        }
    }

}