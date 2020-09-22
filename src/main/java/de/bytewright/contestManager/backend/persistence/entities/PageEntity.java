package de.bytewright.contestManager.backend.persistence.entities;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.apache.wicket.Page;

import de.bytewright.contestManager.backend.persistence.converter.StringToClass;
import de.bytewright.contestManager.backend.persistence.entities.security.Permission;

@Entity
@Table(name = "pages", schema = "gcm")
public class PageEntity extends BaseAuditedEntity {
  @NotBlank
  @Size(max = 200)
  @Column(unique = true)
  private String pageUrlPath;
  @Nullable
  @Size(max = 200)
  private String anchorName;
  private boolean displayInNavi;
  @ManyToOne
  @JoinColumn(name = "required_permission")
  private Permission requiredPermission;
  @Convert(converter = StringToClass.class)
  private Class<? extends Page> pageClass;

  public boolean isDisplayInNavi() {
    return displayInNavi;
  }

  public void setDisplayInNavi(boolean displayInNavi) {
    this.displayInNavi = displayInNavi;
  }

  public String getAnchorName() {
    return anchorName;
  }

  public void setAnchorName(String anchorName) {
    this.anchorName = anchorName;
  }

  public String getPageUrlPath() {
    return pageUrlPath;
  }

  public void setPageUrlPath(String pageUrlPath) {
    this.pageUrlPath = pageUrlPath;
  }

  public Permission getRequiredPermission() {
    return requiredPermission;
  }

  public void setRequiredPermission(Permission requiredPermission) {
    this.requiredPermission = requiredPermission;
  }

  public Class<? extends Page> getPageClass() {
    return pageClass;
  }

  public void setPageClass(Class<? extends Page> pageClass) {
    this.pageClass = pageClass;
  }
}
