package tutorial;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

import de.dfki.km.json.jsonld.JSONLD;
import de.dfki.km.json.jsonld.JSONLDProcessingError;
import de.dfki.km.json.jsonld.impl.JenaJSONLDSerializer;

public class HelloJena {
	
	private static final String CHEESE_PATH = "/Users/mobrown/Dropbox/Development/HelloJena/test/data/cheeses-0.1.ttl";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Model m = ModelFactory.createDefaultModel();
		
		// load the cheese data
		FileManager.get().readModel(m, CHEESE_PATH);
		
		JenaJSONLDSerializer serializer = new JenaJSONLDSerializer();
		
		Object json;
		try {
			json = JSONLD.fromRDF(m, serializer);
			json = JSONLD.simplify(json);

			// used to convert to a usable json form
			ObjectMapper mapper = new ObjectMapper();
			
			mapper.writeValue(System.out, json);
		} catch (JSONLDProcessingError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
