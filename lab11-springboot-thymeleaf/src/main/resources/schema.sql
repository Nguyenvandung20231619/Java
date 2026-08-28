-- Bảng Sinh Viên
CREATE TABLE IF NOT EXISTS students (
    student_code VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    class_name VARCHAR(50) NOT NULL
);

-- Bảng Khóa Học
CREATE TABLE IF NOT EXISTS courses (
    course_code VARCHAR(20) PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL,
    credits INT NOT NULL
);

-- Dữ liệu mẫu Sinh Viên
INSERT INTO students (student_code, full_name, email, class_name) VALUES
('20231619', 'Nguyễn Văn Dũng', 'dung@eaut.edu.vn', 'DCCNTT13.10.1'),
('SV002', 'Trần Thị Bình', 'binh@eaut.edu.vn', 'DCCNTT13.10.2'),
('SV003', 'Lê Văn Cường', 'cuong@eaut.edu.vn', 'DCCNTT13.10.3')
ON DUPLICATE KEY UPDATE full_name=VALUES(full_name);

-- Dữ liệu mẫu Khóa Học
INSERT INTO courses (course_code, course_name, credits) VALUES
('JAVA01', 'Lập trình Java Cơ bản', 3),
('JAVA02', 'Công nghệ Java & Spring Boot', 4),
('DB01', 'Cơ sở dữ liệu & SQL', 3)
ON DUPLICATE KEY UPDATE course_name=VALUES(course_name);