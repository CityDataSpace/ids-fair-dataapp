package de.fraunhofer.fit.IDSFAIRApp.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.tbouron.SpdxLicense;

import de.fraunhofer.fit.IDSFAIRApp.annotations.FAIRvalidatorInputEndpoint;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRHttpResponseModel;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRresponseModel;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRvalidatorInputModel;
import de.fraunhofer.fit.IDSFAIRApp.model.data.FAIRvalidatorOutputResponseModel;
import de.fraunhofer.fit.IDSFAIRApp.utils.Constants;

@Service
public class FAIRvalidatorService {

	private RestTemplate restTemplate;

	public FAIRvalidatorService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();

	}

	public FAIRvalidatorOutputResponseModel getValidation(FAIRvalidatorInputModel fmodel, String baseURL) {

		FAIRvalidatorOutputResponseModel vModel = new FAIRvalidatorOutputResponseModel();
		List<String> msgList = new ArrayList<String>();

		float validationScore = 0;

		FAIRresponseModel fRes = null;

		fRes = this.pidValidation(fmodel.getPid(), baseURL);
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.licenseValidation(fmodel.getLiscence());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.publisherValidation(fmodel.getPublisher());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.titleValidation(fmodel.getTitle());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.dateValidation(fmodel.getPublicationDate(), "Publication ");
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.dateValidation(fmodel.getModifiedDate(), "Modification ");
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.descriptionValidation(fmodel.getDescription());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.keywordValidation(fmodel.getKeywords());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.endpointValidation(fmodel.getDataLink());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.dataAccessLevelValidation(fmodel.getDataAccessLevel());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.fileFormatValidation(fmodel.getFileFormat());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.languageValidation(fmodel.getLanguage());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.contactValidation(fmodel.getContactPoint());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.byteSizeValidation(fmodel.getByteSize());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		fRes = this.versionValidation(fmodel.getVersion());
		msgList.add(fRes.getMessage());
		if (fRes.getIsValid() == true) {
			validationScore++;
		}

		float validationPercentage = (validationScore / Constants.totalMetadata) * 100;
		vModel.setValidationScore(validationPercentage);
		vModel.setValidationScoreDesc("given metadata is " + validationPercentage + "% FAIR.");
		vModel.setMessage(msgList);
		return vModel;
	}

	/*
	 * public FAIRresponseModel testPidValidation(FAIRvalidatorModel fmodel) {
	 * 
	 * FAIRresponseModel fResponse = new FAIRresponseModel();
	 * 
	 * if (fmodel.getPID() == null) { fResponse.setMessage("PID is missing");
	 * fResponse.setIsValid(true);
	 * 
	 * return fResponse; }
	 * 
	 * // create headers HttpHeaders headers = new HttpHeaders(); // set
	 * `content-type` header headers.setContentType(MediaType.APPLICATION_JSON); //
	 * set `accept` header
	 * headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON)); //
	 * create a map for post parameters Map<String, Object> map = new HashMap<>();
	 * map.put("pid", fmodel.getPID()); // build the request HttpEntity<Map<String,
	 * Object>> entity = new HttpEntity<>(map, headers);
	 * 
	 * // send POST request ResponseEntity<HttpResponseModel> response = null; try {
	 * response = this.restTemplate.postForEntity(Constants.pidValidationURL,
	 * entity, HttpResponseModel.class); } catch (RestClientException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * // check response status code if (response != null &&
	 * response.getBody().getData().length > 0) {
	 * fResponse.setMessage(response.getBody().getData()[0]);
	 * fResponse.setIsValid(true); } else {
	 * fResponse.setMessage("pid is not valid"); fResponse.setIsValid(false); }
	 * 
	 * return fResponse; }
	 */
	private FAIRresponseModel pidValidation(String value, String baseURL) {

		System.out.println("your PID is " + value);

		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value == null || value.length() < 1) {
			fResponse.setMessage("PID is missing.");
			fResponse.setIsValid(true);

			return fResponse;
		}

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		// create a map for post parameters
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.pidValidationKey, value);
		// build the request
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		// send POST request
		ResponseEntity<FAIRHttpResponseModel> response = null;
		try {
			response = this.restTemplate.postForEntity(baseURL + Constants.pidValidationURL, entity,
					FAIRHttpResponseModel.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// check response status code
		if (response != null && response.getBody().getData() != null) {
			fResponse.setMessage("PID is valid -" + response.getBody().getData());
			fResponse.setIsValid(true);
		} else {
			fResponse.setMessage("PID exists but is not valid.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel licenseValidation(String value) {
		// https://github.com/spdx/license-list-data
		// https://github.com/tbouron/spdx-license-checker
		FAIRresponseModel fResponse = new FAIRresponseModel();
		if (value != null && value.length() > 0) {
			boolean isValid = SpdxLicense.isValidName(value);
			fResponse.setIsValid(isValid);
			if (isValid) {
				fResponse.setMessage("License is valid.");
			} else {
				fResponse.setMessage("License exists but is not valid.");
			}
		} else {
			fResponse.setMessage("License is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;

	}

	private FAIRresponseModel publisherValidation(String value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();
		if (value != null && value.length() > 0) {
			if (value != null && value.length() > 1) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Publisher is valid");
			} else {
				fResponse.setIsValid(false);
				fResponse.setMessage("Publisher exists but is not valid");
			}
		} else {
			fResponse.setMessage("Publisher is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel titleValidation(String value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();
		if (value != null && value.length() > 0) {
			if (value != null && value.length() > 1) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Title is valid.");
			} else {
				fResponse.setIsValid(false);
				fResponse.setMessage("Title exists but is not valid");
			}
		} else {
			fResponse.setMessage("Title is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel dateValidation(String value, String type) {

		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value.length() > 0) {

			if (validateJavaDate(value, "MM/dd/yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "MM-dd-yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "MM.dd.yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "dd.MM.yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "dd-MM-yy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "dd-MM-yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "yyyy-MM-dd")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "yyyy-MM-dd HH:mm:ss")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "yyyy-MM-dd HH:mm:ss.SSS")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "yyyy-MM-dd HH:mm:ss.SSSZ")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (validateJavaDate(value, "EEEEE MMMMM yyyy HH:mm:ss.SSSZ")) {
				fResponse.setIsValid(true);
				fResponse.setMessage(type + " date is valid.");
			} else if (value.indexOf("T") > -1) {
				String dt = value.substring(0, value.indexOf("T"));

				if (validateJavaDate(dt, "yyyy-MM-dd")) {
					fResponse.setIsValid(true);
					fResponse.setMessage(type + " date is valid.");
				} else {
					fResponse.setIsValid(false);
					fResponse.setMessage(type + " date exists but not valid.");
				}

			} else {
				fResponse.setMessage(type + " date exists but not valid.");
				fResponse.setIsValid(false);
			}
		} else {
			fResponse.setMessage(type + " date is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel publicationDateValidation(String value) {

		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value.length() > 0) {

			if (validateJavaDate(value, "MM/dd/yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Publication date is valid.");
			} else if (validateJavaDate(value, "MM-dd-yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Publication date is valid.");
			} else if (validateJavaDate(value, "MM.dd.yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Publication date is valid.");
			} else if (validateJavaDate(value, "dd.MM.yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Publication date is valid.");
			} else {
				fResponse.setMessage("Publication date exists but not valid.");
				fResponse.setIsValid(false);
			}
		} else {
			fResponse.setMessage("Publication date is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel modifiedValidation(String value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value.length() > 0) {
			fResponse.setMessage("Modified date exists.");

			if (validateJavaDate(value, "MM/dd/yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Modified date is valid.");
			} else if (validateJavaDate(value, "MM-dd-yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Modified date is valid.");
			} else if (validateJavaDate(value, "MM.dd.yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Modified date is valid.");
			} else if (validateJavaDate(value, "dd.MM.yyyy")) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Modified date is valid.");
			} else {
				fResponse.setMessage("Modified date exists but not valid.");
				fResponse.setIsValid(false);
			}
		} else {
			fResponse.setMessage("Modified date is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel descriptionValidation(String value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value.length() > 0) {
			if (value != null && value.length() > 1) {
				fResponse.setIsValid(true);
				fResponse.setMessage("description is valid.");
			} else {
				fResponse.setIsValid(false);
				fResponse.setMessage("description exists but not valid");
			}
		} else {
			fResponse.setMessage("description is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	// data identifier is missing as core metadata
	private FAIRresponseModel keywordValidation(String[] values) {
		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (values != null && values.length > 0) {

			boolean temp = true;
			for (int i = 0; i < values.length; i++) {
				if (values[i] == null || values.length < 1) {
					temp = false;
				}
			}

			if (temp) {
				fResponse.setIsValid(true);
				fResponse.setMessage("keywords are valid.");
			} else {
				fResponse.setIsValid(false);
				fResponse.setMessage("keywords exist but not valid");
			}
		} else {
			fResponse.setMessage("keywords are missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	/*
	 * public FAIRresponseModel testEndpointValidation(String value) {
	 * FAIRresponseModel fResponse = new FAIRresponseModel();
	 * 
	 * if (value == null) { fResponse.setMessage("endpoint is missing.");
	 * fResponse.setIsValid(false);
	 * 
	 * return fResponse; }
	 * 
	 * URL url = null; HttpURLConnection huc = null; int responseCode = 0;
	 * 
	 * try { url = new URL(value); huc = (HttpURLConnection) url.openConnection();
	 * huc.setRequestMethod("HEAD"); responseCode = huc.getResponseCode();
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * fResponse.setMessage("endpoint is not valid." + e.toString());
	 * fResponse.setIsValid(false); }
	 * 
	 * if (HttpURLConnection.HTTP_OK == responseCode) {
	 * fResponse.setMessage("data endpoint exists and is valid.");
	 * fResponse.setIsValid(true); } else {
	 * fResponse.setMessage("resouce is not found."); fResponse.setIsValid(false); }
	 * 
	 * return fResponse; }
	 */
	private FAIRresponseModel endpointValidation(String value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value == null && value.length() < 1) {
			fResponse.setMessage("data endpoint is missing.");
			fResponse.setIsValid(false);

			return fResponse;
		}

		URL url = null;
		HttpURLConnection huc = null;
		int responseCode = 0;

		try {
			url = new URL(value);
			huc = (HttpURLConnection) url.openConnection();
			huc.setRequestMethod("HEAD");
			responseCode = huc.getResponseCode();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			fResponse.setMessage("Something went wrong while connecting with data endpoint." + e.toString());
			fResponse.setIsValid(false);
		}

		if (HttpURLConnection.HTTP_OK == responseCode) {
			fResponse.setMessage("data endpoint exists and is valid.");
			fResponse.setIsValid(true);
		} else {
			fResponse.setMessage("data endpoint is not found.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel dataAccessLevelValidation(String value) {
		// https://op.europa.eu/en/web/eu-vocabularies/at-dataset/-/resource/dataset/access-right
		String[] dataAccessLevelList = { "PUBLIC", "RESTRICTED", "PRIVATE", "NON_PUBLIC", "SENSITIVE", "CONFIDENTIAL" };

		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value.length() > 0) {
			if (Arrays.asList(dataAccessLevelList).contains(value.toUpperCase())) {
				fResponse.setIsValid(true);
				fResponse.setMessage("Data access level is valid");
			} else {
				fResponse.setIsValid(false);
				fResponse.setMessage("Data access level exists but not valid");
			}
		} else {
			fResponse.setMessage("Data access level is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;

	}

	private FAIRresponseModel fileFormatValidation(String extension) {

		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (extension == null || extension.length() < 1) {
			fResponse.setMessage("fileFormat is missing");
			fResponse.setIsValid(false);
			return fResponse;
		}

		File file = new File("test." + extension);
		try {
			if (file.createNewFile()) {
				System.out.println("a test file is created" + file.getName());
			} else {
				System.out.println("test file already exists.");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("something went wrong while creating a test file with this extension");
		}

		String filename = file.getName();
		if (filename != null) {
			String mimeType = URLConnection.guessContentTypeFromName(filename);
			System.out.println("mimeType" + mimeType);
			if (mimeType != null) {
				fResponse.setMessage("file format is valid - " + mimeType);
				fResponse.setIsValid(true);
			} else {
				fResponse.setMessage("file format exists but is not valid.");
				fResponse.setIsValid(false);
			}
		} else {
			fResponse.setMessage("file format exists but is not valid.");
			fResponse.setIsValid(false);
		}

		return fResponse;

	}

	private FAIRresponseModel languageValidation(String value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value.length() > 0) {
			fResponse.setIsValid(true);
			fResponse.setMessage("language is valid.");

		} else {
			fResponse.setMessage("language is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel contactValidation(String value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value.length() > 0) {
			fResponse.setIsValid(true);
			fResponse.setMessage("contact is valid.");

		} else {
			fResponse.setMessage("contact is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel byteSizeValidation(Integer value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value != 0) {
			if (value == (int) value) {
				fResponse.setIsValid(true);
				fResponse.setMessage("byteSize is valid.");
			} else {
				fResponse.setMessage(" byteSize is not in correct format.");
				fResponse.setIsValid(false);
			}
		} else {
			fResponse.setMessage("byteSize is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	private FAIRresponseModel versionValidation(String value) {
		FAIRresponseModel fResponse = new FAIRresponseModel();

		if (value != null && value.length() > 0) {
			fResponse.setIsValid(true);
			fResponse.setMessage("version is valid.");

		} else {
			fResponse.setMessage("version is missing.");
			fResponse.setIsValid(false);
		}

		return fResponse;
	}

	public static boolean validateJavaDate(String strDate, String format) {
		/*
		 * Set preferred date format, For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.
		 */
		// SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat sdfrmt = new SimpleDateFormat(format);
		sdfrmt.setLenient(false);

		try {
			Date javaDate = sdfrmt.parse(strDate);
			System.out.println(strDate + " is valid date format");
		} /* Date format is invalid */ catch (ParseException e) {
			System.out.println(strDate + " is Invalid Date format");
			return false;
		}
		return true;
	}

}
