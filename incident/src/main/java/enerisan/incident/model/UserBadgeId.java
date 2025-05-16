package enerisan.incident.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserBadgeId implements Serializable {
    private static final long serialVersionUID = 8045405360712754664L;
    @jakarta.validation.constraints.NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @jakarta.validation.constraints.NotNull
    @Column(name = "badge_id", nullable = false)
    private Integer badgeId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserBadgeId entity = (UserBadgeId) o;
        return Objects.equals(this.badgeId, entity.badgeId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(badgeId, userId);
    }

}