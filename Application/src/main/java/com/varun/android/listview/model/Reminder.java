package com.varun.android.listview.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by VarunSasidharan_Nair on 31/07/2017.
 */

@Entity (tableName = "Reminder")
public class Reminder {


    @PrimaryKey (autoGenerate = true)
    private long uniqueId;

    @Ignore
    private String id;

    private String title;
    private String recurringType;
    private String status;

    @Ignore
    private String thumbnailUrl;
    private String group;
    @Ignore
    private String parentDate;
    private String dueDate;


    public static ReminderBuilder builder() {
        return new ReminderBuilder();
    }

    public static class ReminderBuilder {
        private long uniqueId;
        private String id;
        private String title;
        private String recurringType;
        private String status;
        private String thumbnailUrl;
        private String group;
        private String parentDate;
        private String dueDate;

        public ReminderBuilder setId(long uniqueId) {
            this.uniqueId = uniqueId;
            return this;
        }

        public ReminderBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ReminderBuilder setRecurringType(String recurringType) {
            this.recurringType = recurringType;
            return this;
        }

        public ReminderBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public ReminderBuilder setGroup(String group) {
            this.group = group;
            return this;
        }

        public ReminderBuilder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Reminder build() {
            Reminder reminder = new Reminder();
            reminder.setGroup(group);
            reminder.setStatus(status);
            reminder.setDueDate(dueDate);
            reminder.setUniqueId(uniqueId);
            reminder.setTitle(title);
            reminder.setRecurringType(recurringType);
            return reminder;
        }
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "id=" + uniqueId +
                ", title='" + title + '\'' +
                ", recurring='" + recurringType + '\'' +
                ", status=" + status +
                ", group=" + group +
                ", duedate=" + dueDate +
                '}';
    }


    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getParentDate() {
        return parentDate;
    }

    public void setParentDate(String parentDate) {
        this.parentDate = parentDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecurringType() {
        return recurringType;
    }

    public void setRecurringType(String recurringType) {
        this.recurringType = recurringType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
