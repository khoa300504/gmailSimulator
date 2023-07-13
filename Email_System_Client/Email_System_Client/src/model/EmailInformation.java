package model;

public class EmailInformation {
	int id;
	String Email;
	String title;
	String body;
	String path;
	String attach;
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "EmailInformation [id=" + id + ", Email=" + Email + ", title=" + title + ", body=" + body + ", path="
				+ path + ", attach=" + attach + "]";
	}


	
	
}
