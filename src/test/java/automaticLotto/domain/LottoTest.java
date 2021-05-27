package automaticLotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
	@Test
	@DisplayName("로또 번호는 6자리 숫자")
	public void lotto_numbers_are_consist_of_siz_numbers() {
		List<Integer> randomNumberList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

		assertThatThrownBy(() -> new Lotto(randomNumberList))
			.isInstanceOf(RuntimeException.class);
	}

	@Test
	@DisplayName("로또 번호는 중복되지 않음")
	public void lotto_numbers_are_not_duplicated() {
		List<Integer> randomNumberList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 5));

		assertThatThrownBy(() -> new Lotto(randomNumberList))
			.isInstanceOf(RuntimeException.class);
	}

	@Test
	@DisplayName("로또 번호는 오름차순 정렬됨")
	public void lotto_numbers_are_ascending() {
		List<Integer> ascendingRandomNumberList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		List<Integer> notAscendingRandomNumberList = new ArrayList<>(Arrays.asList(2, 1, 3, 4, 5, 6));
		Lotto acendingLotto = new Lotto(ascendingRandomNumberList);
		Lotto notAcendingLotto = new Lotto(notAscendingRandomNumberList);

		assertThat(acendingLotto.toString().equals(notAcendingLotto.toString())).isTrue();
	}

	@Test
	@DisplayName("매치되는 로또 번호의 동일한 숫자의 사이즈를 구함")
	public void get_size_of_matched_lotto() {
		List<Integer> randomNumberList1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
		Lotto lotto1 = new Lotto(randomNumberList1);

		assertThat(lotto1.match(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)))).isEqualTo(Ranking.RANKING_2);
	}

	@Test
	@DisplayName("보너스 번호는 로또 번호와 중복되지 않음")
	public void bonus_number_can_not_be_duplicated_with_the_six_numbers() {
		assertThatThrownBy(() -> new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)), 6))
			.isInstanceOf(RuntimeException.class);
	}

	@Test
	@DisplayName("로또 번호 5개만 맞으면 3위")
	public void five_numbers_matched_and_bonus_number_not_contained_then_ranking3() {
		Lotto lotto1 = new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);

		assertThat(lotto1.match(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 8)))).isEqualTo(Ranking.RANKING_3);
	}

	@Test
	@DisplayName("로또 번호 5개가 맞고 보너스 번호도 포함되면 랭킹 2위")
	public void five_numbers_matched_and_bonus_number_contained_then_ranking2() {
		Lotto lotto1 = new Lotto(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)), 7);

		assertThat(lotto1.match(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)))).isEqualTo(Ranking.RANKING_2);
	}
}
