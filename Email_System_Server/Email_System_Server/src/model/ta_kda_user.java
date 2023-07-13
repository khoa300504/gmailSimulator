package model;

import java.io.Serializable;

public class ta_kda_user implements Serializable{
	private static final long serialVersionUID = -6500665823330706018L;
	private int I_Id;
	private String T_EmailAddress;
	private String T_PassWord;
	private String T_FullName;
	private String T_BirthDay;
	private String T_PhoneNumber;

	
	public ta_kda_user(String t_EmailAddress, String t_PassWord, String t_FullName, String t_BirthDay, String t_PhoneNumber) {
	}

	public ta_kda_user() 
	{
		
	}

	public int getI_Id() {
		return I_Id;
	}

	public void setI_Id(int i_Id) {
		I_Id = i_Id;
	}

	public String getT_EmailAddress() {
		return T_EmailAddress;
	}

	public void setT_EmailAddress(String t_EmailAddress) {
		T_EmailAddress = t_EmailAddress;
	}

	public String getT_PassWord() {
		return T_PassWord;
	}

	public void setT_PassWord(String t_PassWord) {
		T_PassWord = t_PassWord;
	}

	public String getT_FullName() {
		return T_FullName;
	}

	public void setT_FullName(String t_FullName) {
		T_FullName = t_FullName;
	}

	public String getT_PhoneNumber() {
		return T_PhoneNumber;
	}

	public void setT_PhoneNumber(String t_PhoneNumber) {
		T_PhoneNumber = t_PhoneNumber;
	}

	public String getT_BirthDay() {
		return T_BirthDay;
	}

	public void setT_BirthDay(String t_BirthDay) {
		T_BirthDay = t_BirthDay;
	}

	@Override
	public String toString() {
		return "ta_kda_user [I_Id=" + I_Id + ", T_EmailAddress=" + T_EmailAddress + ", T_PassWord=" + T_PassWord
				+ ", T_FullName=" + T_FullName + ", T_BirthDay=" + T_BirthDay + ", T_PhoneNumber=" + T_PhoneNumber
				+ "]";
	}
	
	
}
