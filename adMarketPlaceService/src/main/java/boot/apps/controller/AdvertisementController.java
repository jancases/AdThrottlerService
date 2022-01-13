package boot.apps.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import boot.apps.config.AppConfig;
import boot.apps.helper.AdsHelper;
import boot.apps.model.AdvertisementResponse;
import boot.apps.model.Error;
import boot.apps.model.Metadata;
import boot.apps.model.ResponseHelper;

@RestController
@RequestMapping("/")
public class AdvertisementController {
		
	private AppConfig appConfig;
	private AdsHelper adsHelper;
	
	public AdvertisementController(AppConfig appConfig, AdsHelper adsHelper) {
		this.appConfig 			= appConfig;
		this.adsHelper          = adsHelper;
	}
		
	@RequestMapping(value="/health", method = RequestMethod.GET)
	public ResponseEntity<String> health() throws Exception {
		return new ResponseEntity<String>("Advertisement controller active", HttpStatus.OK);
	}
	
	@RequestMapping(value="/getAds", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseHelper> requestChange(HttpServletRequest request) {
		Instant requestTime = Instant.now();
		try {
			Metadata metadata = new Metadata("Response for getAds endpoint" , "/getAds", 200, requestTime);
			
			AdvertisementResponse adResponse = new AdvertisementResponse(appConfig.getProperty("app.config.adType"),appConfig.getProperty("app.config.category"), appConfig.getProperty("app.config.imageUrl"));
			
			adsHelper.executeThrottle();
			
			ResponseHelper responseHelper = new ResponseHelper(adResponse, metadata);
			
			return ResponseEntity.ok(responseHelper);
		} catch (InterruptedException interruptException) {
			interruptException.printStackTrace();
			
			Metadata metadata = new Metadata("Response for getting ads -  :: " + interruptException.getMessage(), "/getAds", 204, requestTime);
			
			List<Error> lsErrors = getErrorObject("204");
			ResponseHelper responseHelper = new ResponseHelper(metadata, lsErrors);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(responseHelper);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			
			Metadata metadata = new Metadata("Response for getting ads -  :: " + ex.getMessage(), "/getAds", 500, requestTime);
			
			List<Error> lsErrors = getErrorObject("500");
			
			ResponseHelper responseHelper = new ResponseHelper(metadata, lsErrors);
			return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(responseHelper);
			
		}
	}
	
	private List<Error> getErrorObject(String errorCode) {
		List<Error> lsErrors = new ArrayList<> ();
		
		Error error = new Error( errorCode );
		lsErrors.add(error);
		return lsErrors;
	}


}
