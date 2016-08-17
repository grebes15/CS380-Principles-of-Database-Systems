class Person {
	private String firstname;
	private String lastname;
	private PhoneNumber phone;
	private PhoneNumber fax;

}
	class PhoneNumber {
		private int code;
		private String number;
}


public static void main(String[] args){

	XStream xstream = new XStream();

	XStream xstream = new XStream(new DomDriver());

	XStream xstream = new XStream(new StaxDriver());

	xstream.alias("person"), Person.class);
	xstream.alias("phonenumber", PhoneNumber.class);

	Person joe = new Person("Joe", "Walnes");
	joe.setPhone (new PhoneNumber(123, "1234-456"));
	joe.setFax(new PhoneNumber(123, 9999-999));

	String xml - xstream.toXML(joe);
}