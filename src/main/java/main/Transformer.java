package main;

import service.FileService;
import service.ValidatorFactory;
import util.Converter;

public class Transformer {

	public static void main(String[] args) {

		String xmlfilePath = "", entity = "";
		transformXml(xmlfilePath, entity);

	}

	private static String transformXml(String xmlfilePath, String entity) {
		FileService fileService = new FileService();
		Converter converter = new Converter();

		String xmlFileContent = fileService.readXmlFilFromPath(xmlfilePath);
		String jsonString = converter.convert(xmlFileContent);
		boolean validate = ValidatorFactory.getValidator(entity).validate();
		if (validate) {
			return jsonString;
		}
		return null;

	}

}
