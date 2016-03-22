
package model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("badge_counts")
    @Expose
    private BadgeCounts badgeCounts;
    @SerializedName("question_count")
    @Expose
    private Integer questionCount;
    @SerializedName("answer_count")
    @Expose
    private Integer answerCount;
    @SerializedName("last_access_date")
    @Expose
    private Integer lastAccessDate;
    @SerializedName("creation_date")
    @Expose
    private Integer creationDate;
    @SerializedName("account_id")
    @Expose
    private Integer accountId;
    @SerializedName("reputation")
    @Expose
    private Integer reputation;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("site_url")
    @Expose
    private String siteUrl;
    @SerializedName("site_name")
    @Expose
    private String siteName;

    /**
     * 
     * @return
     *     The badgeCounts
     */
    public BadgeCounts getBadgeCounts() {
        return badgeCounts;
    }

    /**
     * 
     * @param badgeCounts
     *     The badge_counts
     */
    public void setBadgeCounts(BadgeCounts badgeCounts) {
        this.badgeCounts = badgeCounts;
    }

    /**
     * 
     * @return
     *     The questionCount
     */
    public Integer getQuestionCount() {
        return questionCount;
    }

    /**
     * 
     * @param questionCount
     *     The question_count
     */
    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    /**
     * 
     * @return
     *     The answerCount
     */
    public Integer getAnswerCount() {
        return answerCount;
    }

    /**
     * 
     * @param answerCount
     *     The answer_count
     */
    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    /**
     * 
     * @return
     *     The lastAccessDate
     */
    public Integer getLastAccessDate() {
        return lastAccessDate;
    }

    /**
     * 
     * @param lastAccessDate
     *     The last_access_date
     */
    public void setLastAccessDate(Integer lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    /**
     * 
     * @return
     *     The creationDate
     */
    public Integer getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate
     *     The creation_date
     */
    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 
     * @return
     *     The accountId
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 
     * @param accountId
     *     The account_id
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * 
     * @return
     *     The reputation
     */
    public Integer getReputation() {
        return reputation;
    }

    /**
     * 
     * @param reputation
     *     The reputation
     */
    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    /**
     * 
     * @return
     *     The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The siteUrl
     */
    public String getSiteUrl() {
        return siteUrl;
    }

    /**
     * 
     * @param siteUrl
     *     The site_url
     */
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    /**
     * 
     * @return
     *     The siteName
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * 
     * @param siteName
     *     The site_name
     */
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

}
