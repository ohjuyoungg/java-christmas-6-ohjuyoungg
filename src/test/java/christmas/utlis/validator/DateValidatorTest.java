package christmas.utlis.validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import christmas.utils.validator.DateValidator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DateValidatorTest {

    @Test
    void userInput_날짜검증_성공한다() {
        String validInput = "25";
        assertThat(DateValidator.userInput(validInput)).isEqualTo(25);
    }

    @ParameterizedTest
    @MethodSource("validateDateErrors_날짜검증_실패한다")
    void validateDateErrors_날짜검증_false_반환_성공한다(String input) {
        assertThat(DateValidator.validateDateErrors(input)).isFalse();
    }

    private static Stream<Arguments> validateDateErrors_날짜검증_실패한다() {
        return Stream.of(
            Arguments.of("-1"),
            Arguments.of("0"),
            Arguments.of("99"),
            Arguments.of("100"),
            Arguments.of("1000"),
            Arguments.of(""),
            Arguments.of("pobi"),
            Arguments.of("n"),
            Arguments.of("우테코"),
            Arguments.of("감사합니다")
        );
    }

    @Test
    void validateDateErrors_날짜검증_성공() {
        String validInput = "25";
        assertThatCode(() -> DateValidator.validateDateErrors(validInput)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("validateDateErrors_날짜검증_성공한다")
    void validateDateErrors_날짜검증_true_반환_성공한다(String input) {
        assertThat(DateValidator.validateDateErrors(input)).isTrue();
    }

    private static Stream<Arguments> validateDateErrors_날짜검증_성공한다() {
        return Stream.of(
            Arguments.of("1"),
            Arguments.of("25"),
            Arguments.of("31")
        );
    }
}
