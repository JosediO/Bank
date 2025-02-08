package demo.domain.web.dto.request;

import demo.domain.entity.Transfer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
	
	private String accessKey;
	private Integer receptorId;
	private Integer value;

	public Transfer toDomain() {
		return new Transfer(accessKey, receptorId, value);
	}
}
