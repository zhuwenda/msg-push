package oldpig.msg.push.app.confg;

import java.util.Set;

public class EventConfig {

    String eventId;
    String eventName;

    Set<String> supportedChannelIds;
    Set<String> mustChannelIds;
    Set<String> defaultChannelIds;

    String defaultTitleTpl;
    String defaultContentTpl;

    String status;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Set<String> getSupportedChannelIds() {
        return supportedChannelIds;
    }

    public void setSupportedChannelIds(Set<String> supportedChannelIds) {
        this.supportedChannelIds = supportedChannelIds;
    }

    public Set<String> getMustChannelIds() {
        return mustChannelIds;
    }

    public void setMustChannelIds(Set<String> mustChannelIds) {
        this.mustChannelIds = mustChannelIds;
    }

    public Set<String> getDefaultChannelIds() {
        return defaultChannelIds;
    }

    public void setDefaultChannelIds(Set<String> defaultChannelIds) {
        this.defaultChannelIds = defaultChannelIds;
    }

    public String getDefaultTitleTpl() {
        return defaultTitleTpl;
    }

    public void setDefaultTitleTpl(String defaultTitleTpl) {
        this.defaultTitleTpl = defaultTitleTpl;
    }

    public String getDefaultContentTpl() {
        return defaultContentTpl;
    }

    public void setDefaultContentTpl(String defaultContentTpl) {
        this.defaultContentTpl = defaultContentTpl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
