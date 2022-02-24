package util;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.extern.slf4j.Slf4j;
import model.Consumer;
import model.DebatorAccount;
import model.Transaction;
import model.TransactionDetails;

@Slf4j
public class Converter {
	public static String convert(String xmlString) throws IOException {
		log.info(" : convert Method : Start");
		XmlMapper xmlMapper = new XmlMapper();	
		JsonNode jsonNode = xmlMapper.readTree(xmlString.getBytes());		
		JsonNode acqrrFinInitnNode = jsonNode.get("Document").get("AcqrrFinInitn");	
		JsonNode finInitnNode = acqrrFinInitnNode.get("FinInitn");	
		ObjectMapper objectMapper = new ObjectMapper();		
		Converter convertor=new Converter();	
		TransactionDetails txDetails=convertor.buildTransDetails(finInitnNode);		
		String jsonStr = objectMapper.writeValueAsString(txDetails);
		log.info(" : convert Method : End");
		return jsonStr;
	}
	
	private TransactionDetails buildTransDetails(JsonNode finInitnNode) {
		log.debug(" : buildTransDetails Method : Start");
		TransactionDetails txDetails = new TransactionDetails();
		JsonNode txNode = finInitnNode.get(Contants.XML_ATTR_Tx);
		txDetails.setTransaction(buildTransaction(finInitnNode));
		txDetails.setDebatorAccount(buildDebatorAccount(txNode));
		txDetails.setConsumer(buildConsumer(txNode));
		log.debug(" : buildTransDetails Method : End");
		return txDetails;
	}

	private Transaction buildTransaction(JsonNode finInitnNode) {
		log.debug(" : buildTransaction Method : Start");
		String txChanl = finInitnNode.get(Contants.XML_ATTR_Cntxt).get(Contants.XML_ATTR_TxCntxt).get(Contants.XML_ATTR_TxChanl).asText();
		JsonNode txNode = finInitnNode.get(Contants.XML_ATTR_Tx);
		String txType = txNode.get(Contants.XML_ATTR_TxTp).asText();
		String trTxDtTm = txNode.get(Contants.XML_ATTR_AccptrTxDtTm).asText();
		String accptrTxId = txNode.get(Contants.XML_ATTR_AccptrTxId).asText();
		String initrTxId = txNode.get(Contants.XML_ATTR_InitrTxId).asText();
		String txLifeCyclId = txNode.get(Contants.XML_ATTR_TxLifeCyclId).asText();
		Transaction tansaction = new Transaction();
		tansaction.setTransactionChannel(txChanl);
		tansaction.setTransactionDateTime(trTxDtTm);
		tansaction.setTransactionId(accptrTxId);
		tansaction.setTransactionLifeCycleId(txLifeCyclId);
		tansaction.setTransactionType(txType);
		log.debug(" : buildTransaction Method : End");
		return tansaction;
	}

	private Consumer buildConsumer(JsonNode txNode) {
		log.debug(" : buildConsumer Method : Start");
		Consumer consumer = new Consumer();
		log.debug(" : buildConsumer Method : End");
		return consumer;
	}

	private DebatorAccount buildDebatorAccount(JsonNode txNode) {
		log.debug(" : buildDebatorAccount Method : Start");
		DebatorAccount debatorAccount = new DebatorAccount();
		JsonNode acctFrNode = txNode.get(Contants.XML_ATTR_TxDtls).get(Contants.XML_ATTR_AcctFr);
		String accType = acctFrNode.get(Contants.XML_ATTR_SelctdAcctTp).asText();
		String accNo = acctFrNode.get(Contants.XML_ATTR_AcctIdr).get(Contants.XML_ATTR_Othr).asText();
		debatorAccount.setAccountNumber(accNo);
		debatorAccount.setAccountType(accType);
		log.debug(" : buildDebatorAccount Method : Start");
		return debatorAccount;
	}
}
