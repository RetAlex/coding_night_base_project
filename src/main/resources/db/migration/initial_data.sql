INSERT INTO e_users (user_id, username, email, first_name, last_name, role) VALUES (1, 'user', 'user@mail.com', 'Taras', 'FromSheva', 'STUDENT');
INSERT INTO e_users (user_id, username, email, first_name, last_name, role) VALUES (2, 'teacher', 'skovoroda@mail.com', 'Skovoroda', 'Mohyla', 'TEACHER');
INSERT INTO e_rooms (room_id, building, name, user_id) VALUES (1, '1', '404', 2);
INSERT INTO e_tech (tech_id, name, user_id) VALUES (1, 'molotok', 1);
INSERT INTO e_requests (request_id, creation_timestamp, event_name, expected_amount_of_involved, expected_result,
                        finish_date_time, is_security_needed, prep_finish_date_time, prep_start_date_time, purpose,
                        start_date_time, target_audience, mentor_id, prep_room_id, room_id, student_id, current_status)
VALUES (1, NOW(), 'KMA DAY', 100, 'naukma 4ever', NOW(), FALSE, NULL , NULL, 'naukma is great', NOW(), 'you', 2, NULL, 1, 1, 'NEW');
INSERT INTO e_team_members(team_id, phone_number, responsibility, specialisation, request_id, user_id)
VALUES (1, '380981332537', 'organisator', 'informatics', 1, 1);
SELECT * FROM e_users;
SELECT * FROM e_rooms;
SELECT * FROM e_requests;
SELECT * FROM e_team_members;