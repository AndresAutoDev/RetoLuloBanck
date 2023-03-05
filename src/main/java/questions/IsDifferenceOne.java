package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class IsDifferenceOne implements Question<Boolean> {
    private final int firstValue;
    private final int secondValue;

    public IsDifferenceOne(int firstValue, int secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static IsDifferenceOne of(int firstValue, int secondValue) {
        return new IsDifferenceOne(firstValue, secondValue);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        int difference = Math.abs(firstValue - secondValue);
        return difference == 1;
    }
}