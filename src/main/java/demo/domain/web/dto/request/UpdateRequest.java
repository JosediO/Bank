package demo.domain.web.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest {
	
	private String accessKey;
	private Boolean active;
	private String name;

}
