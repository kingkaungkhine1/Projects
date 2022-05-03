package vend.dao;

public interface vendAuditDao {
    public void writeAuditEntry(String entry) throws vendPersistenceException;
}