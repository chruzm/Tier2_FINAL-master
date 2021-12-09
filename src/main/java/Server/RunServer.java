package Server;
import SOAP.GetMenu;
import SOAP.Orders2Chef;
import Server.REST.RestClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

//----------------------- ÆNDRE IKKE DENNE KLASSE, BUGGY AS HELL
@SpringBootApplication
public class RunServer
{
	public static void main(String[] args) throws Exception {

		//rest client til at sende data modtaget fra tier3 til tier2 api
		RestClient RC = new RestClient();
		//run Server.REST server som snakker med Tier1 klient
		SpringApplication.run(RunServer.class, args);
		//------------------RUN SOAP CLIENT TIL TIER3, connect til relevante addresser
		URL urlorder = new URL("http://localhost:9990/ws/orders");
		URL urlmenu = new URL("http://localhost:9990/ws/getmenu");
		URL urla = new URL("http://localhost:9990/ws/amount");
		//port service for modtage menu fra tier3
		GetMenu getMenu = new GetMenu();
		getMenu.getMenu();
		/*
		port service for modtage AMOUNT of orders fra tier3. int fra denne service bruges i loop som sender orders til chefen
								OG
			// port service for modtage orders fra tier3 som sendes til chefen (kig SOAP.Orders2Chef)
		 */
		Orders2Chef o2c = new Orders2Chef();
		o2c.send2Chef();

	}
}
