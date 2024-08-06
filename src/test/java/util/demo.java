package util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.tests.vendorPortal.VendorPortalTestData;

public class demo {

	public static void main(String[] args) throws Exception {
		
//		InputStream is = ResourceLoader.getResource("src/test/resources/test_data/vendor_portal/john.json");
//		 System.out.println(IOUtils.toString(is, StandardCharsets.UTF_8));

//		VendorPortalTestData td = JsonUtil.getTestData("src/test/resources/test_data/vendor_portal/sam.json");
//		 System.out.println(td.getAnnualEarning()+"-------"+td.getUserName());
		System.setProperty("browser", "firefox");
		config.initialize();
		
		 System.out.println(config.get("flightReservation.url"));
	}

}
