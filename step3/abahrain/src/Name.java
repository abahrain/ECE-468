public class Name {
    String name;
    String type;
    String value;

    public Name (String name, String type) {
        this.name = name;
        this.type = type;
        this.value = null;
    }

    public Name (String name, String type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public boolean catchString() {
        return type.equals("STRING");
    }

    @Override
    public String convertString() {
        if (isString()) {
          return new String("name " + name + " type " + type 
                  + " value " + value); 
        } else {
          return new String("name " + name + " type " + type); 
        }
    }

}