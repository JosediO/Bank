package demo.domain.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
	
	private String accessKey;
	private Integer receptorId;
	private Integer value;
}
