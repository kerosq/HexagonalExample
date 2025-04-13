-- Drop tables in reverse order of dependency
DROP TABLE IF EXISTS billing;
DROP TABLE IF EXISTS prescriptions;
DROP TABLE IF EXISTS medical_records;
DROP TABLE IF EXISTS doctor_schedules;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS specialties;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS users;

-- Tabla de usuarios
CREATE TABLE IF NOT EXISTS users (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    age INT CHECK (age > 0),
    phone VARCHAR(20),
    address TEXT,
    role VARCHAR(20) CHECK (role IN ('admin', 'doctor', 'patient', 'staff')),
    status VARCHAR(20) CHECK (status IN ('active', 'inactive', 'suspended', 'pending')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Patients table con relación a users
CREATE TABLE IF NOT EXISTS patients (
                                        id SERIAL PRIMARY KEY,
                                        user_id INT REFERENCES users(id) ON DELETE CASCADE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    gender CHAR(1) CHECK (gender IN ('M', 'F', 'O')),
    address TEXT,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    insurance_number VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Specialties table
CREATE TABLE IF NOT EXISTS specialties (
                                           id SERIAL PRIMARY KEY,
                                           name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
    );

-- Doctors table con relación a users
CREATE TABLE IF NOT EXISTS doctors (
                                       id SERIAL PRIMARY KEY,
                                       user_id INT REFERENCES users(id) ON DELETE CASCADE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    specialty_id INT REFERENCES specialties(id) ON DELETE RESTRICT,
    license_number VARCHAR(50) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    hire_date DATE,
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Medical appointments table
CREATE TABLE IF NOT EXISTS appointments (
                                            id SERIAL PRIMARY KEY,
                                            patient_id INT REFERENCES patients(id) ON DELETE CASCADE,
    doctor_id INT REFERENCES doctors(id) ON DELETE RESTRICT,
    appointment_date TIMESTAMP NOT NULL,
    reason TEXT NOT NULL,
    status VARCHAR(20) CHECK (status IN ('scheduled', 'completed', 'cancelled', 'no_show')),
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Medical records table
CREATE TABLE IF NOT EXISTS medical_records (
                                               id SERIAL PRIMARY KEY,
                                               patient_id INT REFERENCES patients(id) ON DELETE CASCADE,
    appointment_id INT REFERENCES appointments(id) ON DELETE SET NULL,
    diagnosis TEXT,
    treatment TEXT,
    notes TEXT,
    created_by INT REFERENCES doctors(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Prescriptions table
CREATE TABLE IF NOT EXISTS prescriptions (
                                             id SERIAL PRIMARY KEY,
                                             medical_record_id INT REFERENCES medical_records(id) ON DELETE CASCADE,
    medication_name VARCHAR(100) NOT NULL,
    dosage VARCHAR(50) NOT NULL,
    frequency VARCHAR(50) NOT NULL,
    duration VARCHAR(50),
    notes TEXT,
    prescribed_by INT REFERENCES doctors(id),
    prescribed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Doctor schedules table
CREATE TABLE IF NOT EXISTS doctor_schedules (
                                                id SERIAL PRIMARY KEY,
                                                doctor_id INT REFERENCES doctors(id) ON DELETE CASCADE,
    day_of_week SMALLINT CHECK (day_of_week BETWEEN 1 AND 7),
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    CONSTRAINT valid_time_range CHECK (start_time < end_time)
    );

-- Billing table
CREATE TABLE IF NOT EXISTS billing (
                                       id SERIAL PRIMARY KEY,
                                       appointment_id INT REFERENCES appointments(id) ON DELETE SET NULL,
    patient_id INT REFERENCES patients(id) ON DELETE RESTRICT,
    amount DECIMAL(10, 2) NOT NULL,
    payment_status VARCHAR(20) CHECK (payment_status IN ('pending', 'paid', 'refunded', 'cancelled')),
    payment_date TIMESTAMP,
    payment_method VARCHAR(50),
    insurance_coverage DECIMAL(10, 2) DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Create indexes for common lookups
CREATE INDEX IF NOT EXISTS idx_appointments_patient_id ON appointments(patient_id);
CREATE INDEX IF NOT EXISTS idx_appointments_doctor_id ON appointments(doctor_id);
CREATE INDEX IF NOT EXISTS idx_appointments_date ON appointments(appointment_date);
CREATE INDEX IF NOT EXISTS idx_medical_records_patient_id ON medical_records(patient_id);
CREATE INDEX IF NOT EXISTS idx_doctors_specialty ON doctors(specialty_id);
CREATE INDEX IF NOT EXISTS idx_prescriptions_medical_record ON prescriptions(medical_record_id);
CREATE INDEX IF NOT EXISTS idx_doctors_user_id ON doctors(user_id);
CREATE INDEX IF NOT EXISTS idx_patients_user_id ON patients(user_id);
CREATE INDEX IF NOT EXISTS idx_users_role ON users(role);
CREATE INDEX IF NOT EXISTS idx_users_email ON users(email);

-- Insertar usuarios
INSERT INTO users (name, email, password, age, phone, address, role, status) VALUES
                                                                                 ('Admin Sistema', 'admin@hospital.com', 'hashed_password_123', 35, '+34600111222', 'Calle Administración 1, Madrid', 'admin', 'active'),
                                                                                 ('Antonio García', 'antonio.garcia@hospital.com', 'hashed_password_234', 45, '+34666789012', 'Avenida Médica 23, Madrid', 'doctor', 'active'),
                                                                                 ('Elena Pérez', 'elena.perez@hospital.com', 'hashed_password_345', 38, '+34677890123', 'Calle Salud 45, Barcelona', 'doctor', 'active'),
                                                                                 ('Javier Ruiz', 'javier.ruiz@hospital.com', 'hashed_password_456', 50, '+34688901234', 'Plaza Hospital 12, Valencia', 'doctor', 'active'),
                                                                                 ('Carmen Díaz', 'carmen.diaz@hospital.com', 'hashed_password_567', 41, '+34699012345', 'Avenida Sanitaria 34, Sevilla', 'doctor', 'active'),
                                                                                 ('Pablo Moreno', 'pablo.moreno@hospital.com', 'hashed_password_678', 39, '+34610123456', 'Calle Clínica 67, Bilbao', 'doctor', 'active'),
                                                                                 ('Lucía Ortiz', 'lucia.ortiz@hospital.com', 'hashed_password_789', 36, '+34621234567', 'Plaza Doctores 9, Zaragoza', 'doctor', 'active'),
                                                                                 ('María González', 'maria.gonzalez@email.com', 'hashed_password_890', 38, '+34600123456', 'Calle Principal 123, Madrid', 'patient', 'active'),
                                                                                 ('Juan Martínez', 'juan.martinez@email.com', 'hashed_password_901', 45, '+34611234567', 'Avenida Central 45, Barcelona', 'patient', 'active'),
                                                                                 ('Ana López', 'ana.lopez@email.com', 'hashed_password_012', 31, '+34622345678', 'Plaza Mayor 7, Valencia', 'patient', 'active'),
                                                                                 ('Carlos Rodríguez', 'carlos.rodriguez@email.com', 'hashed_password_123', 58, '+34633456789', 'Calle Secundaria 89, Sevilla', 'patient', 'active'),
                                                                                 ('Laura Fernández', 'laura.fernandez@email.com', 'hashed_password_234', 22, '+34644567890', 'Avenida Norte 56, Bilbao', 'patient', 'active'),
                                                                                 ('Miguel Sánchez', 'miguel.sanchez@email.com', 'hashed_password_345', 34, '+34655678901', 'Calle Sur 23, Zaragoza', 'patient', 'active'),
                                                                                 ('Laura Sánchez', 'laura.sanchez@hospital.com', 'hashed_password_456', 29, '+34633777888', 'Calle Recepción 8, Madrid', 'staff', 'active'),
                                                                                 ('Carlos Jiménez', 'carlos.jimenez@hospital.com', 'hashed_password_567', 42, '+34644888999', 'Avenida Personal 32, Barcelona', 'staff', 'active');

-- Insertar especialidades médicas
INSERT INTO specialties (name, description) VALUES
                                                ('Cardiología', 'Especialidad dedicada al diagnóstico y tratamiento de enfermedades cardiovasculares'),
                                                ('Dermatología', 'Especialidad centrada en el diagnóstico y tratamiento de enfermedades de la piel'),
                                                ('Pediatría', 'Especialidad enfocada en la salud y el desarrollo de niños y adolescentes'),
                                                ('Neurología', 'Especialidad dedicada al diagnóstico y tratamiento de trastornos del sistema nervioso'),
                                                ('Oftalmología', 'Especialidad centrada en el diagnóstico y tratamiento de enfermedades oculares'),
                                                ('Ginecología', 'Especialidad enfocada en la salud del sistema reproductor femenino');

-- Insertar pacientes relacionados con usuarios
INSERT INTO patients (user_id, first_name, last_name, birth_date, gender, address, phone, email, insurance_number) VALUES
                                                                                                                       (8, 'María', 'González', '1985-05-12', 'F', 'Calle Principal 123, Madrid', '+34600123456', 'maria.gonzalez@email.com', 'INS-12345'),
                                                                                                                       (9, 'Juan', 'Martínez', '1978-08-25', 'M', 'Avenida Central 45, Barcelona', '+34611234567', 'juan.martinez@email.com', 'INS-23456'),
                                                                                                                       (10, 'Ana', 'López', '1992-11-02', 'F', 'Plaza Mayor 7, Valencia', '+34622345678', 'ana.lopez@email.com', 'INS-34567'),
                                                                                                                       (11, 'Carlos', 'Rodríguez', '1965-02-14', 'M', 'Calle Secundaria 89, Sevilla', '+34633456789', 'carlos.rodriguez@email.com', 'INS-45678'),
                                                                                                                       (12, 'Laura', 'Fernández', '2001-07-30', 'F', 'Avenida Norte 56, Bilbao', '+34644567890', 'laura.fernandez@email.com', 'INS-56789'),
                                                                                                                       (13, 'Miguel', 'Sánchez', '1989-04-17', 'M', 'Calle Sur 23, Zaragoza', '+34655678901', 'miguel.sanchez@email.com', 'INS-67890');

-- Insertar médicos relacionados con usuarios
INSERT INTO doctors (user_id, first_name, last_name, specialty_id, license_number, phone, email, hire_date, active) VALUES
                                                                                                                        (2, 'Antonio', 'García', 1, 'MED-12345', '+34666789012', 'antonio.garcia@hospital.com', '2015-01-15', TRUE),
                                                                                                                        (3, 'Elena', 'Pérez', 2, 'MED-23456', '+34677890123', 'elena.perez@hospital.com', '2017-03-22', TRUE),
                                                                                                                        (4, 'Javier', 'Ruiz', 3, 'MED-34567', '+34688901234', 'javier.ruiz@hospital.com', '2010-06-10', TRUE),
                                                                                                                        (5, 'Carmen', 'Díaz', 4, 'MED-45678', '+34699012345', 'carmen.diaz@hospital.com', '2019-11-05', TRUE),
                                                                                                                        (6, 'Pablo', 'Moreno', 5, 'MED-56789', '+34610123456', 'pablo.moreno@hospital.com', '2014-09-18', TRUE),
                                                                                                                        (7, 'Lucía', 'Ortiz', 6, 'MED-67890', '+34621234567', 'lucia.ortiz@hospital.com', '2016-05-27', TRUE);

-- Insertar horarios de médicos
INSERT INTO doctor_schedules (doctor_id, day_of_week, start_time, end_time) VALUES
                                                                                (1, 1, '09:00', '14:00'), -- Lunes
                                                                                (1, 3, '09:00', '14:00'), -- Miércoles
                                                                                (1, 5, '09:00', '14:00'), -- Viernes
                                                                                (2, 2, '10:00', '17:00'), -- Martes
                                                                                (2, 4, '10:00', '17:00'), -- Jueves
                                                                                (3, 1, '08:30', '13:30'), -- Lunes
                                                                                (3, 2, '08:30', '13:30'), -- Martes
                                                                                (3, 3, '08:30', '13:30'), -- Miércoles
                                                                                (4, 3, '15:00', '20:00'), -- Miércoles
                                                                                (4, 4, '15:00', '20:00'), -- Jueves
                                                                                (4, 5, '15:00', '20:00'), -- Viernes
                                                                                (5, 1, '11:00', '18:00'), -- Lunes
                                                                                (5, 5, '11:00', '18:00'), -- Viernes
                                                                                (6, 2, '09:30', '16:30'), -- Martes
                                                                                (6, 4, '09:30', '16:30'); -- Jueves

-- Insertar citas médicas
INSERT INTO appointments (patient_id, doctor_id, appointment_date, reason, status, notes) VALUES
                                                                                              (1, 1, '2023-10-15 10:00:00', 'Revisión cardíaca anual', 'completed', 'Paciente reporta leve dolor en el pecho'),
                                                                                              (2, 3, '2023-10-16 09:30:00', 'Control rutinario', 'completed', 'Sin problemas aparentes'),
                                                                                              (3, 6, '2023-10-16 12:00:00', 'Revisión ginecológica', 'completed', 'Paciente con posible quiste ovárico'),
                                                                                              (4, 4, '2023-10-17 16:30:00', 'Dolores de cabeza recurrentes', 'completed', 'Se recomienda realizar una resonancia magnética'),
                                                                                              (5, 2, '2023-10-18 11:15:00', 'Erupción cutánea', 'completed', 'Posible reacción alérgica'),
                                                                                              (6, 5, '2023-10-20 13:45:00', 'Dificultad para ver de lejos', 'completed', 'Se recomienda prescripción de gafas'),
                                                                                              (1, 1, '2023-11-05 10:30:00', 'Seguimiento cardíaco', 'scheduled', ''),
                                                                                              (3, 6, '2023-11-06 11:00:00', 'Resultados de pruebas', 'scheduled', ''),
                                                                                              (2, 3, '2023-10-30 09:00:00', 'Fiebre alta', 'cancelled', 'Paciente canceló por mejoría'),
                                                                                              (4, 4, '2023-11-10 17:30:00', 'Revisión de resonancia', 'scheduled', '');

-- Insertar registros médicos
INSERT INTO medical_records (patient_id, appointment_id, diagnosis, treatment, notes, created_by) VALUES
                                                                                                      (1, 1, 'Hipertensión leve', 'Se recomienda dieta baja en sal y ejercicio moderado', 'Paciente necesita seguimiento cada 3 meses', 1),
                                                                                                      (2, 2, 'Estado saludable', 'No se requiere tratamiento', 'Próxima revisión en un año', 3),
                                                                                                      (3, 3, 'Quiste ovárico funcional', 'Anticonceptivos orales durante 3 meses', 'Programar ecografía de control en 3 meses', 6),
                                                                                                      (4, 4, 'Migraña crónica', 'Sumatriptán 50mg en caso de crisis, Topiramato 25mg diario', 'Evitar desencadenantes como estrés y cafeína', 4),
                                                                                                      (5, 5, 'Dermatitis por contacto', 'Crema de hidrocortisona 1% dos veces al día', 'Evitar jabones perfumados y detergentes fuertes', 2),
                                                                                                      (6, 6, 'Miopía moderada', 'Prescripción de gafas: -2.50 OD, -2.25 OS', 'Revisión anual recomendada', 5);

-- Insertar prescripciones
INSERT INTO prescriptions (medical_record_id, medication_name, dosage, frequency, duration, notes, prescribed_by) VALUES
                                                                                                                      (1, 'Enalapril', '5mg', 'Una vez al día', '3 meses', 'Tomar por la mañana con el desayuno', 1),
                                                                                                                      (3, 'Diane 35', '1 comprimido', 'Una vez al día', '3 meses', 'Iniciar el primer día de menstruación', 6),
                                                                                                                      (4, 'Sumatriptán', '50mg', 'En caso de crisis', 'Según necesidad', 'No exceder de 300mg al día', 4),
                                                                                                                      (4, 'Topiramato', '25mg', 'Una vez al día', '2 meses', 'Tomar por la noche antes de acostarse', 4),
                                                                                                                      (5, 'Hidrocortisona crema 1%', 'Aplicación tópica', 'Dos veces al día', '2 semanas', 'Aplicar una capa fina en las zonas afectadas', 2);

-- Insertar facturación
INSERT INTO billing (appointment_id, patient_id, amount, payment_status, payment_date, payment_method, insurance_coverage) VALUES
                                                                                                                               (1, 1, 120.00, 'paid', '2023-10-15 11:30:00', 'tarjeta', 90.00),
                                                                                                                               (2, 2, 80.00, 'paid', '2023-10-16 10:45:00', 'efectivo', 0.00),
                                                                                                                               (3, 3, 150.00, 'paid', '2023-10-16 13:15:00', 'tarjeta', 120.00),
                                                                                                                               (4, 4, 200.00, 'paid', '2023-10-17 18:00:00', 'transferencia', 160.00),
                                                                                                                               (5, 5, 100.00, 'paid', '2023-10-18 12:30:00', 'tarjeta', 80.00),
                                                                                                                               (6, 6, 130.00, 'paid', '2023-10-20 15:00:00', 'efectivo', 65.00),
                                                                                                                               (7, 1, 120.00, 'pending', NULL, NULL, 90.00),
                                                                                                                               (8, 3, 150.00, 'pending', NULL, NULL, 120.00);