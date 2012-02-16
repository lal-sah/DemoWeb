/**
 * 
 */
package com.sourceallies.webservices;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.cdyne.ws.weatherws.GetCityWeatherByZIP;
import com.cdyne.ws.weatherws.GetCityWeatherByZIPResponse;

/**
 * @author lsah
 * 
 */
@Named("weatherServiceClient")
public class WeatherServiceClient {

	private static final Logger LOGGER = Logger
			.getLogger(WeatherServiceClient.class.getName());

	@Inject
	@Named("webServiceTemplate")
	private WebServiceTemplate webServiceTemplate;

	@Inject
	@Named("soapAction")
	private String soapAction;

	/**
	 * @param zipCode
	 * @return
	 */
	public GetCityWeatherByZIPResponse getWeatherByZipCode(String zipCode) {
		GetCityWeatherByZIP request = new GetCityWeatherByZIP();
		request.setZIP(zipCode);
		SoapActionCallback callback = new SoapActionCallback(soapAction);
		GetCityWeatherByZIPResponse cityWeatherByZIPResponse = null;
		try {
			cityWeatherByZIPResponse = (GetCityWeatherByZIPResponse) webServiceTemplate
					.marshalSendAndReceive(request, callback);
		} catch (Exception e) {
			LOGGER.error("ERROR occurred while retrieving weather!");
			e.printStackTrace();
		}

		return cityWeatherByZIPResponse;
	}

	/**
	 * @return the webServiceTemplate
	 */
	public WebServiceTemplate getWebServiceTemplate() {
		return webServiceTemplate;
	}

	/**
	 * @return the soapAction
	 */
	public String getSoapAction() {
		return soapAction;
	}

	/**
	 * @param webServiceTemplate
	 *            the webServiceTemplate to set
	 */
	public void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
		this.webServiceTemplate = webServiceTemplate;
	}

	/**
	 * @param soapAction
	 *            the soapAction to set
	 */
	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}

}
