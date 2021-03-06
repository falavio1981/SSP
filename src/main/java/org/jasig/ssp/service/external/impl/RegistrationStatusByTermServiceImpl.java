/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.ssp.service.external.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.jasig.ssp.dao.external.RegistrationStatusByTermDao;
import org.jasig.ssp.model.Person;
import org.jasig.ssp.model.external.RegistrationStatusByTerm;
import org.jasig.ssp.model.external.Term;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.external.RegistrationStatusByTermService;
import org.jasig.ssp.service.external.TermService;
import org.jasig.ssp.util.sort.PagingWrapper;
import org.jasig.ssp.util.sort.SortingAndPaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * RegistrationStatusByTerm service implementation
 * 
 * @author jon.adams
 */
@Service
@Transactional
public class RegistrationStatusByTermServiceImpl extends
		AbstractExternalDataService<RegistrationStatusByTerm>
		implements RegistrationStatusByTermService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(RegistrationStatusByTermServiceImpl.class);

	@Autowired
	transient private RegistrationStatusByTermDao dao;

	@Autowired
	transient private TermService termService;

	@Override
	protected RegistrationStatusByTermDao getDao() {
		return dao;
	}

	protected void setDao(final RegistrationStatusByTermDao dao) {
		this.dao = dao;
	}

	@Override
	public RegistrationStatusByTerm getForCurrentTerm(final Person person)
			throws ObjectNotFoundException {
		return getForTerm(person, termService.getCurrentTerm());
	}

	@Override
	public PagingWrapper<RegistrationStatusByTerm> getAllForPerson(
			final Person person, final SortingAndPaging sAndP) {
		return dao.getAllForPerson(person, sAndP);
	}

	@Override
	public PagingWrapper<RegistrationStatusByTerm> getAllForPersonWithAnyCourseCount(
			Person person, SortingAndPaging sortingAndPaging) {
		return dao.getAllForPersonWithAnyCourseCount(person, sortingAndPaging);
	}

	@Override
	public RegistrationStatusByTerm getForTerm(final Person person,
			final Term term) {
		return dao.getForTerm(person, term);
	}

	@Override
	public Person applyRegistrationStatusForCurrentTerm(final Person person) {

		if (person == null) {
			return null;
		}

		try {
			person.setCurrentRegistrationStatus(getForCurrentTerm(person));
		} catch (ObjectNotFoundException e) {
			LOGGER.debug("term not found?", e);
		}
		return person;
	}
	
	@Override
	public Person applyCurrentAndFutureRegistrationStatuses(final Person person) {

		if (person == null) {
			return null;
		}

		try {
			person.setCurrentAndFutureRegistrationStatuses(getCurrentAndFutureTerms(person));
		} catch (ObjectNotFoundException e) {
			LOGGER.debug("term not found?", e);
		}
		return person;
	}	

	@Override
	public List<RegistrationStatusByTerm> getCurrentAndFutureTerms(
			Person person) throws ObjectNotFoundException {
		List<Term> currentAndFutureTerms = termService.getCurrentAndFutureTerms();
		List<RegistrationStatusByTerm> registrationStatuses = new ArrayList<RegistrationStatusByTerm>();
		for (Term term : currentAndFutureTerms) {
			RegistrationStatusByTerm regStatus = dao.getForTerm(person.getSchoolId(), term.getCode());
			if(regStatus != null)
			{
				registrationStatuses.add(regStatus);
			}
		}
		return registrationStatuses;
	}

	@Override
	public PagingWrapper<RegistrationStatusByTerm> getAllForTerm(
			@NotNull Term term, SortingAndPaging sAndP) {
		return  dao.getAllForTerm(term, sAndP);
	}

	@Override
	public RegistrationStatusByTerm getForTerm(@NotNull String schoolId,
			@NotNull String termCode) {
		return dao.getForTerm(schoolId, termCode);
	}

	@Override
	public RegistrationStatusByTerm getForCurrentTerm(@NotNull String schoolId)
			throws ObjectNotFoundException {
		return getForTerm(schoolId, termService.getCurrentTerm().getCode());
	}
}
