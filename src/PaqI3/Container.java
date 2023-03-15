package PaqI3;

/*
    THIS CLASS REFERS TO A CONTAINER (EACH OBJECT IS AN INDIVIDUAL CONTAINER IN THE HUB)
*/

public class Container {
    //1. ATTRIBUTES FOR EACH OF THE CONTAINERS
    int identifier; //ID of the container --> (IF SET TO 0, MEANS THAT THERE IS NO CONTAINER) <--
    int weight; //Weight in kilograms (kg)
    String country; //Country of origin of the container
    boolean inspected; //Whether the container has been inspected by customs or not
    int priorityLevel; //Priority level of the container (1 goes to 1st column, 2 to 2nd, and 3 to the others)
    String description; //Description of the contents of the container (max. 100 characters)
    String companySends; //Name of the company that sends the container (max. 20 characters)
    String companyReceives; //Name of the company that receives the container (max. 20 characters)

    //2. CONSTRUCTORS
    //2.1. BLANK CONSTRUCTOR - (NO CONTAINER)
    Container() {
        this.identifier = 0; //Empty slot for container
    }
    //2.2. CONSTRUCTOR SPECIFYING ALL THE CHARACTERISTICS OF A CONTAINER
    Container(int identifier, int priorityLevel, int weight, String country, String description, String companySends, String companyReceives, boolean inspected) {
        this.identifier = identifier;
        this.weight = weight;
        this.country = country;
        this.inspected = inspected;
        this.priorityLevel = priorityLevel;
        this.description = description;
        this.companySends = companySends;
        this.companyReceives = companyReceives;
    }

    //3. SETTERS AND GETTERS
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
    public int getIdentifier() {
        return identifier;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getWeight() {
        return  this.weight;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return this.country;
    }
    public void setInspected(boolean inspected) {
        this.inspected = inspected;
    }
    public boolean isInspected() {
        return this.inspected;
    }
    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
    public int getPriorityLevel() {
        return this.priorityLevel;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public void setCompanySends(String companySends) {
        this.companySends = companySends;
    }
    public String getCompanySends() {
        return this.companySends;
    }
    public void setCompanyReceives(String companyReceives) {
        this.companyReceives = companyReceives;
    }
    public String getCompanyReceives() {
        return this.companyReceives;
    }

    //4.
}
