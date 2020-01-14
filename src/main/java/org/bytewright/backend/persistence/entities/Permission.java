package org.bytewright.backend.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.tuple.Pair;

/**
 * a colon delimited string like contests:create
 * general permissions:
 * contests:create
 * contests:manage
 * users:manage
 * roles:manage
 * specific roles:
 * contest(ID):manage
 */
@Entity
@Table(name = "permissions", schema = "gcm")
public class Permission {
  private static String PERM_PAIR_DELIMITER = ":";
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Size(max = 255)
  private String perm;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPerm() {
    return perm;
  }

  public void setPerm(String perm) {
    this.perm = perm;
  }

  public Pair<String, String> getPermission() {
    String[] split = getPerm().split(PERM_PAIR_DELIMITER);
    return Pair.of(split[0], split[1]);
  }

  @Override
  public String toString() {
    return "Permission{" +
        "id=" + id +
        ", perm='" + perm + '\'' +
        '}';
  }
}