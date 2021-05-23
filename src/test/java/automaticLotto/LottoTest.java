package automaticLotto;

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
}
