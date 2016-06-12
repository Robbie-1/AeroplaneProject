package pkg23.pkg10.pkg15;

/**
 * @author rm13030
 */
public class Passenger {
    
    String name;
    int age;
    Type gender;
    
    public Passenger(String name, int age, Type gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

	public int getAge() {
        return age;
    }
    
    public Type getGender() {
		return gender;
	}
    
    public enum Type {
    	MALE(),
    	FEMALE();
    }
    
}
