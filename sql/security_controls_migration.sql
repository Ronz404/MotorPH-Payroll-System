-- ============================================================
-- MotorPH Payroll System — MS2 Security Controls Migration
-- Run this script on your payrollsystem_db MySQL database
-- ============================================================

-- ==============================================
-- CONTROL 1: Account Lockout (MFA & RBAC - R-01)
-- ==============================================
-- Add failed_attempts counter and lockout timestamp to position table
ALTER TABLE position
    ADD COLUMN failed_attempts INT DEFAULT 0,
    ADD COLUMN lockout_until DATETIME DEFAULT NULL;

-- ==============================================
-- CONTROL 2: Audit Trail (Payroll Integrity - R-03)
-- ==============================================
-- Create audit_log table to track all changes
CREATE TABLE IF NOT EXISTS audit_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    action_type VARCHAR(50) NOT NULL,          -- INSERT, UPDATE, DELETE
    table_name VARCHAR(100) NOT NULL,          -- which table was changed
    record_id VARCHAR(50) NOT NULL,            -- employee_id or relevant PK
    field_changed VARCHAR(100),                -- which field changed (for UPDATE)
    old_value TEXT,                             -- previous value
    new_value TEXT,                             -- new value
    changed_by VARCHAR(100) NOT NULL,          -- username who made the change
    change_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45),                    -- client IP (optional)
    notes TEXT                                 -- additional context
);

-- Index for fast lookups by employee and timestamp
CREATE INDEX idx_audit_log_record ON audit_log(record_id);
CREATE INDEX idx_audit_log_timestamp ON audit_log(change_timestamp);

-- ==============================================
-- TRIGGER: Auto-log employee table updates
-- ==============================================
DELIMITER $$

CREATE TRIGGER trg_employee_update_audit
AFTER UPDATE ON employee
FOR EACH ROW
BEGIN
    -- Log last_name changes
    IF OLD.last_name <> NEW.last_name THEN
        INSERT INTO audit_log (action_type, table_name, record_id, field_changed, old_value, new_value, changed_by)
        VALUES ('UPDATE', 'employee', NEW.employee_id, 'last_name', OLD.last_name, NEW.last_name, CURRENT_USER());
    END IF;

    -- Log phone changes
    IF OLD.phone <> NEW.phone OR (OLD.phone IS NULL AND NEW.phone IS NOT NULL) THEN
        INSERT INTO audit_log (action_type, table_name, record_id, field_changed, old_value, new_value, changed_by)
        VALUES ('UPDATE', 'employee', NEW.employee_id, 'phone', OLD.phone, NEW.phone, CURRENT_USER());
    END IF;

    -- Log email changes
    IF OLD.email <> NEW.email OR (OLD.email IS NULL AND NEW.email IS NOT NULL) THEN
        INSERT INTO audit_log (action_type, table_name, record_id, field_changed, old_value, new_value, changed_by)
        VALUES ('UPDATE', 'employee', NEW.employee_id, 'email', OLD.email, NEW.email, CURRENT_USER());
    END IF;
END$$

CREATE TRIGGER trg_employee_delete_audit
BEFORE DELETE ON employee
FOR EACH ROW
BEGIN
    INSERT INTO audit_log (action_type, table_name, record_id, field_changed, old_value, new_value, changed_by, notes)
    VALUES ('DELETE', 'employee', OLD.employee_id, 'ALL',
            CONCAT('Name: ', OLD.first_name, ' ', OLD.last_name, ', Email: ', OLD.email),
            NULL, CURRENT_USER(), 'Employee record deleted');
END$$

DELIMITER ;

-- ==============================================
-- VERIFICATION QUERIES (run after migration)
-- ==============================================
-- Check lockout columns exist:
-- DESCRIBE position;

-- Check audit_log table exists:
-- SHOW TABLES LIKE 'audit_log';

-- Check triggers:
-- SHOW TRIGGERS LIKE 'employee';
