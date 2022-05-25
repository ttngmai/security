package study.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ACCOUNT")
@SequenceGenerator(name = "ACCOUNT_SEQ_GEN", sequenceName = "ACCOUNT_SEQ", initialValue = 1, allocationSize = 1)
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ_GEN")
    private Long id;

    private String username;
    private String password;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private Set<AccountRole> accountRoles = new HashSet<>();
}
