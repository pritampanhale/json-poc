package util;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.extern.slf4j.Slf4j;
import model.AdditionalAmount;
import model.Address;
import model.Amounts;
import model.BillingAmount;
import model.Consumer;
import model.CreditorAccount;
import model.DebatorAccount;
import model.Merchant;
import model.Transaction;
import model.TransactionAmount;
import model.TransactionDetails;

@Slf4j
public class Converter {
	public static String convert(String xmlString) throws IOException {
		log.info(" : convert Method : Start");
		XmlMapper xmlMapper = new XmlMapper();
		JsonNode jsonNode = xmlMapper.readTree(xmlString.getBytes());
		JsonNode acqrrFinInitnNode = jsonNode.get(Contants.XML_ATTR_DOCUMENT).get(Contants.XML_ATTR_AcqrrFinInitn);
		JsonNode finInitnNode = acqrrFinInitnNode.get(Contants.XML_ATTR_FinInitn);
		ObjectMapper objectMapper = new ObjectMapper();
		TransactionDetails txDetails = buildTransDetails(finInitnNode);
		String jsonStr = objectMapper.writeValueAsString(txDetails);
		log.info(" : convert Method : End");
		return jsonStr;
	}

	private static TransactionDetails buildTransDetails(JsonNode finInitnNode) {
		log.debug(" : buildTransDetails Method : Start");
		TransactionDetails txDetails = new TransactionDetails();
		JsonNode txNode = finInitnNode.get(Contants.XML_ATTR_Tx);
		txDetails.setTransaction(buildTransaction(finInitnNode));
		txDetails.setDebatorAccount(buildDebatorAccount(txNode));
		txDetails.setCreditorAccount(buildCreditorAccount(txNode));
		txDetails.setConsumer(buildConsumer(finInitnNode));
		txDetails.setAmounts(buildAmounts(txNode));
		txDetails.setMerchant(buildMerchant(finInitnNode));
		log.debug(" : buildTransDetails Method : End");
		return txDetails;
	}

	private static Transaction buildTransaction(JsonNode finInitnNode) {
		log.debug(" : buildTransaction Method : Start");
		JsonNode txCntxtNode = finInitnNode.get(Contants.XML_ATTR_Cntxt).get(Contants.XML_ATTR_TxCntxt);
		String txChanl = txCntxtNode.get(Contants.XML_ATTR_TxChanl).asText();
		JsonNode txNode = finInitnNode.get(Contants.XML_ATTR_Tx);
		String txType = txNode.get(Contants.XML_ATTR_TxTp).asText();
		String trTxDtTm = txNode.get(Contants.XML_ATTR_AccptrTxDtTm).asText();
		String accptrTxId = txNode.get(Contants.XML_ATTR_AccptrTxId).asText();
		String txLifeCyclId = txNode.get(Contants.XML_ATTR_TxLifeCyclId).asText();

		Transaction tansaction = new Transaction();
		tansaction.setTransactionChannel(txChanl);
		tansaction.setTransactionDateTime(trTxDtTm);
		tansaction.setTransactionId(accptrTxId);
		tansaction.setTransactionLifeCycleId(txLifeCyclId);
		tansaction.setTransactionType(txType);

		JsonNode SpclCondsNode = txCntxtNode.get(Contants.XML_ATTR_SpclConds);

		String prmName = SpclCondsNode.get(Contants.XML_ATTR_Prgm).asText();
		if (prmName.equals(Contants.XML_ATTR_TRCTCD)) {
			String splCondValue = SpclCondsNode.get(Contants.XML_ATTR_Val).asText();
			tansaction.setTransactionoriginatedcountrycode(splCondValue);
		}

		log.debug(" : buildTransaction Method : End");
		return tansaction;
	}

	private static Consumer buildConsumer(JsonNode finInitnNode) {
		log.debug(" : buildConsumer Method : Start");
		Consumer consumer = new Consumer();
		String consumerId = finInitnNode.get("Envt").get("Crdhldr").get("Id").get("CstmrNb").asText();
		consumer.setConsumerId(consumerId);
		log.debug(" : buildConsumer Method : End");
		return consumer;
	}

