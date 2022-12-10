package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseTimeEntity {
}
