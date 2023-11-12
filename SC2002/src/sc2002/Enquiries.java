// Enquiries.java
package sc2002;

public class Enquiries {
	private int enquiryId;
    private String enquiry;
    private String reply;
    private String replyBy;
    private String askBy;
    private String status;

    public Enquiries(int enquiryId, String enquiry, String reply, String replyBy, String askBy, String status) {
        this.enquiryId = enquiryId;
		this.enquiry = enquiry;
        this.reply = reply;
        this.replyBy = replyBy;
        this.askBy = askBy;
        this.status = status;
    }

	public int getEnquiryId() {
		return enquiryId;
	}
	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getEnquiry() {
		return enquiry;
	}

	public void setEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getReplyBy() {
		return replyBy;
	}

	public void setReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	public String getAskBy() {
		return askBy;
	}

	public void setAskBy(String askBy) {
		this.askBy = askBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
