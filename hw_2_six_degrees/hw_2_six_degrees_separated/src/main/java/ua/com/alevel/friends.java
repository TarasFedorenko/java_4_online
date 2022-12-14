package ua.com.alevel;

class Friends {
    public static String message;
    public static String newMessage = "Wazzzup";
    String name;
    Friends (String name, String message){
        this.name=name;
        this.message = message;}
    Friends(String name){
        this.name =name;
    }
    void sendMessage(Friends friends){
        friends.message = newMessage;
        System.out.println("Ok get it message "+ message + " from  " + name);
    }
}