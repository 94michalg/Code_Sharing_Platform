package platform;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
public class Code {


    //Automatically set to random UUID
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Id
    private String id;

    @Column
    private String code;

    //Field "date" means date of creation, it is set to LocalDateTime.now
    //during creation of a new object
    @Column
    private LocalDateTime date;

    //Created, because field "time" is part of JSON request
    private Long time;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private LocalDateTime dateOfExpiry;

    @Column
    private int views;

    //If true, object time limit is restricted
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private boolean restrictedTime;


    //If true, object views limit is restricted
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    private boolean restrictedViews;


    //When creating an object, set time of creation, random UUID as ID
    // and both restrictions to false
    public Code() {
        this.date = LocalDateTime.now();
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.restrictedTime = false;
        this.restrictedViews = false;
    }

    //If time is restricted, returns time difference between expiry time and now in seconds
    // If time is not restricted returns 0
    public Long getTime() {
        if (this.isRestrictedTime()) {
            return ChronoUnit.SECONDS.between(LocalDateTime.now(), dateOfExpiry);
        } else {
            return 0L;
        }
    }

    //If "time" sent in request is equal or less to 0, time is not restricted
    // and date of expiry is set to date of creation, if time is restricted
    // date of expiry is set to date of creation plus "time" seconds
    public void setTime(Long time) {
        if (time <= 0) {
            this.dateOfExpiry = this.date;
        } else {
            LocalDateTime timeOfExpiry = date.plusSeconds(time);
            this.dateOfExpiry = timeOfExpiry;
            this.restrictedTime = true;
        }
    }

    //works very similar to Time above
    public int getViews() {
        if (this.isRestrictedViews()) {
            return this.views;
        } else {
            return 0;
        }
    }

    public void setViews(int views) {
        if (views <= 0) {
            this.views = 0;
        } else {
            this.views = views;
            this.restrictedViews = true;
        }
    }

    public boolean isRestrictedTime() {
        return restrictedTime;
    }

    public boolean isRestrictedViews() {
        return restrictedViews;
    }

    //Takes one view from the "view" field
    public void takeOneView() {
        this.views--;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return date.format(formatter);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public boolean shouldBeDeleted() {
        if (this.restrictedViews && this.views <= 0) {
            return true;
        }
        if (this.restrictedTime && this.getTime() <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", date=" + date +
                ", dateOfExpiry=" + dateOfExpiry +
                ", views=" + views +
                ", restrictedTime=" + restrictedTime +
                ", restrictedViews=" + restrictedViews +
                '}';
    }
}
