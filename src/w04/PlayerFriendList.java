package w04;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class PlayerFriendList {
    private final Collection<Player> friendList;

    public PlayerFriendList(Collection<Player> friendList) {
        this.friendList = new ArrayList<>(friendList);
    }

    public Collection<Player> getFriendList() {
        return Collections.unmodifiableCollection(friendList);
    }

    public PlayerFriendList getWithout(Player unFriend) {
        Collection<Player> copy = new ArrayList<>(friendList);
        copy.remove(unFriend);
        return new PlayerFriendList(copy);
    }

    public PlayerFriendList getWith(Player friend) {
        Collection<Player> copy = new ArrayList<>(friendList);
        copy.add(friend);
        return new PlayerFriendList(copy);
    }
}
