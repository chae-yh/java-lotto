package automaticLotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WinnerStatics {
	private static final int NOTHING = 0;

	private final Map<Integer, Integer> winnerCountByMatchedNumber;
	private final Map<Integer, Integer> rankingCondition;
	private final Lottos boughtLottos;
	private final List<Integer> winnerLotto;

	public WinnerStatics(Lottos boughtLottos, List<Integer> winnerLotto) {
		this.boughtLottos = boughtLottos;
		this.winnerLotto = winnerLotto;
		this.winnerCountByMatchedNumber = getWinnerCountByMatchedNumber();
		rankingCondition = new HashMap<>();

		initiateRankingCondition();
	}

	private void initiateRankingCondition() {
		rankingCondition.put(3, 5000);
		rankingCondition.put(4, 500000);
		rankingCondition.put(5, 1500000);
		rankingCondition.put(6, 2000000000);
	}

	private Map<Integer, Integer> getWinnerCountByMatchedNumber() {
		return boughtLottos.announce(winnerLotto);
	}

	public int presentCount(int matchedNumber) {
		if (!hasNumber(matchedNumber)) {
			return NOTHING;
		}

		return this.winnerCountByMatchedNumber.get(matchedNumber);
	}

	private boolean hasNumber(int matchedNumber) {
		return winnerCountByMatchedNumber.containsKey(matchedNumber);
	}

	public double getProfitRate() {
		double profit = 0.00;

		for (Integer matchedNumber : winnerCountByMatchedNumber.keySet()) {
			profit += winnerCountByMatchedNumber.get(matchedNumber) * rankingCondition.get(matchedNumber);
		}

		return profit / getTotalAmount();
	}

	private int getTotalAmount() {
		return boughtLottos.size() * Lotto.LOTTO_PRICE;
	}
}
