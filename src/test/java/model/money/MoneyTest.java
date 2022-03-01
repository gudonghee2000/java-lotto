package model.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @ParameterizedTest
    @DisplayName("투입 금액이 천원보다 작으면 오류를 발생한다.")
    @ValueSource(ints = {-1, 0, 900})
    void generateMoney_underThanThousand(int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.");
    }

    @Test
    @DisplayName("투입 금액이 천원 단위가 아니면 오류를 발생한다.")
    void generateMoney_NotThousandUnit() {
        assertThatThrownBy(() -> new Money(14500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 투입 금액은 천원 단위의 금액으로 입력하세요.");
    }

    @Test
    @DisplayName("투입 금액만큼의 로또 구매개수를 반환한다.")
    void getPurchaseCount() {
        Money money = new Money(100000);

        assertThat(money.getPurchaseCount()).isEqualTo(100);
    }
}