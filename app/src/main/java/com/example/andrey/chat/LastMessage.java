
package com.example.andrey.chat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastMessage {

    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("is_read")
    @Expose
    private Boolean isRead;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("text")
    @Expose
    private String text;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
