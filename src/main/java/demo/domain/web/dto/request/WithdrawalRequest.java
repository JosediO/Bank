package demo.domain.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawalRequest {
	
	private String accessKey;
	private Integer value;

}
