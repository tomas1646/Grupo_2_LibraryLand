package com.libraryland.audit;

import com.libraryland.config.CustomRevisionListener;
import lombok.Data;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "REVINFO")
@RevisionEntity(CustomRevisionListener.class)
@Data
public class Revision implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revision_seq")
    @SequenceGenerator(name = "revision_seq", sequenceName = "rbac.seq_revision_id")
    @RevisionNumber
    private int id;

    @Column(name = "REVTSTMP")
    @Temporal(TemporalType.TIMESTAMP)
    @RevisionTimestamp
    private Date date;
}
