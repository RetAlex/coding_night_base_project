package ua.edu.ukma.e_request.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "ThirdPartyToken_Request")
@Table(name = "third_party_token_request")
public class ThirdPartyToken_Request implements Serializable {
    @Id
    @Column(name = "token", length = 100)
    public String token;
    @Id
    @Column(name = "request_id")
    public long request_id;
}
