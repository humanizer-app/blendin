package org.blendin.blendin.posts.dummy;

import org.blendin.blendin.models.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyPosts {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Post> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Post> ITEM_MAP = new HashMap<>();

    private static final int COUNT = 0;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Post item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.hashCode()+"", item);
    }

    private static Post createDummyItem(int position) {
        return new Post(String.valueOf(position), "Item " + position, makeDetails(position), 1110101);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
//        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
//        }
        return builder.toString();
    }

}
