package techproedenglish01.techproedenglish01api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoPractice03 {

	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("data")
	@Expose
	private Data data;
	@SerializedName("message")
	@Expose
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public PojoPractice03(String status, Data data, String message) {
		
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public PojoPractice03() {
		
	}

	@Override
	public String toString() {
		return "Data [status=" + status + ", data=" + data + ", message=" + message + "]";
	}
}