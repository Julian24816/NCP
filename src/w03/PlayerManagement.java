package w03;

interface PlayerManagement {
    void add(Player p);
    void remove(Player p);
    Player find(String userName);
    Player findAndRemove(String userName);
}
