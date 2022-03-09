package model;

import lombok.Data;

@Data
public class SupplementaryData {
	private String crossBorderTxnIndicator;
	private String partialAuthTermincalIndicator;
	private String acquirerId;
	private String settlementMode;
}
