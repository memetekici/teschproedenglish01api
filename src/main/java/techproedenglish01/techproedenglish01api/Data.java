package techproedenglish01.techproedenglish01api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("employee_name")
	@Expose
	private String employeeName;
	@SerializedName("employee_salary")
	@Expose
	private Integer employeeSalary;
	@SerializedName("employee_age")
	@Expose
	private Integer employeeAge;
	@SerializedName("profile_image")
	@Expose
	private String profileImage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Integer employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public Integer getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(Integer employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Data(Integer id, String employeeName, Integer employeeSalary, Integer employeeAge, String profileImage) {

		this.id = id;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeAge = employeeAge;
		this.profileImage = profileImage;
	}

	public Data() {

		
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", employeeName=" + employeeName + ", employeeSalary=" + employeeSalary
				+ ", employeeAge=" + employeeAge + ", profileImage=" + profileImage + "]";
	}

}
