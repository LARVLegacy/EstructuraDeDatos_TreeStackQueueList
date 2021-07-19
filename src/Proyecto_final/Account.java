
package Proyecto_final;

public class Account {
    
    private int code;  //codigo
    private String holder;  //titular
    private int password; //Contraseña
    private String type; //Tipo
    private String state; //Estado
    private Queue profiles; //Cola de perfiles
    
    public Account(){
    }

    public Account(int code, String holder, int password, String type, String state, Queue profiles) {
        this.code = code;
        this.holder = holder;
        this.password = password;
        this.type = type;
        this.state = state;
        this.profiles = profiles;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Queue getProfiles() {
        return profiles;
    }

    public void setProfiles(Queue profiles) {
        this.profiles = profiles;
    }

    @Override
    public String toString() {
        return "Account{" + "code=" + code + ", holder=" + holder + ", password=" + password + ", type=" + type + ", state=" + state + ", profiles=" + profiles + '}';
    }
    
}