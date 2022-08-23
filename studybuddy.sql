-- -------------------------------------------------------------
-- TablePlus 4.8.2(436)
--
-- https://tableplus.com/
--
-- Database: studybuddy
-- Generation Time: 2022-08-23 21:23:45.3580
-- -------------------------------------------------------------


DROP TABLE IF EXISTS "public"."agenda_item";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS agenda_item_id_seq;

-- Table Definition
CREATE TABLE "public"."agenda_item" (
    "id" int8 NOT NULL DEFAULT nextval('agenda_item_id_seq'::regclass),
    "created_at" timestamp,
    "description" text,
    "link" varchar(255),
    "moment" timestamp,
    "title" varchar(255),
    "updated_at" timestamp,
    "user_id" int8,
    "homework_id" int8,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."agenda_items_subscribers";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS agenda_items_subscribers_id_seq;

-- Table Definition
CREATE TABLE "public"."agenda_items_subscribers" (
    "id" int8 NOT NULL DEFAULT nextval('agenda_items_subscribers_id_seq'::regclass),
    "created_at" timestamp,
    "agenda_item_id" int8,
    "user_id" int8,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."avatar";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."avatar" (
    "id" int8 NOT NULL,
    "content" oid,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."homework";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS homework_id_seq;

-- Table Definition
CREATE TABLE "public"."homework" (
    "id" int8 NOT NULL DEFAULT nextval('homework_id_seq'::regclass),
    "created_at" timestamp,
    "deadline" timestamp,
    "description" text,
    "link" varchar(255),
    "name" varchar(255),
    "updated_at" timestamp,
    "subject_id" int8,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."roles";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS roles_id_seq;

-- Table Definition
CREATE TABLE "public"."roles" (
    "id" int8 NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
    "display_name" varchar(60),
    "name" varchar(60),
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."subjects";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS subjects_id_seq;

-- Table Definition
CREATE TABLE "public"."subjects" (
    "id" int8 NOT NULL DEFAULT nextval('subjects_id_seq'::regclass),
    "created_at" timestamp,
    "name" varchar(255),
    "updated_at" timestamp,
    PRIMARY KEY ("id")
);

DROP TABLE IF EXISTS "public"."users";
-- This script only contains the table creation statements and does not fully represent the table in the database. It's still missing: indices, triggers. Do not use it as a backup.

-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS users_id_seq;

-- Table Definition
CREATE TABLE "public"."users" (
    "id" int8 NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    "avatar" text,
    "created_at" timestamp,
    "email" varchar(255) NOT NULL,
    "name" varchar(255),
    "password" varchar(255),
    "updated_at" timestamp,
    "username" varchar(255) NOT NULL,
    "role_id" int8,
    PRIMARY KEY ("id")
);

INSERT INTO "public"."agenda_item" ("id", "created_at", "description", "link", "moment", "title", "updated_at", "user_id", "homework_id") VALUES
(1, '2022-08-23 21:04:21.595', 'Wie doet er mee?', '', '2023-02-01 12:00:00', 'Recept pagina van het huiswerk maken', '2022-08-23 21:04:21.595', 2, 1),
(2, '2022-08-23 21:16:02.723', 'Wie doet er mee?', '', '2022-08-24 10:00:00', 'Pagina mooi maken', '2022-08-23 21:16:02.723', 2, 2),
(3, '2022-08-23 21:16:35.019', 'Wie doet er mee?', '', '2022-08-24 15:30:00', 'Huiswerk afmaken', '2022-08-23 21:16:35.019', 2, 3),
(4, '2022-08-23 21:17:23.54', 'Ik wil graag met minimaal 2 klasgenoten aan de huiswerk opdracht zitten, wie doet er mee?', '', '2023-01-26 12:00:00', 'Huiswerk opdracht maken', '2022-08-23 21:17:23.54', 2, 4),
(5, '2022-08-23 21:19:30.765', 'Ik wil graag een java tutorial doorlopen, wie doet er mee?', 'https://www.youtube.com/watch?v=3OrEsC-QjUA', '2023-01-12 12:00:00', 'Youtube tutorial', '2022-08-23 21:19:30.765', 2, NULL);

INSERT INTO "public"."homework" ("id", "created_at", "deadline", "description", "link", "name", "updated_at", "subject_id") VALUES
(1, '2022-08-23 20:58:46.445', '2023-02-23 00:00:00', 'Maak de webpagina van het opgegeven huiswerk', 'http://example.com', 'Recept pagina', '2022-08-23 20:58:46.445', 1),
(2, '2022-08-23 20:59:27.216', '2023-02-23 00:00:00', 'Maak de pagina mooi naar aanleiding van het opgegeven huiswerk.', 'http://example.com', 'Pagina stylen', '2022-08-23 20:59:27.216', 2),
(3, '2022-08-23 21:00:11.235', '2022-08-23 22:00:00', 'Maak de huiswerk webpagina met javascript.', 'http://example.com', 'Webpagina', '2022-08-23 21:00:11.235', 3),
(4, '2022-08-23 21:01:32.002', '2022-08-24 00:00:00', 'Maak de huiswerk opdracht', '', 'Java opdracht', '2022-08-23 21:01:32.002', 4),
(5, '2022-08-23 21:02:09.188', '2023-02-23 20:56:00', 'Loop de tutorial door van het huiswerk.', 'http://example.com', 'Tutorial', '2022-08-23 21:02:09.188', 5);

INSERT INTO "public"."roles" ("id", "display_name", "name") VALUES
(1, 'Teacher', 'TEACHER'),
(2, 'Student', 'STUDENT');

INSERT INTO "public"."subjects" ("id", "created_at", "name", "updated_at") VALUES
(1, '2022-08-23 20:57:49.303', 'HTML', '2022-08-23 20:57:49.303'),
(2, '2022-08-23 20:57:52.913', 'CSS', '2022-08-23 20:57:52.913'),
(3, '2022-08-23 20:57:56.979', 'Javascript', '2022-08-23 20:57:56.979'),
(4, '2022-08-23 20:58:00.199', 'Java', '2022-08-23 20:58:00.199'),
(5, '2022-08-23 20:58:07.256', 'Spring Boot', '2022-08-23 20:58:07.256');

INSERT INTO "public"."users" ("id", "avatar", "created_at", "email", "name", "password", "updated_at", "username", "role_id") VALUES
(1, '4d1a77dd-0fe8-43bc-a954-a6624b2902ed_red.jpg', '2022-08-23 20:56:52.84', 'teacher@studybuddy.nl', 'Teacher', '$2a$10$rX7FnUUn9l2snrFGee/yvuACekGIcuZEj9rzi4RexCulJ2gpP9GBW', '2022-08-23 21:22:49.065', 'Teacher', 1),
(2, '2d0bc4c2-d90e-45a0-9139-2da160e5c55f_Foto op 08-06-2022 om 16.00.jpg', '2022-08-23 20:57:18.286', 'student@studdybuddy.nl', 'Student', '$2a$10$f03kPJA3hXwulORu16Zoe.jwXh3JuPETg7Ca0dGATCdz2A4ZrznKW', '2022-08-23 21:22:28.694', 'Student', 2);

ALTER TABLE "public"."agenda_item" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id");
ALTER TABLE "public"."agenda_item" ADD FOREIGN KEY ("homework_id") REFERENCES "public"."homework"("id");
ALTER TABLE "public"."agenda_items_subscribers" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users"("id");
ALTER TABLE "public"."agenda_items_subscribers" ADD FOREIGN KEY ("agenda_item_id") REFERENCES "public"."agenda_item"("id");
ALTER TABLE "public"."homework" ADD FOREIGN KEY ("subject_id") REFERENCES "public"."subjects"("id");
ALTER TABLE "public"."users" ADD FOREIGN KEY ("role_id") REFERENCES "public"."roles"("id");
