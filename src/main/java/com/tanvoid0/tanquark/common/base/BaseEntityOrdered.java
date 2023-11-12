package com.tanvoid0.tanquark.common.base;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostPersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntityOrdered extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -3964381694444315985L;

    protected Long orderSeq;

    @PostPersist
    protected void postPersist() {
        this.orderSeq = this.id;
    }
}
