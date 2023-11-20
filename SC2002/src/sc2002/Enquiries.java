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

    // Get and Set enquiry ID and enquiry
	public int GetEnquiryId() {
		return enquiryId;
	}
	
	public void SetEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String GetEnquiry() {
		return enquiry;
	}

	public void SetEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}

	// Get and Set replier details
	public String GetReply() {
		return reply;
	}

	public void SetReply(String reply) {
		this.reply = reply;
	}

	public String GetReplyBy() {
		return replyBy;
	}

	public void SetReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}

	// Get and Set enquirer details
	public String GetAskBy() {
		return askBy;
	}

	public void SetAskBy(String askBy) {
		this.askBy = askBy;
	}

	// Get and Set enquiry status
	public String GetStatus() {
		return status;
	}

	public void SetStatus(String status) {
		this.status = status;
	}
}
