package ci1.cics.CICSJavaSync.Service;

import java.util.Collections;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

	private final RestTemplate template;

	public RestService(RestTemplateBuilder restTemplateBuilder){
		this.template = restTemplateBuilder.build();
	}

	public String getJson(){
		try{
			String url = "site.com";
			ResponseEntity<String> response = this.template.getForEntity(url, String.class);
			if(response.getStatusCode() == HttpStatus.OK)
				return response.getBody();
		} catch (HttpStatusCodeException ex) {
			// raw http status code e.g `404`
			System.out.println(ex.getRawStatusCode());
			// http status code e.g. `404 NOT_FOUND`
			System.out.println(ex.getStatusCode().toString());
			// get response body
			System.out.println(ex.getResponseBodyAsString());
			// get http headers
			HttpHeaders headers = ex.getResponseHeaders();
			System.out.println(headers.get("Content-Type"));
			System.out.println(headers.get("Server"));
    	}
		return null;
	}

	public String create(){
		String url = "https://jsonplaceholder.typicode.com/posts";

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// create a post object
		String post = "post";

		// build the request
		HttpEntity<String> entity = new HttpEntity<>(post, headers);

		// send POST request
		return template.postForObject(url, entity, String.class);
	}

	public String update(){
		return "";
	}

	public String delete(){
		return "";
	}

}
