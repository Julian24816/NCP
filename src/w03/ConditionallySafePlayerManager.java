package w03;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Vector;

@ConditionallyThreadSafe
public class ConditionallySafePlayerManager implements PlayerManagement{
    private List<Player> players = new Vector<>();
    @Override
    public void add(Player p) {
        players.add(p);
    }

    @Override
    public void remove(Player p) {
        players.remove(p);
    }

    @Override
    public Player find(String userName) {
        return players.stream().filter(player -> player.getName().equals(userName)).findAny()
                .orElseThrow(() -> new NoSuchElementException(userName));
    }

    @Override
    public Player findAndRemove(String userName) {
        Player player = find(userName);
        remove(player);
        return player;
    }
}
