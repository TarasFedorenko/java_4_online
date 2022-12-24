package ua.com.alevel;

class FBIagentStorage {
    static int reserve;

    private static FBIagent[] fbiAgents = new FBIagent[5];
    static FBIagent[] tempArray;

    private FBIagentStorage() {
    }

    public static FBIagent[] getAllFbiAgents() {
        return fbiAgents;
    }

    public static void addFBIagents(FBIagent fbi) {
        for (int i = 0; i < fbiAgents.length; i++) {
            if (fbiAgents[i] == null) {
                fbiAgents[i] = fbi;
                break;
            }
            if (fbiAgents[fbiAgents.length - 1] != null) {
                tempArray = new FBIagent[fbiAgents.length * 2];
                for (int j = 0; j < fbiAgents.length; j++) {
                    tempArray[j] = fbiAgents[j];
                }
                fbiAgents = tempArray;
            }
        }
    }

    public static void deleteFBIagents(String id) {
        for (int i = 0; i < fbiAgents.length; i++) {
            if (fbiAgents[i] != null) {
                if (fbiAgents[i].getId().equals(id)) {
                    fbiAgents[i] = null;
                    return;
                }
            }
        }System.out.println("Wrong ID");
    }

    public static FBIagent getFbiAgent(String id) {
        for (int i = 0; i < fbiAgents.length; i++) {
            if (fbiAgents[i] != null) {
                if (fbiAgents[i].getId().equals(id)) {
                    return fbiAgents[i];
                }
            }
        }
    return null;
    }

    }