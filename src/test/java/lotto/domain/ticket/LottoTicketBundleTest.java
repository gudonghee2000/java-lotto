package lotto.domain.ticket;

import lotto.domain.result.LottoMatchResult;
import lotto.domain.result.LottoMatchResultBundle;
import lotto.domain.result.rank.Rank;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketBundleTest {

    @DisplayName("winLottoTicket로 결과 번들 생성 테스트")
    @Test
    void createLottoResultBundle() {
        //given
        List<Integer> winNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        WinLottoTicket winLottoTicket = new WinLottoTicket(winNumbers, 7);

        Set<LottoBall> lottoBalls = winNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        LottoTicketBundle ticketBundle = new LottoTicketBundle(Arrays.asList(new LottoTicket(lottoBalls)));

        LottoMatchResultBundle expectedResultBundle = new LottoMatchResultBundle(Arrays.asList(new LottoMatchResult(Rank.FIRST)));

        //when
        LottoMatchResultBundle resultBundle = ticketBundle.createLottoMatchResultBundle(winLottoTicket);

        //then
        assertThat(resultBundle).isEqualTo(expectedResultBundle);
    }
}