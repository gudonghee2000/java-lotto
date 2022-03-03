package model.lottonumber;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Map;
import model.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottosTest {

    @Test
    @DisplayName("자동으로 생성할 로또 개수 만큼의 로또를 생성하는지 확인한다.")
    void generateLottos_Test() {
        final List<Lotto> manualLottos = new ArrayList<>();
        final int autoLottoCount = 100;

        final Lottos lottos = new Lottos(manualLottos, autoLottoCount);
        assertThat(lottos.getLottos().size()).isEqualTo(100);
    }

    @Test
    @DisplayName("번호 일치 여부에 따라 당첨결과를 만드는지 확인한다.")
    void makeWinningResult() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;
        WinningNumbers winningNumbers = new WinningNumbers(numbers, bonusNumber);

        final List<Lotto> manualLottos = new ArrayList<>();
        manualLottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        manualLottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        final int autoLottoCount = 0;

        final Lottos lottos = new Lottos(manualLottos, autoLottoCount);
        Map<Rank, Integer> result = lottos.makeWinningResult(winningNumbers).getWinningResult();
        assertThat(result.get(Rank.FIRST)).isEqualTo(2);
        assertThat(result.get(Rank.SECOND)).isEqualTo(0);
        assertThat(result.get(Rank.THIRD)).isEqualTo(0);
        assertThat(result.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(result.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(result.get(Rank.NONE)).isEqualTo(0);

    }
}