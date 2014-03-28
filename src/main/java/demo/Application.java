package demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jersey.JerseyApiReader;
import com.wordnik.swagger.reader.ClassReaders;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Path("/")
@Api(value = "home", description = "Demo API")
public class Application extends ResourceConfig implements CommandLineRunner {
	
	@GET
	@ApiOperation(value = "Get Greeting", notes = "Returns greeting")
	public String home() {
		return "Hello";
	}
	
	@Override
	public void run(String... args) throws Exception {
		SwaggerConfig config = ConfigFactory.config();
		config.setBasePath("http://localhost:8080/");
		config.setApiVersion("1.0.0");
		ScannerFactory.setScanner(new DefaultJaxrsScanner());
		ClassReaders.setReader(new JerseyApiReader());
	}
	
	public Application() {
		register(Application.class);
		packages("com.wordnik.swagger.jersey.listing");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
