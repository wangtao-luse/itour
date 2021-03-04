package com.itour.http;



public class WebServiceUtil {
public static void main(String[] args)   {
		/*
		 * //1.指定调用的webService的地址 String address=""; EndpointReference er = new
		 * EndpointReference(address); //2.选项设置 Options options = new Options();
		 * options.setTo(er); options.setTimeOutInMilliSeconds(1000*90);
		 * //3.ServiceClient ServiceClient client = new ServiceClient();
		 * client.setOptions(options); //4.设置命名空间(如果不增加，可能出问题) OMFactory fac =
		 * OMAbstractFactory.getOMFactory(); String uri = null; String prefix = null;
		 * OMNamespace omNs = fac.createOMNamespace(uri, prefix); String
		 * method="callServer"; OMElement cmethod = fac.createOMElement(method, omNs);
		 * String name="name"; OMElement symbol = fac.createOMElement(name, omNs);
		 * symbol.setText("1"); String data=null;
		 * symbol.addChild(fac.createOMText(symbol, data)); cmethod.addChild(symbol);
		 * cmethod.build(); String result =
		 * client.sendReceive(cmethod).getFirstElement().getText();
		 */
}
}
