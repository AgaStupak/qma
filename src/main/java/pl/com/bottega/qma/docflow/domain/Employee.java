package pl.com.bottega.qma.docflow.domain;

import java.util.Set;

public class Employee {

  private final Long id;
  private final Set<EmployeeRole> roles;
  private final Long superiorId;

  public Employee(Long id, Long superiorId, Set<EmployeeRole> roles) {
    this.id = id;
    this.superiorId = superiorId;
    this.roles = roles;
  }

  public boolean hasRole(EmployeeRole role) {
    return this.roles.contains(role);
  }

  public boolean hasRoles(EmployeeRole... roles) {
    return this.roles.containsAll(Set.of(roles));
  }

  public Long getId() {
    return id;
  }

  public boolean isSupervisedBy(Long employeeId) {
    return this.superiorId != null && this.superiorId.equals(employeeId);
  }
}
