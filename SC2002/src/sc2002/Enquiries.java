// Enquiries.java
package sc2002;

public class Enquiries {
	private int enquiryId;
    private String enquiry;
    private String reply;
    private String replyBy;
    private Student askBy;
    private int status;

    public Enquiries(int enquiryId, String enquiry, String reply, String replyBy, Student currentUser, int status) {
        this.enquiryId = enquiryId;
		this.enquiry = enquiry;
        this.reply = reply;
        this.replyBy = replyBy;
        this.askBy = currentUser;
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
	public Student GetAskBy() {
		return askBy;
	}

	public void SetAskBy(Student askBy) {
		this.askBy = askBy;
	}

	// Get and Set enquiry status (0 - not replied, 1 - replied)
	public int GetStatus() {
		return status;
	}

	public void SetStatus(int status) {
		this.status = status;
	}
}
