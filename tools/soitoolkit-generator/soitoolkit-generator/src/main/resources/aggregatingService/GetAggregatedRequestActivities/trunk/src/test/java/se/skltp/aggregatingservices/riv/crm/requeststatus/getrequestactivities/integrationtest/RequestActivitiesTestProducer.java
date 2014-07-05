package se.skltp.aggregatingservices.riv.crm.requeststatus.getrequestactivities.integrationtest;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.riv.crm.requeststatus.getrequestactivities.v1.rivtabp21.GetRequestActivitiesResponderInterface;
import se.riv.crm.requeststatus.getrequestactivitiesresponder.v1.GetRequestActivitiesResponseType;
import se.riv.crm.requeststatus.getrequestactivitiesresponder.v1.GetRequestActivitiesType;
import se.skltp.agp.test.producer.TestProducerDb;

@WebService(serviceName = "GetRequestActivitiesResponderService", portName = "GetRequestActivitiesResponderPort", targetNamespace = "urn:riv:crm:requeststatus:GetRequestActivities:1:rivtabp21", name = "GetRequestActivitiesInteraction")
public class RequestActivitiesTestProducer implements GetRequestActivitiesResponderInterface {

	private static final Logger log = LoggerFactory.getLogger(RequestActivitiesTestProducer.class);

	private TestProducerDb testDb;
	public void setTestDb(TestProducerDb testDb) {
		this.testDb = testDb;
	}

	@Override
	public GetRequestActivitiesResponseType getRequestActivities(String logicalAddress, GetRequestActivitiesType request) {
		log.info("### Virtual service for GetRequestActivities call the source system with logical address: {} and patientId: {}", logicalAddress, request.getSubjectOfCareId());

		GetRequestActivitiesResponseType response = (GetRequestActivitiesResponseType)testDb.processRequest(logicalAddress, request.getSubjectOfCareId());
        if (response == null) {
        	// Return an empty response object instead of null if nothing is found
        	response = new GetRequestActivitiesResponseType();
        }

		log.info("### Virtual service got {} booknings in the reply from the source system with logical address: {} and patientId: {}", new Object[] {response.getRequestActivity().size(), logicalAddress, request.getSubjectOfCareId()});

		// We are done
        return response;
	}
}