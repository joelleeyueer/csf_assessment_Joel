package ibf2022.batch2.csf.backend.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.csf.backend.models.Archives;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Repository
public class ArchiveRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	//TODO: Task 4
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public JsonObject recordBundle(Archives incomingArchives) {

		Document outgoingDocument = new Document()
		.append("bundleId", incomingArchives.getBundleId())
		.append("date", incomingArchives.getDate())
		.append("title", incomingArchives.getTitle())
		.append("name", incomingArchives.getName())
		.append("comments", incomingArchives.getComments());

		JsonArrayBuilder urlArrayBuilder = Json.createArrayBuilder();
		for (String thisUrl : incomingArchives.getUrls()) {
			urlArrayBuilder.add(thisUrl);
		}

		JsonArray urlArray = urlArrayBuilder.build();
		outgoingDocument.append("urls", urlArray);

		mongoTemplate.insert(outgoingDocument, "archives");

		JsonObjectBuilder bundleIdBuilder = Json.createObjectBuilder();
		bundleIdBuilder.add("bundleId", incomingArchives.getBundleId());
		JsonObject bundleIdObject = bundleIdBuilder.build();

		return bundleIdObject;
	}

	//TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundleByBundleId(/* any number of parameters here */) {
		return null;
	}

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public Object getBundles(/* any number of parameters here */) {
		return null;
	}


}
