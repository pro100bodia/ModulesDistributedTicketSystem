create database ticketmaster charset=utf8;
use ticketmaster;

create table ticket (id bigint auto_increment, created_at TIMESTAMP, description varchar(255), title varchar(255), primary key (id));
create table ticket_user (ticket_id bigint not null, user_id bigint not null, primary key (ticket_id, user_id));
create table user (id bigint auto_increment, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), role varchar(255), username varchar(255), primary key (id));
alter table ticket_user add constraint FKnmy1mk4o43f7y6a3lodg7n7o9 foreign key (user_id) references user(id);
alter table ticket_user add constraint FK15wwc2ukb5w7qs8mw6hvxi8ep foreign key (ticket_id) references ticket(id);

INSERT INTO user (username, password, first_name, last_name, email, role) VALUES
  ('serhiilytka', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'Serhii', 'Lytka', 'serhii@gmail.com', 'ADMIN'),
  ('marypublic', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'Mary', 'Public', 'mary@gmail.com', 'CASHIER'),
  ('johndou', '$2a$04$8km1JN9lPh4C3jn.lsAZiuIq.8xK4QZAWrzSZpZo6TpV7c4gi5Fyu', 'John', 'Dou', 'john@gmail.com', 'USER');


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