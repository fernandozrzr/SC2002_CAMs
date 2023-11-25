package sc2002;

/**
 * Represents an enquiry made by a student.
 * 
 * @author Kiersten Yeo
 * @version 1.0
 * @since 25/11/23
 */

/**
 * STATUS variable to determine whether the enquiry has been replied to.
 * If OPEN, enquiry has not been replied to and can still be edited and removed.
 * If CLOSED, enquiry has been replied to and cannot be edited or removed.
 * 
 */
enum STATUS
{
	OPEN,
	CLOSED
}

public class Enquiries {
	private static int enqID = 0;

	private int enquiryId;
	private int campID;
    	private String enquiry;
    	private String reply;
    	private String replyBy;
    	private Student askBy;
    	private STATUS status;

    	/**
     	* Constructor for creating an Enquiries object.
     	* 
     	* @param campID ID of the camp the enquiry is for
     	* @param enquiry Enquiry text
     	* @param reply Reply to the enquiry
     	* @param replyBy CCM or staff who replied to the enquiry
     	* @param currentUser Student who submitted the enquiry
     	*/
    	public Enquiries(int campID, String enquiry, String reply, String replyBy, Student currentUser) {
        	this.enquiryId = enqID++;
		this.enquiry = enquiry;
        	this.reply = reply;
        	this.replyBy = replyBy;
        	this.askBy = currentUser;
		this.status = STATUS.OPEN;
    	}

    	/**
     	* Gets the ID of the enquiry.
     	* 
     	* @return The ID of the enquiry
     	*/
	public int GetEnquiryId() {
		return enquiryId;
	}
	/**
     	* Sets the ID of the enquiry.
     	*/
	public void SetEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}
	/**
     	* Gets the ID of the camp the enquiry was for.
     	* 
     	* @return The ID of the camp the enquiry was for
     	*/
	public int GetCampID() {
		return campID;
	}
	/**
     	* Sets the ID of the camp the enquiry was for.
     	*/
	public void SetCampID(int campID) {
		this.campID = campID;
	}
	/**
     	* Gets the enquiry text.
     	* 
     	* @return The enquiry text
     	*/
	public String GetEnquiry() {
		return enquiry;
	}
	/**
     	* Sets the enquiry text.
     	*/
	public void SetEnquiry(String enquiry) {
		this.enquiry = enquiry;
	}
	/**
     	* Gets the reply to the enquiry.
     	* 
     	* @return The reply to the enquiry
     	*/
	public String GetReply() {
		return reply;
	}
	/**
     	* Sets the reply to the enquiry.
     	*/
	public void SetReply(String reply) {
		this.reply = reply;
	}
	/**
     	* Gets the CCM or staff who replied to the enquiry.
     	* 
     	* @return The CCM or staff who replied to the enquiry
     	*/
	public String GetReplyBy() {
		return replyBy;
	}
	/**
     	* Sets the CCM or staff who replied to the enquiry.
     	*/
	public void SetReplyBy(String replyBy) {
		this.replyBy = replyBy;
	}
	/**
     	* Gets the student who made the enquiry.
     	* 
     	* @return The student who made the enquiry
     	*/
	public Student GetAskBy() {
		return askBy;
	}
	/**
     	* Sets the Student who replied to the enquiry.
     	*/
	public void SetAskBy(Student askBy) {
		this.askBy = askBy;
	}
	/**
     	* Gets the status of the enquiry.
     	* 
     	* @return The status of the enquiry
     	*/
	public STATUS GetStatus() {
		return status;
	}
	/**
     	* Sets the status of the enquiry.
     	*/
	public void SetStatus(STATUS status) {
		this.status = status;
	}
}
