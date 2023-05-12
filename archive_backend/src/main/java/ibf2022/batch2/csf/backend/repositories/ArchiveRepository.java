package ibf2022.batch2.csf.backend.repositories;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

		System.out.println("in recordBundle");

		try {
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
		} catch (Exception e) {
			System.out.println("Exception in recordBundle");
			System.out.println(e);
			return null;
		}
	}

	//TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public JsonObject getBundleByBundleId(String bundleId) {

		System.out.println("in getBundleByBundleId");

		try {
			Criteria criteria = Criteria.where("bundleId").is(bundleId);
			Query query = Query.query(criteria);
			Document outgoingDocument = mongoTemplate.findOne(query, Document.class, "archives");
			if (outgoingDocument == null) {
				return null;
			}

			JsonObjectBuilder bundleIdBuilder = Json.createObjectBuilder()
			.add("bundleId", outgoingDocument.getString("bundleId"))
			.add("date", new SimpleDateFormat("dd-MM-yyyy").format(outgoingDocument.getDate("date")))
			.add("title", outgoingDocument.getString("title"))
			.add("name", outgoingDocument.getString("name"))
			.add("comments", outgoingDocument.getString("comments"));

			List<Document> urlDocuments = outgoingDocument.getList("urls", Document.class);
			JsonArrayBuilder urlArrayBuilder = Json.createArrayBuilder();
			if (urlDocuments != null) {
				for (Document iterateUrl : urlDocuments) {
					String url = iterateUrl.getString("value");
					if (url != null) {
						urlArrayBuilder.add(url);
					}
				}
			}

			JsonArray urlArray = urlArrayBuilder.build();
			bundleIdBuilder.add("urls", urlArray);

			JsonObject bundleJsonObject = bundleIdBuilder.build();
			return bundleJsonObject;
		} catch (Exception e) {
			System.out.println("Exception in getBundleByBundleId");
			System.out.println(e);
			return null;
		}

	}

	//TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method
	//
	//
	public JsonArray getBundles() {
		Criteria criteria = Criteria.where("bundleId").exists(true);
		Query query = Query.query(criteria).with(Sort.by(Sort.Direction.ASC,"date"));
		List<Document> outgoingDocuments = mongoTemplate.find(query, Document.class, "archives");
		JsonArray outgoingJsonObject = generateJsonObject(outgoingDocuments);
		return outgoingJsonObject;
	}

	private JsonArray generateJsonObject(List<Document> outgoingDocuments) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
		for (Document document : outgoingDocuments) {
			String title = document.getString("title");
			Date date = document.getDate("date");
			String formattedDate = dateFormat.format(date);
	
			JsonObjectBuilder bundleBuilder = Json.createObjectBuilder()
					.add("title", title)
					.add("date", formattedDate);
	
			jsonArrayBuilder.add(bundleBuilder);
		}
	
		return jsonArrayBuilder.build();
	}


}
