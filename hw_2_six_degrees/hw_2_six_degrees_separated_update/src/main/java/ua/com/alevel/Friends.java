package ua.com.alevel;

class Friends {
    private String name;
    boolean isFool;
    static final String cong = "CONGRATULATION VITYA HEAR WAZZZZUP";

    Friends(String name, boolean isFool) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFool(boolean fool) {
        isFool = fool;
    }

    void sendMessageNotFool(String message) {
        System.out.println(message + " send to " + name + " he/she isn`t fool");
    }

    void sendMessageFool() {
        System.out.println("What a fool try again");
    }
}