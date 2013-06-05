package com.eb.kassa.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.eb.kassa.beans.BankRate;
import com.eb.kassa.beans.Currency;
import com.eb.kassa.beans.EuropeanCentralBankRate;
import com.eb.kassa.beans.Rate;

@Service
public class EuropeanCentralBankRateService implements RateService {

	private final String XMLFILE = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

	@Autowired
	private CurrencyService currencyService;

	public Date updateRates() {
		Date updated = null;

		Map<String, BankRate> rates = getLastRates();
		if (rates == null || rates.isEmpty())
			return null;

		Iterator<BankRate> iter = rates.values().iterator();
		updated = iter.next().getUpdated();

		List<Currency> currencies = currencyService.getCurrencies();

		for (Currency currency1 : currencies) {
			for (Currency currency2 : currencies) {
				if (currency1.equals(currency2))
					continue;
				Rate rate = currencyService.getRate(currency1.getId(),
						currency2.getId());

				if (rate == null) {
					rate = new Rate();
					rate.setCurrency1(currency1.getId());
					rate.setCurrency2(currency2.getId());
				}

				rate.setRate(convert(currency1.getCode(), currency2.getCode(),
						rates));

				currencyService.addRate(rate);
			}
		}

		return updated;
	}

	private BigDecimal convert(String code1, String code2,
			Map<String, BankRate> rates) {
		BankRate rate1 = rates.get(code1);
		BankRate rate2 = rates.get(code2);
		if (rate1 == null || rate2 == null)
			return new BigDecimal(1);

		double value = (1 / rate1.getRate().doubleValue())
				* rate2.getRate().doubleValue();

		return new BigDecimal(value);
	}

	private Map<String, BankRate> getLastRates() {
		Map<String, BankRate> rates = new HashMap<String, BankRate>();

		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory
					.newInstance();
			domFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = domFactory.newDocumentBuilder();

			Document doc = builder.parse(XMLFILE);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Element root = doc.getDocumentElement();

			NodeList rootChildren = root.getChildNodes();
			Node cubeNode = null;
			for (int j = 0; j < rootChildren.getLength(); j++) {
				if (rootChildren.item(j).getNodeName().equals("Cube")) {
					cubeNode = rootChildren.item(j);
					break;
				}
			}

			if (cubeNode == null)
				return rates;

			NodeList list = cubeNode.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node item = list.item(i);
				if (item.getNodeType() == Node.TEXT_NODE)
					continue;

				if (item.getAttributes() != null) {

					String time = item.getAttributes().getNamedItem("time")
							.getTextContent();
					Date updated = format.parse(time);

					NodeList cubes = item.getChildNodes();

					for (int k = 0; k < cubes.getLength(); k++) {
						Node cube = cubes.item(k);

						if (cube.getNodeType() == Node.TEXT_NODE)
							continue;

						String code = cube.getAttributes().getNamedItem(
								"currency").getTextContent();
						String rateValue = cube.getAttributes().getNamedItem(
								"rate").getTextContent();
						BigDecimal r = new BigDecimal(rateValue);
						r.setScale(4, RoundingMode.HALF_UP);

						EuropeanCentralBankRate rate = new EuropeanCentralBankRate();
						rate.setUpdated(updated);
						rate.setCurrency(code);
						rate.setRate(r);
						rates.put(code, rate);
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return rates;
	}
}