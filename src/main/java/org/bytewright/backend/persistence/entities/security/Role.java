package org.bytewright.backend.persistence.entities.security;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "roles", schema = "gcm")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Size(max = 100)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "role_permissions", schema = "gcm",
      joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
  private Set<Permission> permissions;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public void addPermissions(Permission permission) {
    CopyOnWriteArraySet<Permission> copy = new CopyOnWriteArraySet<>(this.permissions);
    copy.add(permission);
    this.permissions = copy;
  }

  @Override
  public String toString() {
    return "Role{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", permissions=" + permissions +
        '}';
  }
}