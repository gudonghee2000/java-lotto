package model.lottotickets;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.lottonumbergenerator.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {
    private static final List<Integer> dummyLottoNumber = Arrays.asList(1, 2, 3, 4, 5, 6);

    @Test
    @DisplayName("로또 티켓이 정상적으로 생성됐는지 검사한다.")
    void createLottoTicketsTest() {
        final int purchaseCount = 3;
        final Generator generator = () -> new ArrayList<>(dummyLottoNumber);
        final LottoTickets lottoTickets = new LottoTickets(purchaseCount, generator);

        lottoTickets.tickets()
                .forEach(lottoTicket -> assertThat(lottoTicket).isEqualTo(dummyLottoNumber));
    }
}