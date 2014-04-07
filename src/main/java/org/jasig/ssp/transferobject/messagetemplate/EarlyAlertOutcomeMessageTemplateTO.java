package org.jasig.ssp.transferobject.messagetemplate;

import java.util.UUID;

import org.jasig.ssp.model.Person;
import org.jasig.ssp.model.reference.EarlyAlertOutcome;
import org.jasig.ssp.transferobject.CoachPersonLiteTO;
import org.jasig.ssp.transferobject.reference.EarlyAlertOutcomeTO;

public class EarlyAlertOutcomeMessageTemplateTO extends EarlyAlertOutcomeTO {

	private CoachPersonLiteTO creator;
	
	public EarlyAlertOutcomeMessageTemplateTO() {
		// TODO Auto-generated constructor stub
	}

	public EarlyAlertOutcomeMessageTemplateTO(UUID id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}

	public EarlyAlertOutcomeMessageTemplateTO(UUID id, String name,
			String description, short sortOrder) {
		super(id, name, description, sortOrder);
		// TODO Auto-generated constructor stub
	}

	public EarlyAlertOutcomeMessageTemplateTO(EarlyAlertOutcome model, Person creator) {
		super(model);
		this.setCreator(new CoachPersonLiteTO(creator));
	}

	public CoachPersonLiteTO getCreator() {
		return creator;
	}

	public void setCreator(CoachPersonLiteTO creator) {
		this.creator = creator;
	}

}