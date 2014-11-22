package containers;

public class ProfileInfo {
	
	private String username = null;
	private String firstname = null;
	private String lastname = null;
	private String email = null;
	private String question = null;
	private String answer = null;
	private String company = null;
	private String dob = null;
	
	public ProfileInfo(String username){
		this.username = username;
	}
	
	 public void setUsername(String username){
         this.username = username;
     }
     public String getUsername(){
         return username;
     }

     public void setFirstname(String firstname){
         this.firstname = firstname;
     }
     public String getFirstname(){
         return firstname;
     }

     public void setLastname(String lastname){
         this.lastname = lastname;
     }
     public String getLastname(){
         return lastname;
     }

     public void setEmailaddress(String email){
         this.email = email;
     }
     public String getEmail(){
         return email;
     }

     public void setQuestion(String question){
         this.question = question;
     }
     public String getQuestion(){
         return question;
     }

     public void setAnswer(String answer){
         this.answer = answer;
     }
     public String getAnswer(){
         return answer;
     }
     public void setCompany(String company){
         this.company = company;
     }
     public String getCompany(){
         return company;
     }
     public void setDOB(String dob){
         this.dob = dob;
     }
     public String getDOB(){
         return dob;
     }
}