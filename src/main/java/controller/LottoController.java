package controller;

import dto.LottoDto;
import dto.RankResultDto;
import model.LottoMachine;
import model.lottonumber.Lottos;
import model.generator.Generator;
import model.money.ManualLottoCount;
import model.money.PurchaseMoney;
import model.lottonumber.Lotto;
import model.rank.Rank;
import model.lottonumber.WinningNumbers;
import model.winningresult.WinningResult;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Generator generator;

    public LottoController(Generator generator, InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.generator = generator;
    }

    public void run() {
        LottoMachine lottoMachine;
        PurchaseMoney purchaseMoney = insertPurchaseMoney();
        Lottos lottos = purchaseLottos(purchaseMoney, generator);
        WinningNumbers winningNumbers = insertWinningInformation();
        showWinningResult(lottos, winningNumbers, purchaseMoney);
    }

    private void printLottoPurchaseProcess() {
        int purchaseMoney = inputView.inputMoney();
        int manualLottoCount = inputView.inputManualLottoCount();
        ManualLottoCount manualLottoCount1 = new ManualLottoCount(manualLottoCount);

    }

    private PurchaseMoney insertPurchaseMoney() {
        try {
            int purchaseMoney = inputView.inputMoney();
            return new PurchaseMoney(purchaseMoney);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertPurchaseMoney();
        }
    }

    private Lottos purchaseLottos(PurchaseMoney purchaseMoney, Generator generator) {
        Lottos lottos = new Lottos(purchaseMoney, generator);
        List<LottoDto> lottoDtos = convertLottosToDtos(lottos.getLottos());
        outputView.printLottos(lottoDtos);
        return lottos;
    }

    private List<LottoDto> convertLottosToDtos(List<Lotto> lottos) {
        return lottos.stream()
                .map(lotto -> new LottoDto(lotto.getLottoNumbers()))
                .collect(Collectors.toUnmodifiableList());
    }

    private WinningNumbers insertWinningInformation() {
        List<Integer> winningNumbers = insertWinningNumbers();
        int bonusNumber = insertBonusNumber();
        try {
            return new WinningNumbers(winningNumbers, bonusNumber);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertWinningInformation();
        }
    }

    private List<Integer> insertWinningNumbers() {
        try {
            return inputView.inputWinningNumbers();
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertWinningNumbers();
        }
    }

    private int insertBonusNumber() {
        try {
            return inputView.inputBonusNumber();
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
            return insertBonusNumber();
        }
    }

    private void showWinningResult(Lottos lottos, WinningNumbers winningNumbers, PurchaseMoney purchaseMoney) {
        WinningResult winningResult = lottos.makeWinningResult(winningNumbers);
        List<RankResultDto> rankResultDtos = convertWinningResultToDtos(winningResult.getWinningResult());

        outputView.printWinningResult(rankResultDtos);
        outputView.printRateOfReturn(winningResult.getRateOfReturn(purchaseMoney));
    }

    private List<RankResultDto> convertWinningResultToDtos(final Map<Rank, Integer> results) {
        return results.keySet().stream()
                .map(rank -> new RankResultDto(rank, results.get(rank)))
                .collect(Collectors.toUnmodifiableList());
    }
}
