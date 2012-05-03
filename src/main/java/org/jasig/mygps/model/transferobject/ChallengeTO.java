package org.jasig.mygps.model.transferobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jasig.ssp.model.reference.Challenge;
import org.jasig.ssp.model.reference.ChallengeChallengeReferral;
import org.jasig.ssp.transferobject.TransferObject;
import org.jasig.ssp.transferobject.reference.AbstractReferenceTO;
import org.jasig.ssp.transferobject.reference.ChallengeReferralTO;

import com.google.common.collect.Lists;

/**
 * Transfer object very similar to
 * {@link org.jasig.ssp.transferobject.reference.ChallengeTO} except that it
 * does not map SelfHelpGuideQuestion so a serialization infinite loop doesn't
 * occur.
 * 
 * @author jon.adams
 * 
 */
@JsonIgnoreProperties(value = { "selfHelpGuideQuestions" })
public class ChallengeTO extends AbstractReferenceTO<Challenge> implements
		TransferObject<Challenge>, Serializable {

	private static final long serialVersionUID = 2320351255526248904L;

	private String selfHelpGuideDescription;

	private boolean showInStudentIntake;

	private boolean showInSelfHelpSearch;

	private String tags;

	private UUID defaultConfidentialityLevelId;

	private List<ChallengeReferralTO> challengeChallengeReferrals;

	public ChallengeTO(final Challenge model) {
		super();
		from(model);
	}

	@Override
	public final void from(@NotNull final Challenge model) {
		super.from(model);

		selfHelpGuideDescription = model.getSelfHelpGuideDescription();
		showInStudentIntake = model.isShowInStudentIntake();
		showInSelfHelpSearch = model.isShowInSelfHelpSearch();
		tags = model.getTags();

		if (model.getDefaultConfidentialityLevel() != null) {
			defaultConfidentialityLevelId = model
					.getDefaultConfidentialityLevel()
					.getId();
		}

		if ((model.getChallengeChallengeReferrals() == null)
				|| model.getChallengeChallengeReferrals().isEmpty()) {
			challengeChallengeReferrals = new ArrayList<ChallengeReferralTO>();
		} else {
			final List<ChallengeReferralTO> referralTOs = Lists.newArrayList();
			for (ChallengeChallengeReferral challengeReferral : model
					.getChallengeChallengeReferrals()) {
				referralTOs.add(new ChallengeReferralTO(challengeReferral
						.getChallengeReferral()));
			}
			challengeChallengeReferrals = referralTOs;
		}
	}

	public String getSelfHelpGuideDescription() {
		return selfHelpGuideDescription;
	}

	public void setSelfHelpGuideDescription(
			final String selfHelpGuideDescription) {
		this.selfHelpGuideDescription = selfHelpGuideDescription;
	}

	public boolean isShowInStudentIntake() {
		return showInStudentIntake;
	}

	public void setShowInStudentIntake(final boolean showInStudentIntake) {
		this.showInStudentIntake = showInStudentIntake;
	}

	public boolean isShowInSelfHelpSearch() {
		return showInSelfHelpSearch;
	}

	public void setShowInSelfHelpSearch(final boolean showInSelfHelpSearch) {
		this.showInSelfHelpSearch = showInSelfHelpSearch;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(final String tags) {
		this.tags = tags;
	}

	public List<ChallengeReferralTO> getChallengeChallengeReferrals() {
		return challengeChallengeReferrals;
	}

	public void setChallengeChallengeReferrals(
			final List<ChallengeReferralTO> challengeChallengeReferrals) {
		this.challengeChallengeReferrals = challengeChallengeReferrals;
	}

	public long getReferralCount() {
		return challengeChallengeReferrals == null ? 0
				: challengeChallengeReferrals.size();
	}

	public void setReferralCount(final int referralCount) {
		/* ignore this since it is auto-calculated */
	}

	public UUID getDefaultConfidentialityLevelId() {
		return defaultConfidentialityLevelId;
	}

	public void setDefaultConfidentialityLevelId(
			final UUID defaultConfidentialityLevelId) {
		this.defaultConfidentialityLevelId = defaultConfidentialityLevelId;
	}
}