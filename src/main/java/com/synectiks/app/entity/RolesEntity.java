package com.synectiks.app.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "roles")
public class RolesEntity {
    @Id
    private String id;
    private String name; // e.g., "admin", "user"
    private List<String> permissions;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	@Override
	public String toString() {
		return "RolesEntity [id=" + id + ", name=" + name + ", permissions=" + permissions + "]";
	} 
    
    
}