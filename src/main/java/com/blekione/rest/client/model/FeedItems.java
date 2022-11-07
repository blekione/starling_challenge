package com.blekione.rest.client.model;

import java.util.Objects;
import java.util.Set;

public class FeedItems {
    Set<FeedItem> feedItems;

    // required by the JSON processor
    public FeedItems() {
    }

    public FeedItems(Set<FeedItem> feedItems) {
        super();
        this.feedItems = feedItems;
    }

    public Set<FeedItem> getFeedItems() {
        return feedItems;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedItems);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FeedItems other = (FeedItems) obj;
        return Objects.equals(feedItems, other.feedItems);
    }

    @Override
    public String toString() {
        return "FeedItems [feedItems=" + feedItems + "]";
    }
}
