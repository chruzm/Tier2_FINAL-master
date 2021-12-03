package Server;
import Server.REST.RestClient;
import models.OrderObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

//----------------------- ÆNDRE IKKE DENNE KLASSE, BUGGY AS HELL
@SpringBootApplication
public class TestServer
{
	public static void main(String[] args) throws Exception {

		//rest client til at sende data modtaget fra tier3 til tier2 api
		RestClient RC = new RestClient();

		//run Server.REST server som snakker med Tier1 klient
		SpringApplication.run(TestServer.class, args);

		//------------------RUN SOAP CLIENT TIL TIER3, connect til relevante addresser
		URL urlorder = new URL("http://localhost:9990/ws/addorder");
		URL urlmenu = new URL("http://localhost:9990/ws/getmenu");

		//1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
		// port og service links kan ses i den skabte soap server adresse "http://localhost:9999/ws/tst"

		//port service for modtage menu fra tier3
		QName portnamemenu = new QName("http://soap/", "GetMenuImplPort");
		QName servicenamemenu = new QName("http://soap/", "GetMenuImplService");
		//brug service
		Service servicemenu = Service.create(urlmenu, servicenamemenu);
		SOAP_Interface tstmenu = servicemenu.getPort(portnamemenu, SOAP_Interface.class);

		for (int x = 0; x<= 4; x++){
			System.out.println(tstmenu.getMenu(x).getFood());
			RC.storeMenu(tstmenu.getMenu(x));
		}

		//portservice for addOrder til tier3, som så gemmes i database, virker
		QName portnameorder = new QName("http://soap/", "AddOrderImplPort");
		QName servicenameorder = new QName("http://soap/", "AddOrderImplService");
		//brug service
		Service serviceorder = Service.create(urlorder, servicenameorder);
		SOAP_Interface addorder = serviceorder.getPort(portnameorder, SOAP_Interface.class);
	}
}