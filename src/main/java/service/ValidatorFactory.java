package service;

import constants.Entities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidatorFactory {
	

	private ValidatorFactory() {
		
	}
	
	public static BaseValidator getValidator(String entity) {
		Entities ent = Entities.valueOf(entity);
		
		switch (ent) {
		case HDFC:
			log.info("HDFC Bank Process");
			break;
		case ICICI:
			log.info("ICICI Bank Process");
			break;
		case SAMPLE_BANK:
			return new SampleBankValidator();
		default:
			break;
		}
		return null;
	}

}
