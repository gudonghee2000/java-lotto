package lotto.domain.lotto;

import lotto.exception.DuplicateLottoNumberException;
import lotto.exception.InvalidLottoNumberCountException;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    protected final List<LottoNumber> lottoNumbers;

    public Lotto(final List<Integer> numbers) {
        validate(numbers);
        lottoNumbers = numberToLottoNumbers(numbers);
    }

    public int match(final Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::isContainsNumber)
                .count();
    }

    private List<LottoNumber> numberToLottoNumbers(List<Integer> numbers) {
        return Collections.unmodifiableList(numbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .collect(Collectors.toList()));
    }

    private void validate(final List<Integer> values) {
        if (values.size() != LOTTO_SIZE) {
            throw new InvalidLottoNumberCountException();
        }

        if (new HashSet<>(values).size() != LOTTO_SIZE) {
            throw new DuplicateLottoNumberException();
        }
    }

    public boolean isContainsNumber(final LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers.stream()
            .mapToInt(LottoNumber::getLottoNumber)
            .boxed()
            .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}