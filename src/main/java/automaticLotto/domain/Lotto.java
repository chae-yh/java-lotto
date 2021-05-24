package automaticLotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Lotto {
	public static final int LOTTO_PRICE = 1000;
	public static final int LOTTO_MINIMUM_NUMBER = 1;
	public static final int LOTTO_MAXIMUM_NUMBER = 45;
	public static final int LOTTO_LENGTH = 6;

	private Set<Integer> lotto;

	public Lotto(List<Integer> randomNumberList) {
		this.lotto = new HashSet<>(randomNumberList);
		validateRandomNumberListSize(this.lotto);
	}

	private void validateRandomNumberListSize(Set<Integer> lotto) {
		if (lotto.size() != LOTTO_LENGTH) {
			throw new RuntimeException("lotto numbers must be consist of six numbers");
		}
	}

	public int match(List<Integer> targetNumbers) {
		int matchedSize = 0;

		for (Integer number : targetNumbers) {
			matchedSize = getMatchedSize(matchedSize, number);
		}

		return matchedSize;
	}

	private int getMatchedSize(int matchedSize, Integer number) {
		if (lotto.contains(number)) {
			matchedSize++;
		}
		return matchedSize;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Lotto lotto1 = (Lotto)o;
		return Objects.equals(lotto, lotto1.lotto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lotto);
	}

	@Override
	public String toString() {
		return lotto + "";
	}
}
