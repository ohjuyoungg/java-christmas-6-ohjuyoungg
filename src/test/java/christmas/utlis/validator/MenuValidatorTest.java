package christmas.utlis.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import christmas.utils.validator.MenuValidator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MenuValidatorTest {

    @Test
    void userInput_메뉴검증_성공한다() {
        String validInput = "양송이수프-5,타파스-3,제로콜라-2";
        assertThat(MenuValidator.userInput(validInput)).isEqualTo(validInput);
    }


    @ParameterizedTest
    @MethodSource("validateMenuErrors_메뉴검증_실패한다")
    void validateMenuErrors_메뉴검증_false를_반환_성공한다(String input) {
        assertThat(MenuValidator.validateMenuErrors(input)).isFalse();
    }

    private static Stream<Arguments> validateMenuErrors_메뉴검증_실패한다() {
        return Stream.of(
            Arguments.of("타파스-5,제로콜라-3,해산물파스타-2,크리스마스파스타-20"),
            Arguments.of("치킨-5,피자-3,콜라-2"),
            Arguments.of("해산물파스타-2,크리스마스파스타-타파스"),
            Arguments.of(""),
            Arguments.of("//////////////"),
            Arguments.of("제로콜라-1"),
            Arguments.of("레드와인-10,제로콜라-10"),
            Arguments.of("샴페인-3"),
            Arguments.of("레드와인-5,제로콜라-5,샴페인-5"),
            Arguments.of("        ")
        );
    }

    @Test
    void validateMenuErrors_메뉴검증_성공한다() {
        String validInput = "타파스-10,제로콜라-1";
        assertThat(MenuValidator.validateMenuErrors(validInput)).isTrue();
    }

    @Test
    void validateOrderItem_메뉴검증_성공한다() {
        String validOrderItem = "타파스-5";
        assertThatCode(() -> MenuValidator.validateMenuErrors(validOrderItem)).doesNotThrowAnyException();
    }
}
