INSERT INTO user (username, first_name, last_name, email, role) VALUES
  ('serhiilytka', 'Serhii', 'Lytka', 'serhii@gmail.com', 'ADMIN'),
  ('marypublic', 'Mary', 'Public', 'mary@gmail.com', 'CASHIER'),
  ('johndou', 'John', 'Dou', 'john@gmail.com', 'USER');


INSERT INTO ticket (title, description, created_at) VALUES
  ('title1', 'description for title1', '2019-09-11 12:00:00'),
  ('title2', 'description for title2', '2019-09-11 12:30:00'),
  ('title3', 'description for title3', '2019-09-11 13:00:00'),
  ('title4', 'description for title4', '2019-09-11 13:30:00');

INSERT INTO ticket_user (user_id, ticket_id) VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (3, 4);