	private static DebatorAccount buildDebatorAccount(JsonNode txNode) {
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

	private static CreditorAccount buildCreditorAccount(JsonNode txNode) {
		log.debug(" : buildCreditorAccount Method : Start");
		CreditorAccount creditorAccount = new CreditorAccount();
		JsonNode acctToNode = txNode.get(Contants.XML_ATTR_TxDtls).get("AcctTo");
		String accNo = acctToNode.get(Contants.XML_ATTR_AcctIdr).get(Contants.XML_ATTR_Othr).asText();
		creditorAccount.setAccountNumber(accNo);
		creditorAccount.setAccountType("Othr");
		log.debug(" : buildCreditorAccount Method : Start");
		return creditorAccount;
	}

	private static Address buildAddress(JsonNode txNode) {
		log.debug(" : buildAddress Method : Start");
		Address address = new Address();
		log.debug(" : buildAddress Method : End");
		return address;
	}

	private static Amounts buildAmounts(JsonNode txNode) {
		log.debug(" : buildAmounts Method : Start");
		JsonNode txAmtsNode = txNode.get(Contants.XML_ATTR_TxDtls).get(Contants.XML_ATTR_TxAmts);
		Amounts amounts = new Amounts();

		TransactionAmount transactionAmount = new TransactionAmount();
		JsonNode ttlAmtNode = txAmtsNode.get(Contants.XML_ATTR_TtlAmt);
		transactionAmount.setValue(ttlAmtNode.get("").asText());
		transactionAmount.setCcy(ttlAmtNode.get(Contants.XML_ATTR_Ccy).asText());

		JsonNode crdhldrBllgTxAmtNode = txAmtsNode.get(Contants.XML_ATTR_CrdhldrBllgTxAmt);
		BillingAmount billingAmount = new BillingAmount();
		billingAmount.setValue(crdhldrBllgTxAmtNode.get(Contants.XML_ATTR_Amt).asText());
		billingAmount.setExchangerate(crdhldrBllgTxAmtNode.get(Contants.XML_ATTR_XchgRate).asText());
		billingAmount.setDate(crdhldrBllgTxAmtNode.get(Contants.XML_ATTR_QtnDt).asText());
		billingAmount.setCcy(crdhldrBllgTxAmtNode.get(Contants.XML_ATTR_Labl).asText());

		JsonNode rcncltnTxAmtNode = txAmtsNode.get(Contants.XML_ATTR_RcncltnTxAmt);
		AdditionalAmount additionalAmount = new AdditionalAmount();
		additionalAmount.setValue(rcncltnTxAmtNode.get(Contants.XML_ATTR_Amt).asText());
		additionalAmount.setCcy(rcncltnTxAmtNode.get(Contants.XML_ATTR_Labl).asText());
		additionalAmount.setType(Contants.XML_ATTR_PRE_CONVERSION);

		amounts.setAdditionalAmount(additionalAmount);
		amounts.setBillingAmount(billingAmount);
		amounts.setTransactionAmount(transactionAmount);

		log.debug(" : buildAmounts Method : End");
		return amounts;
	}

	private static Merchant buildMerchant(JsonNode finInitnNode) {
		log.debug(" : buildMerchant Method : Start");
		Merchant merchant = new Merchant();

		JsonNode txNode = finInitnNode.get(Contants.XML_ATTR_Tx);
		JsonNode accptrNode = finInitnNode.get(Contants.XML_ATTR_Envt).get(Contants.XML_ATTR_Accptr);
		String comnNm = accptrNode.get(Contants.XML_ATTR_CmonNm).asText();
		String merchantId = accptrNode.get(Contants.XML_ATTR_Id).get(Contants.XML_ATTR_Id).asText();
		String merchantcategoryCode = txNode.get(Contants.XML_ATTR_MrchntCtgyCd).asText();
		merchant.setMerchantcategoryCode(merchantcategoryCode);
		merchant.setMerchantName(comnNm);
		merchant.setMerchantId(merchantId);

		log.debug(" : buildMerchant Method : End");
		return merchant;
	}

}
