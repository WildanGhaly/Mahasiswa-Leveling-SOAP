
package org.example.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.DOCUMENT, parameterStyle = ParameterStyle.WRAPPED)
public interface TopupService {
	@WebMethod
	public int topupPoint (
			@WebParam(name = "userId", targetNamespace = "http://service.example.org/") int userId,
			@WebParam(name = "cost", targetNamespace = "http://service.example.org/") int cost);
}
