package demo.domain.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositRequest {
	
	private Integer value;
	private String accessKey;

}
